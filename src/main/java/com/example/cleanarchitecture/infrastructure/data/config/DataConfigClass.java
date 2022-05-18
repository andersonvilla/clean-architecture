package com.example.cleanarchitecture.infrastructure.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.cleanarchitecture.infrastructure.data.repository.client"
)
@EnableMongoRepositories(
        basePackages = "com.example.cleanarchitecture.infrastructure.data.repository.image"
)
@ConfigurationProperties("spring.datasource")
@EnableJpaAuditing
@EntityScan(basePackages = "com.example.cleanarchitecture.infrastructure.data.entity")
public class DataConfigClass {
}
