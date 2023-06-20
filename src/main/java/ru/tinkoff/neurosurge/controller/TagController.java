package ru.tinkoff.neurosurge.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.neurosurge.dto.TagsDto;
import ru.tinkoff.neurosurge.service.tags.TagService;

@RestController
@RequestMapping(value = "input/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public TagsDto tags(@RequestParam String text) {
        return tagService.tagsFor(text);
    }
}
