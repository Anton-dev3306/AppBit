package com.appbit.postulaciones.entity;

import com.appbit.candidatos.entity.Candidato;
import com.appbit.vacantes.entity.Vacante;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "postulaciones")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidato_id", nullable = false)
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vacante_id", nullable = false)
    private Vacante vacante;

    @Column(nullable = false)
    private LocalDateTime fechaPostulacion = LocalDateTime.now();

    @Column(nullable = false)
    private String estado = "POSTULADO";

    private Double scoreMatch;
    private String badgeDiversidad;
    private Long idAgenteIA;

    private String cvUrl;

    @Column(length = 1000)
    private String comentarios;
}
