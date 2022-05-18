package com.example.cleanarchitecture.application.mapper;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.application.dto.ClientDTO;
import org.modelmapper.ModelMapper;


public class ApplicationMapper {

    private final ModelMapper modelMapper = new ModelMapper();
    private static ApplicationMapper applicationMapper;

    private ApplicationMapper(){
    }

    public static ApplicationMapper singleInstance(){
        if(applicationMapper==null){
            applicationMapper = new ApplicationMapper();
        }
        return applicationMapper;
    }

    public ClientDTO toDto(Client client){
        return modelMapper.map(client, ClientDTO.class);
    }

    public Client dtoToDomain(ClientDTO clientDTO){
        return modelMapper.map(clientDTO, Client.class);
    }

}

