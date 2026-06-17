package com.appbit.vacantes.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacanteResponseDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String area;
    private String nivel;
    private String region;
    private String modalidad;
    private Double salario;
    private boolean activa;
    private LocalDateTime fechaCreacion;
    private Long empresaId;
    private String empresaNombre;
    private List<String> skills;
}
