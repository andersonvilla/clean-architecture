package com.example.cleanarchitecture.domain.service;

import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.domain.exceptions.EmptyDataException;
import com.example.cleanarchitecture.domain.exceptions.ErrorConstant;
import com.example.cleanarchitecture.domain.exceptions.NoDataFoundException;
import com.example.cleanarchitecture.domain.exceptions.ResourceNotFoundException;
import com.example.cleanarchitecture.domain.repository.ImageRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public Optional<Image> getImageById(Long id){
        if(!imageRepository.getImageById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        return imageRepository.getImageById(id);
    }
    public List<Image> getImages(){
        if (imageRepository.getImages().isEmpty()){
            throw new NoDataFoundException(ErrorConstant.NOTDATA);
        }
        return imageRepository.getImages();
    }
    public void deleteImage(Long id){
        if(!imageRepository.getImageById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        imageRepository.deleteImage(id);
    }
    public Image createImage(Image image){
        if (image.getId()==null || image.getPhoto().isEmpty()){
            throw new EmptyDataException(ErrorConstant.EMPTYDATA);
        }
        return imageRepository.createImage(image);
    }
    public Image updateImage(Long id, Image image){
        if (image.getId()==null || image.getPhoto().isEmpty()){
            throw new EmptyDataException(ErrorConstant.EMPTYDATA);
        } else if (id != image.getId()) {
            throw new EmptyDataException("Los id no coinciden!!!");
        }
        Image currentImage = imageRepository.getImageById(id).orElseThrow(()->{
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);});
        currentImage.setPhoto(image.getPhoto());
        return imageRepository.updateImage(id,currentImage);
    }

}
