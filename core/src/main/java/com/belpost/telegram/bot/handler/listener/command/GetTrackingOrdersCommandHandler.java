package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.repository.ChatTrackRequestRepository;
import com.belpost.telegram.bot.utils.UpdateUtils;
import com.belpost.telegram.bot.utils.tempate.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class GetTrackingOrdersCommandHandler implements CommandHandler {
    private final ChatTrackRequestRepository chatTrackRequestRepository;
    private final Template<ChatTrackRequest> chatTrackRequestTemplate;

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.GET_TRACKING_ORDERS;
    }

    @Override
    public void handleFirst(BelpostBot bot, Update update) {
        Long chatId = UpdateUtils.extractChatId(update);
        LanguageEnum language = UpdateUtils.extractLanguage(update);

        var allByChatId = chatTrackRequestRepository.findAllByChatId(chatId);
        bot.sendUpdateResponseMessage(
                chatTrackRequestTemplate.build(allByChatId, language),
                update);
    }

    @Override
    public void handleSecond(BelpostBot bot, Update update) {
    }
}
