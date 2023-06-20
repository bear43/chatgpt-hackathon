package ru.tinkoff.neurosurge.service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tinkoff.neurosurge.model.ClusterizedText;
import ru.tinkoff.neurosurge.repo.ClusterizedTextRepo;

@Service
@RequiredArgsConstructor
public class ClusterizedTextService {
    private final ClusterizedTextRepo clusterizedTextRepo;

    public Collection<String> findAllTexts(Collection<String> tags) {
        String tagsStr = String.join(" ", tags);
        return clusterizedTextRepo.findAllByTag(tagsStr)
                .stream()
                .distinct()
                .map(ClusterizedText::getText)
                .toList();
    }
}
