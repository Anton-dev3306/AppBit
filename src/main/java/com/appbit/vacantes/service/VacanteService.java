package com.appbit.vacantes.service;

import com.appbit.vacantes.dto.request.VacanteRequestDTO;
import com.appbit.vacantes.dto.response.VacanteResponseDTO;
import java.util.List;

public interface VacanteService {
    VacanteResponseDTO crearVacante(VacanteRequestDTO request);
    List<VacanteResponseDTO> obtenerTodas();
    List<VacanteResponseDTO> obtenerActivas();
    VacanteResponseDTO obtenerPorId(Long id);
    VacanteResponseDTO actualizarVacante(Long id, VacanteRequestDTO request);
    void desactivarVacante(Long id);
    List<VacanteResponseDTO> obtenerPorEmpresa(Long empresaId);
}
