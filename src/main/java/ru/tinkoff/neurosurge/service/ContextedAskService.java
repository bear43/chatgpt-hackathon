package ru.tinkoff.neurosurge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import ru.tinkoff.neurosurge.dto.ChoiceDto;
import ru.tinkoff.neurosurge.dto.CompletionInDto;
import ru.tinkoff.neurosurge.dto.CompletionOutDto;
import ru.tinkoff.neurosurge.dto.MessageDto;
import ru.tinkoff.neurosurge.dto.TagsDto;

@Service
@RequiredArgsConstructor
public class ContextedAskService {

    private static final String NO_ANSWER_PROVIDED = "Не удалось получить ответ. Уточните вопрос";
    private final ChatGPTService chatGPTService;

    public String ask(String text, Collection<String> texts) {
        if (texts.isEmpty()) {
            return NO_ANSWER_PROVIDED;
        }
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) (SecurityContextHolder.getContext()
                .getAuthentication());
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        List<MessageDto> messages = new ArrayList<>(texts.stream()
                .map(content -> new MessageDto("user", content, "user"))
                .toList());
        messages.add(new MessageDto("user", "Ответь на вопрос с учётом информации, предоставленной ранее: " + text, "user"));
        CompletionOutDto completions = chatGPTService.completions(new CompletionInDto(
                "gpt-4",
                messages,
                null,
                0,
                null,
                null,
                List.of(),
                null,
                null,
                null,
                principal.getClaim("sid")
        ));
        return completions.choices()
                .stream()
                .findFirst()
                .map(ChoiceDto::message)
                .map(MessageDto::content)
                .orElse(NO_ANSWER_PROVIDED);
    }
}
