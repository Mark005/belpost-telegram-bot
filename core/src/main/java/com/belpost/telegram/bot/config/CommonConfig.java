package com.belpost.telegram.bot.config;

import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.handler.listener.command.CommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Configuration
public class CommonConfig {

    @Bean
    Map<CommandEnum, CommandHandler> commandHandlersMap(List<CommandHandler> commandHandlers) {
        return commandHandlers.stream()
                .collect(Collectors.toMap(
                        CommandHandler::getHandlingCommand,
                        Function.identity()));
    }
}
