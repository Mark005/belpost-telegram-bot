package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.common.validator.TrackNumberValidator;
import com.belpost.telegram.bot.mapper.TrackingInfoMapper;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.model.TrackStatusEnum;
import com.belpost.telegram.bot.service.TrackRequestCreationException;
import com.belpost.telegram.bot.service.TrackRequestService;
import com.belpost.telegram.bot.service.TrackingService;
import com.belpost.telegram.bot.utils.UpdateUtils;
import com.belpost.telegram.bot.utils.tempate.MultiLanguageTextResponse;
import com.belpost.telegram.bot.utils.tempate.Template;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class AddTrackingOrderCommandHandler implements CommandHandler {
    private final TrackNumberValidator validator;
    private final TrackingInfoMapper trackingInfoMapper;
    private final TrackingService trackingService;
    private final TrackRequestService trackRequestService;
    private final MultiLanguageTextResponse trackNumberIsNotValidTemplate;
    private final Template<ChatTrackRequest> chatTrackRequestDuplicateTemplate;

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.ADD_TRACKING_ORDER;
    }

    @Override
    public void handleFirst(BelpostBot bot, Update update) {
        bot.sendUpdateResponseMessage("Type post number for request.\nExample: AB123456789CD", update);
    }

    @Override
    public void handleSecond(BelpostBot bot, Update update) {
        LanguageEnum language = UpdateUtils.extractLanguage(update);

        var trackNumberWithName = UpdateUtils.extractMessageText(update).trim();
        var trackNumber = trackNumberWithName.substring(0, trackNumberWithName.indexOf(" "));
        var name = trackNumberWithName.substring(trackNumberWithName.indexOf(" ") + 1);

        if (!validator.isValid(trackNumber)) {
            bot.sendUpdateResponseMessage(trackNumberIsNotValidTemplate.get(language), update);
            return;
        }
        trackingService.getTrackInfo(trackNumber)
                .doOnSuccess(postTrackingResponse -> {
                    var trackingInfo = trackingInfoMapper.convert(postTrackingResponse.getData().get(0));
                    trackingInfo.setTrackStatusEnum(TrackStatusEnum.NEW);
                    var request = ChatTrackRequest.builder()
                            .chatId(UpdateUtils.extractChatId(update))
                            .name(name)
                            .trackingInfo(trackingInfo)
                            .build();
                    trackRequestService.addNewTrackingNumber(request);
                })
                .doOnError(WebClientResponseException.NotFound.class, notFound ->
                        bot.sendUpdateResponseMessage("Order not found", update))
                .doOnError(TrackRequestCreationException.class, e ->
                        bot.sendUpdateResponseMessage(
                                chatTrackRequestDuplicateTemplate.build(
                                        e.getDuplicates(),
                                        language),
                                update))
                .doOnError(WebClientResponseException.TooManyRequests.class, e ->
                        bot.sendUpdateResponseMessage("Too many requests, please try later", update)
                )
                .doOnError(WebClientResponseException.class, e ->
                        bot.sendUpdateResponseMessage(
                                "An exception occurred during the request, please try later", update))
                .subscribe();

    }
}
