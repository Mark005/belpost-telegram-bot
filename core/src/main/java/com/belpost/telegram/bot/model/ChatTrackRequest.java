package com.belpost.telegram.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "chat_track_request")
public class ChatTrackRequest {
    @Id
    @Column(nullable = false)
    private UUID id;

    @Column(name = "chat_id")
    private Long chatId;

    @ManyToOne
    @JoinColumn(name = "tracking_info_id")
    private TrackingInfo trackingInfo;
}
