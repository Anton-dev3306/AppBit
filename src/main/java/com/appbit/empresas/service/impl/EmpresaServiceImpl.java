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
        boolean yaExiste = empresaRepository.findAll().stream()
                .anyMatch(e -> e.getEmail().equalsIgnoreCase(request.getEmail()));
        if (yaExiste) {
            throw new AppBitException("Ya existe una empresa registrada con el email: " + request.getEmail());
        }
        Empresa empresa = empresaMapper.toEntity(request);
        Empresa saved = empresaRepository.save(empresa);
        return empresaMapper.toResponse(saved);
    }

    @Override
    public List<EmpresaResponseDTO> obtenerTodas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresaMapper.toResponseList(empresas);
    }

    @Override
    public EmpresaResponseDTO obtenerPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Empresa no encontrada con ID: " + id));
        return empresaMapper.toResponse(empresa);
    }

    @Override
    @Transactional
    public EmpresaResponseDTO actualizarEmpresa(Long id, EmpresaRequestDTO request) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Empresa no encontrada con ID: " + id));

        boolean emailDuplicado = empresaRepository.findAll().stream()
                .anyMatch(e -> !e.getId().equals(id) && e.getEmail().equalsIgnoreCase(request.getEmail()));
        if (emailDuplicado) {
            throw new AppBitException("El email '" + request.getEmail() + "' ya está registrado por otra empresa.");
        }

        empresa.setNombre(request.getNombre());
        empresa.setEmail(request.getEmail());
        empresa.setRuc(request.getRuc());
        empresa.setRegion(request.getRegion());
        empresa.setFoto(request.getFoto());

        if (request.getEncargado() != null) {
            if (empresa.getEncargado() == null) {
                empresa.setEncargado(empresaMapper.toEntity(request.getEncargado()));
            } else {
                empresa.getEncargado().setNombres(request.getEncargado().getNombres());
                empresa.getEncargado().setCedula(request.getEncargado().getCedula());
                empresa.getEncargado().setRol(request.getEncargado().getRol());
            }
        } else {
            empresa.setEncargado(null);
        }

        Empresa updated = empresaRepository.save(empresa);
        return empresaMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void eliminarEmpresa(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Empresa no encontrada con ID: " + id));
        empresaRepository.delete(empresa);
    }
}
