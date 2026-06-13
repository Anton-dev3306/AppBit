package com.appbit.matching.ai;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlowiseClient {

    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;
    @Value("${flowise.url}")
    private String flowiseUrl;

    @Value("${flowise.chatflow-id}")
    private String chatflowId;

    @Value("${flowise.api-key}")
    private String apiKey;

    public String ejecutar(String question) throws JsonProcessingException {
        System.out.println("=== QUESTION ENVIADA A FLOWISE ===");
        System.out.println(question);
        System.out.println("==================================");

        String body = "{\"question\":" + objectMapper.writeValueAsString(question) + "}";

        return webClientBuilder.build()
                .post()
                .uri(flowiseUrl + "/api/v1/prediction/" + chatflowId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .bodyValue(body)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        response.bodyToMono(String.class).map(errorBody -> {
                            System.out.println("=== ERROR FLOWISE ===");
                            System.out.println(errorBody);
                            System.out.println("====================");
                            return new RuntimeException(errorBody);
                        })
                )
                .bodyToMono(String.class)
                .block();
    }


}