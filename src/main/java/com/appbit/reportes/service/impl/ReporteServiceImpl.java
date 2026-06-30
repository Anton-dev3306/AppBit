package com.appbit.reportes.service.impl;

import com.appbit.matching.entity.MatchResultado;
import com.appbit.matching.repository.MatchResultadoRepository;
import com.appbit.reportes.dto.request.ReporteRequestDTO;
import com.appbit.reportes.dto.response.CandidatoReporteDTO;
import com.appbit.reportes.dto.response.RegionReporteDTO;
import com.appbit.reportes.dto.response.ReporteResponseDTO;
import com.appbit.reportes.entity.ReporteEsg;
import com.appbit.reportes.mapper.ReporteMapper;
import com.appbit.reportes.pdf.PdfGeneratorService;
import com.appbit.reportes.repository.ReporteEsgRepository;
import com.appbit.reportes.service.ReporteService;
import com.appbit.shared.exception.AppBitException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReporteServiceImpl implements ReporteService {

    private final MatchResultadoRepository matchResultadoRepository;
    private final ReporteEsgRepository reporteEsgRepository;
    private final ReporteMapper reporteMapper;
    private final PdfGeneratorService pdfGeneratorService;

    @Override
    @Transactional
    public ReporteResponseDTO generarReporte(ReporteRequestDTO request) {
        List<MatchResultado> resultados = matchResultadoRepository
                .findByEmpresaIdOrderByScoreMatchDesc(request.getEmpresaId());

        if (resultados.isEmpty()) {
            throw new AppBitException(
                    "No hay resultados de matching para la empresa: " + request.getEmpresaId());
        }

        long totalConBadge = resultados.stream()
                .filter(MatchResultado::isBadgeDiversidad)
                .count();

        double porcentaje = (totalConBadge * 100.0) / resultados.size();
        boolean metaCumplida = porcentaje >= request.getMetaDiversidad();

        String diversidadResultado = resultados.stream()
                .map(MatchResultado::getDiversidadResultado)
                .filter(d -> d != null && !d.isBlank())
                .findFirst()
                .orElse("Sin resumen ESG disponible");

        ReporteEsg reporte = new ReporteEsg();
        reporte.setEmpresaId(request.getEmpresaId());
        reporte.setEmpresaNombre("Empresa " + request.getEmpresaId());
        reporte.setTotalAnalizados(resultados.size());
        reporte.setTotalConBadge((int) totalConBadge);
        reporte.setPorcentajeDiversidad(porcentaje);
        reporte.setMetaDiversidad(request.getMetaDiversidad());
        reporte.setMetaCumplida(metaCumplida);
        reporte.setDiversidadResultado(diversidadResultado);
        reporte.setRegionesAnalizadas(
                resultados.stream()
                        .map(MatchResultado::getRegion)
                        .distinct()
                        .collect(Collectors.joining(", "))
        );

        ReporteEsg saved = reporteEsgRepository.save(reporte);

        List<CandidatoReporteDTO> topCandidatos = resultados.stream()
                .limit(10)
                .map(m -> {
                    CandidatoReporteDTO dto = new CandidatoReporteDTO();
                    dto.setNombre(m.getNombre());
                    dto.setScoreMatch(m.getScoreMatch());
                    dto.setBadgeDiversidad(m.isBadgeDiversidad());
                    dto.setRegion(m.getRegion());
                    return dto;
                })
                .toList();

        Map<String, List<MatchResultado>> porRegion = resultados.stream()
                .collect(Collectors.groupingBy(m ->
                        m.getRegion() != null ? m.getRegion() : "Sin región"));

        List<RegionReporteDTO> concentracion = porRegion.entrySet().stream()
                .map(e -> {
                    RegionReporteDTO dto = new RegionReporteDTO();
                    dto.setRegion(e.getKey());
                    dto.setTotalCandidatos(e.getValue().size());
                    dto.setCandidatosConBadge(
                            e.getValue().stream().filter(MatchResultado::isBadgeDiversidad).count());
                    dto.setPorcentajeDiversidad(
                            (dto.getCandidatosConBadge() * 100.0) / dto.getTotalCandidatos());
                    return dto;
                })
                .toList();

        ReporteResponseDTO response = reporteMapper.toResponse(saved);
        response.setTopCandidatos(topCandidatos);
        response.setConcentracionPorRegion(concentracion);

        log.info("Reporte ESG generado para empresa {} — diversidad: {}%",
                request.getEmpresaId(), porcentaje);

        return response;
    }

    @Override
    public List<ReporteResponseDTO> obtenerPorEmpresa(String empresaId) {
        return reporteMapper.toResponseList(
                reporteEsgRepository.findByEmpresaIdOrderByGeneradoEnDesc(empresaId));
    }

    @Override
    public byte[] exportarPdf(Long reporteId)   {
        ReporteEsg reporte = reporteEsgRepository.findById(reporteId)
                .orElseThrow(() -> new AppBitException("Reporte no encontrado con ID: " + reporteId));
        return pdfGeneratorService.generar(reporte);
    }
}