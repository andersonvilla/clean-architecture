package com.example.cleanarchitecture.infrastructure.data.repository.client;

import com.example.cleanarchitecture.infrastructure.data.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDataRepository extends JpaRepository<ClientEntity, Long> {
}
