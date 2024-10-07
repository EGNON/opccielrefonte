package com.ged.service.risque.impl;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.risque.*;
import com.ged.service.risque.RisqueService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
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
    @PersistenceContext
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
        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

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
    @Override
    public List<CorrelationDto> afficherCorrelation(long idOpcvm,BeginEndDateParameter beginEndDateParameter) {
        Sort sort=Sort.by(Sort.Direction.ASC,"dateOuverture");
        List<Object[]>  correlation;
        List<CorrelationDto>  correlationDtos=new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_CORRELATION_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dateDebut", LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dateFin", LocalDateTime.class, ParameterMode.IN);

        //Fournir les différents paramètres
        query.setParameter("idOpcvm", idOpcvm);
        query.setParameter("dateDebut", beginEndDateParameter.getStartDate());
        query.setParameter("dateFin", beginEndDateParameter.getEndDate());

        query.execute();

        correlation= query.getResultList();

        for(Object[] o:correlation)
        {
            CorrelationDto corr=new CorrelationDto();
            corr.setDateFermeture(LocalDateTime.parse(o[1].toString().replace(' ', 'T')));
            corr.setVL(Double.valueOf(o[2].toString()));
            corr.setNav(Double.valueOf(o[3].toString()));
            corr.setPerformancePortefeuille(Double.valueOf(o[4].toString()));
            corr.setPerformanceBenchMark(Double.valueOf(o[5].toString()));
            corr.setCorrelation(Double.valueOf(o[6].toString()));
            correlationDtos.add(corr);
        }
        return correlationDtos;
    }

    @Override
    public List<CovarianceDto> afficherCovariance(long idOpcvm, BeginEndDateParameter beginEndDateParameter) {
        Sort sort=Sort.by(Sort.Direction.ASC,"dateOuverture");
        List<Object[]>  covariance;
        List<CovarianceDto>  covarianceDtos=new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_COVARIANCE_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dateDebut", LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dateFin", LocalDateTime.class, ParameterMode.IN);

        //Fournir les différents paramètres
        query.setParameter("idOpcvm", idOpcvm);
        query.setParameter("dateDebut", beginEndDateParameter.getStartDate());
        query.setParameter("dateFin", beginEndDateParameter.getEndDate());

        query.execute();

        covariance= query.getResultList();

        for(Object[] o:covariance)
        {
            CovarianceDto cov=new CovarianceDto();
            cov.setDateFermeture(LocalDateTime.parse(o[1].toString().replace(' ', 'T')));
            cov.setVL(Double.valueOf(o[2].toString()));
            cov.setNav(Double.valueOf(o[3].toString()));
            cov.setPerformancePortefeuille(Double.valueOf(o[4].toString()));
            cov.setPerformanceBenchMark(Double.valueOf(o[5].toString()));
            cov.setCovariance(Double.valueOf(o[6].toString()));
            covarianceDtos.add(cov);
        }
        return covarianceDtos;
    }

    @Override
    public List<BetaDto> afficherBeta(long idOpcvm, BeginEndDateParameter beginEndDateParameter) {
        Sort sort=Sort.by(Sort.Direction.ASC,"dateOuverture");
        List<Object[]>  beta;
        List<BetaDto>  betaDtos=new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_BETA_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dateDebut", LocalDateTime.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("dateFin", LocalDateTime.class, ParameterMode.IN);

        //Fournir les différents paramètres
        query.setParameter("idOpcvm", idOpcvm);
        query.setParameter("dateDebut", beginEndDateParameter.getStartDate());
        query.setParameter("dateFin", beginEndDateParameter.getEndDate());

        query.execute();

        beta= query.getResultList();

        for(Object[] o:beta)
        {
            BetaDto bet =new BetaDto();
            bet.setDateFermeture(LocalDateTime.parse(o[1].toString().replace(' ', 'T')));
            bet.setVL(Double.valueOf(o[2].toString()));
            bet.setNav(Double.valueOf(o[3].toString()));
            bet.setPerformancePortefeuille(Double.valueOf(o[4].toString()));
            bet.setPerformanceBenchMark(Double.valueOf(o[5].toString()));
            bet.setVolatiliteAnnualiseeBenchMark(Double.valueOf(o[8].toString()));
            bet.setVolatiliteAnnualiseeOpcvm(Double.valueOf(o[9].toString()));
            bet.setBeta(Double.valueOf(o[10].toString()));
            betaDtos.add(bet);
        }
        return betaDtos;
    }

}
