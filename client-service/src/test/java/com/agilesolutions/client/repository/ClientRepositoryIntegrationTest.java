package com.agilesolutions.client.repository;

import com.agilesolutions.client.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataR2dbcTest
@Slf4j
// Ensure Flyway is auto-configured even in a slice test
@ImportAutoConfiguration(FlywayAutoConfiguration.class)
class ClientRepositoryIntegrationTest {

    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    R2dbcEntityTemplate template;

    @Autowired
    private ClientRepository repository;

    @Test
    void findByCompany() {

        this.template.insert(new Client(6L, "John", "A.", "Doe"))
                .flatMap(client ->
                        repository.findById(client.getId())
                )
                .log()
                .as(StepVerifier::create)
                .consumeNextWith(client -> {
                            log.info("saved client: {}", client);
                            assertThat(client.getFirstName()).isEqualTo("John");
                            assertThat(client.getMiddleName()).isEqualTo("A.");
                            assertThat(client.getLastName()).isEqualTo("Doe");
                        }
                )
                .verifyComplete();

    }

    @Test
    void findAll() {
        this.template.insert(new Client(7L, "Jane", "B.", "Smith"))
                .thenMany(repository.findAll())
                .log()
                .as(StepVerifier::create)
                .expectNextCount(1) // Expect at least one client (the one we just inserted)
                .consumeNextWith(client -> {
                    log.info("found client: {}", client);
                    assertThat(client.getFirstName()).isEqualTo("Alice");
                    assertThat(client.getMiddleName()).isEqualTo("C.");
                    assertThat(client.getLastName()).isEqualTo("Johnson");
                })
                .verifyComplete();
    }

}