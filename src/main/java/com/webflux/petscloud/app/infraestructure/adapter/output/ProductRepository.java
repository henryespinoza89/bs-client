package com.webflux.petscloud.app.infraestructure.adapter.output;

import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {
  Mono<Product> createProduct(Product product);
  Flux<Product> findAll();
  Mono<Product> findById(String id);
  Mono<Void> deleteProduct(Product product);
}
