package com.appbit.empresas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private String ruc;
    private String region;
    private String foto;
    private EncargadoResponseDTO encargado;
}