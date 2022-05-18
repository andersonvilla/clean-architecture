package com.example.cleanarchitecture.infrastructure.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.example.cleanarchitecture.infrastructure")
@EntityScan(basePackages = "com.example.cleanarchitecture.domain")
public class CleanarchitectureApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanarchitectureApplication.class, args);
	}

}
