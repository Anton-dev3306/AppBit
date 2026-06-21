package com.appbit.candidatos.mapper;

import com.appbit.candidatos.dto.request.CandidatoRequestDTO;
import com.appbit.candidatos.dto.response.CandidatoResponseDTO;
import com.appbit.candidatos.entity.Candidato;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidatoMapper {

    @Mapping(target = "id", ignore = true)
    Candidato toEntity(CandidatoRequestDTO request);

    CandidatoResponseDTO toResponse(Candidato candidato);

    List<CandidatoResponseDTO> toResponseList(List<Candidato> candidatos);
}