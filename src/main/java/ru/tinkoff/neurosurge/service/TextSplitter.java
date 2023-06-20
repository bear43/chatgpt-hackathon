package ru.tinkoff.neurosurge.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TextSplitter {

    private final int MAX_CHARS = 4000;

    public Collection<String> split(Collection<String> texts) {
        return texts.stream()
                .map(this::split)
                .flatMap(Collection::stream)
                .toList();
    }


    private Collection<String> split(String text) {
        int len = text.length();
        int parts = (len / MAX_CHARS) + ((len % MAX_CHARS) > 0 ? 1 : 0);
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < parts; i++) {
            int beg = i * MAX_CHARS;
            String substring = text.substring(beg,
                    Math.min(beg + MAX_CHARS, beg + (len - beg)));
            strings.add(substring);
        }
        return strings;
    }

}
