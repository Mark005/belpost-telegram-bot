package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.TrackingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrackingInfoRepository extends JpaRepository<TrackingInfo, UUID> {
}
