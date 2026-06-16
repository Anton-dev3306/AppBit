package com.appbit.matching.service;

import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.DashboardResponseDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface MatchService {
    ShortlistResponseDTO ejecutarMatching(MatchRequestDTO request) throws JsonProcessingException;

    DashboardResponseDTO getDashboard(String empresaId);
}