package com.agilesolutions.gateway.config;

import com.agilesolutions.gateway.rest.StockHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RestConfig {

    @Bean("stockClient")
    public StockHttpClient stockClient(TwelveDataProperties applicationProperties, WebClient.Builder builder) {
        var wca = WebClientAdapter.create(builder.baseUrl(applicationProperties.getUrl()).build());
        return HttpServiceProxyFactory.builderFor(wca)
                .build()
                .createClient(StockHttpClient.class);
    }

    @Bean
    public RestClient stockRestClient(TwelveDataProperties applicationProperties) {
        return RestClient.builder()
                .baseUrl(applicationProperties.getUrl())
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
