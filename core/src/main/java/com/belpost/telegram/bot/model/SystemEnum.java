package com.belpost.telegram.bot.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum SystemEnum {
    UNKNOWN(""),
    INTERNAL("internal"),
    EXTERNAL("external");

    private final String name;

    @Nullable
    public static SystemEnum getEnumByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return Stream.of(SystemEnum.values())
                .filter(systemEnum -> systemEnum.getName().equals(name.toLowerCase()))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
