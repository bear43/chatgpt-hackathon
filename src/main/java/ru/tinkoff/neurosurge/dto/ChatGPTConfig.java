package ru.tinkoff.neurosurge.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "chat-gpt")
public record ChatGPTConfig(String baseUrl) {

}
