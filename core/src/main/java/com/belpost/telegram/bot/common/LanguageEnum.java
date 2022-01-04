package com.belpost.telegram.bot.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LanguageEnum {
    RUSSIAN("ru"),
    ENGLISH("en");

    private final String code;
}
