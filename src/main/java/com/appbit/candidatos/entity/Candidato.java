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

    @Column(name = "lat")
    private Double latitud;

    @Column(name = "lng")
    private Double longitud;

    private String region;
    private String residencia;
    private String documento;
    private String grupo;
    private String foto;
    private Integer experienciaAnios;
    private String genero;

    @ElementCollection
    @CollectionTable(name = "candidato_certificaciones", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "certificacion")
    private List<String> certificaciones;

    public Double getLat() {
        return latitud;
    }

    public void setLat(Double lat) {
        this.latitud = lat;
    }

    public Double getLng() {
        return longitud;
    }

    public void setLng(Double lng) {
        this.longitud = lng;
    }
}
