package com.agilesolutions.gateway.config;

import com.agilesolutions.gateway.rest.StockHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestConfig {

    @Bean("stockClient")
    public StockHttpClient stockClient(ApplicationProperties applicationProperties) {
        RestClient restClient = RestClient.builder().baseUrl(applicationProperties.getUrl()).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(StockHttpClient.class);
    }
}
