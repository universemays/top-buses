package com.example.topbuses.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import java.time.Duration;
import javax.net.ssl.SSLException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Component
public class WebClientConfiguration {

    private static final int MAX_IN_MEMORY_SIZE_IN_MB = 10 * 1024 * 1024;

    @Bean
    WebClient client(@Value("${api.url:http://localhost:3000/}") String url) throws SSLException {
        SslContext context = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();

        HttpClient httpClient = HttpClient
            .create()
            .secure(spec -> spec.sslContext(context))
            .compress(true)
            .responseTimeout(Duration.ofSeconds(5));

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);

        ExchangeStrategies strategies = ExchangeStrategies
            .builder()
            .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE_IN_MB))
            .build();

        return WebClient
            .builder() //
            .clientConnector(connector)
            .baseUrl(url)
            .exchangeStrategies(strategies)
            .build();
    }
}
