package com.appbit.matching.mapper;

import com.appbit.empresas.entity.Empresa;
import com.appbit.matching.dto.response.MatchResultDTO;
import com.appbit.matching.entity.MatchResultado;
import com.appbit.vacantes.entity.Vacante;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-07T13:43:34-0600",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Eclipse Adoptium)"
)
@Component
public class MatchMapperImpl implements MatchMapper {

    @Override
    public MatchResultado toEntity(MatchResultDTO candidato, Vacante vacante) {
        if ( candidato == null && vacante == null ) {
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
        if ( vacante != null ) {
            Long id = vacanteEmpresaId( vacante );
            if ( id != null ) {
                matchResultado.setEmpresaId( String.valueOf( id ) );
            }
            matchResultado.setTitulo( vacante.getTitulo() );
            matchResultado.setRegion( vacante.getRegion() );
        }

        return matchResultado;
    }

    private Long vacanteEmpresaId(Vacante vacante) {
        if ( vacante == null ) {
            return null;
        }
        Empresa empresa = vacante.getEmpresa();
        if ( empresa == null ) {
            return null;
        }
        Long id = empresa.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
