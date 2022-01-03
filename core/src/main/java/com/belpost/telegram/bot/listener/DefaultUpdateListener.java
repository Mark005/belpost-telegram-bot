package com.belpost.telegram.bot.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.TelegramBot;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class DefaultUpdateListener implements UpdateListener {


    private final ObjectMapper mapper;

    @Override
    public void onUpdate(TelegramLongPollingBot bot, Update update) {
        System.out.println("ffff");
        bot.sendApiMethod(SendMessage.builder()
                .text("ffrtrtrt")
                .chatId(String.valueOf(update.getMessage().getChatId()))
                .build());
    }
}
