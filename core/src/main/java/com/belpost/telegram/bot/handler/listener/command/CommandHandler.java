package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandHandler {

    CommandEnum getHandlingCommand();

    void handle(BelpostBot bot, Update update);
}
