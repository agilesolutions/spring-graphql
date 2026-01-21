package com.agilesolutions.client.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "authorization-server")
@Data
public class AuthorizationServerProperties {

    private String url;

}