package com.example.cleanarchitecture.domain.service;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.domain.exceptions.EmptyDataException;
import com.example.cleanarchitecture.domain.exceptions.ErrorConstant;
import com.example.cleanarchitecture.domain.exceptions.NoDataFoundException;
import com.example.cleanarchitecture.domain.exceptions.ResourceNotFoundException;
import com.example.cleanarchitecture.domain.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    public Optional<Client> getClientById(Long id){
        if (!clientRepository.getClientById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        return clientRepository.getClientById(id);
    }
    public List<Client> getClients(){
        if(clientRepository.getClients().isEmpty()){
            throw new NoDataFoundException(ErrorConstant.NOTDATA);
        }
        return clientRepository.getClients();
    }
    public void deleteClient(Long id){
        if(!clientRepository.getClientById(id).isPresent()){
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);
        }
        clientRepository.deleteClient(id);
    }
    public Client createClient(Client client){
        if(client.getFirstName().isEmpty() || client.getLastName().isEmpty() || client.getCity().isEmpty() || client.getTypeOfId().isEmpty() || client.getAge() == 0 || client.getId() == null){
            throw new EmptyDataException(ErrorConstant.EMPTYDATA);
        }
        return clientRepository.createClient(client);

    }
    public Client updateClient(Long id, Client client){
        if(client.getFirstName().isEmpty() || client.getLastName().isEmpty() || client.getCity().isEmpty() || client.getTypeOfId().isEmpty() || client.getAge() == 0 || client.getId() == null){
            throw new EmptyDataException(ErrorConstant.EMPTYDATA);
        } else if (id.longValue()!=client.getId().longValue()){
            throw new EmptyDataException("Los id no coinciden!!!");
        }
        Client currentClient = clientRepository.getClientById(id).get();
        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setCity(client.getCity());
        currentClient.setTypeOfId(client.getTypeOfId());
        currentClient.setAge(client.getAge());
        return clientRepository.update(id, client);
    }

}
