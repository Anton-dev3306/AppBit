package com.appbit.matching.ai;

import com.appbit.postulaciones.entity.Postulacion;
import com.appbit.vacantes.entity.SkillVacante;
import com.appbit.vacantes.entity.Vacante;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PromptBuilder {

    private final ObjectMapper objectMapper;

    public String build(Vacante vacante, List<Postulacion> postulaciones, String diversidadMinima) {
        try {
            List<Map<String, Object>> candidatos = postulaciones.stream()
                    .map(p -> {
                        var candidato = p.getCandidato();
                        return Map.<String, Object>of(
                                "candidatoId", candidato.getId().toString(),
                                "nombre", candidato.getNombre(),
                                "skills", candidato.getSkills() != null ? candidato.getSkills() : List.of(),
                                "lat", candidato.getLat() != null ? candidato.getLat() : 0.0,
                                "lng", candidato.getLng() != null ? candidato.getLng() : 0.0,
                                "genero", candidato.getGenero(),
                                "experienciaAnios", candidato.getExperienciaAnios()
                        );
                    })
                    .collect(Collectors.toList());

            Map<String, Object> prompt = Map.of(
                    "titulo", vacante.getTitulo(),
                    "skills", vacante.getSkills().stream()
                            .map(SkillVacante::getNombre)
                            .collect(Collectors.joining(", ")),
                    "nivel", vacante.getNivel(),
                    "region", vacante.getRegion(),
                    "diversidad_minima", diversidadMinima,
                    "candidatos", candidatos
            );

            return objectMapper.writeValueAsString(prompt);
        } catch (Exception e) {
            throw new RuntimeException("Error construyendo el prompt para Flowise", e);
        }
    }
}