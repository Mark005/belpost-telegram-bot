package com.belpost.telegram.bot.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class LocalDateTimeFromTimestampDeserializer extends LocalDateTimeDeserializer {

    @Override
    public LocalDateTime deserialize(JsonParser parser, DeserializationContext context) throws IOException {
        if (parser.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            Instant instant = Instant.ofEpochMilli(parser.getValueAsLong());
            return LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        }

        return super.deserialize(parser, context);
    }
}
