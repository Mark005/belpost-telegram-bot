package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.TrackingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrackingInfoService {
    Page<TrackingInfo> findAll(Pageable pageable);

    TrackingInfo updateByTrackingNumber(TrackingInfo newTrackingInfo);
}
