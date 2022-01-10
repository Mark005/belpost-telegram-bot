package com.belpost.telegram.bot.handler.listener.tips;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.handler.listener.UpdateListener;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class CommandsTipsSender implements UpdateListener {


    @Override
    @SneakyThrows
    public void onUpdate(BelpostBot bot, Update update) {
        var command = UpdateUtils.extractCommand(update);
        if (command == null) {
            return;
        }

        if (command == CommandEnum.UNKNOWN) {
            bot.sendUpdateResponseMessage(
                    "Unknown command %s for more info".formatted(CommandEnum.HELP.getCommand()), update);
        }

        if (command == CommandEnum.HELP) {
            bot.sendUpdateResponseMessage("Only god can help you", update);
        }

        if (command == CommandEnum.GET_TRACK_INFO) {
            bot.sendUpdateResponseMessage("Type post number for request.\nExample: AB123456789CD", update);
        }


    }
}
