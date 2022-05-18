package com.example.cleanarchitecture.infrastructure.data.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Document(collection = "imagecl")
public class ImageEntity {
    @Id
    private Long id;
    private String photo;

}
