package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;
import lombok.SneakyThrows;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

public interface MultilanguageTemplate {

    @SneakyThrows
    default String getTemplate(String relativePath, LanguageEnum language) {
        var templateUrl = ResourceUtils.getURL(
                "classpath:templates/%s/%s".formatted(language.getCode(), relativePath));

        return Files.readString(Paths.get(templateUrl.toURI()));
    }
}
