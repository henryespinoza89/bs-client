package com.webflux.petscloud.app.domain.service.impl;

import com.webflux.petscloud.app.domain.service.ProductService;
import com.webflux.petscloud.app.infraestructure.adapter.output.ProductoRepository;
import com.webflux.petscloud.app.models.documents.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired private ProductoRepository productoRepository;
  @Override
  public Mono<Producto> save(Producto producto) {
    return productoRepository.create(producto);
  }
  @Override
  public Flux<Producto> findAll() {
    return productoRepository.lista();
  }
  @Override
  public Mono<Producto> findById(String id) {
    return productoRepository.ver(id);
  }
  @Override
  public Mono<Void> delete(Producto producto) {
    return productoRepository.eliminar(producto);
  }
}
