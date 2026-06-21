package com.appbit.matching.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MatchRequestDTO {

    @NotNull(message = "El ID de la vacante es obligatorio")
    private Long vacanteId;

    private String diversidadMinima = "0";
}