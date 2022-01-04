package com.belpost.telegram.bot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UpdateRecorderImpl implements UpdateRecorder {

    private final Map<Long, Update> storage = new HashMap<>();

    @Override
    public Optional<Update> getPreviousUpdateFromChatByChatId(Long chatId) {
        return Optional.ofNullable(storage.get(chatId));
    }

    @Override
    public Update save(Update update) {
        Long chatId = update.getMessage().getChatId();
        storage.put(chatId, update);
        return update;
    }

    @Override
    public Update update(Update update) {
        Long chatId = update.getMessage().getChatId();
        storage.put(chatId, update);
        return update;
    }
}
