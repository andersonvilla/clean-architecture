package com.example.cleanarchitecture.application.usecase;

import com.example.cleanarchitecture.application.mapper.ApplicationImageMapper;
import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.domain.service.ImageService;
import com.example.cleanarchitecture.application.dto.ImageDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ImageUseCase {

    private final ImageService imageService;
    static ApplicationImageMapper mapper = ApplicationImageMapper.singleInstance();

    public List<Image> getImages(){
        return imageService.getImages();
    }
    public ImageDTO createImage(ImageDTO image){
        return mapper.toDto(imageService.createImage(mapper.dtoToDomain(image)));
    }
    public void deleteImage(Long id){
        imageService.deleteImage(id);
    }
    public Image updateImage(Long id, ImageDTO image){
        return imageService.updateImage(id, mapper.dtoToDomain(image));
    }
    public ImageDTO getImagenById(Long id){
        return mapper.toDto(imageService.getImageById(id).get());
    }

}
