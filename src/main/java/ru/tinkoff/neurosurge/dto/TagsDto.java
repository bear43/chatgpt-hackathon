package ru.tinkoff.neurosurge.dto;

import java.util.Collection;

public record TagsDto(String text, Collection<String> tags) {

}
