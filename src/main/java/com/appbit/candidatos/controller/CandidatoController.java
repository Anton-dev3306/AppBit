package com.appbit.candidatos.controller;

import com.appbit.candidatos.dto.request.CandidatoRequestDTO;
import com.appbit.candidatos.dto.response.CandidatoResponseDTO;
import com.appbit.candidatos.service.CandidatoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatos")
@RequiredArgsConstructor
public class CandidatoController {

    private final CandidatoService candidatoService;

    @PostMapping
    public ResponseEntity<CandidatoResponseDTO> crear(@Valid @RequestBody CandidatoRequestDTO request) {
        return new ResponseEntity<>(candidatoService.crearCandidato(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CandidatoResponseDTO>> obtenerTodos() {
        return ResponseEntity.ok(candidatoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidatoResponseDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(candidatoService.obtenerPorId(id));
    }
}