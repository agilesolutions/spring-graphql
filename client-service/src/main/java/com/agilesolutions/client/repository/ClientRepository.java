package com.agilesolutions.client.repository;

import com.agilesolutions.client.entity.Client;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository extends R2dbcRepository<Client, Long> {

    @Override
    Mono<Client> findById(Long aLong);

    @Override
    Flux<Client> findAll();
}