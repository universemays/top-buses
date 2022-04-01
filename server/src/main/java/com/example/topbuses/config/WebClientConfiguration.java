package com.example.topbuses.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfiguration {

    @Bean
    public WebClient client(@Value("${api.url:http://localhost:3000/}") String url) {
        ExchangeStrategies strategies = ExchangeStrategies
            .builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(10 * 1024 * 1024))
            .build();

        return WebClient
            .builder() //
            .baseUrl(url)
            .exchangeStrategies(strategies)
            .build();
    }
}
