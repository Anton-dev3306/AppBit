package com.appbit.candidatos.service.impl;

import com.appbit.candidatos.dto.request.CandidatoRequestDTO;
import com.appbit.candidatos.dto.response.CandidatoResponseDTO;
import com.appbit.candidatos.entity.Candidato;
import com.appbit.candidatos.mapper.CandidatoMapper;
import com.appbit.candidatos.repository.CandidatoRepository;
import com.appbit.candidatos.service.CandidatoService;
import com.appbit.shared.exception.AppBitException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidatoServiceImpl implements CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final CandidatoMapper candidatoMapper;

    @Override
    @Transactional
    public CandidatoResponseDTO crearCandidato(CandidatoRequestDTO request) {
        Candidato candidato = candidatoMapper.toEntity(request);
        Candidato saved = candidatoRepository.save(candidato);
        return candidatoMapper.toResponse(saved);
    }
    @Override
    @Transactional
    public List<CandidatoResponseDTO> crearCandidatosBatch(List<CandidatoRequestDTO> requests) {
        List<Candidato> candidatos = requests.stream()
                .map(candidatoMapper::toEntity)
                .toList();

        List<Candidato> saved = candidatoRepository.saveAll(candidatos);
        return candidatoMapper.toResponseList(saved);
    }
    @Override
    public List<CandidatoResponseDTO> obtenerTodos() {
        return candidatoMapper.toResponseList(candidatoRepository.findAll());
    }

    @Override
    public CandidatoResponseDTO obtenerPorId(Long id) {
        Candidato candidato = candidatoRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Candidato no encontrado con ID: " + id));
        return candidatoMapper.toResponse(candidato);
    }
}