package com.example.cleanarchitecture.infrastructure.data.repository.image;

import com.example.cleanarchitecture.domain.repository.ImageRepository;
import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.infrastructure.data.entity.ImageEntity;
import com.example.cleanarchitecture.infrastructure.data.helpers.ImageMapper;
import com.example.cleanarchitecture.domain.exceptions.EmptyDataException;
import com.example.cleanarchitecture.domain.exceptions.ErrorConstant;
import com.example.cleanarchitecture.domain.exceptions.NoDataFoundException;
import com.example.cleanarchitecture.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImplImageDataRepository implements ImageRepository {

    private final ImageDataRepository imageDataRepository;
    static ImageMapper mapper = ImageMapper.singleInstance();
    @Override
    public Image createImage(Image image) {
        return mapper.toDomain(imageDataRepository.save(mapper.toEntity(image)));
    }
    @Override
    public List<Image> getImages() {
        return mapper.toList(imageDataRepository.findAll());
    }
    @Override
    public void deleteImage(Long id) {
        if (!imageDataRepository.findById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        imageDataRepository.deleteById(id);
    }
    @Override
    public Image updateImage(Long id, Image image) {
        return mapper.toDomain(imageDataRepository.save(mapper.toEntity(image)));
    }
    @Override
    public Optional<Image> getImageById(Long id) {
        ImageEntity image = imageDataRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);});
        Optional<Image> optionalImage = Optional.of(mapper.toDomain(image));
        return optionalImage;
    }
}
