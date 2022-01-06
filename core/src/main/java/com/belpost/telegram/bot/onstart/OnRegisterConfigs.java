package com.belpost.telegram.bot.onstart;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface OnRegisterConfigs {
    void setUpConfigs(TelegramLongPollingBot bot);
}
