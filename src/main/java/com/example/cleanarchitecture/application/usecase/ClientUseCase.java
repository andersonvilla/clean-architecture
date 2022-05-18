package com.example.cleanarchitecture.application.usecase;

import com.example.cleanarchitecture.application.mapper.ApplicationMapper;
import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.domain.service.ClientService;
import com.example.cleanarchitecture.application.dto.ClientDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClientUseCase {

    private final ClientService clientService;
    static ApplicationMapper mapper = ApplicationMapper.singleInstance();

    public List<Client> getClients(){
        return clientService.getClients();
    }
    public ClientDTO createClient(ClientDTO client){
        return mapper.toDto(clientService.createClient(mapper.dtoToDomain(client)));
    }
    public void deleteClient(Long id){
        clientService.deleteClient(id);
    }
    public Client update(Long id, ClientDTO client){
        return clientService.updateClient(id,mapper.dtoToDomain(client));
    }
    public ClientDTO getClientById(Long id){
        return mapper.toDto(clientService.getClientById(id).get());
    }

}
