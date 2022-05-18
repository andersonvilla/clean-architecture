package com.example.cleanarchitecture.infrastructure.controller;

import com.example.cleanarchitecture.application.usecase.ImageUseCase;
import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.application.usecase.ClientUseCase;
import com.example.cleanarchitecture.application.dto.ClientDTO;
import com.example.cleanarchitecture.application.dto.ImageDTO;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"cliente"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClientController {

    private final ClientUseCase clientUseCase;
    private final ImageUseCase imageUseCase;

    @PostMapping()
    ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO client){
        ImageDTO imageDTO = new ImageDTO(client.getId(), client.getPhoto());
        imageUseCase.createImage(imageDTO);
       return new ResponseEntity<>((clientUseCase.createClient(client)),
               HttpStatus.CREATED);
    }
    @GetMapping()
    ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok(clientUseCase.getClients());
    }
    @GetMapping("/{clientId}")
    ResponseEntity<ClientDTO> getClientById(@PathVariable("clientId") Long id){
        ClientDTO clientDTO = clientUseCase.getClientById(id);
        clientDTO.setPhoto(imageUseCase.getImagenById(id).getPhoto());
        return new ResponseEntity<>(clientDTO, HttpStatus.OK);
    }
    @DeleteMapping("/{clientId}")
    ResponseEntity<Void> deleteClient(@PathVariable("clientId") Long id){
        imageUseCase.deleteImage(id);
        clientUseCase.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{clientId}")
    ResponseEntity<ClientDTO> updateClient(@PathVariable("clientId") Long id, @RequestBody ClientDTO client ){
        ImageDTO imageDTO = new ImageDTO(client.getId(), client.getPhoto());
        clientUseCase.update(id,client);
        imageUseCase.updateImage(id, imageDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
