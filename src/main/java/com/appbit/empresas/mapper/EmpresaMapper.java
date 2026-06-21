package com.appbit.empresas.mapper;

import com.appbit.empresas.dto.request.EmpresaRequestDTO;
import com.appbit.empresas.dto.response.EmpresaResponseDTO;
import com.appbit.empresas.entity.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    @Mapping(target = "id", ignore = true)
    Empresa toEntity(EmpresaRequestDTO request);

    EmpresaResponseDTO toResponse(Empresa empresa);

    List<EmpresaResponseDTO> toResponseList(List<Empresa> empresas);
}