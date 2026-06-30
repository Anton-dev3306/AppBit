package com.appbit.reportes.service;

import com.appbit.reportes.dto.request.ReporteRequestDTO;
import com.appbit.reportes.dto.response.ReporteResponseDTO;

import java.util.List;

public interface ReporteService {
    ReporteResponseDTO generarReporte(ReporteRequestDTO request);
    List<ReporteResponseDTO> obtenerPorEmpresa(String empresaId);
    byte[] exportarPdf(Long reporteId);
}