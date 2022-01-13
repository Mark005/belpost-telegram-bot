package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.repository.ChatTrackRequestRepository;
import com.belpost.telegram.bot.utils.UpdateUtils;
import com.belpost.telegram.bot.utils.tempate.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class GetTrackingOrderCommandHandler implements CommandHandler {
    private final ChatTrackRequestRepository chatTrackRequestRepository;
    private final Template<ChatTrackRequest> template;

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.GET_TRACKING_ORDERS;
    }

    @Override
    public void handle(BelpostBot bot, Update update) {
        var allByChatId = chatTrackRequestRepository.findAllByChatId(UpdateUtils.extractChatId(update));
        bot.sendUpdateResponseMessage(
                template.build(allByChatId, UpdateUtils.extractLanguage(update)),
                update);
    }
}
