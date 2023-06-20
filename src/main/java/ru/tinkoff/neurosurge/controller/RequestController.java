package ru.tinkoff.neurosurge.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tinkoff.neurosurge.dto.TagsDto;
import ru.tinkoff.neurosurge.service.ClusterizedTextService;
import ru.tinkoff.neurosurge.service.ContextedAskService;
import ru.tinkoff.neurosurge.service.tags.TagService;
import ru.tinkoff.neurosurge.service.TextSplitter;

@Controller
@RequestMapping("/pages")
@RequiredArgsConstructor
@Slf4j
public class RequestController {
    private final TagService tagService;
    private final ClusterizedTextService clusterizedTextService;
    private final ContextedAskService contextedAskService;
    private final TextSplitter textSplitter;

    @GetMapping("/input")
    public String input(Model model) {
        return "input";
    }

    @PostMapping("/input")
    public String tags(@RequestParam String text, Model model) {
        TagsDto tagsDto = tagService.tagsFor(text);
        Collection<String> tags = tagsDto.tags();
        log.info("Found tags for user request: {}", tags);
        Collection<String> texts = clusterizedTextService.findAllTexts(tags);
        log.info("Found {} texts for requested tags", texts.size());
        List<String> strings = new ArrayList<>();
        Collection<String> limitedTexts = textSplitter.split(texts);
        log.info("After splitting there is {} texts", limitedTexts.size());
        Integer totalChars = limitedTexts.stream()
                .map(String::length)
                .reduce(Integer::sum)
                .orElse(0);
        log.info("Total chars in all texts: {}", totalChars);
        if (totalChars > 10000) {
            List<String> slice = Collections.singletonList(limitedTexts.stream()
                    .reduce(String::concat)
                    .orElse("")
                    .substring(0, 10000));
            strings.addAll(textSplitter.split(slice));
        } else {
            strings.addAll(limitedTexts);
        }
        String answer = contextedAskService.ask(text, strings);
        model.addAttribute("answer", answer);
        model.addAttribute("question", text);
        return "input";
    }
}
