package com.webflux.petscloud.app.domain.service.impl;

import com.webflux.petscloud.app.domain.service.ProductService;
import com.webflux.petscloud.app.infraestructure.adapter.output.ProductRepository;
import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired private ProductRepository productRepository;
  @Override
  public Mono<Product> createProduct(Product product) {
    return productRepository.createProduct(product);
  }
  @Override
  public Flux<Product> findAll() {
    return productRepository.findAll();
  }
  @Override
  public Mono<Product> findById(String id) {
    return productRepository.findById(id);
  }
  @Override
  public Mono<Void> deleteProduct(Product product) {
    return productRepository.deleteProduct(product);
  }
}
