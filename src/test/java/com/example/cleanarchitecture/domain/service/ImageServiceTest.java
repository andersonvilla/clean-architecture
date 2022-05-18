package com.example.cleanarchitecture.domain.service;

import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.domain.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ImageServiceTest {
    @Mock
    private ImageRepository imageRepository;
    @InjectMocks
    private ImageService imageService;
    private Image image;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        image = new Image(1L, "prueba.png");
    }
    @Test
    void getImageById() {
        when(imageRepository.getImageById(image.getId())).thenReturn(Optional.of(image));
        assertNotNull(imageService.getImageById(image.getId()));
    }
    @Test
    void getImages() {
        when(imageRepository.getImages()).thenReturn(Collections.singletonList(image));
        assertNotNull(imageService.getImages());
        assertEquals(imageService.getImages(), imageRepository.getImages());
    }
    @Test
    void deleteImage() {
        when(imageRepository.getImageById(image.getId())).thenReturn(Optional.of(image));
        imageService.deleteImage(image.getId());
        verify(imageRepository, times(1)).deleteImage(image.getId());
    }
    @Test
    void createImage() {
        when(imageRepository.createImage(any(Image.class))).thenReturn(image);
        assertEquals("prueba.png", imageService.createImage(image).getPhoto());
        Image imageCreated = imageService.createImage(image);
        assertEquals(imageCreated.getId(), image.getId());
        assertNotNull(imageService.createImage(image));
    }
    @Test
    void updateImage() {
        when(imageRepository.getImageById(image.getId())).thenReturn(Optional.of(image));
        Image imageToUpdate = image;
        imageToUpdate.setPhoto("prueba_cambio.png");
        when(imageRepository.updateImage(image.getId(), imageToUpdate)).thenReturn(image);
        assertNotNull(imageService.updateImage(image.getId(), imageToUpdate));
        assertEquals("prueba_cambio.png", imageService.updateImage(image.getId(), imageToUpdate).getPhoto());
    }
}