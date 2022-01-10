package com.belpost.telegram.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "tracking_info_id")
    private TrackingInfo trackingInfo;

}
