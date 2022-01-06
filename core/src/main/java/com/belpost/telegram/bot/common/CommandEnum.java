package com.belpost.telegram.bot.common;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum CommandEnum {

    UNKNOWN(""),
    STATUS("/status"),
    HELP("/help"),
    TRACK_ORDER("/track_order");

    private final String command;

    @Nullable
    public static CommandEnum getEnumByCommand(String command) {
        if (!StringUtils.hasText(command)) {
            return null;
        }

        return Stream.of(CommandEnum.values())
                .filter(commandEnum -> commandEnum.getCommand().equals(command))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
