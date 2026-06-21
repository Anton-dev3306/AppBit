package com.appbit.matching.mapper;

import com.appbit.matching.dto.request.MatchRequestDTO;
import com.appbit.matching.dto.response.MatchResultDTO;
import com.appbit.matching.dto.response.ShortlistResponseDTO;
import com.appbit.matching.entity.MatchResultado;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-17T13:37:05-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Override
    public MatchResultado toEntity(MatchResultDTO candidato, MatchRequestDTO request, ShortlistResponseDTO shortlist) {
        if ( candidato == null && request == null && shortlist == null ) {
            return null;
        }

        MatchResultado matchResultado = new MatchResultado();

        if ( candidato != null ) {
            matchResultado.setCandidatoId( candidato.getCandidatoId() );
            matchResultado.setNombre( candidato.getNombre() );
            matchResultado.setScoreMatch( candidato.getScoreMatch() );
            matchResultado.setBadgeDiversidad( candidato.isBadgeDiversidad() );
            matchResultado.setLat( candidato.getLat() );
            matchResultado.setLng( candidato.getLng() );
            List<String> list = candidato.getSkills();
            if ( list != null ) {
                matchResultado.setSkills( new ArrayList<String>( list ) );
            }
        }
        if ( request != null ) {
            matchResultado.setEmpresaId( request.getEmpresaId() );
            matchResultado.setTitulo( request.getTitulo() );
            matchResultado.setRegion( request.getRegion() );
        }
        if ( shortlist != null ) {
            matchResultado.setTotalAnalizados( shortlist.getTotalAnalizados() );
            matchResultado.setDiversidadResultado( shortlist.getDiversidadResultado() );
        }

        return matchResultado;
    }
}
