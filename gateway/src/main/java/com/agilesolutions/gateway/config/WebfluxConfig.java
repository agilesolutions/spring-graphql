package com.agilesolutions.gateway.config;

import com.agilesolutions.gateway.domain.Account;
import com.agilesolutions.gateway.domain.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Flux;

import java.util.List;

@Configuration
public class WebfluxConfig {

    interface ClientHttpClient {
        @GetExchange("/v1/movieinfos")
        Flux<Client> getMovies();
    }

    interface AccountHttpClient {
        @PostExchange("/v1/reviews/list")
        Flux<Account> getReviews(@RequestBody List<Long> clientIds);
    }

    @Bean
    public ClientHttpClient movieInfoHttpProxy(WebClient.Builder builder) {
        var wca = WebClientAdapter.forClient(builder.build());
//        var wca = WebClientAdapter.forClient(builder.baseUrl("http://localhost:8080").build());
        return HttpServiceProxyFactory.builder()
                .clientAdapter(wca)
                .build()
                .createClient(ClientHttpClient.class);
    }



    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder.build();
    }

}
