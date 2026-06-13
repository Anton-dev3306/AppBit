package com.appbit.matching.ai;

import com.appbit.matching.dto.request.MatchRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PromptBuilder {

    private final ObjectMapper objectMapper;

    public String build(MatchRequestDTO request) {
        try {
            Map<String, Object> prompt = Map.of(
                    "titulo",            request.getTitulo(),
                    "skills",            request.getSkills(),
                    "nivel",             request.getNivel(),
                    "region",            request.getRegion(),
                    "diversidad_minima", request.getDiversidadMinima() != null
                            ? request.getDiversidadMinima()
                            : "0",
                    "candidatos",        request.getCandidatos()
            );
            return objectMapper.writeValueAsString(prompt);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error construyendo el prompt para Flowise", e);
        }
    }
}