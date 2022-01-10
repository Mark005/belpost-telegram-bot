package com.belpost.telegram.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "track_update")
public class TrackUpdate {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "system")
    private SystemEnum system;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "place")
    private String place;

    @Column(name = "event")
    private String event;

    @Column(name = "is_border")
    private Boolean isBorder;

    @Column(name = "border_link")
    private String borderLink;

    @Column(name = "place_index")
    private String placeIndex;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "tracking_info_id")
    private TrackingInfo trackingInfo;

}
