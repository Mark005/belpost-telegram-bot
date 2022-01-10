package com.belpost.telegram.bot.handler.listener.command;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.common.validator.TrackNumberValidator;
import com.belpost.telegram.bot.service.TrackingService;
import com.belpost.telegram.bot.utils.TemplateBuilder;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@RequiredArgsConstructor
public class GetTrackInfoCommandHandler implements CommandHandler {
    private final TrackNumberValidator validator;
    private final TrackingService trackingService;

    @Override
    public CommandEnum getHandlingCommand() {
        return CommandEnum.GET_TRACK_INFO;
    }

    @SneakyThrows
    @Override
    public void handle(BelpostBot bot, Update update) {

        var trackNumber = UpdateUtils.extractMessageText(update);

        if (!validator.isValid(trackNumber)) {
            bot.sendUpdateResponseMessage("Track number isn't valid", update);
            return;
        }

        trackingService.getTrackInfo(trackNumber)
                .doOnSuccess(postTrackingResponse ->
                        bot.sendUpdateResponseMessage(
                                TemplateBuilder.build(
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
