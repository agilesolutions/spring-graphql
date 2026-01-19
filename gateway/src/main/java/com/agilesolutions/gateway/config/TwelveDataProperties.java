package com.agilesolutions.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twelvedata.api")
@Data
public class TwelveDataProperties {

    private String key;

    private String url;
}