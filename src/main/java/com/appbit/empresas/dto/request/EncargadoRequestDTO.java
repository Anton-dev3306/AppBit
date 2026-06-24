package com.appbit.empresas.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncargadoRequestDTO {

    @NotBlank(message = "El nombre del encargado es obligatorio")
    private String nombres;

    @NotBlank(message = "La cédula del encargado es obligatoria")
    private String cedula;

    private String rol;
}
