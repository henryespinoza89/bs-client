package com.webflux.petscloud.app.application.port.input;

import com.webflux.petscloud.app.models.documents.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/api/productos/v2")
public interface ProductPort {
  @PostMapping
  Mono<ResponseEntity<Producto>> crear(@RequestBody Producto producto);

  @GetMapping
  Mono<ResponseEntity<Flux<Producto>>> lista();

  @GetMapping("/{id}")
  Mono<ResponseEntity<Producto>> ver(@PathVariable String id);

  @PutMapping("/{id}")
  Mono<ResponseEntity<Producto>> editar(@RequestBody Producto producto, @PathVariable String id);

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id);
}
