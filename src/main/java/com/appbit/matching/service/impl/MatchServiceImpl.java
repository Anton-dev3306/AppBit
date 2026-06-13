package com.appbit.matching.service.impl;

import com.appbit.matching.ai.FlowiseClient;
import com.appbit.matching.ai.FlowiseResponseParser;
import com.appbit.matching.ai.PromptBuilder;
import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.appbit.matching.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final PromptBuilder promptBuilder;
    private final FlowiseClient flowiseClient;
    private final FlowiseResponseParser responseParser;

    @Override
    public ShortlistResponseDTO ejecutarMatching(MatchRequestDTO request) throws JsonProcessingException {
        log.info("Iniciando matching — vacante: {} | región: {}",
                request.getTitulo(), request.getRegion());

        String question = promptBuilder.build(request);
        String rawResponse = flowiseClient.ejecutar(question);
        ShortlistResponseDTO result = responseParser.parse(rawResponse);

        log.info("Matching completado — candidatos: {} | diversidad: {}",
                result.getTotalAnalizados(), result.getDiversidadResultado());

        return result;
    }
}