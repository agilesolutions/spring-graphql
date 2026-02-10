package com.agilesolutions.client.repository;

import com.agilesolutions.client.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Testcontainers
class ClientRepositoryTest extends BasePGIntegrationTest {

    @Autowired
    private ClientRepository repository;


    @Test
    void findByCompany() {

        Mono<Client> result =
                repository.deleteAll()
                        .then(repository.save(new Client(null, "John", "A.", "Doe")))
                        .flatMap(saved -> repository.findById(saved.getId()));

        StepVerifier.create(result)
                .expectNextMatches(client ->
                        client.getFirstName().equals("John") &&
                        client.getMiddleName().equals("A.") &&
                        client.getLastName().equals("Doe"))
                .verifyComplete();


    }

}