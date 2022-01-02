package com.belpost.telegram.bot.controller;

import com.belpost.telegram.bot.model.belpost.PostTrackingRequest;
import com.belpost.telegram.bot.service.FeignTrackingService;
import com.belpost.telegram.bot.service.TrackingService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TestClass {

    private final TelegramBot bot;
    private final TrackingService trackingService;
    private final FeignTrackingService feignTrackingService;

    @PostConstruct
    public void init() {
        //var request =
        //        PostTrackingRequest.builder()
        //                .number("LA144130537CN")
        //                .build();
//
        //var response1 = feignTrackingService.getTrackInfo(request);
        //var response2 = trackingService.getTrackInfo(request);

        GetUpdates getUpdates = new GetUpdates().limit(100).offset(0).timeout(0);
        GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
        List<Update> updates = updatesResponse.updates();

//        var list = bot.execute(getUpdates)
//                .updates()
//                .stream()
//                .map(Update::message)
//                .map(message -> {
//                    System.out.println(message.text());
//                    return message;
//                })
//                .collect(Collectors.toList());

        //System.out.println(list);
    }

}
