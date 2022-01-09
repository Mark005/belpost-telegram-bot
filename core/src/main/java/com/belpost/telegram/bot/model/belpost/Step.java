package com.belpost.telegram.bot.model.belpost;

import com.belpost.telegram.bot.deserializer.LocalDateTimeFromTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.time.LocalDateTime;


@Data
public class Step {

    private String system;
    @JsonDeserialize(using = LocalDateTimeFromTimestampDeserializer.class)
    private LocalDateTime timestamp;
    private String created_at;
    private String place;
    private String event;
    private Boolean is_border;
    private String border_link;
    private String place_index;
}
