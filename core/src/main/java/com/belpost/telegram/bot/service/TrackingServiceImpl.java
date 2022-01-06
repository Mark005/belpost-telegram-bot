package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;
import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TrackingServiceImpl implements TrackingService {

    private final WebClient postClient;

    @Override
    public Mono<PostTrackingResponse> getTrackInfo(PostTrackingRequest postTrackingRequest) {

        return postClient.post()
                .uri("/api/v1/tracking")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(postTrackingRequest), PostTrackingRequest.class)
                .retrieve()
                .bodyToMono(PostTrackingResponse.class);
    }
}
