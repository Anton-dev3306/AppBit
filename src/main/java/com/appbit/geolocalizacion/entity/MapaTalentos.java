package com.appbit.geolocalizacion.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Mapa Talentos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapaTalentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMapaTalentos;
    private String region;
    private Integer concentracion;
    private String coberturaRed;
    private Integer perfilesDisponibles;
    private String residencia;

    private Double latitud;
    private Double longitud;


    //@OneToMany
    //private List<Candidato> candidatos;
}
