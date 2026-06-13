package com.appbit.matching.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class ShortlistResponseDTO {

    private List<MatchResultDTO> candidatos;

    @JsonProperty("total_analizados")
    private int totalAnalizados;

    @JsonProperty("diversidad_resultado")
    private String diversidadResultado;
}