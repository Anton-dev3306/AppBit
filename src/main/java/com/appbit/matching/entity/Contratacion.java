package com.appbit.matching.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "contrataciones")
public class Contratacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String empresaId;
    private String candidatoId;
    private String vacanteId;
}