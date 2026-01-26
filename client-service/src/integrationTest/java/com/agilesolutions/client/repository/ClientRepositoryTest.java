package com.agilesolutions.client.repository;

import com.agilesolutions.client.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

class ClientRepositoryTest extends BasePGIntegrationTest {

    @Autowired
    private ClientRepository repository;


    @Test
    void findByCompany() {

        Mono<Client> result =
                repository.deleteAll()
                        .then(repository.save(new Client(null, "John", "A.", "Doe")))
                        .flatMap(saved -> repository.findById(saved.getId()));


    }

}