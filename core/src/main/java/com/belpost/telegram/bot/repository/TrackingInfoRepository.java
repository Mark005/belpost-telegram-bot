package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.TrackStatusEnum;
import com.belpost.telegram.bot.model.TrackingInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface TrackingInfoRepository extends JpaRepository<TrackingInfo, UUID> {

    @Query("select t from TrackingInfo t where t.trackStatusEnum not in (:trackStatus)")
    Page<TrackingInfo> findAllExcludeStatus(@Param("trackStatus")TrackStatusEnum trackStatus, Pageable pageable);
}
