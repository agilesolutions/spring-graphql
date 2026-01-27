package com.agilesolutions.client.controller;


import com.agilesolutions.client.domain.Client;
import com.agilesolutions.client.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.mockito.Mockito.when;

class ClientControllerTest {

    private WebTestClient webTestClient;

    private ClientService clientService;


    @BeforeEach
    void setUp() {
        clientService = org.mockito.Mockito.mock(ClientService.class);
        webTestClient = WebTestClient.bindToController(new ClientController(clientService)).build();
        webTestClient
                .mutate()
                .responseTimeout(Duration.ofSeconds(30))
                .build();
    }

    /**
     * Tests that the endpoint returns a list of client as JSON when shares exist.
     * Verifies the HTTP status is 200 (OK) and the response contains the expected data.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    @DisplayName("Returns list of client as JSON")
    void getAllClients() throws Exception {
        when(clientService.findAllClients()).thenReturn(
                Flux.just(Client.builder().firstName("John").lastName("Doe").id(1L).middleName("").build(),
                Client.builder().firstName("Jane").lastName("Smith").id(2L).middleName("").build()));

        webTestClient.get().uri("/api/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].firstName").isEqualTo("John")
                .jsonPath("$[0].lastName").isEqualTo("Doe")
                .jsonPath("$[1].id").isEqualTo(2)
                .jsonPath("$[1].firstName").isEqualTo("Jane")
                .jsonPath("$[1].lastName").isEqualTo("Smith");

    }


    /**
     * Tests that the endpoint returns an empty list as JSON when no client exist.
     * Verifies the HTTP status is 200 (OK) and the response is an empty array.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    @DisplayName("Returns empty list when no client exist")
    void getAllClientsReturnsEmptyList() throws Exception {
        when(clientService.findAllClients()).thenReturn(Flux.just());

        webTestClient.get().uri("/api/clients")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .json("[]");

    }

    /**
     * Tests that the endpoint handles a service exception by returning a 500 (Internal Server Error) status.
     * Verifies the HTTP status is 500 when the service layer throws an exception.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    @DisplayName("Handles service exception with 500 error")
    void getAllClientsServiceThrowsException() throws Exception {
        when(clientService.findAllClients()).thenThrow(new RuntimeException("Service error"));

        webTestClient.get().uri("/api/clients")
                .exchange()
                .expectStatus().is5xxServerError();


    }
}