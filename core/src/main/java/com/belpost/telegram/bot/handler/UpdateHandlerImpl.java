package com.belpost.telegram.bot.handler;

import com.belpost.telegram.bot.handler.listener.UpdateListener;
import com.belpost.telegram.bot.service.UpdateRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateHandlerImpl implements UpdateHandler {

    private final List<UpdateListener> listeners;
    private final UpdateRecorder updateRecorder;

    @Override
    public void handle(TelegramLongPollingBot bot, Update update) {
        listeners.forEach(updatesListener -> updatesListener.onUpdate(bot, update));
        updateRecorder.save(update);
    }
}
