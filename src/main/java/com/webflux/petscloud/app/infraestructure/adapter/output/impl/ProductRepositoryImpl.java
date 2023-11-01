package com.webflux.petscloud.app.infraestructure.adapter.output.impl;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.webflux.petscloud.app.infraestructure.adapter.output.ProductRepository;
import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

  @Autowired
  private DynamoDBMapper dynamoDBMapper;

  @Autowired
  private AmazonDynamoDB amazonDynamoDB;
  @Override
  public Mono<Product> createProduct(Product product) {
    dynamoDBMapper.save(product);
    return Mono.just(product);
  }
  @Override
  public Flux<Product> findAll() {
    ScanRequest scanRequest = new ScanRequest().withTableName("product");
    ScanResult scanResult = amazonDynamoDB.scan(scanRequest);
    List<Product> products = dynamoDBMapper.marshallIntoObjects(Product.class, scanResult.getItems());
    return Flux.fromIterable(products);
  }
  @Override
  public Mono<Product> findById(String id) {
    var valueByAttributeName = Map.of(":productId", new AttributeValue(id));
    var queryExpression =
      new DynamoDBQueryExpression<Product>()
        //.withIndexName("productId-index")
        .withKeyConditionExpression("productId = :productId")
        .withExpressionAttributeValues(valueByAttributeName)
        .withConsistentRead(false);
    var result = dynamoDBMapper.query(Product.class, queryExpression).stream().findFirst();
    return Mono.fromSupplier(() -> result.orElse(null));
  }
  @Override
  public Mono<Void> deleteProduct(Product product) {
    dynamoDBMapper.delete(product);
    return Mono.fromRunnable(() -> {
      // Operación de eliminación realizada
    });
  }
}
