package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.BelpostBot;
import com.belpost.telegram.bot.common.LanguageEnum;
import com.belpost.telegram.bot.mapper.TrackingInfoMapper;
import com.belpost.telegram.bot.model.TrackUpdate;
import com.belpost.telegram.bot.utils.tempate.Template;
import io.netty.handler.timeout.ReadTimeoutException;
import io.netty.handler.timeout.WriteTimeoutException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import javax.net.ssl.SSLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StatusScheduler {
    private final BelpostBot bot;
    private final TrackingInfoService trackingInfoService;
    private final TrackingService trackingService;
    private final TrackingInfoMapper trackingInfoMapper;
    private final Template<TrackUpdate> template;

    @Scheduled(timeUnit = TimeUnit.MINUTES, fixedDelay = 1)
    public void updateStatus() {
        var pageRequest = PageRequest.of(0, 10);
        var page = trackingInfoService.findAllForUpdate(pageRequest);

        while (!page.isEmpty()) {
            pageRequest = pageRequest.next();

            page.forEach(trackingInfo -> {
                trackingService.getTrackInfo(trackingInfo.getTrackNumber())
                        .doOnSuccess(postTrackingResponse -> {
                            var newTrackingInfo =
                                    trackingInfoMapper.convert(postTrackingResponse.getData().get(0));

                            List<TrackUpdate> newSteps = new ArrayList<>(newTrackingInfo.getTrackUpdates());
                            newSteps.removeAll(trackingInfo.getTrackUpdates());

                            if (!CollectionUtils.isEmpty(newSteps)) {
                                trackingInfo.setTrackUpdates(newTrackingInfo.getTrackUpdates());

                                trackingInfoService.updateByTrackingNumber(trackingInfo);
                                trackingInfo.getChatTrackRequest()
                                        .forEach(trackRequest ->
                                                bot.sendUpdateResponseMessage(
                                                        template.build(newSteps, LanguageEnum.RUSSIAN),
                                                        trackRequest.getChatId())
                                        );
                            }
                        })
                        .doOnError(WebClientResponseException.NotFound.class,
                                notFound -> trackingInfo.getChatTrackRequest()
                                        .forEach(trackRequest ->
                                                bot.sendUpdateResponseMessage(
                                                        "Order not found",
                                                        trackRequest.getChatId())
                                        )
                        )
                        .doOnError(WebClientResponseException.class,
                                e -> trackingInfo.getChatTrackRequest()
                                        .forEach(trackRequest ->
                                                bot.sendUpdateResponseMessage(
                                                        "An exception occurred during the request, please try later",
                                                        trackRequest.getChatId())
                                        )
                        )
                        .subscribe();
            });

            page = trackingInfoService.findAllForUpdate(pageRequest);
        }
    }
}
