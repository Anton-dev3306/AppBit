package com.appbit.vacantes.service.impl;

import com.appbit.empresas.entity.Empresa;
import com.appbit.empresas.repository.EmpresaRepository;
import com.appbit.shared.exception.AppBitException;
import com.appbit.vacantes.dto.request.VacanteRequestDTO;
import com.appbit.vacantes.dto.response.VacanteResponseDTO;
import com.appbit.vacantes.entity.Vacante;
import com.appbit.vacantes.mapper.VacanteMapper;
import com.appbit.vacantes.repository.VacanteRepository;
import com.appbit.vacantes.service.VacanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VacanteServiceImpl implements VacanteService {

    private final VacanteRepository vacanteRepository;
    private final EmpresaRepository empresaRepository;
    private final VacanteMapper vacanteMapper;

    @Override
    @Transactional
    public VacanteResponseDTO crearVacante(VacanteRequestDTO request) {
        Empresa empresa = empresaRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new AppBitException("Empresa no encontrada con ID: " + request.getEmpresaId()));

        Vacante vacante = vacanteMapper.toEntity(request);
        vacante.setEmpresa(empresa);

        Vacante saved = vacanteRepository.save(vacante);
        return vacanteMapper.toResponse(saved);
    }

    @Override
    public List<VacanteResponseDTO> obtenerTodas() {
        List<Vacante> vacantes = vacanteRepository.findAll();
        return vacanteMapper.toResponseList(vacantes);
    }

    @Override
    public List<VacanteResponseDTO> obtenerActivas() {
        List<Vacante> vacantes = vacanteRepository.findByActivaTrue();
        return vacanteMapper.toResponseList(vacantes);
    }

    @Override
    public VacanteResponseDTO obtenerPorId(Long id) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Vacante no encontrada con ID: " + id));
        return vacanteMapper.toResponse(vacante);
    }

    @Override
    @Transactional
    public VacanteResponseDTO actualizarVacante(Long id, VacanteRequestDTO request) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Vacante no encontrada con ID: " + id));

        Empresa empresa = empresaRepository.findById(request.getEmpresaId())
                .orElseThrow(() -> new AppBitException("Empresa no encontrada con ID: " + request.getEmpresaId()));

        vacante.setTitulo(request.getTitulo());
        vacante.setDescripcion(request.getDescripcion());
        vacante.setArea(request.getArea());
        vacante.setNivel(request.getNivel());
        vacante.setRegion(request.getRegion());
        vacante.setModalidad(request.getModalidad());
        vacante.setSalario(request.getSalario());
        vacante.setEmpresa(empresa);
        vacante.setSkills(vacanteMapper.stringsToSkills(request.getSkills()));

        Vacante updated = vacanteRepository.save(vacante);
        return vacanteMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void desactivarVacante(Long id) {
        Vacante vacante = vacanteRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Vacante no encontrada con ID: " + id));
        vacante.setActiva(false);
        vacanteRepository.save(vacante);
    }

    @Override
    public List<VacanteResponseDTO> obtenerPorEmpresa(Long empresaId) {
        List<Vacante> vacantes = vacanteRepository.findByEmpresaId(empresaId);
        return vacanteMapper.toResponseList(vacantes);
    }
}
