package com.appbit.matching.controller;

import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.appbit.matching.service.MatchService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matching")
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/match")
    public ResponseEntity<ShortlistResponseDTO> match(@Valid @RequestBody MatchRequestDTO request) throws JsonProcessingException {
        ShortlistResponseDTO response = matchService.ejecutarMatching(request);
        return ResponseEntity.ok(response);
    }
}