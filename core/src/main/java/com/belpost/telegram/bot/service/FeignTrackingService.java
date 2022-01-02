package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "tracking-service", url = "${client.belpost.base-url}")
public interface FeignTrackingService {

    @RequestMapping(value = "/api/v1/tracking", method = RequestMethod.POST)
    PostTrackingResponse getTrackInfo(PostTrackingRequest postTrackingRequest);
}
