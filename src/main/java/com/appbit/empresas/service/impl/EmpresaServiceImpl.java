package com.appbit.empresas.service.impl;

import com.appbit.empresas.dto.request.EmpresaRequestDTO;
import com.appbit.empresas.dto.response.EmpresaResponseDTO;
import com.appbit.empresas.entity.Empresa;
import com.appbit.empresas.mapper.EmpresaMapper;
import com.appbit.empresas.repository.EmpresaRepository;
import com.appbit.empresas.service.EmpresaService;
import com.appbit.shared.exception.AppBitException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final EmpresaMapper empresaMapper;

    @Override
    @Transactional
    public EmpresaResponseDTO crearEmpresa(EmpresaRequestDTO request) {
        Empresa empresa = empresaMapper.toEntity(request);
        Empresa saved = empresaRepository.save(empresa);
        return empresaMapper.toResponse(saved);
    }

    @Override
    public List<EmpresaResponseDTO> obtenerTodas() {
        return empresaMapper.toResponseList(empresaRepository.findAll());
    }

    @Override
    public EmpresaResponseDTO obtenerPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Empresa no encontrada con ID: " + id));
        return empresaMapper.toResponse(empresa);
    }
}