package com.belpost.telegram.bot.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "client.belpost")
public class BelpostClientProperties {
    private String baseUrl;
}
