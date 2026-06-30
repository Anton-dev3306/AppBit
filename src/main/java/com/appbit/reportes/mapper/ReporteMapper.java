package com.appbit.reportes.mapper;

import com.appbit.reportes.dto.response.CandidatoReporteDTO;
import com.appbit.reportes.dto.response.ReporteResponseDTO;
import com.appbit.reportes.entity.ReporteEsg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReporteMapper {

    @Mapping(target = "topCandidatos", ignore = true)
    @Mapping(target = "concentracionPorRegion", ignore = true)
    ReporteResponseDTO toResponse(ReporteEsg reporte);

    List<ReporteResponseDTO> toResponseList(List<ReporteEsg> reportes);
}