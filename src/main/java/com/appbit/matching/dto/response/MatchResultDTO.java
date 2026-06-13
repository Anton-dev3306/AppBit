package com.appbit.matching.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import java.util.List;

@Data
public class MatchResultDTO {

    @JsonProperty("candidato_id")
    private String candidatoId;

    private String nombre;

    @JsonProperty("score_match")
    private int scoreMatch;

    @JsonProperty("badge_diversidad")
    private boolean badgeDiversidad;

    @JsonDeserialize(using = FlexibleSkillsDeserializer.class)
    private List<String> skills;

    private double lat;
    private double lng;
}