package com.webflux.petscloud.app.controllers;

import com.webflux.petscloud.app.models.documents.Producto;
import com.webflux.petscloud.app.models.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Instant;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

  @Autowired private ProductoService productoService;

  @GetMapping
  public Mono<ResponseEntity<Flux<Producto>>> lista() {
    return Mono.just(
      ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(productoService.findAll())
    ); // Aquí usamos una clase ResponseEntity para armar la respuesta
    // return productoService.findAll(); // Respuesta simplificada.
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<Producto>> ver(@PathVariable String id){
    return productoService.findById(id).map(p -> ResponseEntity.ok()
          .contentType(MediaType.APPLICATION_JSON)
          .body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<Producto>> crear(@RequestBody Producto producto) {
    if (producto.getCreateAt() == null) {
      producto.setCreateAt(Instant.now());
    }
    return productoService.save(producto)
      .map(prod -> ResponseEntity
        .created(URI.create("/api/productos/" + prod.getId()))
        .contentType(MediaType.APPLICATION_JSON)
        .body(prod));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<Producto>> editar(@RequestBody Producto producto, @PathVariable String id){
    return productoService.findById(id).flatMap(p -> {
        p.setNombre(producto.getNombre());
        p.setPrecio(producto.getPrecio());
        p.setCategoria(producto.getCategoria());
        return productoService.save(p);
      }).map(p->ResponseEntity.created(URI.create("/api/productos/" + p.getId()))
          .contentType(MediaType.APPLICATION_JSON)
          .body(p))
      .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<Void>> eliminar(@PathVariable String id){
    return productoService.findById(id).flatMap(p -> {
      return productoService.delete(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
  }
}
