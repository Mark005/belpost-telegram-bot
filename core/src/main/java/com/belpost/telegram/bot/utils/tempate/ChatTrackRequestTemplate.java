package com.belpost.telegram.bot.utils.tempate;

import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import org.springframework.stereotype.Component;
import org.stringtemplate.v4.ST;

import java.util.List;

@Component
public class ChatTrackRequestTemplate implements Template<ChatTrackRequest> {

    @Override
    public String build(List<ChatTrackRequest> trackRequests, LanguageEnum language) {
        var template = getTemplate("entity/chat-tracking-request.tmp", language);

        ST st = new ST(template);
        st.add("requests", trackRequests);
        return st.render();
    }
}