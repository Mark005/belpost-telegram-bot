package com.belpost.telegram.bot.utils;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import com.belpost.telegram.bot.model.belpost.TrackingInfo;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.stringtemplate.v4.ST;

import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class TemplateBuilder {

    public static String build(PostTrackingResponse postTrackingResponse, LanguageEnum language) {
        if (CollectionUtils.isEmpty(postTrackingResponse.getData()) ||
                CollectionUtils.isEmpty(postTrackingResponse.getData().get(0).getSteps())) {
            return getTemplate("post/order-notfound-response.tmp", language);
        }

        TrackingInfo trackingInfo = postTrackingResponse.getData().get(0);

        var template = getTemplate("post/track-response.tmp", language);

        ST st = new ST(template);
        st.add("number", trackingInfo.getNumber());
        st.add("steps", trackingInfo.getSteps());
        return st.render();
    }

    @SneakyThrows
    private static String getTemplate(String relativePath, LanguageEnum language) {
        var templateUrl = ResourceUtils.getURL(
                "classpath:templates/%s/%s".formatted(language.getCode(), relativePath));

        return Files.readString(Paths.get(templateUrl.toURI()));
    }
}
