package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.handler.listener.UpdateListener;
import com.belpost.telegram.bot.service.UpdateRecorder;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PreCommandHandler implements UpdateListener {

    private final UpdateRecorder updateRecorder;
    private final Map<CommandEnum, CommandHandler> commandHandlersMap;

    @Override
    public void onUpdate(BelpostBot bot, Update update) {
        Optional<Update> previousUpdate = updateRecorder.getPreviousUpdateFromChatByChatId(
                UpdateUtils.extractChatId(update));

        if (previousUpdate.isEmpty()) {
            return;
        }

        if (UpdateUtils.extractCommand(update) != null) {
            return;
        }

        CommandEnum command = UpdateUtils.extractCommand(previousUpdate);

        Optional.ofNullable(commandHandlersMap.get(command))
                .ifPresentOrElse(
                        commandHandler -> commandHandler.handle(bot, update),
                        () -> bot.sendUpdateResponseMessage("Handler not implemented", update));
    }
}
