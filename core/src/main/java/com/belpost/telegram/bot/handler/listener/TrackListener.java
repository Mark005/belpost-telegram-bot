package com.belpost.telegram.bot.handler.listener;

import com.belpost.telegram.bot.common.CommandEnum;
import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;
import com.belpost.telegram.bot.service.TrackingService;
import com.belpost.telegram.bot.service.UpdateRecorder;
import com.belpost.telegram.bot.utils.UpdateUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrackListener implements UpdateListener {
    private final TrackingService trackingService;
    private final UpdateRecorder updateRecorder;

    @SneakyThrows
    @Override
    public void onUpdate(TelegramLongPollingBot bot, Update update) {
        Optional<Update> previousUpdate = updateRecorder.getPreviousUpdateFromChatByChatId(
                UpdateUtils.extractChatId(update));

        if (previousUpdate.isEmpty()) {
            return;
        }

        if (UpdateUtils.extractCommand(update) == null &&
                CommandEnum.TRACK_ORDER == UpdateUtils.extractCommand(previousUpdate)) {
            var trackNumber = UpdateUtils.extractMessageText(update);
            if (isValid(trackNumber)) {
                var request =
                        PostTrackingRequest.builder()
                                .number(trackNumber)
                                .build();
                try {
                    var response = trackingService.getTrackInfo(request).toString();

                    bot.execute(
                            SendMessage.builder()
                                    .text(response)
                                    .chatId(String.valueOf(UpdateUtils.extractChatId(update)))
                                    .build());
                } catch (Exception e) {
                    bot.execute(
                            SendMessage.builder()
                                    .text("An exception occurred during the request")
                                    .chatId(String.valueOf(UpdateUtils.extractChatId(update)))
                                    .build());
                }
            } else {
                bot.execute(
                        SendMessage.builder()
                                .text("Track number isn't valid")
                                .chatId(String.valueOf(UpdateUtils.extractChatId(update)))
                                .build());
            }
        }
    }

    private boolean isValid(String trackNumber) {
        if (!StringUtils.hasText(trackNumber)) {
            return false;
        }
        return trackNumber.length() == 13;
    }
}
