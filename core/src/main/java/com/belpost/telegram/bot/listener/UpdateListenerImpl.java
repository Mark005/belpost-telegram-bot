package com.belpost.telegram.bot.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class UpdateListenerImpl implements UpdatesListener {

    @Lazy
    private final TelegramBot bot;

    private final ObjectMapper mapper;

    @Override
    public int process(List<Update> list) {

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup(
                new InlineKeyboardButton("url").url("www.google.com"),
                new InlineKeyboardButton("callback_data").callbackData("callback_data"),
                new InlineKeyboardButton("Switch!").switchInlineQuery("switch_inline_query"));

        ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton("text"),
                new KeyboardButton("contact").requestContact(true),
                new KeyboardButton("location").requestLocation(true));

        IntStream.range(0, 10)
                        .forEach(value -> keyboard.addRow(new KeyboardButton(String.valueOf(value))));

        list.stream()
                .map(Update::message)
                .forEach(incomeMessage -> {
                    SendMessage sendMessage =
                            null;
                    try {
                        sendMessage = new SendMessage(
                                incomeMessage.chat().id(),
                                //"Message handled: %s".formatted(incomeMessage.text())
                                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list)
                        );
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    sendMessage.replyMarkup(keyboard);
                    bot.execute(sendMessage, new Callback<SendMessage, SendResponse>() {
                        @Override
                        public void onResponse(SendMessage request, SendResponse response) {
                            System.out.println("");
                        }

                        @Override
                        public void onFailure(SendMessage request, IOException e) {
                            System.out.println("");
                        }
                    });
                });

        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
