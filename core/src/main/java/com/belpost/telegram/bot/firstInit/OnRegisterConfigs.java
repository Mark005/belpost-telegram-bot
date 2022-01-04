package com.belpost.telegram.bot.firstInit;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public interface OnRegisterConfigs {
    void setUpConfigs(TelegramLongPollingBot bot);
}
