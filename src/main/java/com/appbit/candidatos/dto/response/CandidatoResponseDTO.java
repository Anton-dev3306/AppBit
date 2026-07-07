package com.appbit.candidatos.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatoResponseDTO {
    private Long id;
    private String nombre;
    private String email;
    private List<String> skills;
    private List<String> skillsTecnicas;
    private List<String> skillsBlandas;
    private Double lat;
    private Double lng;
    private String region;
    private String residencia;
    private String documento;
    private String grupo;
    private String foto;
    private String genero;
    private Integer experienciaAnios;
    private String nivel;
    private List<String> certificaciones;
}