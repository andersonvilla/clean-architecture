package com.example.cleanarchitecture.domain.service;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.domain.exceptions.EmptyDataException;
import com.example.cleanarchitecture.domain.exceptions.ResourceNotFoundException;
import com.example.cleanarchitecture.domain.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;
    @InjectMocks
    private ClientService clientService;
    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client(1L, "Anderson", "Villa", "Bello", "CC", 24);
    }

    @Test
    void getClientById() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.of(client));
        assertNotNull(clientService.getClientById(client.getId()));
    }

    @Test
    void getClients() {
        when(clientRepository.getClients()).thenReturn(Collections.singletonList(client));
        assertNotNull(clientService.getClients());
        assertEquals(clientService.getClients(), clientRepository.getClients());
    }

    @Test
    void deleteClient() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.of(client));
        clientService.deleteClient(client.getId());
        verify(clientRepository, times(1)).deleteClient(client.getId());
    }

    @Test
    void createClient() {
        when(clientRepository.createClient(any(Client.class))).thenReturn(client);
        assertEquals("Anderson", clientService.createClient(client).getFirstName());
        Client clientCreated = clientService.createClient(client);
        assertTrue(clientCreated.getCity().equalsIgnoreCase(client.getCity()));
        assertNotNull(clientService.createClient(client));
    }

    @Test
    void updateClient() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.of(client));
        Client clientToUpdate = client;
        clientToUpdate.setFirstName("Estiven");
        clientToUpdate.setLastName("Sierra");
        when(clientRepository.update(client.getId(), clientToUpdate)).thenReturn(client);
        assertEquals("Estiven", clientService.updateClient(client.getId(), clientToUpdate).getFirstName());
        assertEquals("Sierra", clientService.updateClient(client.getId(), clientToUpdate).getLastName());
    }

    @Test
    void deleteException() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.deleteClient(client.getId());
        });
        assertEquals("No se encuentra el cliente con el siguiente ID : -> " + client.getId(), resourceNotFoundException.getMessage());
    }

    @Test
    void createException() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.empty());
        Client clientNoData = client;
        clientNoData.setFirstName("");
        EmptyDataException exception = assertThrows(EmptyDataException.class, () -> {
            clientService.createClient(clientNoData);
        });
        assertEquals("No llenaste todos los campos", exception.getMessage());
    }

    @Test
    void updateException() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.empty());
        Client clientNoData = client;
        clientNoData.setAge(0);
        EmptyDataException exception = assertThrows(EmptyDataException.class, () -> {
            clientService.updateClient(client.getId(), clientNoData);
        });
        assertEquals("No llenaste todos los campos", exception.getMessage());

    }

    @Test
    void differentIdException() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.of(client));
        EmptyDataException differentId = assertThrows(EmptyDataException.class, () -> {
            clientService.updateClient(3L, client);
        });
        assertEquals("Los id no coinciden!!!", differentId.getMessage());
    }

    @Test
    void getClientByIdException() {
        when(clientRepository.getClientById(client.getId())).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> {
            clientService.getClientById(client.getId());
        });
        assertEquals("No se encuentra el cliente con el siguiente ID : -> " + client.getId(), resourceNotFoundException.getMessage());
    }

}