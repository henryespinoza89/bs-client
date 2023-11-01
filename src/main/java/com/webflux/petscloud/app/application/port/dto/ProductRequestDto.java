package com.webflux.petscloud.app.application.port.dto;

import com.webflux.petscloud.app.infraestructure.adapter.output.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
  private String name;
  private Double price;
  private String createAt;
  private Category category;
  private String photo;
}
