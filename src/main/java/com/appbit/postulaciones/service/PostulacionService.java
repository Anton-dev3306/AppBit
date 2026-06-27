package com.appbit.postulaciones.service;

import com.appbit.postulaciones.dto.request.PostulacionRequestDTO;
import com.appbit.postulaciones.dto.response.PostulacionResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PostulacionService {
    PostulacionResponseDTO postular(PostulacionRequestDTO request);
    List<PostulacionResponseDTO> postularBatch(List<PostulacionRequestDTO> requests);
    List<PostulacionResponseDTO> obtenerTodas();
    PostulacionResponseDTO obtenerPorId(Long id);
    PostulacionResponseDTO actualizarEstado(Long id, String estado);
    List<PostulacionResponseDTO> obtenerPorCandidato(Long candidatoId);
    List<PostulacionResponseDTO> obtenerPorVacante(Long vacanteId);
}
