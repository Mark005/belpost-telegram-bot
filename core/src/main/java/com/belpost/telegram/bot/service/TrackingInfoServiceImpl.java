package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.TrackingInfo;
import com.belpost.telegram.bot.repository.TrackingInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackingInfoServiceImpl implements TrackingInfoService {
    private final TrackingInfoRepository trackingInfoRepository;

    @Override
    public Page<TrackingInfo> findAll(Pageable pageable) {
        return trackingInfoRepository.findAll(pageable);
    }

    @Override
    public TrackingInfo updateByTrackingNumber(TrackingInfo newTrackingInfo) {
        return null;
    }
}
