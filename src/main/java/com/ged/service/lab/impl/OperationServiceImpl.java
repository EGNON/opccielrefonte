package com.ged.service.lab.impl;

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
import com.ged.projection.PersonnePhysiqueProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.lab.OperationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<Operation2Dto> resultatOperationDto =new ArrayList<>();

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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString().replace(' ', 'T')));
            operation2Dto.add(ope);
        }
        resultatOperationDto=operation2Dto;
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherDepotSuperieurADixMillionsSurAnneeEtat(long codeExercice, HttpServletResponse response) throws IOException, JRException {
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            ope.setDatePremiereSouscription(LocalDateTime.parse(o[11].toString().replace(' ', 'T')));
            operation2Dto.add(ope);
        }
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:Operation_Sup_10_Millions.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/Operation_Sup_10_Millions.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return resultatOperationDto;
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherOperationConstituantDeNouvelleRelationEtat(long annee, HttpServletResponse response) throws IOException, JRException {
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/Operation_Sup_10_Millions_Nouvelle_Relation.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:Operation_Sup_10_Millions_Nouvelle_Relation.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherTransactionConditionInhabituelEtat(long annee, HttpServletResponse response) throws IOException, JRException {
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:Operation_Sup_10_Millions_Conditions_Inhabituelles.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/Operation_Sup_10_Millions_Conditions_Inhabituelles.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherTransactionConditionNormaleEtat(long annee, HttpServletResponse response) throws IOException, JRException {
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:Operation_Sup_50_Millions.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/Operation_Sup_50_Millions.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
        resultatOperationDto=operation2Dto;
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherOperationSuperieurACinqMillionsEtat(long codeExercice, HttpServletResponse response) throws IOException, JRException {
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:Operation_Sup_5_Millions.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/Operation_Sup_5_Millions.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return resultatOperationDto;
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
        resultatOperationDto=operation2Dto;
        return operation2Dto;
    }

    @Override
    public List<Operation2Dto> afficherDepotSurAnneeEtat(long codeExercice, HttpServletResponse response) throws IOException, JRException {
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
            ope.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[3].toString())));
            ope.setTotal(BigDecimal.valueOf(Double.valueOf(o[10].toString())));
            operation2Dto.add(ope);
        }
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:depot_Espece_Recense_Annee.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/depot_Espece_Recense_Annee.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operation2Dto);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        return resultatOperationDto;
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
