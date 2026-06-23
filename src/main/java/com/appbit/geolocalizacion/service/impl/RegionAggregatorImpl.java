package com.appbit.geolocalizacion.service.impl;

import com.appbit.geolocalizacion.dto.response.MapaTalentosResponseDTO;
import com.appbit.geolocalizacion.entity.MapaTalentos;
import com.appbit.geolocalizacion.mapper.MapaTalentosMapper;
import com.appbit.geolocalizacion.repository.MapaTalentosRepository;
import com.appbit.geolocalizacion.service.RegionAggregator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionAggregatorImpl implements RegionAggregator {

    private final MapaTalentosRepository mapaTalentosRepository;
    private final MapaTalentosMapper mapaTalentosMapper;

    public RegionAggregatorImpl(
            MapaTalentosRepository mapaTalentosRepository,
            MapaTalentosMapper mapaTalentosMapper
    ) {
        this.mapaTalentosRepository = mapaTalentosRepository;
        this.mapaTalentosMapper = mapaTalentosMapper;
    }

    @Override
    public List<MapaTalentosResponseDTO> obtenerMapaTalentosPorRegion(String region) {
        List<MapaTalentos> mapas = mapaTalentosRepository.findByRegionIgnoreCase(region);

        return mapas.stream()
                .map(mapaTalentosMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<MapaTalentosResponseDTO> obtenerTodosLosMapas() {
        return mapaTalentosRepository.findAll()
                .stream()
                .map(mapaTalentosMapper::toResponseDTO)
                .toList();
    }

}
