package com.ged.service.opcciel.impl;

import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.service.opcciel.OperationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    @PersistenceContext
    private EntityManager emOpcciel;
    @Override
    public List<Operation2Dto> afficherDepotSuperieurADixMillionsSurAnnee(long codeExercice) {
        List<Object[]>  operation;
        List<Operation2Dto> operation2Dto =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_OperationSuperieurDixMillions_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("annee", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        query.setParameter("annee", String.valueOf(codeExercice));

        query.execute();

        operation= query.getResultList();

        for(Object[] o:operation)
        {
            Operation2Dto ope=new Operation2Dto();
            ope.setIdOperation(Long.valueOf(o[0].toString()));
            ope.setDateOperation(LocalDateTime.parse(o[2].toString().replace(' ', 'T')));
            ope.setDenominationOpcvm((o[4].toString()));
            ope.setDenomination((o[9].toString()));
            ope.setMontantDepose(Double.valueOf(o[3].toString()));
            ope.setTotal(Double.valueOf(o[10].toString()));
            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString().replace(' ', 'T')));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherOperationConstituantDeNouvelleRelation(long annee) {
        List<Object[]>  operation;
        List<Operation2Dto> operation2Dto =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_OperationNouvelleRelation_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("annee", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        query.setParameter("annee", String.valueOf(annee));

        query.execute();

        operation= query.getResultList();

        for(Object[] o:operation)
        {
            Operation2Dto ope=new Operation2Dto();
            ope.setIdOperation(Long.valueOf(o[0].toString()));
            ope.setDateOperation(LocalDateTime.parse(o[2].toString().replace(' ', 'T')));
            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString().replace(' ', 'T')));
            ope.setDenominationOpcvm((o[4].toString()));
            ope.setDenomination((o[9].toString()));
            ope.setMontantDepose(Double.valueOf(o[3].toString()));
            ope.setTotal(Double.valueOf(o[10].toString()));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherTransactionConditionInhabituel(long annee) {
        List<Object[]>  operation;
        List<Operation2Dto> operation2Dto =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_OperationConditionAnormale_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("annee", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        query.setParameter("annee", String.valueOf(annee));

        query.execute();

        operation= query.getResultList();

        for(Object[] o:operation)
        {
            Operation2Dto ope=new Operation2Dto();
            ope.setIdOperation(Long.valueOf(o[0].toString()));
            ope.setDateOperation(LocalDateTime.parse(o[2].toString().replace(' ', 'T')));
            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString().replace(' ', 'T')));
            ope.setDenominationOpcvm((o[4].toString()));
            ope.setDenomination((o[9].toString()));
            ope.setMontantDepose(Double.valueOf(o[3].toString()));
            ope.setTotal(Double.valueOf(o[10].toString()));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherTransactionConditionNormale(long annee) {
        List<Object[]>  operation;
        List<Operation2Dto> operation2Dto =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_OperationConditionNormale_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("annee", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        query.setParameter("annee", String.valueOf(annee));

        query.execute();

        operation= query.getResultList();

        for(Object[] o:operation)
        {
            Operation2Dto ope=new Operation2Dto();
            ope.setIdOperation(Long.valueOf(o[0].toString()));
            ope.setDateOperation(LocalDateTime.parse(o[2].toString().replace(' ', 'T')));
            //ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString().replace(' ', 'T')));
            ope.setDenominationOpcvm((o[4].toString()));
            ope.setDenomination((o[9].toString()));
            ope.setMontantDepose(Double.valueOf(o[3].toString()));
            ope.setTotal(Double.valueOf(o[10].toString()));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherOperationSuperieurACinqMillions(long codeExercice) {
        List<Object[]>  operation;
        List<Operation2Dto> operation2Dto =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_OperationSuperieurCinqMillions_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("annee", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        query.setParameter("annee", String.valueOf(codeExercice));

        query.execute();

        operation= query.getResultList();

        for(Object[] o:operation)
        {
            Operation2Dto ope=new Operation2Dto();
            ope.setIdOperation(Long.valueOf(o[0].toString()));
            ope.setDateOperation(LocalDateTime.parse(o[2].toString().replace(' ', 'T')));
//            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString()));
            ope.setDenominationOpcvm((o[4].toString()));
            ope.setDenomination((o[9].toString()));
            ope.setMontantDepose(Double.valueOf(o[3].toString()));
            ope.setTotal(Double.valueOf(o[10].toString()));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherDepotSurAnnee(long codeExercice) {
        List<Object[]>  operation;
        List<Operation2Dto> operation2Dto =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Impression].[PS_DepotSurAnnee_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("annee", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        query.setParameter("annee", String.valueOf(codeExercice));

        query.execute();

        operation= query.getResultList();

        for(Object[] o:operation)
        {
            Operation2Dto ope=new Operation2Dto();
            ope.setIdOperation(Long.valueOf(o[0].toString()));
            ope.setDateOperation(LocalDateTime.parse(o[2].toString().replace(' ', 'T')));
//            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString()));
            ope.setDenominationOpcvm((o[4].toString()));
            ope.setDenomination((o[9].toString()));
            ope.setMontantDepose(Double.valueOf(o[3].toString()));
            ope.setTotal(Double.valueOf(o[10].toString()));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }
}
