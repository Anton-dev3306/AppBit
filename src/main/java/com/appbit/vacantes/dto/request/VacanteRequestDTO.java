package com.appbit.vacantes.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacanteRequestDTO {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El área es obligatoria")
    private String area;

    @NotBlank(message = "El nivel es obligatorio")
    private String nivel;

    @NotBlank(message = "La región es obligatoria")
    private String region;

    @NotBlank(message = "La modalidad es obligatoria")
    private String modalidad;

    @NotNull(message = "El salario es obligatorio")
    @Min(value = 0, message = "El salario debe ser mayor o igual a 0")
    private Double salario;

    @NotNull(message = "El ID de la empresa es obligatorio")
    private Long empresaId;

    private List<String> skills;
}
