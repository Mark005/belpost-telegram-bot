package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.TrackUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrackUpdateRepository extends JpaRepository<TrackUpdate, UUID> {
}
