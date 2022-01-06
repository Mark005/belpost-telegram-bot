package com.belpost.telegram.bot.service;

import com.belpost.telegram.bot.BelpostBot;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class StatusScheduler {

    private final BelpostBot belpostBot;

    @Scheduled(timeUnit = TimeUnit.SECONDS, fixedDelay = 10)
    public void updateStatus() {

        //belpostBot.execute()
        System.out.println(LocalDateTime.now());
    }
}
