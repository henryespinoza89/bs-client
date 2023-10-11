package com.webflux.petscloud.app.models.documents;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
@Data
@AllArgsConstructor
@Document(collection="productos")
public class Producto {
	@Id
	private String id;
	@NotNull
	private String nombre;
	@NotNull
	private Double precio;
	private Instant createAt;
	@Valid
	private Categoria categoria;
	private String foto;
}
