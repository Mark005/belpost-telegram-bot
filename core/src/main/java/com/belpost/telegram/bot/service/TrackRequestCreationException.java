package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.ChatTrackRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class TrackRequestCreationException extends RuntimeException {
    private final List<ChatTrackRequest> duplicates;

    public TrackRequestCreationException(List<ChatTrackRequest> duplicates) {
        this.duplicates = duplicates;
    }
}
