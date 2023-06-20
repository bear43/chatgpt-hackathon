package ru.tinkoff.neurosurge.dto;

import java.util.Collection;

public record CompletionInDto(String model, Collection<MessageDto> messages,
                              Integer maxTokens, Integer temperature,
                              Integer topP, Integer n, Collection<String> stop,
                              Integer presencePenalty, Integer frequencyPenalty,
                              LogitBiasDto logitBiasDto, String user) {

}
