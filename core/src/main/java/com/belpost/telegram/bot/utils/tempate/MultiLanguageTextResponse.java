package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;

public interface MultiLanguageTextResponse extends MultilanguageTemplate {

    String get(LanguageEnum language);
}
