package com.webflux.petscloud.app.infraestructure.adapter.output.impl;

import com.webflux.petscloud.app.infraestructure.adapter.config.PetsCloudRepository;
import com.webflux.petscloud.app.infraestructure.adapter.output.ProductoRepository;
import com.webflux.petscloud.app.models.documents.Producto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Repository
public class ProductoRepositoryImpl implements ProductoRepository {
  private final PetsCloudRepository<Producto> petsCloudRepository;
  @Override
  public Mono<Producto> create(Producto product) {
    return petsCloudRepository.save(product);
  }
  @Override
  public Flux<Producto> lista() {
    return petsCloudRepository.findAll();
  }
  @Override
  public Mono<Producto> ver(String id) {
    return petsCloudRepository.findById(id);
  }
  @Override
  public Mono<Void> eliminar(Producto product) {
    return petsCloudRepository.delete(product);
  }
}
