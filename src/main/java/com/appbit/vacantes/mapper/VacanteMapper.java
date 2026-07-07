package com.appbit.vacantes.mapper;

import com.appbit.vacantes.dto.request.VacanteRequestDTO;
import com.appbit.vacantes.dto.response.VacanteResponseDTO;
import com.appbit.vacantes.entity.SkillVacante;
import com.appbit.vacantes.entity.Vacante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface VacanteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "activa", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "empresa", ignore = true)
    @Mapping(target = "skills", ignore = true)
    @Mapping(target = "skillsTecnicas", source = "skillsTecnicas")
    @Mapping(target = "skillsBlandas", source = "skillsBlandas")
    Vacante toEntity(VacanteRequestDTO request);

    @Mapping(target = "empresaId", source = "empresa.id")
    @Mapping(target = "empresaNombre", source = "empresa.nombre")
    @Mapping(target = "skills", source = "skills")
    @Mapping(target = "skillsTecnicas", source = "skillsTecnicas")
    @Mapping(target = "skillsBlandas", source = "skillsBlandas")
    VacanteResponseDTO toResponse(Vacante vacante);

    List<VacanteResponseDTO> toResponseList(List<Vacante> vacantes);

    default List<SkillVacante> stringsToSkills(List<String> skills) {
        if (skills == null) return null;
        return skills.stream()
                .map(SkillVacante::new)
                .toList();
    }

    default List<String> skillsToStrings(List<SkillVacante> skills) {
        if (skills == null) return null;
        return skills.stream()
                .map(SkillVacante::getNombre)
                .toList();
    }
}
