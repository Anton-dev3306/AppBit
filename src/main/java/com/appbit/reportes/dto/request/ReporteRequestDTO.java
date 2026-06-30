package com.appbit.reportes.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReporteRequestDTO {

    @NotBlank(message = "El ID de la empresa es obligatorio")
    private String empresaId;

    private double metaDiversidad = 30.0;
}