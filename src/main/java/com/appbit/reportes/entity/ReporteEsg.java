package com.appbit.reportes.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reportes_esg")
public class ReporteEsg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaId;
    private String empresaNombre;

    private int totalAnalizados;
    private int totalConBadge;
    private double porcentajeDiversidad;
    private double metaDiversidad;
    private boolean metaCumplida;

    @Column(length = 2000)
    private String diversidadResultado;

    @Column(length = 500)
    private String regionesAnalizadas;

    private LocalDateTime generadoEn = LocalDateTime.now();
}