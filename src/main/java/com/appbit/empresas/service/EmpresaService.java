package com.appbit.empresas.service;

import com.appbit.empresas.dto.request.EmpresaRequestDTO;
import com.appbit.empresas.dto.response.EmpresaResponseDTO;
import java.util.List;

public interface EmpresaService {
    EmpresaResponseDTO crearEmpresa(EmpresaRequestDTO request);
    List<EmpresaResponseDTO> obtenerTodas();
    EmpresaResponseDTO obtenerPorId(Long id);
    EmpresaResponseDTO actualizarEmpresa(Long id, EmpresaRequestDTO request);
    void eliminarEmpresa(Long id);
}
