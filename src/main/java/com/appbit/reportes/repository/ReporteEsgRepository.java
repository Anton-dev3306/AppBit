package com.appbit.reportes.repository;

import com.appbit.reportes.entity.ReporteEsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteEsgRepository extends JpaRepository<ReporteEsg, Long> {
    List<ReporteEsg> findByEmpresaIdOrderByGeneradoEnDesc(String empresaId);
}