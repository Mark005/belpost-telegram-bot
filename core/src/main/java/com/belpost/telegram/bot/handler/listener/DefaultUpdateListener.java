package com.belpost.telegram.bot.handler.listener;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.service.UpdateRecorder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class DefaultUpdateListener implements UpdateListener {

    private final UpdateRecorder updateRecorder;
    private final ObjectMapper mapper;

    @SneakyThrows
    @Override
    public void onUpdate(BelpostBot bot, Update update) {
        //bot.execute(SendMessage.builder()
        //        .text("current message: %s\n\nprevious message: %s".formatted(
        //                update.getMessage().getText(),
        //                updateRecorder.getPreviousUpdateFromChatByChatId(update.getMessage().getChatId())
        //                        .map(Update::getMessage)
        //                        .map(Message::getText)
        //                        .orElse("none")))
        //        .chatId(String.valueOf(update.getMessage().getChatId()))
        //        .build());
    }
}
