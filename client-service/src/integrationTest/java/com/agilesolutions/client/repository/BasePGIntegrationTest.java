package com.agilesolutions.client.repository;

import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataR2dbcTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class BasePGIntegrationTest {

    @ServiceConnection
    protected static final PostgreSQLContainer<?> dbContainer = new PostgreSQLContainer<>("postgres:16-alpine");

}