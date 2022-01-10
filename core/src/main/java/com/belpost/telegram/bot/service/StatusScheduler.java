package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.mapper.TrackingInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StatusScheduler {
    private final BelpostBot bot;
    private final TrackingInfoService trackingInfoService;
    private final TrackingService trackingService;
    private final TrackingInfoMapper trackingInfoMapper;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 1)
    public void updateStatus() {
        var pageRequest = PageRequest.of(0, 10);
        var page = trackingInfoService.findAll(pageRequest);

        while (!page.isEmpty()) {
            pageRequest = pageRequest.next();

            page.forEach(trackingInfo -> {
                trackingService.getTrackInfo(trackingInfo.getTrackNumber())
                        .doOnSuccess(postTrackingResponse -> {
                            var newTrackingInfo =
                                    trackingInfoMapper.convert(postTrackingResponse.getData().get(0));

                            var updates = new ArrayList<>(newTrackingInfo.getTrackUpdates());
                            updates.removeAll(trackingInfo.getTrackUpdates());

                            trackingInfoService.updateByTrackingNumber(newTrackingInfo);
                            trackingInfo.getChatTrackRequest()
                                    .forEach(trackRequest ->
                                            bot.sendUpdateResponseMessage(updates.toString(),
                                                    trackRequest.getChatId())
                                    );
                        })
                        .doOnError(WebClientResponseException.NotFound.class,
                                notFound ->
                                        trackingInfo.getChatTrackRequest()
                                                .forEach(trackRequest ->
                                                        bot.sendUpdateResponseMessage("Order not found",
                                                                trackRequest.getChatId())
                                                )
                        )
                        .doOnError(WebClientResponseException.class, e ->
                                        trackingInfo.getChatTrackRequest()
                                                .forEach(trackRequest ->
                                                        bot.sendUpdateResponseMessage(
                                                                "An exception occurred during the request, please try later",
                                                                trackRequest.getChatId())
                                                )
                        )
                        .subscribe();
            });

            page = trackingInfoService.findAll(pageRequest);
        }
    }
}
