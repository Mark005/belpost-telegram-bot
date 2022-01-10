package com.belpost.telegram.bot.repository;

import com.belpost.telegram.bot.model.ChatTrackRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ChatTrackRequestRepository extends JpaRepository<ChatTrackRequest, UUID> {

    List<ChatTrackRequest> findAllByChatId(Long chatId);

    @Query("""
            SELECT r
            from ChatTrackRequest r
            join TrackingInfo ti
            on r.trackingInfo = ti
            where r.name =:name or
            ti.trackNumber =:trackNumber
            """)
    List<ChatTrackRequest> findAllByChatIdAndTrackNumber(@Param("name") String name,
                                                         @Param("trackNumber") String trackNumber);
}
