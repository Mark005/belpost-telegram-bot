package com.belpost.telegram.bot.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                //.registerModules(new JavaTimeModule(), new Jdk8Module())
                //.setSerializationInclusion(JsonInclude.Include.NON_NULL)
                //.setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                //.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                //.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                //.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        ;
    }
}
