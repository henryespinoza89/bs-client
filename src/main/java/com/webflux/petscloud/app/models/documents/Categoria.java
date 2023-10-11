package com.webflux.petscloud.app.models.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
@Data
@AllArgsConstructor
@Document(collection = "categorias")
public class Categoria {
	@Id
	@NotEmpty
	private String id;
	private String nombre;
}
