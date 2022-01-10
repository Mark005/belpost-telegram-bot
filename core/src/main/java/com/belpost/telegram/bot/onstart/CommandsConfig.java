package com.belpost.telegram.bot.onstart;

import com.belpost.telegram.bot.common.BotStatusEnum;
import com.belpost.telegram.bot.common.CommandEnum;
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
                                .command(CommandEnum.HELP.getCommand())
                                .description("Дополнительная информация")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.GET_TRACK_INFO.getCommand())
                                .description("Отследить отправление")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.ADD_TRACKING_ORDER.getCommand())
                                .description("Добавить отправление для постоянного отслеживания")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.GET_TRACKING_ORDERS.getCommand())
                                .description("Посмотреть список отслеживаемых отправлений")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.DELETE_TRACKING_ORDER.getCommand())
                                .description("Прекратить отслеживать отправление")
                                .build()
                ))
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
                                .command(CommandEnum.HELP.getCommand())
                                .description("Additional info")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.GET_TRACK_INFO.getCommand())
                                .description("Track order by post number")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.ADD_TRACKING_ORDER.getCommand())
                                .description("Add new order for tracking")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.GET_TRACKING_ORDERS.getCommand())
                                .description("Get all tracking orders")
                                .build(),
                        BotCommand.builder()
                                .command(CommandEnum.DELETE_TRACKING_ORDER.getCommand())
                                .description("Stop tracking order")
                                .build()
                ))
                .languageCode(LanguageEnum.ENGLISH.getCode())
                .build();
    }
}
