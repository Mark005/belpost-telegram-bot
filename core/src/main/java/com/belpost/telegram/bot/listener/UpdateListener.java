package com.belpost.telegram.bot.listener;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateListener {

    void onUpdate(TelegramLongPollingBot bot, Update update);
}
