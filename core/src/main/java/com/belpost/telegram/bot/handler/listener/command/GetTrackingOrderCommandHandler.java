package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.repository.ChatTrackRequestRepository;
import com.belpost.telegram.bot.repository.TrackUpdateRepository;
import com.belpost.telegram.bot.repository.TrackingInfoRepository;
import com.belpost.telegram.bot.utils.TemplateBuilder;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class GetTrackingOrderCommandHandler implements CommandHandler {
    private final ChatTrackRequestRepository chatTrackRequestRepository;
    private final TrackingInfoRepository trackingInfoRepository;
    private final TrackUpdateRepository trackUpdateRepository;

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.GET_TRACKING_ORDERS;
    }

    @Override
    public void handle(BelpostBot bot, Update update) {
        var allByChatId = chatTrackRequestRepository.findAllByChatId(UpdateUtils.extractChatId(update));
        bot.sendUpdateResponseMessage(
                TemplateBuilder.build(allByChatId, UpdateUtils.extractLanguage(update)),
                update);
    }
}
