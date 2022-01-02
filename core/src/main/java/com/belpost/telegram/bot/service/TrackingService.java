package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;

public interface TrackingService {

    PostTrackingResponse getTrackInfo(PostTrackingRequest postTrackingRequest);
}
