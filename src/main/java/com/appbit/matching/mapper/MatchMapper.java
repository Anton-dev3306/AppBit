package com.appbit.matching.mapper;

import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.MatchResultDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.appbit.matching.entity.MatchResultado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    @Mapping(target = "candidatoId", source = "candidato.candidatoId")
    @Mapping(target = "nombre", source = "candidato.nombre")
    @Mapping(target = "scoreMatch", source = "candidato.scoreMatch")
    @Mapping(target = "badgeDiversidad", source = "candidato.badgeDiversidad")
    @Mapping(target = "lat", source = "candidato.lat")
    @Mapping(target = "lng", source = "candidato.lng")
    @Mapping(target = "skills", source = "candidato.skills")
    @Mapping(target = "empresaId", source = "request.empresaId")
    @Mapping(target = "titulo", source = "request.titulo")
    @Mapping(target = "region", source = "request.region")
    @Mapping(target = "totalAnalizados", source = "shortlist.totalAnalizados")
    @Mapping(target = "diversidadResultado", source = "shortlist.diversidadResultado")
    MatchResultado toEntity(MatchResultDTO candidato,
                            MatchRequestDTO request,
                            ShortlistResponseDTO shortlist);

    default List<MatchResultado> toEntityList(ShortlistResponseDTO shortlist,
                                              MatchRequestDTO request) {
        return shortlist.getCandidatos().stream()
                .map(c -> toEntity(c, request, shortlist))
                .toList();
    }
}