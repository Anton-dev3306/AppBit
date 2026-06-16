package com.appbit.matching.dto.response;

import com.appbit.matching.entity.MatchResultado;
import lombok.Data;
import java.util.List;

@Data
public class DashboardResponseDTO {
    private List<MatchResultado> top10Candidatos;
    private List<MapaTalentoDTO> mapaTalentos;
    private MetasEsgDTO metasEsg;

    @Data
    public static class MapaTalentoDTO {
        private String nombre;
        private double lat;
        private double lng;
        private int scoreMatch;
        private boolean badgeDiversidad;
    }

    @Data
    public static class MetasEsgDTO {
        private int totalAnalizados;
        private long totalConBadge;
        private double porcentajeDiversidad;
        private String ultimoDiversidadResultado;
    }
}