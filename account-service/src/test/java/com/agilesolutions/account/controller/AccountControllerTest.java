package com.agilesolutions.account.controller;

import com.agilesolutions.account.domain.Account;
import com.agilesolutions.account.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

class AccountControllerTest {

    private WebTestClient webTestClient;

    private AccountService accountService;


    @BeforeEach
    void setUp() {
        accountService = org.mockito.Mockito.mock(AccountService.class);
        webTestClient = WebTestClient.bindToController(new AccountController(accountService)).build();
        webTestClient
                .mutate()
                .responseTimeout(Duration.ofSeconds(30))
                .build();
    }

    /**
     * Tests that the endpoint returns a list of accounts as JSON when account exist.
     * Verifies the HTTP status is 200 (OK) and the response contains the expected data.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    @DisplayName("Returns list of accounts as JSON")
    void getAllAccountsReturnsListOfShares() throws Exception {

        when(accountService.findAllAccounts(List.of(1L, 2L))).thenReturn(Flux.just(
                Account.builder().id("1").clientId(1L).maturityDate(LocalDate.now()).openingDayBalance(1.2F).amount(100).description("Personal Account").lineOfBusiness("Retail").number("AAPL").build(),
                Account.builder().id("2").clientId(2L).maturityDate(LocalDate.now()).openingDayBalance(2.5F).amount(200).description("Business Account").lineOfBusiness("Corporate").number("AAPL").build()
        ));

        webTestClient.post().uri("/api/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(List.of(1L, 2L))
                         .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].id").isEqualTo("1")
                .jsonPath("$[0].clientId").isEqualTo(1)
                .jsonPath("$[0].description").isEqualTo("Personal Account")
                .jsonPath("$[1].id").isEqualTo("2")
                .jsonPath("$[1].clientId").isEqualTo(2)
                .jsonPath("$[1].description").isEqualTo("Business Account");
    }


    /**
     * Tests that the endpoint returns an empty list as JSON when no accounts exist.
     * Verifies the HTTP status is 200 (OK) and the response is an empty array.
     *
     * @throws Exception if the MockMvc request fails
     */
    @Test
    @DisplayName("Returns empty list when no accounts exist")
    void getAllAccountsReturnsEmptyList() throws Exception {
        when(accountService.findAllAccounts(List.of(99L))).thenReturn(Flux.just());

        webTestClient.post().uri("/api/accounts")
                .bodyValue(List.of(99L))
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
    void getAllAccountsServiceThrowsException() throws Exception {
        when(accountService.findAllAccounts(List.of(99L))).thenThrow(new RuntimeException("Service error"));

        webTestClient.post().uri("/api/accounts")
                .bodyValue(List.of(99L))
                         .exchange()
                .expectStatus().is5xxServerError();


    }
}