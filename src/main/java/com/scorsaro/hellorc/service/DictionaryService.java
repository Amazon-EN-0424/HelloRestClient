package com.scorsaro.hellorc.service;

import com.scorsaro.hellorc.model.WordDefinition;
import com.scorsaro.hellorc.exception.DictionaryApiException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DictionaryService {

    private final RestClient restClient;

    public DictionaryService(RestClient.Builder builder, @Value("${dictionary.api.base-url}") String baseUrl) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    public List<WordDefinition> getWordDefinition(String word) {
        return restClient.get()
                .uri("/entries/en/{word}", word)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new DictionaryApiException("Word not found", HttpStatus.NOT_FOUND);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    throw new DictionaryApiException("Dictionary API is currently unavailable", HttpStatus.SERVICE_UNAVAILABLE);
                })
                .body(new ParameterizedTypeReference<List<WordDefinition>>() {});
    }

    public String getApiStatus() {
        return restClient.get()
                .uri("/")
                .retrieve()
                .toBodilessEntity()
                .getStatusCode()
                .is2xxSuccessful() ? "UP" : "DOWN";
    }
}