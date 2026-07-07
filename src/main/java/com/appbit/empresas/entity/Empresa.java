package com.appbit.empresas.entity;

import com.appbit.seguridad.entity.Usuario;
import com.appbit.vacantes.entity.Vacante;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;
import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "empresas")
@PrimaryKeyJoinColumn(name = "id")
public class Empresa extends Usuario {

    private String ruc;
    private String region;
    private String foto;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "encargado_id")
    private Encargado encargado;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacante> vacantes = new ArrayList<>();
}
