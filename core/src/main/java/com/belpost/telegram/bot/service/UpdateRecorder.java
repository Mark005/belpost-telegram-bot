package com.belpost.telegram.bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

public interface UpdateRecorder {
    Optional<Update> getPreviousUpdateFromChatByChatId(Long chatId);

    Update save(Update update);

    Update update(Update update);
}
