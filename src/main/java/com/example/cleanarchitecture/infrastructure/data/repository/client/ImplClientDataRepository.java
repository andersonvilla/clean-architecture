package com.example.cleanarchitecture.infrastructure.data.repository.client;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.domain.repository.ClientRepository;
import com.example.cleanarchitecture.infrastructure.data.entity.ClientEntity;
import com.example.cleanarchitecture.infrastructure.data.helpers.ClientMapper;
import com.example.cleanarchitecture.domain.exceptions.ErrorConstant;
import com.example.cleanarchitecture.domain.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImplClientDataRepository implements ClientRepository {

    private final ClientDataRepository clientDataRepository;
    static ClientMapper mapper = ClientMapper.singleInstance();
    @Override
    public Client createClient(Client client) {
        return mapper.toDomain(clientDataRepository.save(mapper.toEntity(client)));
    }
    @Override
    public List<Client> getClients() {
        return mapper.toList(clientDataRepository.findAll());
    }
    @Override
    public void deleteClient(Long id) {
        clientDataRepository.deleteById(id);
    }
    @Override
    public Client update(Long id, Client client) {
        return mapper.toDomain(clientDataRepository.save(mapper.toEntity(client)));}
    @Override
    public Optional<Client> getClientById(Long id) {
        ClientEntity clientEntity = clientDataRepository.findById(id).orElseThrow(()->{
            throw new ResourceNotFoundException(ErrorConstant.NOTFOUND + id);});
        return Optional.of(mapper.toDomain(clientEntity));
    }
}
