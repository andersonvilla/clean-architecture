package com.example.cleanarchitecture.infrastructure.data.repository.client;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.infrastructure.data.entity.ClientEntity;
import com.example.cleanarchitecture.infrastructure.data.helpers.ClientMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ImplClientDataRepositoryTest {
    @Mock
    private ClientDataRepository clientDataRepository;
    @Mock
    static ClientMapper mapper = ClientMapper.singleInstance();
    @InjectMocks
    private ImplClientDataRepository implClientDataRepository;
    private ClientEntity client;
    private Client domainClient;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        domainClient = new Client(1L, "Anderson", "Villa", "Bello" , "CC", 24);
        client = new ClientEntity(1L, "Anderson", "Villa", "Bello" , "CC", 24 );
    }
    @Test
    void createClient() {
        when(clientDataRepository.save(any(ClientEntity.class))).thenReturn(client);
        assertEquals("Anderson", implClientDataRepository.createClient(domainClient).getFirstName());
        Client clientCreated = implClientDataRepository.createClient(domainClient);
        assertEquals(clientCreated.getCity(), domainClient.getCity());
        assertNotNull(implClientDataRepository.createClient(domainClient));
    }
    @Test
    void getClients() {
        when(clientDataRepository.findAll())
                .thenReturn(Collections.singletonList(client));
        assertNotNull(implClientDataRepository.getClients());
    }
    @Test
    void deleteClient() {
        when(clientDataRepository.findById(client.getId())).thenReturn(Optional.of(client));
        implClientDataRepository.deleteClient(client.getId());
        verify(clientDataRepository, times(1)).deleteById(client.getId());
    }
    @Test
    void update() {
        when(clientDataRepository.findById(client.getId())).thenReturn(Optional.of(client));
        ClientEntity clientToUpdate = client;
        clientToUpdate.setFirstName("Estiven");
        clientToUpdate.setCity("Medellín");
        when(clientDataRepository.save(any(ClientEntity.class))).thenReturn(client);
        assertEquals("Estiven", implClientDataRepository.update(domainClient.getId(), domainClient).getFirstName());
    }
    @Test
    void getClientById() {
        when(clientDataRepository.findById(client.getId())).thenReturn(Optional.of(client));
        assertNotNull(implClientDataRepository.getClientById(client.getId()));
        assertEquals(implClientDataRepository.getClientById(client.getId()).get().getFirstName(),
                clientDataRepository.findById(client.getId()).get().getFirstName());
    }
}