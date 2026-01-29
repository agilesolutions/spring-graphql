package com.agilesolutions.gateway.controller;

import com.agilesolutions.gateway.domain.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Flux;

import static org.mockito.BDDMockito.*;

@GraphQlTest(GatewayController.class)
class GatewayControllerTest {

    @Autowired
    GraphQlTester graphQlTester;

    @MockitoBean
    private com.agilesolutions.gateway.rest.ClientHttpClient clientHttpClient;

    @MockitoBean
    private com.agilesolutions.gateway.rest.AccountHttpClient accountHttpClient;

    @MockitoBean
    private com.agilesolutions.gateway.service.StockService stockService;

    @MockitoBean
    private io.micrometer.observation.ObservationRegistry observationRegistry;

    @Test
    void testClientsQuery() {

        given(clientHttpClient.getAllClients())
                .willReturn(Flux.just(
                        new Client(1L, "John", "A.", "Doe"),
                        new Client(2L, "Jane", "B.", "Smith")));

        String query = """
                query {
                    clients {
                        id
                        firstName
                        middleName
                        lastName
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("clients")
                .entityList(Client.class) // Replace Object.class with the actual Client class if available
                .hasSizeGreaterThan(0)
                .containsExactly(
                        new Client(1L, "John", "A.", "Doe"),
                        new Client(2L, "Jane", "B.", "Smith")
                );

    }

    @Test
    void testClientsQueryNoClients() {

        given(clientHttpClient.getAllClients())
                .willReturn(Flux.empty());

        String query = """
                query {
                    clients {
                        id
                        firstName
                        middleName
                        lastName
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("clients")
                .entityList(Client.class) // Replace Object.class with the actual Client class if available
                .hasSize(0);

    }

    @Test
    void testClientsQueryError() {

        given(clientHttpClient.getAllClients())
                .willReturn(Flux.error(new RuntimeException("Service unavailable")));

        String query = """
                query {
                    clients {
                        id
                        firstName
                        middleName
                        lastName
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .errors()
                .satisfy(errors -> {
                    assert errors.size() == 1;
                    assert errors.get(0).getMessage().contains("INTERNAL_ERROR");§
                });

    }


}