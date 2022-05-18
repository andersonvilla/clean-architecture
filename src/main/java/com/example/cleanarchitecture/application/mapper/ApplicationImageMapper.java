package com.example.cleanarchitecture.application.mapper;

import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.application.dto.ImageDTO;
import org.modelmapper.ModelMapper;

public class ApplicationImageMapper {

    private final ModelMapper modelMapper = new ModelMapper();
    private static ApplicationImageMapper instance;
    private ApplicationImageMapper(){
    }
    public static ApplicationImageMapper singleInstance(){
        if (instance == null){
            instance = new ApplicationImageMapper();
        }
        return instance;
    }

    public ImageDTO toDto(Image image){
        return modelMapper.map(image, ImageDTO.class);
    }
    public Image dtoToDomain(ImageDTO image){
        return modelMapper.map(image,Image.class);
    }

}
