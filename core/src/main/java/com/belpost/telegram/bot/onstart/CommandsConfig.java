package com.belpost.telegram.bot.onstart;

import com.belpost.telegram.bot.common.BotStatusEnum;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.common.LanguageEnum;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class CommandsConfig implements OnRegisterConfigs {

    @SneakyThrows
    @Override
    public void setUpConfigs(TelegramLongPollingBot bot) {
        setCommands(bot, BotStatusEnum.ONLINE);

        Thread haltedHook = new Thread(() -> setCommands(bot, BotStatusEnum.OFFLINE));
        Runtime.getRuntime().addShutdownHook(haltedHook);
    }

    @SneakyThrows
    private void setCommands(TelegramLongPollingBot bot, BotStatusEnum status) {
        bot.execute(getRussianCommands(status));
        bot.execute(getEnglishCommands(status));
    }

    private SetMyCommands getRussianCommands(BotStatusEnum status) {
        return SetMyCommands.builder()
                .commands(List.of(BotCommand.builder()
                                .command(CommandEnum.STATUS.getCommand())
                                .description("Статус бота: %s %s".formatted(status.getEmoji(), status.getText()))
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.TRACK_ORDER.getCommand())
                                .description("Отследить отправление")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.HELP.getCommand())
                                .description("Дополнительная информация")
                                .build()))
                .languageCode(LanguageEnum.RUSSIAN.getCode())
                .build();
    }

    private SetMyCommands getEnglishCommands(BotStatusEnum status) {
        return SetMyCommands.builder()
                .commands(List.of(
                        BotCommand.builder()
                                .command(CommandEnum.STATUS.getCommand())
                                .description("Bot status:  %s %s".formatted(status.getEmoji(), status.getText()))
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.TRACK_ORDER.getCommand())
                                .description("Track order by post number")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.HELP.getCommand())
                                .description("Additional info")
                                .build()))
                .languageCode(LanguageEnum.ENGLISH.getCode())
                .build();
    }
}
