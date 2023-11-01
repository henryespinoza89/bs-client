package com.webflux.petscloud.app.application.port.input;

import com.webflux.petscloud.app.application.port.dto.ProductRequestDto;
import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
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

@RequestMapping("/api/product/v2")
public interface ProductPort {
  @PostMapping
  Mono<ResponseEntity<Product>> createProduct(@RequestBody ProductRequestDto product);

  @GetMapping
  Mono<ResponseEntity<Flux<Product>>> findAll();

  @GetMapping("/{id}")
  Mono<ResponseEntity<Product>> findById(@PathVariable String id);

  @PutMapping("/{id}")
  Mono<ResponseEntity<Product>> editProduct(@RequestBody Product product, @PathVariable String id);

  @DeleteMapping("/{id}")
  Mono<ResponseEntity<Void>> deleteProduct(@PathVariable String id);
}
