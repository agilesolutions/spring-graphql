package com.agilesolutions.client.service;

import com.agilesolutions.client.domain.Client;
import com.agilesolutions.client.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    /**
     * Initializes mocks before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllClientsWhenClientsExist() {

        when(clientRepository.findAll()).thenReturn(Flux.just(
                com.agilesolutions.client.entity.Client.builder()
                        .id(1L)
                        .firstName("John")
                        .middleName("A.")
                        .lastName("Doe")
                        .build(),
                com.agilesolutions.client.entity.Client.builder()
                        .id(2L)
                        .firstName("Jane")
                        .middleName("B.")
                        .lastName("Smith")
                        .build()));

        Flux<Client> clientFlux = clientService.findAllClients();

        StepVerifier.create(clientFlux).expectNextMatches(client ->
                client.id().equals(1L) &&
                client.firstName().equals("John") &&
                client.middleName().equals("A.") &&
                client.lastName().equals("Doe"))
            .expectNextMatches(client ->
                client.id().equals(2L) &&
                client.firstName().equals("Jane") &&
                client.middleName().equals("B.") &&
                client.lastName().equals("Smith"))
            .verifyComplete();


    }

    @Test
    void findAllClientsWhenNoClientsExist() {

        when(clientRepository.findAll()).thenReturn(Flux.empty());

        Flux<Client> clientFlux = clientService.findAllClients();

        StepVerifier.create(clientFlux)
                .expectNextCount(0)
                .verifyComplete();
    }


}