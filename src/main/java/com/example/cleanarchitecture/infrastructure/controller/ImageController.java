package com.example.cleanarchitecture.infrastructure.controller;

import com.example.cleanarchitecture.application.usecase.ImageUseCase;
import com.example.cleanarchitecture.domain.Image;
import com.example.cleanarchitecture.application.dto.ImageDTO;
import com.example.cleanarchitecture.infrastructure.data.helpers.ImageMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Api(tags = {"imagen"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageUseCase imageUseCase;
    static ImageMapper mapper = ImageMapper.singleInstance();

    @PostMapping()
    ResponseEntity<ImageDTO> createImage(@RequestBody ImageDTO image){
        return new ResponseEntity<>(imageUseCase.createImage(image),
                HttpStatus.CREATED);
    }
    @GetMapping()
    ResponseEntity<List<Image>> getImages(){
        return ResponseEntity.ok(imageUseCase.getImages());
    }
    @GetMapping("/{imageId}")
    ResponseEntity<ImageDTO> getImageById(@PathVariable("imageId") Long id){
        return new ResponseEntity<>((imageUseCase.getImagenById(id)),HttpStatus.OK);
    }
    @DeleteMapping("/{imageId}")
    ResponseEntity<Void> deleteImage(@PathVariable("imageId") Long id){
        imageUseCase.deleteImage(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{imageId}")
    ResponseEntity<ImageDTO> updateImage(@PathVariable("imageId") Long id, @RequestBody ImageDTO image){
        imageUseCase.updateImage(id, image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
