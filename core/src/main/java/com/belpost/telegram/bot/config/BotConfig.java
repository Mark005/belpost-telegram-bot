package com.belpost.telegram.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Configuration
public class BotConfig {

//    @Bean
//    TelegramBot telegramBot(@Value("${telegram.belpost-bot.token}") String token,
//                            List<UpdatesListener> listeners) {
//        //TelegramBot bot = new TelegramBot(token);
////
//        //listeners.forEach(bot::setUpdatesListener);
//        return null;
//    }

    @Bean
    @SneakyThrows
    TelegramBotsApi telegramBotApi(@Value("${telegram.belpost-bot.token}") String token,
                                   List<UpdatesListener> listeners,
                                   TelegramLongPollingBot telegramLongPollingBot) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramLongPollingBot);
        return telegramBotsApi;
    }

    @Bean
    TelegramLongPollingBot telegramLongPollingBot(@Value("${telegram.belpost-bot.token}") String token,
                                                  List<UpdatesListener> listeners) {
        return new TelegramLongPollingBot() {
            @Override
            public String getBotToken() {
                return token;
            }

            @SneakyThrows
            @Override
            public void onUpdateReceived(Update update) {
                System.out.println("ffff");
                sendApiMethod(SendMessage.builder()
                        .text("ffrtrtrt")
                        .chatId(String.valueOf(update.getMessage().getChatId()))
                        .build());
            }

            @Override
            public String getBotUsername() {
                return "usernameeeee";
            }
        };
    }
}
