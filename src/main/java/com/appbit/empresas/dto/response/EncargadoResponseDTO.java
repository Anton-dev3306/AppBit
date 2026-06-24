package com.appbit.empresas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoResponseDTO {
    private Long id;
    private String nombres;
    private String cedula;
    private String rol;
}
