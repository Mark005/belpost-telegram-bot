package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.model.TrackUpdate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.stringtemplate.v4.ST;

import java.util.List;

@Component
public class TrackUpdateTemplate implements Template<TrackUpdate>, UpdateNotificationTemplate {

    @Override
    public String build(ChatTrackRequest trackRequest, List<TrackUpdate> newSteps, LanguageEnum language) {
        if (CollectionUtils.isEmpty(newSteps)) {
            throw new RuntimeException("Steps list shouldn't be empty");
        }

        var template = getTemplate("post/new-step-notification.tmp", language);

        ST st = new ST(template);
        st.add("name", trackRequest.getName());
        st.add("number", trackRequest.getTrackingInfo().getTrackNumber());
        st.add("steps", newSteps);
        return st.render();
    }
}
