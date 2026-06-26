package com.appbit.matching.ai;

import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FlowiseResponseParser {

    private final ObjectMapper objectMapper;

    public ShortlistResponseDTO parse(String rawResponse) {
        try {
            JsonNode root = objectMapper.readTree(rawResponse);
            String text = root.get("text").asText();

            ShortlistResponseDTO result = objectMapper.readValue(text, ShortlistResponseDTO.class);

            result.getCandidatos().sort(
                    Comparator.comparingInt(MatchResultDTO::getScoreMatch).reversed()
            );

            return result;        } catch (Exception e) {
            log.error("Error parseando respuesta de Flowise: {}", e.getMessage());
            log.debug("Raw response: {}", rawResponse);
            throw new RuntimeException("Error procesando respuesta del agente ESG", e);
        }
    }
}