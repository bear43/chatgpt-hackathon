package ru.tinkoff.neurosurge.dto;

import java.util.Collection;

public record CompletionOutDto(String id, String object, int created,
                               String model, Collection<ChoiceDto> choices,
                               UsageDto usage) {

}
