package com.webflux.petscloud.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class BsClientApplication {
	public static void main(String[] args) {
		SpringApplication.run(BsClientApplication.class, args);
	}
}
