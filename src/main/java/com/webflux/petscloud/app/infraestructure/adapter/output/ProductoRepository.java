package com.webflux.petscloud.app.infraestructure.adapter.output;

import com.webflux.petscloud.app.models.documents.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductoRepository{
  Mono<Producto> create(Producto product);
  Flux<Producto> lista();
  Mono<Producto> ver(String id);
  Mono<Void> eliminar(Producto product);
}
