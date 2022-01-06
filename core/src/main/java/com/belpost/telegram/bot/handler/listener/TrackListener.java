package com.belpost.telegram.bot.handler.listener;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;
import com.belpost.telegram.bot.service.TrackingService;
import com.belpost.telegram.bot.service.UpdateRecorder;
import com.belpost.telegram.bot.utils.TemplateBuilder;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackListener implements UpdateListener {
    private final TrackingService trackingService;
    private final UpdateRecorder updateRecorder;

    @SneakyThrows
    @Override
    public void onUpdate(BelpostBot bot, Update update) {
        Optional<Update> previousUpdate = updateRecorder.getPreviousUpdateFromChatByChatId(
                UpdateUtils.extractChatId(update));

        if (previousUpdate.isEmpty()) {
            return;
        }

        if (UpdateUtils.extractCommand(update) != null ||
                CommandEnum.TRACK_ORDER != UpdateUtils.extractCommand(previousUpdate)) {
            return;
        }

        var trackNumber = UpdateUtils.extractMessageText(update);

        if (!isValid(trackNumber)) {
            bot.sendUpdateResponseMessage("Track number isn't valid", update);
            return;
        }

        var request =
                PostTrackingRequest.builder()
                        .number(trackNumber)
                        .build();

        trackingService.getTrackInfo(request)
                .doOnSuccess(postTrackingResponse ->
                        bot.sendUpdateResponseMessage(
                                TemplateBuilder.build(
                                        postTrackingResponse,
                                        UpdateUtils.extractLanguage(update)), update))
                .doOnError(WebClientResponseException.NotFound.class, notFound ->
                        bot.sendUpdateResponseMessage("Order not found", update))
                .doOnError(throwable ->
                        bot.sendUpdateResponseMessage(
                                "An exception occurred during the request, please try later", update))
                .subscribe();
    }

    private boolean isValid(String trackNumber) {
        if (!StringUtils.hasText(trackNumber)) {
            return false;
        }
        return trackNumber.length() == 13;
    }
}
