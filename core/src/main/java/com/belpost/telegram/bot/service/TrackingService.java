package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;
import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import reactor.core.publisher.Mono;

public interface TrackingService {

    Mono<PostTrackingResponse> getTrackInfo(PostTrackingRequest postTrackingRequest);
}
