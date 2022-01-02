package com.belpost.telegram.bot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UpdateListener implements UpdatesListener {

    @Lazy
    private final TelegramBot bot;

    @Override
    public int process(List<Update> list) {
        list.stream()
                .map(Update::message)
                .map(Message::text)
                .forEach(System.out::println);

        list.stream()
                .map(Update::message)
                .forEach(message -> bot.execute(
                        new SendMessage(
                                message.chat().id(),
                                "Message handled: %s".formatted(message.text()))));
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
