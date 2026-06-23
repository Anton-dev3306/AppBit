package com.appbit.geolocalizacion.controller;

import com.appbit.geolocalizacion.dto.request.InsightRegionDTO;
import com.appbit.geolocalizacion.dto.response.MapaTalentosResponseDTO;
import com.appbit.geolocalizacion.service.RegionAggregator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/insights")
@CrossOrigin(origins = "*")
public class InsightController {
    private final RegionAggregator regionAggregator;

    public InsightController(RegionAggregator regionAggregator) {
        this.regionAggregator = regionAggregator;
    }

    @GetMapping("/mapa-talentos")
    public List<MapaTalentosResponseDTO> obtenerTodosLosMapas() {
        return regionAggregator.obtenerTodosLosMapas();
    }

    @PostMapping("/region")
    public List<MapaTalentosResponseDTO> obtenerPorRegion(@RequestBody InsightRegionDTO request) {
        return regionAggregator.obtenerMapaTalentosPorRegion(request.region());
    }
}
