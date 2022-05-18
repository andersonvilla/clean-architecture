package com.example.cleanarchitecture.infrastructure.data.repository.image;

import com.example.cleanarchitecture.infrastructure.data.entity.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageDataRepository extends MongoRepository<ImageEntity, Long> {
}
