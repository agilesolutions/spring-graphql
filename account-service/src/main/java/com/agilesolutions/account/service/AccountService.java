package com.agilesolutions.account.service;

import com.agilesolutions.account.domain.Account;
import com.agilesolutions.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Flux<Account> findAllAccounts() {
        return Flux.fromStream(accountRepository.findAll().stream().map(this::mapToDomain));
    }

    private Account mapToDomain(com.agilesolutions.account.entity.Account entity) {
        return Account.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .description(entity.getDescription())
                .lineOfBusiness(entity.getLineOfBusiness())
                .amount(entity.getAmount())
                .openingDayBalance(entity.getOpeningDayBalance())
                .maturityDate(entity.getMaturityDate())
                .build();
    }

}
