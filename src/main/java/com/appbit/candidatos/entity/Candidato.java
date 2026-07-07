package com.appbit.candidatos.entity;

import com.appbit.seguridad.entity.Usuario;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "candidatos")
@PrimaryKeyJoinColumn(name = "id")
public class Candidato extends Usuario {

    @ElementCollection
    @CollectionTable(name = "candidato_skills_tecnicas", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "skill")
    private List<String> skillsTecnicas;

    @ElementCollection
    @CollectionTable(name = "candidato_skills_blandas", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "skill")
    private List<String> skillsBlandas;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    private String region;
    private String residencia;
    private String documento;
    private String grupo;
    private String foto;
    private Integer experienciaAnios;
    private String genero;
    private String nivel;

    @ElementCollection
    @CollectionTable(name = "candidato_certificaciones", joinColumns = @JoinColumn(name = "candidato_id"))
    @Column(name = "certificacion")
    private List<String> certificaciones;

    public List<String> getSkills() {
        List<String> combined = new java.util.ArrayList<>();
        if (skillsTecnicas != null) {
            combined.addAll(skillsTecnicas);
        }
        if (skillsBlandas != null) {
            combined.addAll(skillsBlandas);
        }
        return combined;
    }
}
