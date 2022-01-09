package com.belpost.telegram.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private UUID id;

    @Column(name = "track_number")
    private String trackNumber;

    @OneToMany(mappedBy = "trackingInfo", orphanRemoval = true)
    private List<TrackUpdate> trackUpdates = new ArrayList<>();

}
