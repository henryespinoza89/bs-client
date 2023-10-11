package com.webflux.petscloud.app.domain.service;

import com.webflux.petscloud.app.models.documents.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
  Mono<Producto> save(Producto producto);
  Flux<Producto> findAll();
  Mono<Producto> findById(String id);
  Mono<Void> delete(Producto producto);
}
