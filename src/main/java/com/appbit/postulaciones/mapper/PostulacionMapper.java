package com.appbit.postulaciones.mapper;

import com.appbit.postulaciones.dto.request.PostulacionRequestDTO;
import com.appbit.postulaciones.dto.response.PostulacionResponseDTO;
import com.appbit.postulaciones.entity.Postulacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PostulacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "candidato", ignore = true)
    @Mapping(target = "vacante", ignore = true)
    @Mapping(target = "fechaPostulacion", ignore = true)
    @Mapping(target = "estado", ignore = true)
    Postulacion toEntity(PostulacionRequestDTO request);

    @Mapping(target = "candidatoId", source = "candidato.id")
    @Mapping(target = "candidatoNombre", source = "candidato.nombre")
    @Mapping(target = "candidatoEmail", source = "candidato.email")
    @Mapping(target = "vacanteId", source = "vacante.id")
    @Mapping(target = "vacanteTitulo", source = "vacante.titulo")
    @Mapping(target = "empresaNombre", source = "vacante.empresa.nombre")
    PostulacionResponseDTO toResponse(Postulacion postulacion);

    List<PostulacionResponseDTO> toResponseList(List<Postulacion> postulaciones);
}
