package com.appbit.candidatos.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "candidatos")
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection
    @CollectionTable(name = "candidato_skills", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "skill")
    private List<String> skills;

    private Double lat;
    private Double lng;
    private String genero;
    @ElementCollection
    @CollectionTable(name = "candidato_certificaciones", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "certificacion")
    private List<String> certificaciones;
}
