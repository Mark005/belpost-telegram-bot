package com.belpost.telegram.bot.common.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class BelpostTrackNumberValidator implements TrackNumberValidator {

    @Override
    public boolean isValid(String trackNumber) {
        if (!StringUtils.hasText(trackNumber)) {
            return false;
        }
        return trackNumber.length() == 13;
    }
}
