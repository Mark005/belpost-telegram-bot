package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UnknownCommandHandler implements CommandHandler {

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.UNKNOWN;
    }

    @Override
    public void handleFirst(BelpostBot bot, Update update) {
        bot.sendUpdateResponseMessage(
                "Unknown command %s for more info".formatted(CommandEnum.HELP.getCommand()), update);
    }

    @Override
    public void handleSecond(BelpostBot bot, Update update) {

    }
}
