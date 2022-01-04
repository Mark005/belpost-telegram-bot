package com.belpost.telegram.bot.handler;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
    void handle(TelegramLongPollingBot bot, Update update);
}
