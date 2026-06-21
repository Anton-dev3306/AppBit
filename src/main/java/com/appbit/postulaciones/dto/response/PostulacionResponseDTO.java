package com.appbit.postulaciones.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostulacionResponseDTO {
    private Long id;
    private Long candidatoId;
    private String candidatoNombre;
    private String candidatoEmail;
    private Long vacanteId;
    private String vacanteTitulo;
    private String empresaNombre;
    private LocalDateTime fechaPostulacion;
    private String estado;
    private String cvUrl;
    private String comentarios;
}
