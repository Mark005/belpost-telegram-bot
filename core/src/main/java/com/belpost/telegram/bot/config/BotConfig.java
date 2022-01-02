package com.belpost.telegram.bot.config;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration
public class BotConfig {

    @Bean
    TelegramBot telegramBot(@Value("${telegram.belpost-bot.token}") String token,
                            List<UpdatesListener> listeners) {
        TelegramBot bot = new TelegramBot(token);

        listeners.forEach(bot::setUpdatesListener);
        return bot;
    }
}
