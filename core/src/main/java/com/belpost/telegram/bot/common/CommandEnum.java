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
    GET_TRACK_INFO("/get_track_info"),
    ADD_TRACKING_ORDER("/add_order_for_tracking"),
    GET_TRACKING_ORDERS("/get_tracking_orders"),
    DELETE_TRACKING_ORDER("/del_tracking_order");

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
