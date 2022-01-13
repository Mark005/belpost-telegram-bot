package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import com.belpost.telegram.bot.model.belpost.TrackingInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.stringtemplate.v4.ST;

@Component
public class PostTrackingResponseTemplate implements Template<PostTrackingResponse>{

    @Override
    public String build(PostTrackingResponse postTrackingResponse, LanguageEnum language) {
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

}
