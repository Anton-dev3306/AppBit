package com.appbit.matching.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "procesos_seleccion")
public class ProcesoSeleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaId;
    private String vacanteId;
    private String estado;
}