package com.appbit.geolocalizacion.repository;

import com.appbit.geolocalizacion.entity.MapaTalentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MapaTalentosRepository extends JpaRepository<MapaTalentos, Long> {
    List<MapaTalentos> findByRegionIgnoreCase(String region);
}
