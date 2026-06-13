package com.appbit.matching.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "match_resultados")
public class MatchResultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaId;
    private String titulo;
    private String region;
    private int totalAnalizados;
    private String diversidadResultado;
}