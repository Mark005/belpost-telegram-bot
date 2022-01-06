package com.belpost.telegram.bot.handler;

import com.belpost.telegram.bot.BelpostBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {
    void handle(BelpostBot bot, Update update);
}
