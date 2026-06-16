package com.appbit.matching.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import java.util.List;

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

    // Campos del candidato evaluado
    private String candidatoId;
    private String nombre;
    private int scoreMatch;
    private boolean badgeDiversidad;
    private double lat;
    private double lng;

    @ElementCollection
    @CollectionTable(name = "match_skills", joinColumns = @JoinColumn(name = "match_id"))
    @Column(name = "skill")
    private List<String> skills;

    private Instant creadoEn = Instant.now();
}