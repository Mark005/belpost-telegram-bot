package com.belpost.telegram.bot.utils;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import com.belpost.telegram.bot.model.belpost.TrackingInfoDto;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.stringtemplate.v4.ST;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class TemplateBuilder {

    public static String build(PostTrackingResponse postTrackingResponse, LanguageEnum language) {
        if (CollectionUtils.isEmpty(postTrackingResponse.getData()) ||
                CollectionUtils.isEmpty(postTrackingResponse.getData().get(0).getSteps())) {
            return getTemplate("post/order-notfound-response.tmp", language);
        }

        TrackingInfoDto trackingInfoDto = postTrackingResponse.getData().get(0);

        var template = getTemplate("post/track-response.tmp", language);

        ST st = new ST(template);
        st.add("number", trackingInfoDto.getNumber());
        st.add("steps", trackingInfoDto.getSteps());
        return st.render();
    }

    public static String build(List<ChatTrackRequest> trackRequests, LanguageEnum language) {
        var template = getTemplate("entity/chat-tracking-request.tmp", language);

        ST st = new ST(template);
        st.add("requests", trackRequests);
        return st.render();
    }

    @SneakyThrows
    private static String getTemplate(String relativePath, LanguageEnum language) {
        var templateUrl = ResourceUtils.getURL(
                "classpath:templates/%s/%s".formatted(language.getCode(), relativePath));

        return Files.readString(Paths.get(templateUrl.toURI()));
    }
}
