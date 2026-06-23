package com.appbit.geolocalizacion.dto.response;

public record MapaTalentosResponseDTO(
        Long idMapaTalentos,
        String region,
        Integer concentracion,
        String coberturaRed,
        Integer perfilesDisponibles,
        String residencia,
        Double latitud,
        Double longitud
) {

}
