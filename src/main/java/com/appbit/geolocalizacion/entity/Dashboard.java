package com.appbit.geolocalizacion.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDashboard;

    private Integer totalVacantes;
    private Integer totalCandidatos;
    private String metricasDiversidad;

    @OneToMany
    private List<MapaTalentos> mapasTalentos;

    @OneToOne
    private ReporteDiversidad reporteDiversidad;
}
