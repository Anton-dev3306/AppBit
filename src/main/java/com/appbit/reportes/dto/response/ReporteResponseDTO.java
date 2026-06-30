package com.appbit.reportes.dto.response;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReporteResponseDTO {

    private Long id;
    private String empresaId;
    private String empresaNombre;
    private int totalAnalizados;
    private int totalConBadge;
    private double porcentajeDiversidad;
    private double metaDiversidad;
    private boolean metaCumplida;
    private String diversidadResultado;
    private List<CandidatoReporteDTO> topCandidatos;
    private List<RegionReporteDTO> concentracionPorRegion;
    private LocalDateTime generadoEn;
}