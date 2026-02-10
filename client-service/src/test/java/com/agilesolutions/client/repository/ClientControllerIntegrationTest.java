package com.agilesolutions.client.repository;

import com.agilesolutions.client.entity.Client;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@DataR2dbcTest
@Testcontainers
@Slf4j
//@Import(value = {TestcontainersConfiguration.class})
class ClientControllerIntegrationTest {

    @Autowired
    R2dbcEntityTemplate template;

    @Autowired
    private ClientRepository repository;


    @Test
    void findByCompany() {

        this.template.insert(new Client(null, "John", "A.", "Doe"))
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

}