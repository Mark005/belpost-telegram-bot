package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.TrackStatusEnum;
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
    public Page<TrackingInfo> findAllForUpdate(Pageable pageable) {
        final Page<TrackingInfo> allExcludeStatus =
                trackingInfoRepository.findAllExcludeStatus(TrackStatusEnum.FINISHED, pageable);
        return allExcludeStatus;
    }

    @Override
    public TrackingInfo updateByTrackingNumber(TrackingInfo newTrackingInfo) {
        return trackingInfoRepository.save(newTrackingInfo);
    }
}
