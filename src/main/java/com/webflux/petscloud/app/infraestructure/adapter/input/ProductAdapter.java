package com.webflux.petscloud.app.infraestructure.adapter.input;

import com.webflux.petscloud.app.application.port.dto.ProductRequestDto;
import com.webflux.petscloud.app.application.port.input.ProductPort;
import com.webflux.petscloud.app.application.port.mapper.ProductMapper;
import com.webflux.petscloud.app.domain.service.ProductService;
import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProductAdapter implements ProductPort {
  @Autowired
  private final ProductService productService;
  @Autowired
  private final ProductMapper productMapper;
  @Override
  public Mono<ResponseEntity<Product>> createProduct(ProductRequestDto product) {
    return Mono.justOrEmpty(
        Optional.ofNullable(product.getCreateAt())
        .map(createAt -> product)
        .orElseGet(() -> {
          product.setCreateAt(String.valueOf(Instant.now()));
          return product;
      }))
      .map(productMapper::toDomainProduct)
      .flatMap(productService::createProduct)
      .map(prod -> ResponseEntity
        .created(URI.create("/api/product/v2/" + prod.getProductId()))
        .contentType(MediaType.APPLICATION_JSON)
        .body(prod));
  }

  @Override
  public Mono<ResponseEntity<Flux<Product>>> findAll() {
    return Mono.just(
      ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(productService.findAll())
    );
  }

  @Override
  public Mono<ResponseEntity<Product>> findById(String id) {
    return productService.findById(id).map(product -> ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(product))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Product>> editProduct(Product product, String id) {
    return productService.findById(id).flatMap(prod -> {
      prod.setName(product.getName());
      prod.setPrice(product.getPrice());
      prod.setCategory(product.getCategory());
      return productService.createProduct(prod);
    }).map(p -> ResponseEntity.created(URI.create("/api/product/v2/" + p.getProductId()))
        .contentType(MediaType.APPLICATION_JSON)
        .body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Void>> deleteProduct(String id) {
    return productService.findById(id).flatMap(prod -> {
      return productService.deleteProduct(prod).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }
}
