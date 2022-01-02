package com.belpost.telegram.bot.config;

import com.belpost.telegram.bot.properties.BelpostClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class PostConfig {

    @Bean
    public WebClient postClient(BelpostClientProperties properties) {
//        HttpClient httpClient = HttpClient.create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//                .responseTimeout(Duration.ofMillis(5000))
//                .doOnConnected(conn ->
//                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
//                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
//
//        WebClient client = WebClient.builder()
//                .baseUrl("http://localhost:52200")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .clientConnector(new ReactorClientHttpConnector(httpClient))
//                .build();

        return WebClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                        System.out.println("");
                        return Mono.just(clientRequest);
                    }));
                    exchangeFilterFunctions.add(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                        System.out.println("");
                        return Mono.just(clientResponse);
                    }));
                })
                .build();
    }
}
