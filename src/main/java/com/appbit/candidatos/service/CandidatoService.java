package com.appbit.candidatos.service;

import com.appbit.candidatos.dto.request.CandidatoRequestDTO;
import com.appbit.candidatos.dto.response.CandidatoResponseDTO;

import java.util.List;

public interface CandidatoService {
    CandidatoResponseDTO crearCandidato(CandidatoRequestDTO request);
    List<CandidatoResponseDTO> obtenerTodos();
    CandidatoResponseDTO obtenerPorId(Long id);
}