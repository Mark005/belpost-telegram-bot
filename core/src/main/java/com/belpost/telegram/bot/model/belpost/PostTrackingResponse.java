package com.belpost.telegram.bot.model.belpost;

import lombok.Data;

import java.util.List;

@Data
public class PostTrackingResponse {
    private List<TrackingInfoDto> data;
    private String uuid;
}
