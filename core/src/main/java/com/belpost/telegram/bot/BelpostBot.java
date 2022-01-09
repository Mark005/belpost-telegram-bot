package com.belpost.telegram.bot;

import com.belpost.telegram.bot.handler.UpdateHandler;
import com.belpost.telegram.bot.onstart.OnRegisterConfigs;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BelpostBot extends TelegramLongPollingBot {

    @Value("${telegram.belpost-bot.token}")
    private final String token;

    @Value("${telegram.belpost-bot.username}")
    private final String username;

    private final List<OnRegisterConfigs> onRegisterConfigs;
    private final UpdateHandler updateHandler;

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public void onRegister() {
        onRegisterConfigs.forEach(config -> config.setUpConfigs(this));
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        updateHandler.handle(this, update);
    }

    @SneakyThrows
    public Message sendUpdateResponseMessage(String text, Update update) {
        return execute(
                SendMessage.builder()
                        .text(text)
                        .chatId(String.valueOf(UpdateUtils.extractChatId(update)))
                        .build());
    }

}
