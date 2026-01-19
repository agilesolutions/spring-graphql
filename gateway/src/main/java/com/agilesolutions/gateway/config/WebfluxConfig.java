package com.agilesolutions.gateway.config;

import com.agilesolutions.gateway.rest.AccountHttpClient;
import com.agilesolutions.gateway.rest.ClientHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebfluxConfig {

    @Bean
    public ClientHttpClient clientHttpProxy(WebClient client, ServiceProperties serviceProperties) {
        var wca = WebClientAdapter.create(client.mutate().baseUrl(serviceProperties.getClientServiceUrl()).build());
        return HttpServiceProxyFactory.builderFor(wca)
                .build()
                .createClient(ClientHttpClient.class);
    }

    @Bean
    public AccountHttpClient accountHttpProxy(WebClient client, ServiceProperties serviceProperties) {
        var wca = WebClientAdapter.create(client.mutate().baseUrl(serviceProperties.getAccountServiceUrl()).build());
        return HttpServiceProxyFactory.builderFor(wca)
                .build()
                .createClient(AccountHttpClient.class);
    }

}
