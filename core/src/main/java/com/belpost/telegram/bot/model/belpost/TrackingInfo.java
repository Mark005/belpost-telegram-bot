package com.belpost.telegram.bot.model.belpost;

import lombok.Data;

import java.util.List;

@Data
public class TrackingInfo {
    private String number;
    private List<Step> steps;
}
