package com.belpost.telegram.bot.utils.tempate.validation;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.utils.tempate.MultiLanguageTextResponse;
import org.springframework.stereotype.Component;

@Component
public class TrackNumberIsNotValidTemplate implements MultiLanguageTextResponse {

    @Override
    public String get(LanguageEnum language) {
        return getTemplate("error/validation/track-number-is-not-valid.tmp", language);
    }
}
