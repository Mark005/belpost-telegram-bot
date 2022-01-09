package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.ChatTrackRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatTrackRequestRepository extends JpaRepository<ChatTrackRequest, UUID> {

    List<ChatTrackRequest> findAllByChatId(Long chatId);
}
