package ru.tinkoff.neurosurge.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.tinkoff.neurosurge.dto.ChatGPTConfig;
import ru.tinkoff.neurosurge.dto.CompletionInDto;
import ru.tinkoff.neurosurge.dto.CompletionOutDto;

@Service
@RequiredArgsConstructor
public class ChatGPTService {

    private final ChatGPTConfig chatGPTConfig;

    public CompletionOutDto completions(CompletionInDto completionInDto) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) (SecurityContextHolder.getContext()
                .getAuthentication());
        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        httpHeaders.setBearerAuth(principal.getIdToken().getTokenValue());
        return restTemplate.exchange(chatGPTConfig.baseUrl() + "/public/v1/chat/completions",
                HttpMethod.POST, new HttpEntity<>(completionInDto, httpHeaders), CompletionOutDto.class)
                .getBody();
    }
}
