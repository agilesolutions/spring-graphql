package com.agilesolutions.account.init;

import com.agilesolutions.account.entity.Account;
import com.agilesolutions.account.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class MongoDBInitializer implements CommandLineRunner {

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public void run(String... args) {


        accountRepository.saveAll(List.of(
                Account.builder().id("1").clientId(1L).maturityDate(LocalDateTime.now()).openingDayBalance(1.2F).amount(100).description("Personal Account").lineOfBusiness("Retail").number("AAPL").build(),
                Account.builder().id("2").clientId(2L).maturityDate(LocalDateTime.now()).openingDayBalance(2.5F).amount(200).description("Business Account").lineOfBusiness("Corporate").number("AAPL").build(),
                Account.builder().id("3").clientId(3L).maturityDate(LocalDateTime.now()).openingDayBalance(3.0F).amount(300).description("Savings Account").lineOfBusiness("Retail").number("AAPL").build(),
                Account.builder().id("4").clientId(4L).maturityDate(LocalDateTime.now()).openingDayBalance(4.5F).amount(400).description("Investment Account").lineOfBusiness("Corporate").number("AAPL").build(),
                Account.builder().id("5").clientId(5L).maturityDate(LocalDateTime.now()).openingDayBalance(5.0F).amount(500).description("Joint Account").lineOfBusiness("Retail").number("AAPL").build()
        ));

        log.info("MongoDB records saved successfully-------");

    }
}
