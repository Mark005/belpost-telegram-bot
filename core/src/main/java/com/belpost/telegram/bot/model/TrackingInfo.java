package com.belpost.telegram.bot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @OneToMany(mappedBy = "trackingInfo", orphanRemoval = true)
    private List<ChatTrackRequest> chatTrackRequest = new ArrayList<>();

    @Column(name = "track_number")
    private String trackNumber;

    @OneToMany(
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "tracking_info_id")
    private List<TrackUpdate> trackUpdates = new ArrayList<>();

}
