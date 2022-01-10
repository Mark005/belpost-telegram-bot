package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.model.ChatTrackRequest;

public interface TrackRequestService {

    ChatTrackRequest addNewTrackingNumber(ChatTrackRequest request);
}
