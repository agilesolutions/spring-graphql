package com.agilesolutions.client.repository;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

public class BasePGIntegrationTest {

    @Container
    @ServiceConnection // Automatically handles R2DBC URL/User/Pass
    protected static final PostgreSQLContainer<?> dbContainer = new PostgreSQLContainer<>("postgres:16-alpine");

}