package com.agilesolutions.gateway.rest;

import com.agilesolutions.gateway.domain.Account;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AccountHttpClient {

    @PostExchange("/api/accounts")
    Flux<Account> getAllAccounts(@RequestBody List<Long> clientIds);
}
