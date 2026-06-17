package com.appbit.vacantes.repository;

import com.appbit.vacantes.entity.Vacante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VacanteRepository extends JpaRepository<Vacante, Long> {
    List<Vacante> findByEmpresaId(Long empresaId);
    List<Vacante> findByActivaTrue();
}
