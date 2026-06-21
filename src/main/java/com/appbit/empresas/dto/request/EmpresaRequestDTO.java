package com.appbit.empresas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email
    @NotBlank(message = "El email es obligatorio")
    private String email;
}