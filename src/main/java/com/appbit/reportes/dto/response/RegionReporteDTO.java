package com.appbit.reportes.dto.response;

import lombok.Data;

@Data
public class RegionReporteDTO {
    private String region;
    private int totalCandidatos;
    private long candidatosConBadge;
    private double porcentajeDiversidad;
}