package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;

import java.util.List;

public interface Template<T> extends MultilanguageTemplate {

    default String build(T object, LanguageEnum language) {
        return "Not Implemented";
    }

    default String build(List<T> objects, LanguageEnum language){
        return "Not Implemented";
    }
}
