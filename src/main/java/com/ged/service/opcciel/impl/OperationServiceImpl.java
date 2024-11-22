package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.OperationDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.entity.standard.Personne;
import com.ged.mapper.opcciel.OperationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    @PersistenceContext
    private EntityManager emOpcciel;
    private final OperationMapper operationMapper;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final NatureOperationDao natureOperationDao;
    private final OperationDao operationDao;

    public OperationServiceImpl(OperationMapper operationMapper, PersonneDao personneDao, OpcvmDao opcvmDao, NatureOperationDao natureOperationDao, OperationDao operationDao) {
        this.operationMapper = operationMapper;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.natureOperationDao = natureOperationDao;
        this.operationDao = operationDao;
    }

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

    @Override
    public ResponseEntity<Object> creer(OperationDto operationDto) {
        try {
            //Enregistrement de l'opération
            Operation operation = operationMapper.deOperationDto(operationDto);
            if (operationDto.getActionnaire() != null) {
                Personne personne = personneDao.findById(operationDto.getActionnaire().getIdPersonne()).orElseThrow();
                operation.setActionnaire(personne);
            }
            if (operationDto.getOpcvm() != null) {
                Opcvm opcvm = opcvmDao.findById(operationDto.getOpcvm().getIdOpcvm()).orElseThrow();
                operation.setOpcvm(opcvm);
            }
            if(operationDto.getNatureOperation() != null) {
                NatureOperation natureOperation = natureOperationDao.findById(operationDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                operation.setNatureOperation(natureOperation);
            }
            operation = operationDao.save(operation);

            System.out.println("Dep === " + operation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    operationMapper.deOperation(operation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OperationDto operationDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOperation) {
        return null;
    }
}
