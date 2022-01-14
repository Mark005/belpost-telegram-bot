package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.model.TrackUpdate;

import java.util.List;

public interface UpdateNotificationTemplate {

    String build(ChatTrackRequest trackRequest, List<TrackUpdate> newSteps, LanguageEnum language);
}
