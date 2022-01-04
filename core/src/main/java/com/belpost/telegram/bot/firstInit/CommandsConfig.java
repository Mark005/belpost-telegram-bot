package com.belpost.telegram.bot.firstInit;

import com.belpost.telegram.bot.common.LanguageEnum;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

@Service
public class CommandsConfig implements OnRegisterConfigs {

    @SneakyThrows
    @Override
    public void setUpConfigs(TelegramLongPollingBot bot) {
        bot.execute(getRussianCommands());
        bot.execute(getEnglishCommands());
    }

    private SetMyCommands getRussianCommands() {
        return SetMyCommands.builder()
                .commands(List.of(
                        BotCommand.builder()
                                .command("/track_order")
                                .description("Отследить отправление")
                                .build()))
                .languageCode(LanguageEnum.RUSSIAN.getCode())
                .build();
    }

    private SetMyCommands getEnglishCommands() {
        return SetMyCommands.builder()
                .commands(List.of(
                        BotCommand.builder()
                                .command("/track_order")
                                .description("Track order by post number")
                                .build()))
                .languageCode(LanguageEnum.ENGLISH.getCode())
                .build();
    }
}
