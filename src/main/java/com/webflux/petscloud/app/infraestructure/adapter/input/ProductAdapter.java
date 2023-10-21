package com.webflux.petscloud.app.infraestructure.adapter.input;

import com.webflux.petscloud.app.application.port.input.ProductPort;
import com.webflux.petscloud.app.domain.service.ProductService;
import com.webflux.petscloud.app.models.documents.Producto;
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
public class ProductAdapter implements ProductPort {

  @Autowired private ProductService productService;

  @Override
  public Mono<ResponseEntity<Producto>> crear(Producto producto) {
    return Mono.justOrEmpty(Optional.ofNullable(producto.getCreateAt())
        .map(createAt -> producto)
        .orElseGet(() -> {
          producto.setCreateAt(String.valueOf(Instant.now()));
          return producto;
      }))
      .flatMap(productService::save)
      .map(prod -> ResponseEntity
        .created(URI.create("/api/productos/v2/" + prod.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .body(prod));
  }

  @Override
  public Mono<ResponseEntity<Flux<Producto>>> lista() {
    return Mono.just(
      ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(productService.findAll())
    );
  }

  @Override
  public Mono<ResponseEntity<Producto>> ver(String id) {
    return productService.findById(id).map(producto -> ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(producto))
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Producto>> editar(Producto producto, String id) {
    return productService.findById(id).flatMap(prod -> {
      prod.setNombre(producto.getNombre());
      prod.setPrecio(producto.getPrecio());
      prod.setCategoria(producto.getCategoria());
      return productService.save(prod);
    }).map(p -> ResponseEntity.created(URI.create("/api/productos/v2/" + p.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @Override
  public Mono<ResponseEntity<Void>> eliminar(String id) {
    return productService.findById(id).flatMap(prod -> {
      return productService.delete(prod).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }
}
