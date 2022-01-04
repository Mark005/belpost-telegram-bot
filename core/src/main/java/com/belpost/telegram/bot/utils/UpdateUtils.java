package com.belpost.telegram.bot.utils;

import com.belpost.telegram.bot.common.CommandEnum;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collections;
import java.util.Optional;

public class UpdateUtils {

    public static Long extractChatId(Update update) {
        return Optional.of(update)
                .map(Update::getMessage)
                .map(Message::getChatId)
                .orElseThrow(() -> new RuntimeException("Chat id not found!"));
    }

    public static String extractMessageText(Optional<Update> updateOptional) {
        return updateOptional.map(Update::getMessage)
                .map(Message::getText)
                .orElse(null);
    }

    public static String extractMessageText(Update update) {
        return extractMessageText(Optional.of(update));
    }

    public static CommandEnum extractCommand(Optional<Update> updateOptional) {
        return updateOptional.map(Update::getMessage)
                .map(Message::getEntities)
                .orElse(Collections.emptyList())
                .stream()
                .findFirst()
                .map(MessageEntity::getText)
                .map(CommandEnum::getEnumByCommand)
                .orElse(null);
    }

    public static CommandEnum extractCommand(Update update) {
        return extractCommand(Optional.of(update));
    }

}
