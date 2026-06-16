package com.appbit.matching.repository;

import com.appbit.matching.entity.MatchResultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchResultadoRepository extends JpaRepository<MatchResultado, Long> {

    @Query("""
        SELECT m FROM MatchResultado m
        WHERE m.empresaId = :empresaId
        ORDER BY m.scoreMatch DESC
        LIMIT 10
    """)
    List<MatchResultado> findTop10ByEmpresaId(@Param("empresaId") String empresaId);

    List<MatchResultado> findByEmpresaIdOrderByScoreMatchDesc(String empresaId);
}