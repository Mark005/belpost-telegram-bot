package com.belpost.telegram.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor

@Entity
@Table(name = "tracking_info")
public class TrackingInfo {
    @Id
    @Column(nullable = false)
    @GeneratedValue
    private UUID id;

    @OneToMany(
            mappedBy = "trackingInfo",
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.PERSIST)
    @Fetch(FetchMode.SUBSELECT)
    private List<ChatTrackRequest> chatTrackRequest = new ArrayList<>();

    @Column(name = "track_number")
    private String trackNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "track_status_enum")
    private TrackStatusEnum trackStatusEnum;

    @OneToMany(
            orphanRemoval = true,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "tracking_info_id")
    private List<TrackUpdate> trackUpdates = new ArrayList<>();

}
