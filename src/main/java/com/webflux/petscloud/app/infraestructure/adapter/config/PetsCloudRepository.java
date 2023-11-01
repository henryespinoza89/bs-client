package com.webflux.petscloud.app.infraestructure.adapter.config;

import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PetsCloudRepository<T> extends ReactiveMongoRepository<Product, String> { }
