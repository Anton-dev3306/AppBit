package com.appbit.reportes.controller;

import com.appbit.reportes.dto.request.ReporteRequestDTO;
import com.appbit.reportes.dto.response.ReporteResponseDTO;
import com.appbit.reportes.service.ReporteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
public class ReporteController {

    private final ReporteService reporteService;

    @PostMapping("/esg")
    public ResponseEntity<ReporteResponseDTO> generarReporte(
            @Valid @RequestBody ReporteRequestDTO request) {
        return ResponseEntity.ok(reporteService.generarReporte(request));
    }

    @GetMapping("/esg/{empresaId}")
    public ResponseEntity<List<ReporteResponseDTO>> obtenerPorEmpresa(
            @PathVariable String empresaId) {
        return ResponseEntity.ok(reporteService.obtenerPorEmpresa(empresaId));
    }

    @GetMapping("/esg/{reporteId}/pdf")
    public ResponseEntity<byte[]> exportarPdf(@PathVariable Long reporteId) {
        byte[] pdf = reporteService.exportarPdf(reporteId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=reporte-esg-" + reporteId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @PostMapping("/esg/generar-pdf")
    public ResponseEntity<byte[]> generarYDescargarPdf(
            @Valid @RequestBody ReporteRequestDTO request) {

        byte[] pdf = reporteService.generarYExportarPdf(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=reporte-esg-" + request.getEmpresaId() + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}