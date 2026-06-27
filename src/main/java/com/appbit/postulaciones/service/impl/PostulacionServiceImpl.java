package com.appbit.postulaciones.service.impl;

import com.appbit.candidatos.entity.Candidato;
import com.appbit.candidatos.repository.CandidatoRepository;
import com.appbit.postulaciones.dto.request.PostulacionRequestDTO;
import com.appbit.postulaciones.dto.response.PostulacionResponseDTO;
import com.appbit.postulaciones.entity.Postulacion;
import com.appbit.postulaciones.mapper.PostulacionMapper;
import com.appbit.postulaciones.repository.PostulacionRepository;
import com.appbit.postulaciones.service.PostulacionService;
import com.appbit.shared.exception.AppBitException;
import com.appbit.vacantes.entity.Vacante;
import com.appbit.vacantes.repository.VacanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostulacionServiceImpl implements PostulacionService {

    private final PostulacionRepository postulacionRepository;
    private final CandidatoRepository candidatoRepository;
    private final VacanteRepository vacanteRepository;
    private final PostulacionMapper postulacionMapper;

    @Override
    @Transactional
    public PostulacionResponseDTO postular(PostulacionRequestDTO request) {
        Candidato candidato = candidatoRepository.findById(request.getCandidatoId())
                .orElseThrow(() -> new AppBitException("Candidato no encontrado con ID: " + request.getCandidatoId()));

        Vacante vacante = vacanteRepository.findById(request.getVacanteId())
                .orElseThrow(() -> new AppBitException("Vacante no encontrada con ID: " + request.getVacanteId()));

        if (!vacante.isActiva()) {
            throw new AppBitException("No es posible postularse a una vacante inactiva");
        }

        boolean yaExiste = postulacionRepository.existsByCandidatoIdAndVacanteId(request.getCandidatoId(), request.getVacanteId());
        if (yaExiste) {
            throw new AppBitException("El candidato ya se ha postulado a esta vacante");
        }

        Postulacion postulacion = postulacionMapper.toEntity(request);
        postulacion.setCandidato(candidato);
        postulacion.setVacante(vacante);

        Postulacion saved = postulacionRepository.save(postulacion);
        return postulacionMapper.toResponse(saved);
    }
    @Override
    @Transactional
    public List<PostulacionResponseDTO> postularBatch(List<PostulacionRequestDTO> requests) {
        return requests.stream()
                .map(this::postular)
                .toList();
    }

    @Override
    public List<PostulacionResponseDTO> obtenerTodas() {
        List<Postulacion> postulaciones = postulacionRepository.findAll();
        return postulacionMapper.toResponseList(postulaciones);
    }

    @Override
    public PostulacionResponseDTO obtenerPorId(Long id) {
        Postulacion postulacion = postulacionRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Postulación no encontrada con ID: " + id));
        return postulacionMapper.toResponse(postulacion);
    }

    @Override
    @Transactional
    public PostulacionResponseDTO actualizarEstado(Long id, String estado) {
        Postulacion postulacion = postulacionRepository.findById(id)
                .orElseThrow(() -> new AppBitException("Postulación no encontrada con ID: " + id));

        postulacion.setEstado(estado);
        Postulacion updated = postulacionRepository.save(postulacion);
        return postulacionMapper.toResponse(updated);
    }

    @Override
    public List<PostulacionResponseDTO> obtenerPorCandidato(Long candidatoId) {
        List<Postulacion> postulaciones = postulacionRepository.findByCandidatoId(candidatoId);
        return postulacionMapper.toResponseList(postulaciones);
    }

    @Override
    public List<PostulacionResponseDTO> obtenerPorVacante(Long vacanteId) {
        List<Postulacion> postulaciones = postulacionRepository.findByVacanteId(vacanteId);
        return postulacionMapper.toResponseList(postulaciones);
    }
}
