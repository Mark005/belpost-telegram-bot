package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.TrackUpdate;
import com.belpost.telegram.bot.model.belpost.TrackingInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.stringtemplate.v4.ST;

import java.util.List;

@Component
public class TrackUpdateTemplate implements Template<TrackUpdate> {

    @Override
    public String build(List<TrackUpdate> trackRequests, LanguageEnum language) {
        if (!CollectionUtils.isEmpty(trackRequests)) {
            return getTemplate("post/order-notfound-response.tmp", language);
        }

        var template = getTemplate("post/new-step-notification.tmp", language);

        ST st = new ST(template);
        st.add("number", trackRequests.get(0).getTrackingInfo().getTrackNumber());
        st.add("steps", trackRequests);
        return st.render();
    }
}
