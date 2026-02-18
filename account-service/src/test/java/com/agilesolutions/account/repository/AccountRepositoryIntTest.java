package com.agilesolutions.account.repository;

import com.agilesolutions.account.entity.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.util.List;

@DataMongoTest
@EnableMongoRepositories(basePackages = "com.agilesolutions.account.repository")
class AccountRepositoryIntTest extends BaseMongoDBIntegrationTest {


    @Autowired
    private AccountRepository repository;

    @BeforeEach
    void setUp() {

        repository.saveAll(List.of(
                Account.builder().id("1").clientId(1L).maturityDate(LocalDateTime.now()).openingDayBalance(1.2F).amount(100).description("Personal Account").lineOfBusiness("Retail").number("PA").build(),
                Account.builder().id("2").clientId(2L).maturityDate(LocalDateTime.now()).openingDayBalance(2.5F).amount(200).description("Business Account").lineOfBusiness("Corporate").number("BA").build(),
                Account.builder().id("3").clientId(3L).maturityDate(LocalDateTime.now()).openingDayBalance(3.0F).amount(300).description("Savings Account").lineOfBusiness("Retail").number("SA").build(),
                Account.builder().id("4").clientId(4L).maturityDate(LocalDateTime.now()).openingDayBalance(4.5F).amount(400).description("Investment Account").lineOfBusiness("Corporate").number("IA").build(),
                Account.builder().id("5").clientId(5L).maturityDate(LocalDateTime.now()).openingDayBalance(5.0F).amount(500).description("Joint Account").lineOfBusiness("Retail").number("JA").build()
        ));
    }

    @Test
    void findItemByCompany() {

        List<Account> account = repository.findAccountByNumber("BA");
        Assertions.assertNotNull(account);
        Assertions.assertEquals(1, account.size());
        Assertions.assertEquals("BA", account.get(0).getNumber());

    }

    @Test
    void findByClientIdIn() {

        List<Account> accounts = repository.findByClientIdIn(List.of(1L, 3L, 5L));
        Assertions.assertNotNull(accounts);
        Assertions.assertEquals(3, accounts.size());
        Assertions.assertTrue(accounts.stream().anyMatch(account -> account.getClientId() == 1L));
        Assertions.assertTrue(accounts.stream().anyMatch(account -> account.getClientId() == 3L));
        Assertions.assertTrue(accounts.stream().anyMatch(account -> account.getClientId() == 5L));

    }



}