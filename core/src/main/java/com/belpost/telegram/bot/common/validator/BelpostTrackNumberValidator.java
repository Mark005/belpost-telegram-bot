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

        if (trackNumber.length() != 13) {
            return false;
        }

        var prefix = trackNumber.substring(0, 2);
        var number = trackNumber.substring(2, 11);
        var postfix = trackNumber.substring(11, 13);
        if (hasNotOnlyLatinLetters(prefix) ||
                hasNotOnlyLatinLetters(postfix) ||
                hasNotOnlyNumbers(number)) {
            return false;
        }

        return true;
    }

    private boolean hasNotOnlyLatinLetters(String sting) {
        return !sting.matches("[a-zA-Z]+");
    }

    private boolean hasNotOnlyNumbers(String number) {
        return !number.matches("[0-9]+");
    }
}
