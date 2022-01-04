package com.belpost.telegram.bot.config;

import com.belpost.telegram.bot.firstInit.OnRegisterConfigs;
import com.belpost.telegram.bot.handler.UpdateHandler;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Configuration
public class BotConfig {

    @Bean
    @SneakyThrows
    TelegramBotsApi telegramBotApi(TelegramLongPollingBot telegramLongPollingBot) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(telegramLongPollingBot);
        return telegramBotsApi;
    }

    @Bean
    TelegramLongPollingBot telegramLongPollingBot(@Value("${telegram.belpost-bot.token}") String token,
                                                  List<OnRegisterConfigs> onRegisterConfigs,
                                                  UpdateHandler updateHandler) {
        return new TelegramLongPollingBot() {

            @Override
            public String getBotToken() {
                return token;
            }

            @Override
            public String getBotUsername() {
                return "usernameeeee";
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
        };
    }
}
