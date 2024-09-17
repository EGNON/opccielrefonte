package com.ged.service.risque.impl;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.risque.AlphaDto;
import com.ged.dto.risque.RatioSharpDto;
import com.ged.service.risque.RisqueService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RisqueServiceImpl implements RisqueService {
    @Autowired
    @Qualifier("refonteEntityManagerFactory")
    private EntityManager emOpcciel;

    @Override
    public ResponseEntity<Object> afficherAlpha(DatatableParameters parameters) {
        return null;
    }

    @Override
    public List<AlphaDto> afficherAlpha(long idOpcvm,String anneeDebut,String anneeFin) {

            Sort sort=Sort.by(Sort.Direction.ASC,"dateOuverture");
            List<Object[]>  alpha;
            List<AlphaDto>  alphaDto=new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_Alpha_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("anneeDebut", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("anneeFin", String.class, ParameterMode.IN);

            //Fournir les différents paramètres
            query.setParameter("idOpcvm", idOpcvm);
            query.setParameter("anneeDebut", anneeDebut);
            query.setParameter("anneeFin", anneeFin);

            query.execute();

            alpha= query.getResultList();

            for(Object[] o:alpha)
            {
                AlphaDto aph=new AlphaDto();
                aph.setDateFermeture(LocalDateTime.parse(o[0].toString().replace(' ', 'T')));
                aph.setVL(Double.valueOf(o[1].toString()));
                aph.setDividendeDistribue(Double.valueOf(o[2].toString()));
                aph.setPerformanceAnnuelle(Double.valueOf(o[3].toString()));
                aph.setIndiceReference(Double.valueOf(o[4].toString()));
                aph.setAlpha(Double.valueOf(o[5].toString()));
                alphaDto.add(aph);
            }
            return alphaDto;
    }

    @Override
    public List<RatioSharpDto> afficherRatioSharp(long idOpcvm, String anneeDebut, String anneeFin) {
        Sort sort=Sort.by(Sort.Direction.ASC,"dateOuverture");
        List<Object[]>  alpha;
        List<RatioSharpDto>  ratioSharpDto=new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_RATIOSHARP_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("anneeDebut", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("anneeFin", String.class, ParameterMode.IN);

        //Fournir les différents paramètres
        query.setParameter("idOpcvm", idOpcvm);
        query.setParameter("anneeDebut", anneeDebut);
        query.setParameter("anneeFin", anneeFin);

        query.execute();

        alpha= query.getResultList();

        for(Object[] o:alpha)
        {
            RatioSharpDto aph=new RatioSharpDto();
            aph.setDateFermeture(LocalDateTime.parse(o[0].toString().replace(' ', 'T')));
            aph.setVL(Double.valueOf(o[1].toString()));
            aph.setDividendeDistribue(Double.valueOf(o[2].toString()));
            aph.setPerformanceAnnuelle(Double.valueOf(o[3].toString()));
            aph.setVolatiliteAnnualisee(Double.valueOf(o[4].toString()));
            aph.setSharp(Double.valueOf(o[5].toString()));
            ratioSharpDto.add(aph);
        }
        return ratioSharpDto;
    }

}
