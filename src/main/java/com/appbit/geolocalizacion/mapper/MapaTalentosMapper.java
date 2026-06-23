package com.appbit.geolocalizacion.mapper;

import com.appbit.geolocalizacion.dto.response.MapaTalentosResponseDTO;
import com.appbit.geolocalizacion.entity.MapaTalentos;
import org.springframework.stereotype.Component;

@Component
public class MapaTalentosMapper {

    public MapaTalentosResponseDTO toResponseDTO(MapaTalentos entity){
        return new MapaTalentosResponseDTO(
                entity.getIdMapaTalentos(),
                entity.getRegion(),
                entity.getConcentracion(),
                entity.getCoberturaRed(),
                entity.getPerfilesDisponibles(),
                entity.getResidencia(),
                entity.getLatitud(),
                entity.getLongitud()
        );

    }

}
