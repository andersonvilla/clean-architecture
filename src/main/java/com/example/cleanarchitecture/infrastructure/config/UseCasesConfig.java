package com.example.cleanarchitecture.infrastructure.config;

import com.example.cleanarchitecture.domain.Client;
import com.example.cleanarchitecture.domain.repository.ClientRepository;
import com.example.cleanarchitecture.domain.repository.ImageRepository;
import com.example.cleanarchitecture.application.usecase.ClientUseCase;
import com.example.cleanarchitecture.application.usecase.ImageUseCase;
import com.example.cleanarchitecture.domain.service.ClientService;
import com.example.cleanarchitecture.domain.service.ImageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

        @Bean
        public ClientUseCase useCase( ClientService clientService){
                return new ClientUseCase( clientService);
        }
        @Bean
        public ClientService clientService(ClientRepository clientRepository){
                return new ClientService(clientRepository);
        }
        @Bean
        public ImageService imageService(ImageRepository imageRepository){
                return new ImageService(imageRepository);
        }
        @Bean
        public ImageUseCase imageUseCase( ImageService imageService){
                return new ImageUseCase( imageService);
        }
}
