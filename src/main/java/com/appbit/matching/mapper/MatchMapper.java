package com.appbit.matching.mapper;

import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.MatchResultDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.appbit.matching.entity.MatchResultado;
import com.appbit.vacantes.entity.Vacante;
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
    @Mapping(target = "empresaId", source = "vacante.empresa.id")
    @Mapping(target = "titulo", source = "vacante.titulo")
    @Mapping(target = "region", source = "vacante.region")
    @Mapping(target = "totalAnalizados", ignore = true)
    @Mapping(target = "diversidadResultado", ignore = true)
    MatchResultado toEntity(MatchResultDTO candidato, Vacante vacante);

    default List<MatchResultado> toEntityList(ShortlistResponseDTO shortlist, Vacante vacante) {
        return shortlist.getCandidatos().stream()
                .map(c -> {
                    MatchResultado m = toEntity(c, vacante);
                    m.setTotalAnalizados(shortlist.getTotalAnalizados());
                    m.setDiversidadResultado(shortlist.getDiversidadResultado());
                    return m;
                })
                .toList();
    }
}
