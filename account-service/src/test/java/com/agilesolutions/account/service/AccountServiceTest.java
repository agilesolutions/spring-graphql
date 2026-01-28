package com.agilesolutions.account.service;

import com.agilesolutions.account.domain.Account;
import com.agilesolutions.account.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    /**
     * Initializes mocks before each test execution.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests that findAllAccounts returns a Flux of Account objects when accounts exist for the given client IDs.
     */
    @Test
    @DisplayName("findAllAccounts returns Flux of Accounts when accounts exist")
    void findAllAccountsWhenAccountsExists() {

        // Mock the repository to return sample Account objects.
        when(accountRepository.findByClientIdIn(List.of(1L, 2L))).thenReturn(List.of(
                com.agilesolutions.account.entity.Account.builder()
                        .id("1")
                        .clientId(1L)
                        .number("ACC123")
                        .description("Personal Account")
                        .lineOfBusiness("Retail")
                        .amount(1000)
                        .openingDayBalance(500.0f)
                        .maturityDate(LocalDate.of(2025, 12, 31))
                        .build(),
                com.agilesolutions.account.entity.Account.builder()
                        .id("2")
                        .clientId(2L)
                        .number("ACC456")
                        .description("Business Account")
                        .lineOfBusiness("Corporate")
                        .amount(5000)
                        .openingDayBalance(2000.0f)
                        .maturityDate(LocalDate.of(2026, 6, 30))
                        .build()
        ));

        Flux<Account> result = accountService.findAllAccounts(List.of(1L, 2L));

        StepVerifier.create(result)
                .expectNextMatches(account -> account.id().equals("1") && account.clientId().equals(1L))
                .expectNextMatches(account -> account.id().equals("2") && account.clientId().equals(2L))
                .verifyComplete();

    }

    /**
     * Tests that findAllAccounts returns an empty Flux when no accounts exist for the given client IDs.
     */
    @Test
    @DisplayName("findAllAccounts returns empty Flux when no accounts exist")
    void findAllAccountsWhenNoAccountsExists() {

        // Mock the repository to return an empty list.
        when(accountRepository.findByClientIdIn(List.of(3L, 4L))).thenReturn(List.of());

        Flux<Account> result = accountService.findAllAccounts(List.of(3L, 4L));

        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();
    }

}