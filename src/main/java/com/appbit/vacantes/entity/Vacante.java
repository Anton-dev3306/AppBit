package com.appbit.vacantes.entity;

import com.appbit.empresas.entity.Empresa;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "vacantes")
public class Vacante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 2000)
    private String descripcion;

    private String area;
    private String nivel;
    private String region;
    private String modalidad;
    private Double salario;

    private boolean activa = true;

    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    @ElementCollection
    @CollectionTable(name = "vacante_skills", joinColumns = @JoinColumn(name = "vacante_id"))
    private List<SkillVacante> skills;
}
