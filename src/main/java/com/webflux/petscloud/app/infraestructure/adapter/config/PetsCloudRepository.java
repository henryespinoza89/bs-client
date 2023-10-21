package com.webflux.petscloud.app.infraestructure.adapter.config;

import com.webflux.petscloud.app.models.documents.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PetsCloudRepository<T> extends ReactiveMongoRepository<Producto, String> { }
