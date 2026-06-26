package com.appbit.postulaciones.controller;

import com.appbit.postulaciones.dto.request.PostulacionRequestDTO;
import com.appbit.postulaciones.dto.response.PostulacionResponseDTO;
import com.appbit.postulaciones.service.PostulacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/postulaciones")
@RequiredArgsConstructor
public class PostulacionController {

    private final PostulacionService postulacionService;

    @PostMapping
    public ResponseEntity<PostulacionResponseDTO> postular(@Valid @RequestBody PostulacionRequestDTO request) {
        PostulacionResponseDTO response = postulacionService.postular(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/batch")
    public ResponseEntity<List<PostulacionResponseDTO>> postularBatch(
            @Valid @RequestBody List<PostulacionRequestDTO> requests) {
        return new ResponseEntity<>(postulacionService.postularBatch(requests), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<PostulacionResponseDTO>> obtenerTodas() {
        return ResponseEntity.ok(postulacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostulacionResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(postulacionService.obtenerPorId(id));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PostulacionResponseDTO> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(postulacionService.actualizarEstado(id, estado));
    }

    @GetMapping("/candidato/{candidatoId}")
    public ResponseEntity<List<PostulacionResponseDTO>> obtenerPorCandidato(@PathVariable Long candidatoId) {
        return ResponseEntity.ok(postulacionService.obtenerPorCandidato(candidatoId));
    }

    @GetMapping("/vacante/{vacanteId}")
    public ResponseEntity<List<PostulacionResponseDTO>> obtenerPorVacante(@PathVariable Long vacanteId) {
        return ResponseEntity.ok(postulacionService.obtenerPorVacante(vacanteId));
    }
}
