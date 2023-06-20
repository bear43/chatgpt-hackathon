package ru.tinkoff.neurosurge.dto;

public record UsageDto(int promptTokens, int completionTokens, int totalTokens) {

}
