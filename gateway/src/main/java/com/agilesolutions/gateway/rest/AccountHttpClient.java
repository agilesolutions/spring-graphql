package com.agilesolutions.gateway.rest;

import com.agilesolutions.gateway.domain.Account;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AccountHttpClient {

    @PostExchange("/api/accounts")
    @Observed(name = "get-all-accounts",
            contextualName = "",
            lowCardinalityKeyValues = {"AccountHttpClient", "getAllAccounts"})
    Flux<Account> getAllAccounts(@RequestBody List<Long> clientIds);
}
