package com.appbit.candidatos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email
    @NotBlank(message = "El email es obligatorio")
    private String email;

    private List<String> skills;
    private Double lat;
    private Double lng;
    private Double latitud;
    private Double longitud;
    private String region;
    private String residencia;
    private String documento;
    private String grupo;
    private String foto;
    private String genero;
    private Integer experienciaAnios;
    private List<String> certificaciones;
}