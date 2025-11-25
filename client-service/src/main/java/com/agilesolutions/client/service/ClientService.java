package com.agilesolutions.client.service;

import com.agilesolutions.client.domain.Client;
import com.agilesolutions.client.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Flux<Client> findAllClients() {
        return clientRepository.findAll().map(client -> Client.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .lastName(client.getLastName())
                .build());
    }

    public Mono<Client> save(Client client) {
        return clientRepository.save(com.agilesolutions.client.entity.Client.builder().build())
                .map(savedClient -> Client.builder()
                        .id(savedClient.getId())
                        .firstName(savedClient.getFirstName())
                        .middleName(savedClient.getMiddleName())
                        .lastName(savedClient.getLastName())
                        .build());
    }

}
