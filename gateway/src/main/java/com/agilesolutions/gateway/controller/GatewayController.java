package com.agilesolutions.gateway.controller;

import com.agilesolutions.gateway.config.WebfluxConfig;
import com.agilesolutions.gateway.domain.Account;
import com.agilesolutions.gateway.domain.Client;
import com.agilesolutions.gateway.exception.ClientNotFoundException;
import com.agilesolutions.gateway.rest.AccountHttpClient;
import com.agilesolutions.gateway.rest.ClientHttpClient;
import com.agilesolutions.gateway.service.StockService;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class GatewayController {

    private final ClientHttpClient clientHttpClient;
    private final AccountHttpClient accountHttpClient;
    private final StockService stockService;
    private final ObservationRegistry registry;


    @GetMapping("/health")
    public String healthCheck() {
        return "Gateway is up and running!";
    }

    @QueryMapping
    public Flux<Client> clients() {
        log.info("Starting to fetch client infos");
        return clientHttpClient.getAllClients().
                doOnError(throwable -> {
                        log.error("Error fetching clients");
                        throw new ClientNotFoundException("Failed to fetch clients");});

    }

    @BatchMapping(typeName = "Client")
    public Mono<Map<Client, List<Account>>> accounts(List<Client> clients) {
        var clientIds = clients.stream()
                .map(client -> client.id())
                .collect(Collectors.toList());

        var accounts = accountHttpClient.getAllAccounts(clientIds);

        var updateAccounts = accounts.map(account -> {
            log.info("Fetched account: {}", account);
            Float balance = stockService.getLatestStockPrices(account.getNumber()).price();
            account.setOpeningDayBalance(balance);
            return account;
        })
        .onErrorResume(Flux::error);


        return updateAccounts.collectList()
                .map(accountList -> {

                    Map<Long, List<Account>> collect = accountList.stream()
                            .collect(Collectors.groupingBy(Account::getClientId));

                    return clients.stream()
                            .collect(Collectors.toMap(client -> client,
                                    client -> collect.get(client.id())));
                });
    }

}
