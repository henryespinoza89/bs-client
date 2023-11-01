package com.webflux.petscloud.app.application.port.mapper;

import com.webflux.petscloud.app.application.port.dto.ProductRequestDto;
import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
@Component
public interface ProductMapper {
  @Mapping(target = "productId", expression = "java(UUID.randomUUID().toString())")
  Product toDomainProduct(ProductRequestDto requestDto);
}
