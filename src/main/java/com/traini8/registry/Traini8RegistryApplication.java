package com.traini8.registry;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@OpenAPIDefinition(info = @Info(title = "Training Center API", version = "0.0.1-SNAPSHOT", description = "API for managing training centers"))
public class Traini8RegistryApplication {

	public static void main(String[] args) {

		SpringApplication.run(Traini8RegistryApplication.class, args);
	}

}
