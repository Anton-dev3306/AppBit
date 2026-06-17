package com.appbit.postulaciones.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionRequestDTO {

    @NotNull(message = "El ID del candidato es obligatorio")
    private Long candidatoId;

    @NotNull(message = "El ID de la vacante es obligatorio")
    private Long vacanteId;

    private String cvUrl;

    private String comentarios;
}
