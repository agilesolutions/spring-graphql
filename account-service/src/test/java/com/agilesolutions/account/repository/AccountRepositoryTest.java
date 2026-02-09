package com.agilesolutions.account.repository;

import com.agilesolutions.account.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.List;

@DataMongoTest
@ContextConfiguration(classes = {AccountRepository.class})
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest extends BaseMongoDBIntegrationTest {


    @Autowired
    private AccountRepository repository;

    @BeforeEach
    void setUp() {

        repository.saveAll(List.of(
                Account.builder().id("1").clientId(1L).maturityDate(LocalDate.now()).openingDayBalance(1.2F).amount(100).description("Personal Account").lineOfBusiness("Retail").number("AAPL").build(),
                Account.builder().id("2").clientId(2L).maturityDate(LocalDate.now()).openingDayBalance(2.5F).amount(200).description("Business Account").lineOfBusiness("Corporate").number("AAPL").build(),
                Account.builder().id("3").clientId(3L).maturityDate(LocalDate.now()).openingDayBalance(3.0F).amount(300).description("Savings Account").lineOfBusiness("Retail").number("AAPL").build(),
                Account.builder().id("4").clientId(4L).maturityDate(LocalDate.now()).openingDayBalance(4.5F).amount(400).description("Investment Account").lineOfBusiness("Corporate").number("AAPL").build(),
                Account.builder().id("5").clientId(5L).maturityDate(LocalDate.now()).openingDayBalance(5.0F).amount(500).description("Joint Account").lineOfBusiness("Retail").number("AAPL").build()
        ));
    }

    @Test
    void findItemByCompany() {

        Account account = repository.findAccountByNumber("AAPL");
        assert account != null;
        assert "AAPL".equals(account.getNumber());

    }



}