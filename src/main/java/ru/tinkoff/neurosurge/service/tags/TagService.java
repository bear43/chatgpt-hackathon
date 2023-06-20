package ru.tinkoff.neurosurge.service.tags;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.neurosurge.dto.ChoiceDto;
import ru.tinkoff.neurosurge.dto.CompletionInDto;
import ru.tinkoff.neurosurge.dto.CompletionOutDto;
import ru.tinkoff.neurosurge.dto.MessageDto;
import ru.tinkoff.neurosurge.dto.TagsDto;
import ru.tinkoff.neurosurge.service.ChatGPTService;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ChatGPTService chatGPTService;
    private final TagDetailLevel tagDetailLevel = TagDetailLevel.DEFAULT;

    public TagsDto tagsFor(String text) {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) (SecurityContextHolder.getContext()
                .getAuthentication());
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        CompletionOutDto completions = chatGPTService.completions(new CompletionInDto(
                "gpt-3.5-turbo",
                List.of(new MessageDto("user", tagDetailLevel.getString() + text, "user")),
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
                .map(String::trim)
                .map(s -> s.replace(".", ""))
                .map(s -> s.replace("!", ""))
                .map(s -> s.replace("?", ""))
                .map(choice -> new TagsDto(text, Arrays.asList(choice.split(",")))).orElseGet(() -> new TagsDto(text, List.of()));
    }
}
