package com.appbit.geolocalizacion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ReporteDiversidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    private Integer totalAnalizados;
    private Double porcentajeDiversidad;
    private String diversidadResultado;

    public void generarReporte() {
        // lógica del reporte
    }

    public void exportarPDF() {
        // lógica para exportar PDF
    }
}
