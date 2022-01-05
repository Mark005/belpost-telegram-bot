package com.belpost.telegram.bot.handler.listener;

import com.belpost.telegram.bot.BelpostBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateListener {

    void onUpdate(BelpostBot bot, Update update);
}
