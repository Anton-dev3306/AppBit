package com.appbit.matching.dto.request;

import com.appbit.matching.dto.response.FlexibleSkillsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import java.util.List;

@Data
public class MatchRequestDTO {

    @NotBlank
    private String empresaId;

    @NotBlank
    private String titulo;

    @JsonDeserialize(using = FlexibleSkillsDeserializer.class)
    private List<String> skills;

    @NotBlank
    private String nivel;

    @NotBlank
    private String region;

    private String diversidadMinima = "0";

    @NotEmpty
    private List<CandidatoInputDTO> candidatos;

    @Data
    public static class CandidatoInputDTO {
        private String candidatoId;
        private String nombre;
        private List<String> skills;
        private double lat;
        private double lng;
        private String genero;
        private String etnia;
        private Boolean discapacidad;
        private Integer experienciaAnios;
        private List<String> certificaciones;
    }
}