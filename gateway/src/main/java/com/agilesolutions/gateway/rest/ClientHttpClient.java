package com.agilesolutions.gateway.rest;

import com.agilesolutions.gateway.domain.Client;
import io.micrometer.observation.annotation.Observed;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface ClientHttpClient {

    @GetExchange("/api/clients")
    @Observed(name = "get-all-clients",
            contextualName = "Fetch All Clients",
            lowCardinalityKeyValues = {"ClientHttpClient", "getAllClients"})
    public Flux<Client> getAllClients();

}

