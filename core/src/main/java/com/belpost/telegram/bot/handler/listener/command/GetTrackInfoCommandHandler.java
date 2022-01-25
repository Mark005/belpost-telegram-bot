package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.common.validator.TrackNumberValidator;
import com.belpost.telegram.bot.model.belpost.PostTrackingResponse;
import com.belpost.telegram.bot.service.TrackingService;
import com.belpost.telegram.bot.utils.UpdateUtils;
import com.belpost.telegram.bot.utils.tempate.Template;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class GetTrackInfoCommandHandler implements CommandHandler {
    private final TrackNumberValidator belpostTrackNumberValidator;
    private final TrackingService trackingService;
    private final Template<PostTrackingResponse> template;

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.GET_TRACK_INFO;
    }

    @Override
    public void handleFirst(BelpostBot bot, Update update) {
        bot.sendUpdateResponseMessage("Type post number for request.\nExample: AB123456789CD", update);
    }

    @SneakyThrows
    @Override
    public void handleSecond(BelpostBot bot, Update update) {

        var trackNumber = UpdateUtils.extractMessageText(update);

        if (!belpostTrackNumberValidator.isValid(trackNumber)) {
            bot.sendUpdateResponseMessage("Track number isn't valid", update);
            return;
        }

        trackingService.getTrackInfo(trackNumber)
                .doOnSuccess(postTrackingResponse ->
                        bot.sendUpdateResponseMessage(
                                template.build(
                                        postTrackingResponse,
                                        UpdateUtils.extractLanguage(update)), update))
                .doOnError(WebClientResponseException.NotFound.class, notFound ->
                        bot.sendUpdateResponseMessage("Order not found", update))
                .doOnError(WebClientResponseException.class, e ->
                        bot.sendUpdateResponseMessage(
                                "An exception occurred during the request, please try later", update))
                .subscribe();
    }
}
