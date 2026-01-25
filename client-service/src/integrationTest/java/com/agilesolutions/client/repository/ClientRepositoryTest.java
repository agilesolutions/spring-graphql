package com.agilesolutions.client.repository;

import com.agilesolutions.client.entity.Client;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

class ClientRepositoryTest extends BasePGIntegrationTest {

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {

        clientRepository.deleteAll();

    }

    @Test
    void findByCompany() {

        Mono<Client> save = clientRepository.save(new Client(null, "John", "A.", "Doe"));

        clientRepository.findById(1L).subscribe(c -> Assert.assertTrue(c.getFirstName().contains("John")));

    }

}