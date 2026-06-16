package com.appbit.matching.service.impl;

import com.appbit.matching.ai.FlowiseClient;
import com.appbit.matching.ai.FlowiseResponseParser;
import com.appbit.matching.ai.PromptBuilder;
import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.DashboardResponseDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.appbit.matching.entity.MatchResultado;
import com.appbit.matching.mapper.MatchMapper;
import com.appbit.matching.repository.MatchResultadoRepository;
import com.appbit.matching.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final PromptBuilder promptBuilder;
    private final FlowiseClient flowiseClient;
    private final FlowiseResponseParser responseParser;
    private final MatchMapper matchMapper;
    private final MatchResultadoRepository matchResultadoRepository;

    @Override
    public ShortlistResponseDTO ejecutarMatching(MatchRequestDTO request) throws JsonProcessingException {
        log.info("Iniciando matching — vacante: {} | región: {}", request.getTitulo(), request.getRegion());

        String question = promptBuilder.build(request);
        String rawResponse = flowiseClient.ejecutar(question);
        ShortlistResponseDTO result = responseParser.parse(rawResponse);

        // Persistir resultados
        List<MatchResultado> resultados = matchMapper.toEntityList(result, request);
        matchResultadoRepository.saveAll(resultados);

        log.info("Matching completado — candidatos: {} | diversidad: {}",
                result.getTotalAnalizados(), result.getDiversidadResultado());

        return result;
    }

    @Override
    public DashboardResponseDTO getDashboard(String empresaId) {
        List<MatchResultado> top10 = matchResultadoRepository.findTop10ByEmpresaId(empresaId);

        List<DashboardResponseDTO.MapaTalentoDTO> mapa = top10.stream().map(m -> {
            DashboardResponseDTO.MapaTalentoDTO dto = new DashboardResponseDTO.MapaTalentoDTO();
            dto.setNombre(m.getNombre());
            dto.setLat(m.getLat());
            dto.setLng(m.getLng());
            dto.setScoreMatch(m.getScoreMatch());
            dto.setBadgeDiversidad(m.isBadgeDiversidad());
            return dto;
        }).toList();

        long totalConBadge = top10.stream().filter(MatchResultado::isBadgeDiversidad).count();
        double porcentaje = top10.isEmpty() ? 0 : (totalConBadge * 100.0) / top10.size();

        DashboardResponseDTO.MetasEsgDTO esg = new DashboardResponseDTO.MetasEsgDTO();
        esg.setTotalAnalizados(top10.size());
        esg.setTotalConBadge(totalConBadge);
        esg.setPorcentajeDiversidad(porcentaje);
        top10.stream().findFirst()
                .ifPresent(m -> esg.setUltimoDiversidadResultado(m.getDiversidadResultado()));

        DashboardResponseDTO dashboard = new DashboardResponseDTO();
        dashboard.setTop10Candidatos(top10);
        dashboard.setMapaTalentos(mapa);
        dashboard.setMetasEsg(esg);

        return dashboard;
    }
}