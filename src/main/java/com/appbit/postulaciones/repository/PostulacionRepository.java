package com.appbit.postulaciones.repository;

import com.appbit.postulaciones.entity.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByCandidatoId(Long candidatoId);
    List<Postulacion> findByVacanteId(Long vacanteId);
    boolean existsByCandidatoIdAndVacanteId(Long candidatoId, Long vacanteId);
}
