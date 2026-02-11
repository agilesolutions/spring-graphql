package com.agilesolutions.client.repository;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;

@TestConfiguration(proxyBeanMethods = false)
//@EnableR2dbcRepositories
class TestcontainersConfiguration {

    /**
    @Bean
    @ServiceConnection
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer(DockerImageName.parse("postgres:latest"));
    }
        */


    //@Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }

}