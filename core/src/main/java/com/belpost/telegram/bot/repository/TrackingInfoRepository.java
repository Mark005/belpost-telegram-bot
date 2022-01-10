package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.TrackingInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackingInfoRepository extends JpaRepository<TrackingInfo, UUID> {
}
