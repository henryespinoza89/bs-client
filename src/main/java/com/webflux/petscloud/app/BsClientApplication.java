package com.webflux.petscloud.app;

import com.webflux.petscloud.app.models.documents.Categoria;
import com.webflux.petscloud.app.models.documents.Producto;
import com.webflux.petscloud.app.models.services.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
public class BsClientApplication implements CommandLineRunner {

	@Autowired private ProductoService productoService;
	@Autowired private ReactiveMongoTemplate mongoTemplate;
	private static final Logger log = LoggerFactory.getLogger(BsClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BsClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("productos").subscribe();
		mongoTemplate.dropCollection("categorias").subscribe();

		Categoria electronico = new Categoria(String.valueOf(UUID.randomUUID()), "Electrónico");
		Categoria deporte = new Categoria(String.valueOf(UUID.randomUUID()), "Deporte");
		Categoria computacion = new Categoria(String.valueOf(UUID.randomUUID()), "Computación");
		Categoria muebles = new Categoria(String.valueOf(UUID.randomUUID()), "Muebles");

		Flux.just(electronico, deporte, computacion, muebles)
			.flatMap(cat -> productoService.saveCategoria(cat))
			.doOnNext(c -> log.info("Categoría creada: {}, Id: {}", c.getNombre(), c.getId()))
			.thenMany(
				Flux.just(new Producto(String.valueOf(UUID.randomUUID()),"TV Panasonic Pantalla LCD", 456.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "Sony Camara HD Digital", 177.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "Apple iPod", 46.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "Sony Notebook", 846.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "Hewlett Packard Multifuncional", 200.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "Bianchi Bicicleta", 70.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "HP Notebook Omen 17", 2500.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "Mica Cómoda 5 Cajones", 150.89, Instant.now().toString(), electronico, ""),
					new Producto(String.valueOf(UUID.randomUUID()), "TV Sony Bravia OLED 4K Ultra HD", 2255.89, Instant.now().toString(), electronico, "")
				).flatMap(producto -> {
					return productoService.save(producto);
				})
			)
				.subscribe(producto -> log.info("Insert: {} {}", producto.getProductId(), producto.getNombre()));
	}
}
