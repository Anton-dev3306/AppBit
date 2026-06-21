package com.appbit.vacantes.controller;

import com.appbit.vacantes.dto.request.VacanteRequestDTO;
import com.appbit.vacantes.dto.response.VacanteResponseDTO;
import com.appbit.vacantes.service.VacanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/vacantes")
@RequiredArgsConstructor
public class VacanteController {

    private final VacanteService vacanteService;

    @PostMapping
    public ResponseEntity<VacanteResponseDTO> crearVacante(@Valid @RequestBody VacanteRequestDTO request) {
        VacanteResponseDTO response = vacanteService.crearVacante(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VacanteResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(vacanteService.obtenerTodas());
    }

    @GetMapping("/activas")
    public ResponseEntity<List<VacanteResponseDTO>> obtenerActivas() {
        return ResponseEntity.ok(vacanteService.obtenerActivas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacanteResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vacanteService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacanteResponseDTO> actualizarVacante(
            @PathVariable Long id,
            @Valid @RequestBody VacanteRequestDTO request) {
        return ResponseEntity.ok(vacanteService.actualizarVacante(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarVacante(@PathVariable Long id) {
        vacanteService.desactivarVacante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<VacanteResponseDTO>> obtenerPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(vacanteService.obtenerPorEmpresa(empresaId));
    }
}
