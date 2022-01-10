package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.common.validator.TrackNumberValidator;
import com.belpost.telegram.bot.mapper.TrackingInfoMapper;
import com.belpost.telegram.bot.model.ChatTrackRequest;
import com.belpost.telegram.bot.service.TrackRequestCreationException;
import com.belpost.telegram.bot.service.TrackRequestService;
import com.belpost.telegram.bot.service.TrackingService;
import com.belpost.telegram.bot.utils.UpdateUtils;
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

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.ADD_TRACKING_ORDER;
    }

    @Override
    public void handle(BelpostBot bot, Update update) {
        var trackNumberWithName = UpdateUtils.extractMessageText(update);

        var trackNumber = trackNumberWithName.substring(0, trackNumberWithName.indexOf(" "));

        var name = trackNumberWithName.substring(trackNumberWithName.indexOf(" ")+1);

        if (!validator.isValid(trackNumber)) {
            bot.sendUpdateResponseMessage("Track number isn't valid", update);
            return;
        }

        trackingService.getTrackInfo(trackNumber)
                .doOnSuccess(postTrackingResponse -> {
                    var request = ChatTrackRequest.builder()
                            .chatId(UpdateUtils.extractChatId(update))
                            .name(name)
                            .trackingInfo(trackingInfoMapper.convert(postTrackingResponse.getData().get(0)))
                            .build();
                    trackRequestService.addNewTrackingNumber(request);
                })
                .doOnError(WebClientResponseException.NotFound.class, notFound ->
                        bot.sendUpdateResponseMessage("Order not found", update))
                .doOnError(TrackRequestCreationException.class, e ->
                        bot.sendUpdateResponseMessage(e.getDuplicates().toString(), update))
                .doOnError(WebClientResponseException.class, e ->
                        bot.sendUpdateResponseMessage(
                                "An exception occurred during the request, please try later", update))
                .subscribe();

    }
}
