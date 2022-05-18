package com.example.cleanarchitecture.application.usecase;

import com.example.cleanarchitecture.application.dto.ClientDTO;
import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.domain.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientUseCaseTest {
    @Mock
    private ClientService clientService;
    @InjectMocks
    private ClientUseCase clientUseCase;
    private ClientDTO clientDTO;
    private Client client;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new Client(1L, "Anderson", "Villa", "Bello" , "CC", 24 );
        clientDTO = new ClientDTO(1L, "Anderson", "Villa", "Bello", "CC", 24, "prueba.png");
    }
    @Test
    void getClients() {
        when(clientService.getClients()).thenReturn(Arrays.asList(client));
        assertNotNull(clientUseCase.getClients());
        assertEquals(clientUseCase.getClients(), clientService.getClients());
    }
    @Test
    void createClient() {
        when(clientService.createClient(any(Client.class))).thenReturn(client);
        assertEquals("Anderson", clientUseCase.createClient(clientDTO).getFirstName());
        ClientDTO clientCreated = clientUseCase.createClient(clientDTO);
        assertTrue(clientCreated.getCity().equalsIgnoreCase(clientDTO.getCity()));
        assertNotNull(clientUseCase.createClient(clientDTO));
    }
    @Test
    void deleteClient() {
        when(clientService.getClientById(client.getId())).thenReturn(Optional.of(client));
        clientUseCase.deleteClient(client.getId());
        verify(clientService, times(1)).deleteClient(client.getId());
    }
    @Test
    void getClientById() {
        when(clientService.getClientById(client.getId())).thenReturn(Optional.of(client));
        assertNotNull(clientUseCase.getClientById(client.getId()));
    }
}