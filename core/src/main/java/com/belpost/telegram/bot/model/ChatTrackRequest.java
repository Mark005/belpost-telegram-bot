package com.belpost.telegram.bot.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat_track_request")
public class ChatTrackRequest {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tracking_info_id")
    private TrackingInfo trackingInfo;

}
