package com.appbit.geolocalizacion.service;

import com.appbit.geolocalizacion.dto.response.MapaTalentosResponseDTO;

import java.util.List;

public interface RegionAggregator {
    List<MapaTalentosResponseDTO> obtenerMapaTalentosPorRegion(String region);

    List<MapaTalentosResponseDTO> obtenerTodosLosMapas();
}
