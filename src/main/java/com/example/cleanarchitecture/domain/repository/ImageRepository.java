package com.example.cleanarchitecture.domain.repository;

import com.example.cleanarchitecture.domain.Image;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {

    List<Image> getImages();
    Image createImage(Image image);
    void deleteImage(Long id);
    Image updateImage(Long id, Image image);
    Optional<Image> getImageById(Long id);

}
