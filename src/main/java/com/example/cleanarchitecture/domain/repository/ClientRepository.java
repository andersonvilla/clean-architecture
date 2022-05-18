package com.example.cleanarchitecture.domain.repository;

import com.example.cleanarchitecture.domain.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {
    List<Client> getClients();
    Client createClient(Client client);
    void deleteClient(Long id);
    Client update(Long id, Client client);
    Optional<Client> getClientById(Long id);
}
