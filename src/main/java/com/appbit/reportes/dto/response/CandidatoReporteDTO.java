package com.appbit.reportes.dto.response;

import lombok.Data;

@Data
public class CandidatoReporteDTO {
    private String nombre;
    private int scoreMatch;
    private boolean badgeDiversidad;
    private String region;
}