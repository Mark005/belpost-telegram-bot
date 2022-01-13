package com.belpost.telegram.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrackUpdate that = (TrackUpdate) o;
        return system == that.system &&
                timestamp.equals(that.timestamp) &&
                createdAt.equals(that.createdAt) &&
                Objects.equals(place, that.place) &&
                event.equals(that.event) &&
                Objects.equals(isBorder, that.isBorder) &&
                Objects.equals(borderLink, that.borderLink) &&
                Objects.equals(placeIndex, that.placeIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                system,
                timestamp,
                createdAt,
                place,
                event,
                isBorder,
                borderLink,
                placeIndex);
    }
}
