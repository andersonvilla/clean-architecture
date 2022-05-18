package com.example.cleanarchitecture.infrastructure.data.helpers;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.infrastructure.data.entity.ClientEntity;
import com.google.common.reflect.TypeToken;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;

public class ClientMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    private static ClientMapper instance;

    private ClientMapper(){}

    public static ClientMapper singleInstance(){
        if(instance==null){
            instance = new ClientMapper();
        }
        return instance;
    }

    public Client toDomain(ClientEntity client){
        return modelMapper.map(client, Client.class);
    }
    public ClientEntity toEntity(Client client){
        return modelMapper.map(client, ClientEntity.class);
    }
    public List<Client> toList(List<ClientEntity> clientEntityList){
        Type listType = new TypeToken<List<Client>>(){}.getType();

        return modelMapper.map(clientEntityList, listType);
    }


}
