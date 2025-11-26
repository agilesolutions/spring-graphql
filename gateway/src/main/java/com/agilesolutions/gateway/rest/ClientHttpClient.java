package com.agilesolutions.gateway.rest;

import com.agilesolutions.gateway.domain.Client;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface ClientHttpClient {

    @GetExchange("/api/clients")
    public Flux<Client> getAllClients();

}

