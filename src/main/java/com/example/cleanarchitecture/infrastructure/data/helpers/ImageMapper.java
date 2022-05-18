package com.example.cleanarchitecture.infrastructure.data.helpers;

import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.application.dto.ImageDTO;
import com.example.cleanarchitecture.infrastructure.data.entity.ImageEntity;
import com.google.common.reflect.TypeToken;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;

public class ImageMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private static ImageMapper instance;
    private ImageMapper(){}
    public static ImageMapper singleInstance(){
        if (instance==null){
            instance = new ImageMapper();
        }
        return instance;
    }
    public Image toDomain(ImageEntity image){
        return modelMapper.map(image,Image.class);
    }
    public ImageEntity toEntity(Image image){
        return modelMapper.map(image, ImageEntity.class);
    }
    public List<Image> toList(List<ImageEntity> imageEntities){
        Type listType = new TypeToken<List<Image>>(){}.getType();
        return modelMapper.map(imageEntities, listType);
    }
}
