package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.TrackStatusEnum;
import com.belpost.telegram.bot.model.TrackingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface TrackingInfoRepository extends JpaRepository<TrackingInfo, UUID> {

    @Query("select t from TrackingInfo t where t.trackStatusEnum not in ?1")
    Page<TrackingInfo> findAllExcludeStatus(TrackStatusEnum trackStatus, Pageable pageable);
}
