package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.SocieteDeGestionDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.etats.PointRachatGlobalDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.dto.request.SoldeDesComptesComptablesRequest;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.*;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.*;
import com.ged.entity.opcciel.*;
import com.ged.entity.opcciel.comptabilite.*;
import com.ged.entity.security.Utilisateur;
import com.ged.entity.standard.Personne;
import com.ged.mapper.opcciel.*;
import com.ged.projection.*;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.IbService;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.opcciel.PlanService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

//@RequiredArgsConstructor
@Service
public class AppService {
    @PersistenceContext
    EntityManager em;

    @Autowired
    private DataSource dataSource;
    private final UtilisateurDao utilisateurDao;
    private final LibraryDao libraryDao;
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;
    private final PlanService planService;
    private final OpcvmService opcvmService;
    private final IbService ibService;
    private final MouvementDao mouvementDao;
    private final OpcvmMapper opcvmMapper;
    private final OpcvmDao opcvmDao;
    private final SocieteDeGestionMapper societeDeGestionMapper;
    private final SocieteDeGestionDao societeDeGestionDao;
    private final NatureOperationMapper natureOperationMapper;
    private final TransactionDao transactionDao;
    private final OperationMapper operationMapper;
    private final FormuleDao formuleDao;
    private final OperationFormuleDao operationFormuleDao;
    private final OperationCodeAnalytiqueDao operationCodeAnalytiqueDao;
    private final NatureOperationDao natureOperationDao;
    private final JournalDao journalDao;
    private final OperationJournalDao operationJournalDao;
    private final PersonneDao personneDao;
    private final OperationSouscriptionRachatMapper souscriptionRachatMapper;
    private final OperationRestitutionReliquatMapper operationRestitutionReliquatMapper;
    private final OperationTransfertPartMapper transfertPartMapper;
    private final OperationConstatationChargeMapper constatationChargeMapper;
    private final OperationCommissionMapper commissionMapper;

    public AppService(UtilisateurDao utilisateurDao, LibraryDao libraryDao, ExerciceDao exerciceDao, ExerciceMapper exerciceMapper, PlanService planService, OpcvmService opcvmService, IbService ibService, MouvementDao mouvementDao, OpcvmMapper opcvmMapper, OpcvmDao opcvmDao, SocieteDeGestionMapper societeDeGestionMapper, SocieteDeGestionDao societeDeGestionDao, NatureOperationMapper natureOperationMapper, TransactionDao transactionDao, OperationMapper operationMapper, FormuleDao formuleDao, OperationFormuleDao operationFormuleDao, OperationCodeAnalytiqueDao operationCodeAnalytiqueDao, NatureOperationDao natureOperationDao, JournalDao journalDao, OperationJournalDao operationJournalDao, PersonneDao personneDao, OperationSouscriptionRachatMapper souscriptionRachatMapper, OperationRestitutionReliquatMapper operationRestitutionReliquatMapper, OperationTransfertPartMapper transfertPartMapper, OperationConstatationChargeMapper constatationChargeMapper, OperationCommissionMapper commissionMapper) {
        this.utilisateurDao = utilisateurDao;
        this.libraryDao = libraryDao;
        this.exerciceDao = exerciceDao;
        this.exerciceMapper = exerciceMapper;
        this.planService = planService;
        this.opcvmService = opcvmService;
        this.ibService = ibService;
        this.mouvementDao = mouvementDao;
        this.opcvmMapper = opcvmMapper;
        this.opcvmDao = opcvmDao;
        this.societeDeGestionMapper = societeDeGestionMapper;
        this.societeDeGestionDao = societeDeGestionDao;
        this.natureOperationMapper = natureOperationMapper;
        this.transactionDao = transactionDao;
        this.operationMapper = operationMapper;
        this.formuleDao = formuleDao;
        this.operationFormuleDao = operationFormuleDao;
        this.operationCodeAnalytiqueDao = operationCodeAnalytiqueDao;
        this.natureOperationDao = natureOperationDao;
        this.journalDao = journalDao;
        this.operationJournalDao = operationJournalDao;
        this.personneDao = personneDao;
        this.souscriptionRachatMapper = souscriptionRachatMapper;
        this.operationRestitutionReliquatMapper = operationRestitutionReliquatMapper;
        this.transfertPartMapper = transfertPartMapper;
        this.constatationChargeMapper = constatationChargeMapper;
        this.commissionMapper = commissionMapper;
    }

    public Utilisateur currentUser(Principal principal) {
        String username = principal.getName();
        return utilisateurDao.findByUsernameAndEstActif(username, true).orElse(null);
    }
    //soldecompteclient
    public ResponseEntity<?> afficherSoldeCompteClient(Long idActionnaire,Long idOpcvm) {

        BigDecimal value=libraryDao.solde(idActionnaire, idOpcvm);

        return ResponseHandler.generateResponse(
                "Portefeuille opcvm",
                HttpStatus.OK,
                value);

    }
    //releveTitreFCP
    public ResponseEntity<?> afficherReleveTitreFcp(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ReleveTitreFCPProjection> releveTitreFCPPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                releveTitreFCPPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                releveTitreFCPPage = libraryDao.releveTitreFCP(
                        request.getIdOpcvm(),request.getDateDebut(),request.getDateFin(),
                        pageable
                );
            }
            List<ReleveTitreFCPProjection> content = releveTitreFCPPage.getContent().stream().toList();
            DataTablesResponse<ReleveTitreFCPProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)releveTitreFCPPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)releveTitreFCPPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherReleveTitreFCP(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<ReleveTitreFCPProjection> releveTitreFCPProjections = libraryDao.releveTitreFCP(
                request.getIdOpcvm(), request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/ReleveTitreFCP.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


        LocalDateTime dateTime = request.getDateDebut();
        Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date dateDebut = Date.from(i);

         dateTime = request.getDateFin();
         i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
         Date dateFin = Date.from(i);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        String dateFin2 = dateFormatter.format(dateFin);
        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
        parameters.put("dateFin", dateFin2);
        parameters.put("dateDebut", dateDebut2);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("description", opcvm.getDenominationOpcvm());
        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(releveTitreFCPProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                releveTitreFCPProjections);


    }
    public ResponseEntity<?> afficherReleveTitreFCPListe(ReleveTitreFCPRequest request) {
        try
        {
            List<ReleveTitreFCPProjection> releveTitreFCPProjections=libraryDao.releveTitreFCP(
                    request.getIdOpcvm(),request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    releveTitreFCPProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }


    //releveDePartFCP
    public ResponseEntity<?> afficherReleveDePartFCP(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ReleveDePartFCPProjection> releveDePartFCPPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                releveDePartFCPPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                releveDePartFCPPage = libraryDao.releveDePartFCP(
                        request.getIdOpcvm(),request.getDateDebut(),request.getDateFin(),
                        pageable
                );
            }
            List<ReleveDePartFCPProjection> content = releveDePartFCPPage.getContent().stream().toList();
            DataTablesResponse<ReleveDePartFCPProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)releveDePartFCPPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)releveDePartFCPPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Releve de Part FCP opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherReleveDePartFCP(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<ReleveDePartFCPProjection> releveDePartFCPProjections = libraryDao.releveDePartFCP(
                request.getIdOpcvm(), request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Releve_Part_FCP.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        String Descrip = request.getDescrip();
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("Descrip", opcvm.getDenominationOpcvm());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(releveDePartFCPProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                releveDePartFCPProjections);


    }
    public ResponseEntity<?> afficherReleveDePartFCPListe(ReleveTitreFCPRequest request) {
        try
        {
            List<ReleveDePartFCPProjection> releveDePartFCPProjections=libraryDao.releveDePartFCP(
                    request.getIdOpcvm(),request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    releveDePartFCPProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //releveDePartActionnaire
    public ResponseEntity<?> afficherReleveDePartActionnaire(ReleveDePartActionnaireRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ReleveDePartActionnaireProjection> releveDePartActionnairePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                releveDePartActionnairePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                releveDePartActionnairePage = libraryDao.releveDePartActionnaire(
                        request.getIdActionnaire(), request.getIdOpcvm(),request.getDateDebut(),request.getDateFin(),
                        pageable
                );
            }
            List<ReleveDePartActionnaireProjection> content = releveDePartActionnairePage.getContent().stream().toList();
            DataTablesResponse<ReleveDePartActionnaireProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)releveDePartActionnairePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)releveDePartActionnairePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Releve de Part FCP opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherReleveDePartActionnaire(ReleveDePartActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<ReleveDePartActionnaireProjection> releveDePartActionnaireProjections = libraryDao.releveDePartActionnaire(
                request.getIdActionnaire(),request.getIdOpcvm(), request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Releve_Part_Actionnaire.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        String Descrip = request.getDescrip();
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("Descrip", opcvm.getDenominationOpcvm());
        Personne personne=personneDao.afficherPersonneSelonId(request.getIdActionnaire());
        parameters.put("Actionnaire", personne.getDenomination());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(releveDePartActionnaireProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                releveDePartActionnaireProjections);


    }
    public ResponseEntity<?> afficherReleveDePartActionnaireListe(ReleveDePartActionnaireRequest request) {
        try
        {
            List<ReleveDePartActionnaireProjection> releveDePartActionnaireProjections=libraryDao.releveDePartActionnaire(
                    request.getIdActionnaire(), request.getIdOpcvm(),request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    releveDePartActionnaireProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //journal
    public ResponseEntity<Object> afficherJournal(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<JournalProjection2> journalProjection2s = libraryDao.journal(
                request.getIdOpcvm(), request.getCodeJournal(), request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Journal.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


        LocalDateTime dateTime = request.getDateDebut();
        Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date dateDebut = Date.from(i);

        dateTime = request.getDateFin();
        i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date dateFin = Date.from(i);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(journalProjection2s)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                journalProjection2s);


    }

    //Balance avant inventaire
    public ResponseEntity<?> afficherBalanceAvantInventaire(BalanceRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<BalanceProjection> balancePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                balancePage = new PageImpl<>(new ArrayList<>());
            }
            else {
               balancePage = libraryDao.balanceAvantInventaire(
                       request.getCodePlan(), request.getIdOpcvm(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<BalanceProjection> content = balancePage.getContent().stream().toList();
            DataTablesResponse<BalanceProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)balancePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)balancePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Balance opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherBalanceAvantInventaire(BalanceRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<BalanceProjection> balanceProjections = libraryDao.balanceAvantInventaire(
                request.getCodePlan(),request.getIdOpcvm(), request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Balance.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(balanceProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                balanceProjections);


    }
    public ResponseEntity<?> afficherBalanceAvantInventaireListe(BalanceRequest request) {
        try
        {
            List<BalanceProjection> balanceProjections=libraryDao.balanceAvantInventaire(
                    request.getCodePlan(), request.getIdOpcvm(),request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Balance opcvm",
                    HttpStatus.OK,
                    balanceProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    //Balance
    public ResponseEntity<?> afficherBalance(BalanceRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<BalanceProjection> balancePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                balancePage = new PageImpl<>(new ArrayList<>());
            }
            else {
               balancePage = libraryDao.balance(
                       request.getCodePlan(), request.getIdOpcvm(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<BalanceProjection> content = balancePage.getContent().stream().toList();
            DataTablesResponse<BalanceProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)balancePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)balancePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Balance opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherBalance(BalanceRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<BalanceProjection> balanceProjections = libraryDao.balance(
                request.getCodePlan(),request.getIdOpcvm(), request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Balance.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(balanceProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                balanceProjections);


    }
    public ResponseEntity<?> afficherBalanceListe(BalanceRequest request) {
        try
        {
            List<BalanceProjection> balanceProjections=libraryDao.balance(
                    request.getCodePlan(), request.getIdOpcvm(),request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Balance opcvm",
                    HttpStatus.OK,
                    balanceProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //GrandLivre
    public ResponseEntity<?> afficherGrandLivre(GrandLivreRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<GrandLivreProjection> grandLivrePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                grandLivrePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                grandLivrePage = libraryDao.grandLivre(
                        request.getIdOpcvm(), request.getCodePlan(), request.getNumCompteComptable(), request.getCodeAnalytique(), request.getTypeAnalytique(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<GrandLivreProjection> content = grandLivrePage.getContent().stream().toList();
            DataTablesResponse<GrandLivreProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)grandLivrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)grandLivrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Grand Livre opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherGrandLivre(GrandLivreRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<GrandLivreProjection> grandLivreProjections = libraryDao.grandLivre(
                request.getIdOpcvm(), request.getCodePlan(), request.getNumCompteComptable(), request.getCodeAnalytique(), request.getTypeAnalytique(), request.getDateDebut(), request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Grand_Livre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String Descrip = request.getDescrip();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("Descrip", opcvm.getDenominationOpcvm());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(grandLivreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                grandLivreProjections);


    }
    public ResponseEntity<?> afficherGrandLivreListe(GrandLivreRequest request) {
        try
        {
            List<GrandLivreProjection> grandLivreProjections=libraryDao.grandLivre(
                    request.getIdOpcvm(),request.getCodePlan(), request.getNumCompteComptable(), request.getCodeAnalytique(), request.getTypeAnalytique(), request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "GrandLivre opcvm",
                    HttpStatus.OK,
                    grandLivreProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //SoldeDesComptesComptable
    public ResponseEntity<?> afficherSoldeDesComptesComptables(SoldeDesComptesComptablesRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<SoldeDesComptesComptablesProjection> soldeDesComptesComptablesPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                soldeDesComptesComptablesPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                soldeDesComptesComptablesPage = libraryDao.soldeDesComptesComptables(
                        request.getCodePlan(), request.getIdOpcvm(),null, request.getDateEstimation(),
                        pageable
                );
            }
            List<SoldeDesComptesComptablesProjection> content = soldeDesComptesComptablesPage.getContent().stream().toList();
            DataTablesResponse<SoldeDesComptesComptablesProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)soldeDesComptesComptablesPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)soldeDesComptesComptablesPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Releve de Part FCP opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public void afficherSoldeDesComptesComptables(
            SoldeDesComptesComptablesRequest request,
            HttpServletResponse response
    ) throws JRException, IOException {

        // Exemple : récupère les données (à adapter selon ton DAO)
        List<SoldeDesComptesComptablesProjection> data =
                libraryDao.soldeDesComptesComptables(
                        request.getCodePlan(),
                        request.getIdOpcvm(),
                        null,
                        request.getDateEstimation()
                );

        // Charge le fichier JRXML
        InputStream rapportStream = getClass().getResourceAsStream("/Solde_Compte_Comptable.jrxml");
        if (rapportStream == null) {
            throw new RuntimeException("Fichier de rapport introuvable !");
        }

        // Compile et remplit le rapport
        JasperReport report = JasperCompileManager.compileReport(rapportStream);
        Map<String, Object> params = new HashMap<>();
        params.put("letterDate", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));

        JasperPrint print = JasperFillManager.fillReport(report, params, new JRBeanCollectionDataSource(data));

        // Écrit directement le flux PDF dans la réponse HTTP
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
    }
    public ResponseEntity<?> afficherSoldeDesComptesComptablesListe(SoldeDesComptesComptablesRequest request) {
        try
        {
            List<SoldeDesComptesComptablesProjection> soldeDesComptesComptablesProjections=libraryDao.soldeDesComptesComptables(
                    request.getCodePlan(), request.getIdOpcvm(),null,request.getDateEstimation()
            );
            return ResponseHandler.generateResponse(
                    "Solde des Comptes Comptables opcvm",
                    HttpStatus.OK,
                    soldeDesComptesComptablesProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //PointSouscriptionDetaille
    public ResponseEntity<?> afficherPointSouscriptionDetaille(PointSouscriptionDetailleRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointSouscriptionDetailleProjection> pointSouscriptionDetaillePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointSouscriptionDetaillePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointSouscriptionDetaillePage = libraryDao.pointSouscriptionDetaille(
                        request.getIdOpcvm(), null, null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<PointSouscriptionDetailleProjection> content = pointSouscriptionDetaillePage.getContent().stream().toList();
            DataTablesResponse<PointSouscriptionDetailleProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointSouscriptionDetaillePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointSouscriptionDetaillePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Point des Souscriptions Detaillees opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherPointSouscriptionDetaille(PointSouscriptionDetailleRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointSouscriptionDetailleProjection> pointSouscriptionDetailleProjections = libraryDao.pointSouscriptionDetaille(
                request.getIdOpcvm(), null, null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin());

                // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Point_Souscription_Detaille.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointSouscriptionDetailleProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointSouscriptionDetailleProjections);


    }
    public ResponseEntity<?> afficherPointSouscriptionDetailleListe(PointSouscriptionDetailleRequest request) {
        try
        {
            List<PointSouscriptionDetailleProjection> pointSouscriptionDetailleProjections=libraryDao.pointSouscriptionDetaille(
                    request.getIdOpcvm(), null, null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Point des Souscriptions Detaille opcvm",
                    HttpStatus.OK,
                    pointSouscriptionDetailleProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //PointSouscriptionGlobal
    public ResponseEntity<?> afficherPointSouscriptionGlobal(PointSouscriptionGlobalRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointSouscriptionGlobalProjection> pointSouscriptionGlobalPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointSouscriptionGlobalPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointSouscriptionGlobalPage = libraryDao.pointSouscriptionGlobal(
                        request.getIdOpcvm(),null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<PointSouscriptionGlobalProjection> content = pointSouscriptionGlobalPage.getContent().stream().toList();
            DataTablesResponse<PointSouscriptionGlobalProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointSouscriptionGlobalPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointSouscriptionGlobalPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Point des Souscriptions Global opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherPointSouscriptionGlobal(PointSouscriptionGlobalRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointSouscriptionGlobalProjection> pointSouscriptionGlobalProjections = libraryDao.pointSouscriptionGlobal(
                request.getIdOpcvm(), null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin());


        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Point_Souscription_Global.jrxml");
        subreportStream = getClass().getResourceAsStream("/Point_Souscription_Global_Recap.jrxml");

        if (rapportStream == null || subreportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        parameters.put("ALL_LIGNES", new JRBeanCollectionDataSource(pointSouscriptionGlobalProjections));
        parameters.put("SUBREPORT_REF", subreport); // Passer le sous-rapport compilé
        parameters.put("SUBREPORT_NAME", "Point_Souscription_Global_Recap.jrxml"); // Chemin relatif pour le sous-rapport

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointSouscriptionGlobalProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointSouscriptionGlobalProjections);
//
    }
    public ResponseEntity<?> afficherPointSouscriptionGlobalListe(PointSouscriptionGlobalRequest request) {
        try
        {
            List<PointSouscriptionGlobalProjection> pointSouscriptionGlobalProjections=libraryDao.pointSouscriptionGlobal(
                    request.getIdOpcvm(),null,request.getIdPersonne(), request.getDateDebut(), request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Point des Souscriptions Global opcvm",
                    HttpStatus.OK,
                    pointSouscriptionGlobalProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //PointRachatGlobal
    public ResponseEntity<?> afficherPointRachatGlobal(PointRachatGlobalRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointRachatGlobalProjection> pointRachatGlobalPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointRachatGlobalPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointRachatGlobalPage = libraryDao.pointRachatGlobal(
                        request.getIdOpcvm(), null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<PointRachatGlobalProjection> content = pointRachatGlobalPage.getContent().stream().toList();
            DataTablesResponse<PointRachatGlobalProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointRachatGlobalPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointRachatGlobalPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Point des Rachats Detaillees opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherPointRachatGlobal(PointRachatGlobalRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointRachatGlobalProjection> pointRachatGlobalProjections = libraryDao.pointRachatGlobal(
                request.getIdOpcvm(), null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin());


        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Point_Rachats_Global.jrxml");
        subreportStream = getClass().getResourceAsStream("/Point_Rachats_Global_Recap.jrxml");

        if (rapportStream == null || subreportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        if(pointRachatGlobalProjections.isEmpty()|| pointRachatGlobalProjections.size()==2){
            parameters.put("ALL_LIGNES", new JRBeanCollectionDataSource(pointRachatGlobalProjections));
        }
        else
        {
            List<PointRachatGlobalDto>  pointRachatGlobalDtos=new ArrayList<>();
            for( PointRachatGlobalProjection o:pointRachatGlobalProjections){
                PointRachatGlobalDto pointRachatGlobalDto=new PointRachatGlobalDto();
                if(o.getLibelleTypePersonne()=="PERSONNE MORALE"){
                    pointRachatGlobalDto.setLibelleTypePersonne("PERSONNE PHYSIQUE");
                    pointRachatGlobalDto.setNombrePartSousRachat(BigDecimal.ZERO);
                    pointRachatGlobalDto.setCommisiionSousRachat(BigDecimal.ZERO);
                    pointRachatGlobalDto.setMontantSousALiquider(BigDecimal.ZERO);
                    pointRachatGlobalDto.setRetrocessionSousRachat(BigDecimal.ZERO);
                    pointRachatGlobalDtos.add(pointRachatGlobalDto);
                }
                else
                {
                    pointRachatGlobalDto.setLibelleTypePersonne("PERSONNE MORALE");
                    pointRachatGlobalDto.setNombrePartSousRachat(BigDecimal.ZERO);
                    pointRachatGlobalDto.setCommisiionSousRachat(BigDecimal.ZERO);
                    pointRachatGlobalDto.setMontantSousALiquider(BigDecimal.ZERO);
                    pointRachatGlobalDto.setRetrocessionSousRachat(BigDecimal.ZERO);
                    pointRachatGlobalDtos.add(pointRachatGlobalDto);
                }
                pointRachatGlobalDto=new PointRachatGlobalDto();
                pointRachatGlobalDto.setLibelleTypePersonne(o.getLibelleTypePersonne());
                pointRachatGlobalDto.setNombrePartSousRachat(o.getNombrePartSousRachat());
                pointRachatGlobalDto.setCommisiionSousRachat(o.getCommisiionSousRachat());
                pointRachatGlobalDto.setMontantSousALiquider(o.getMontantSousALiquider());
                pointRachatGlobalDto.setRetrocessionSousRachat(o.getRetrocessionSousRachat());
                pointRachatGlobalDtos.add(pointRachatGlobalDto);
            }
            parameters.put("ALL_LIGNES", new JRBeanCollectionDataSource(pointRachatGlobalDtos));
            //List<PointRachatGlobalProjection>
        }

        parameters.put("SUBREPORT_REF", subreport); // Passer le sous-rapport compilé
        parameters.put("SUBREPORT_NAME", "Point_Souscription_Global_Recap.jrxml"); // Chemin relatif pour le sous-rapport

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointRachatGlobalProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointRachatGlobalProjections);


    }
    public ResponseEntity<?> afficherPointRachatGlobalListe(PointRachatGlobalRequest request) {
        try
        {
            List<PointRachatGlobalProjection> pointRachatGlobalProjections=libraryDao.pointRachatGlobal(
                    request.getIdOpcvm(), null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Point des Rachats Global opcvm",
                    HttpStatus.OK,
                    pointRachatGlobalProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //PointRachatDetaille
    public ResponseEntity<?> afficherPointRachatDetaille(PointRachatDetailleRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointRachatDetailleProjection> pointRachatDetaillePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointRachatDetaillePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointRachatDetaillePage = libraryDao.pointRachatDetaille(
                        request.getIdOpcvm(), null, null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin(),
                        pageable
                );
            }
            List<PointRachatDetailleProjection> content = pointRachatDetaillePage.getContent().stream().toList();
            DataTablesResponse<PointRachatDetailleProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointRachatDetaillePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointRachatDetaillePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Point des Rachats Detaillees opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherPointRachatDetaille(PointRachatDetailleRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointRachatDetailleProjection> pointRachatDetailleProjections = libraryDao.pointRachatDetaille(
                request.getIdOpcvm(), null, null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Point_Rachats_Detaille.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointRachatDetailleProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointRachatDetailleProjections);


    }
    public ResponseEntity<?> afficherPointRachatDetailleListe(PointRachatDetailleRequest request) {
        try
        {
            List<PointRachatDetailleProjection> pointRachatDetailleProjections=libraryDao.pointRachatDetaille(
                    request.getIdOpcvm(), null, null, request.getIdPersonne(), request.getDateDebut(), request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Point des Rachats Detaille opcvm",
                    HttpStatus.OK,
                    pointRachatDetailleProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //EtatFinancierAnnuelF1Bilan
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1Bilan(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1BilanProjection> etatFinancierAnnuelF1BilanProjections = libraryDao.etatFinancierAnnuelF1Bilan(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Bilan.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1BilanProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1BilanProjections);


    }

    //EtatFinancierAnnuelF1Resultat
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1Resultat(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1ResultatProjection> etatFinancierAnnuelF1ResultatProjections = libraryDao.etatFinancierAnnuelF1Resultat(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Resultat.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1ResultatProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1ResultatProjections);


    }

    //EtatFinancierAnnuelF1EtatVariationActifNet
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1EtatVariationActifNet(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1EtatVariationActifNetProjection> etatFinancierAnnuelF1EtatVariationActifNetProjections = libraryDao.etatFinancierAnnuelF1EtatVariationActifNet(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Variation_Actif_Net.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1EtatVariationActifNetProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1EtatVariationActifNetProjections);


    }

    //EtatFinancierAnnuelF1NotesRevenusPortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1NotesRevenusPortefeuilleTitre(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1NotesRevenusPortefeuilleTitreProjection> etatFinancierAnnuelF1NotesRevenusPortefeuilleTitreProjections = libraryDao.etatFinancierAnnuelF1NotesRevenusPortefeuilleTitre(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Notes_Revenus_Portefeuille_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1NotesRevenusPortefeuilleTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1NotesRevenusPortefeuilleTitreProjections);


    }

    //EtatFinancierAnnuelF1NotesRevenusPlacementsMonetaires
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1NotesRevenusPlacementsMonetaires(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1NotesRevenusPlacementsMonetairesProjection> etatFinancierAnnuelF1NotesRevenusPlacementsMonetairesProjections = libraryDao.etatFinancierAnnuelF1NotesRevenusPlacementsMonetaires(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Notes_Revenus_Placements_Monetaires.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1NotesRevenusPlacementsMonetairesProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1NotesRevenusPlacementsMonetairesProjections);


    }

    //EtatFinancierAnnuelF1NotesSommesDistribuables
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1NotesSommesDistribuables(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1NotesSommesDistribuablesProjection> etatFinancierAnnuelF1NotesSommesDistribuablesProjections = libraryDao.etatFinancierAnnuelF1NotesSommesDistribuables(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Notes_Sommes_Distribuables.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1NotesSommesDistribuablesProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1NotesSommesDistribuablesProjections);


    }

    //EtatFinancierAnnuelF1DonneesActionRatiosPertinents
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1DonneesActionRatiosPertinents(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1DonneesActionRatiosPertinentsProjection> etatFinancierAnnuelF1DonneesActionRatiosPertinentsProjections = libraryDao.etatFinancierAnnuelF1DonneesActionRatiosPertinents(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Donnees_Action_Ratios_Pertinents.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1DonneesActionRatiosPertinentsProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1DonneesActionRatiosPertinentsProjections);


    }

    //EtatFinancierAnnuelF1EngagementHorsBilan
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF1EngagementHorsBilan(EtatFinancierAnnuelF1EngagementHorsBilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF1EngagementHorsBilanProjection> etatFinancierAnnuelF1EngagementHorsBilanProjections = libraryDao.etatFinancierAnnuelF1EngagementHorsBilan(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F1_Engagement_Hors_Bilan.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime date1 = request.getDateEstimation();
        parameters.put("date1", date1);
        LocalDateTime dateEstimation = request.getDateEstimation(); // supposons que c’est un LocalDate
        int anneePrecedente = dateEstimation.minusYears(1).getYear();
        LocalDateTime date2 = LocalDateTime.parse(anneePrecedente+"-12-31T00:00:00");

        //LocalDateTime date2 = request.getDate2();
        parameters.put("date2", date2);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF1EngagementHorsBilanProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF1EngagementHorsBilanProjections);


    }

    //EtatFinancierAnnuelF2Bilan
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2Bilan(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2BilanProjection> etatFinancierAnnuelF2BilanProjections = libraryDao.etatFinancierAnnuelF2Bilan(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Bilan.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2BilanProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2BilanProjections);


    }

    //EtatFinancierAnnuelF2Resultat
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2Resultat(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2ResultatProjection> etatFinancierAnnuelF2ResultatProjections = libraryDao.etatFinancierAnnuelF2Resultat(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Resultat.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2ResultatProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2ResultatProjections);


    }

    //EtatFinancierAnnuelF2EtatVariationActifNet
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2EtatVariationActifNet(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2EtatVariationActifNetProjection> etatFinancierAnnuelF2EtatVariationActifNetProjections = libraryDao.etatFinancierAnnuelF2EtatVariationActifNet(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Etat_Variation_Actif_Net.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2EtatVariationActifNetProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2EtatVariationActifNetProjections);


    }

    //EtatFinancierAnnuelF2NotesRevenusPortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2NotesRevenusPortefeuilleTitre(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2NotesRevenusPortefeuilleTitreProjection> etatFinancierAnnuelF2NotesRevenusPortefeuilleTitreProjections = libraryDao.etatFinancierAnnuelF2NotesRevenusPortefeuilleTitre(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Notes_Revenus_Portefeuille_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2NotesRevenusPortefeuilleTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2NotesRevenusPortefeuilleTitreProjections);


    }

    //EtatFinancierAnnuelF2NotesRevenusPlacementsMonetaires
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2NotesRevenusPlacementsMonetaires(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2NotesRevenusPlacementsMonetairesProjection> etatFinancierAnnuelF2NotesRevenusPlacementsMonetairesProjections = libraryDao.etatFinancierAnnuelF2NotesRevenusPlacementsMonetaires(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Notes_Revenus_Placements_Monetaires.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2NotesRevenusPlacementsMonetairesProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2NotesRevenusPlacementsMonetairesProjections);


    }

    //EtatFinancierAnnuelF2NotesSommesDistribuables
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2NotesSommesDistribuables(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2NotesSommesDistribuablesProjection> etatFinancierAnnuelF2NotesSommesDistribuablesProjections = libraryDao.etatFinancierAnnuelF2NotesSommesDistribuables(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Notes_Sommes_Distribuable.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2NotesSommesDistribuablesProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2NotesSommesDistribuablesProjections);


    }

    //EtatFinancierAnnuelF2DonneesActionRatiosPertinents
    public ResponseEntity<Object> afficherEtatFinancierAnnuelF2DonneesActionRatiosPertinents(EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnuelF2DonneesActionRatiosPertinentsProjection> etatFinancierAnnuelF2DonneesActionRatiosPertinentsProjections = libraryDao.etatFinancierAnnuelF2DonneesActionRatiosPertinents(
                request.getIdOpcvm(), request.getAnnee());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_F2_Donnees_Action_Ratios_Pertinents.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getAnnee().toString();
        parameters.put("exercice", exercice);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnuelF2DonneesActionRatiosPertinentsProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnuelF2DonneesActionRatiosPertinentsProjections);


    }

    //EtatFinancierAnnexesEtatsEntreesPortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierAnnexesNotesEtatsFinanaciers(EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annuel_Annexes_Notes_Etas_Financiers_Annuels.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime localDateTime =  request.getDateDebut(); // ton LocalDateTime
        ZoneId zone = ZoneId.systemDefault(); // ou ZoneId.of("Africa/Porto-Novo") si tu veux un fuseau spécifique

        // Conversion
        Date date = Date.from(localDateTime.atZone(zone).toInstant());

        parameters.put("dateEstimation", date);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(null)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                null);


    }
//EtatFinancierAnnexesEtatsEntreesPortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierAnnexesEtatsEntreesPortefeuilleTitre(EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        CleExercice cleExercice=new CleExercice();
        cleExercice.setIdOpcvm(request.getIdOpcvm());
        cleExercice.setCodeExercice(request.getExercice());
        ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.findById(cleExercice).orElseThrow());

        List<EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreProjection> etatFinancierAnnexesEtatsEntreesPortefeuilleTitreProjections = libraryDao.etatFinancierAnnexesEtatsEntreesPortefeuilleTitre(
                request.getIdOpcvm(), exerciceDto.getDateDebut(), exerciceDto.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Etats_Entrees_Portefeuille_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getExercice();
        parameters.put("exercice", exercice);

        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesEtatsEntreesPortefeuilleTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesEtatsEntreesPortefeuilleTitreProjections);


    }

    //EtatFinancierAnnexesEtatSortiesPortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierAnnexesEtatSortiesPortefeuilleTitre(EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        CleExercice cleExercice=new CleExercice();
        cleExercice.setIdOpcvm(request.getIdOpcvm());
        cleExercice.setCodeExercice(request.getExercice());
        ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.findById(cleExercice).orElseThrow());

        List<EtatFinancierAnnexesEtatSortiesPortefeuilleTitreProjection> etatFinancierAnnexesEtatSortiesPortefeuilleTitreProjections = libraryDao.etatFinancierAnnexesEtatSortiesPortefeuilleTitre(
                request.getIdOpcvm(), exerciceDto.getDateDebut(), exerciceDto.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Etat_Sorties_Portefeuille_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String exercice = request.getExercice();
        parameters.put("exercice", exercice);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesEtatSortiesPortefeuilleTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesEtatSortiesPortefeuilleTitreProjections);


    }

    //EtatFinancierAnnexesNotePortefeuilleTitresAnnuel
    public ResponseEntity<Object> afficherEtatFinancierAnnexesNotePortefeuilleTitresAnnuel(EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnexesNotePortefeuilleTitresAnnuelProjection> etatFinancierAnnexesNotePortefeuilleTitresAnnuelProjections = libraryDao.etatFinancierAnnexesNotePortefeuilleTitresAnnuel(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Note_Portefeuille_Titres_Annuel.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        String periodicite = request.getPeriodicite();
        parameters.put("periodicite",periodicite);
//        LocalDateTime dateEstimation = request.getDateEstimation();

        LocalDateTime localDateTime =  request.getDateEstimation(); // ton LocalDateTime
        ZoneId zone = ZoneId.systemDefault(); // ou ZoneId.of("Africa/Porto-Novo") si tu veux un fuseau spécifique

        // Conversion
        Date date = Date.from(localDateTime.atZone(zone).toInstant());
        parameters.put("dateEstimation",date);
//        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesNotePortefeuilleTitresAnnuelProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesNotePortefeuilleTitresAnnuelProjections);


    }

    //EtatFinancierAnnexesNotePlacementsMonetairesAnnuel
    public ResponseEntity<Object> afficherEtatFinancierAnnexesNotePlacementsMonetairesAnnuel(EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnexesNotePlacementsMonetairesAnnuelProjection> etatFinancierAnnexesNotePlacementsMonetairesAnnuelProjections = libraryDao.etatFinancierAnnexesNotePlacementsMonetairesAnnuel(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Note_Placements_Monetaires_Annuel.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        String periodicite = request.getPeriodicite();
        parameters.put("periodicite",periodicite);
        LocalDateTime localDateTime =  request.getDateEstimation(); // ton LocalDateTime
        ZoneId zone = ZoneId.systemDefault(); // ou ZoneId.of("Africa/Porto-Novo") si tu veux un fuseau spécifique

        // Conversion
        Date date = Date.from(localDateTime.atZone(zone).toInstant());
        parameters.put("dateEstimation",date);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesNotePlacementsMonetairesAnnuelProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesNotePlacementsMonetairesAnnuelProjections);


    }

    //EtatFinancierAnnexesNotesurleCapital
    public ResponseEntity<Object> afficherEtatFinancierAnnexesNotesurleCapital(EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnexesNotesurleCapitalProjection> etatFinancierAnnexesNotesurleCapitalProjections = libraryDao.etatFinancierAnnexesNotesurleCapital(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Note_sur_le_Capital.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String periodicite = request.getPeriodicite();
//        parameters.put("periodicite",periodicite);
        LocalDateTime dateEstimation = request.getDateEstimation();
        LocalDateTime firstDayOfMonth = LocalDateTime.of(
                dateEstimation.getYear(),
                dateEstimation.plusMonths(periodicite.equals("Trimestre") ? -2 : -5).getMonth(),
                1,
                0, 0
        );

// Formatage des dates
        DateTimeFormatter fmtShort = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.FRENCH);
        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);

// txt_capital2
        LocalDateTime txt_capital2 = dateEstimation;

// txt_capital1
        LocalDateTime txt_capital1 =LocalDateTime.parse(dateEstimation.minusYears(1).getYear()+"-12-31T00:00:00");

// txt_periode
        String txt_periode;
        if (periodicite.trim().equals("Trimestre") || periodicite.trim().equals("Semestre")) {
            txt_periode = "(Du " + firstDayOfMonth.format(fmtLong)
                    + " au " + dateEstimation.format(fmtLong) + ")";
        } else {
            LocalDateTime dateDebut = dateEstimation.minusMonths(12).plusDays(1);
            txt_periode = "(Du " + dateDebut.format(fmtLong)
                    + " au " + dateEstimation.format(fmtLong) + ")";
        }

        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        parameters.put("Capital2",txt_capital2);
        parameters.put("Capital1", txt_capital1);
        parameters.put("periodicite", txt_periode);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesNotesurleCapitalProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesNotesurleCapitalProjections);


    }

    //EtatFinancierAnnexesActionAdmiseCote
    public ResponseEntity<Object> afficherEtatFinancierAnnexesActionAdmiseCote(EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnexesActionAdmiseCoteProjection> etatFinancierAnnexesActionAdmiseCoteProjections = libraryDao.etatFinancierAnnexesActionAdmiseCote(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Action_Admise_a_la_Cote.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String periodicite = request.getPeriodicite();
        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);

// Construction de la période

        String txt_periode;
        LocalDateTime dateEstimation = request.getDateEstimation();
        LocalDateTime firstDayOfMonth = LocalDateTime.of(
                dateEstimation.getYear(),
                dateEstimation.plusMonths(periodicite.equals("Trimestre") ? -2 : -5).getMonth(),
                1,
                0, 0
        );
        if (!periodicite.trim().equals("Annuel")) {
            txt_periode = "(Du " + firstDayOfMonth.format(fmtLong)
                    + " au " + dateEstimation.format(fmtLong) + ")";
        } else {
            txt_periode = "(Du 01 Janvier " + dateEstimation.getYear()
                    + " au 31 Décembre " + dateEstimation.getYear() + ")";
        }
        parameters.put("periodicite",txt_periode);

//        parameters.put("dateEstimation",dateEstimation);
//        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesActionAdmiseCoteProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesActionAdmiseCoteProjections);


    }

    //EtatFinancierAnnexesRemunerationGestionnaireDepositaire
    public ResponseEntity<Object> afficherEtatFinancierAnnexesRemunerationGestionnaireDepositaire(EtatFinancierAnnexesRemunerationGestionnaireDepositaireRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierAnnexesRemunerationGestionnaireDepositaireProjection> etatFinancierAnnexesRemunerationGestionnaireDepositaireProjections = libraryDao.etatFinancierAnnexesRemunerationGestionnaireDepositaire(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Annexes_Remuneration_Gestionnaire_et_Depositaire.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
//        SocieteDeGestionDto societeDeGestionDto=societeDeGestionMapper.deSocieteDeGestion(societeDeGestionDao.findById(
//                504
//        ))
        String raisonSocial = request.getRaisonSocial();
        parameters.put("raisonSocial",raisonSocial);
        LocalDateTime dateEstimation = request.getDateEstimation();
        parameters.put("dateEstimation",dateEstimation);

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.FRENCH);
        // Construction de la chaîne
        String titre = "(Du 31/12/" + (dateEstimation.getYear() - 1)
                + " au " + dateEstimation.format(fmtLong) + ")";

        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm", opcvm.getDenominationOpcvm());
        parameters.put("titre",titre);
        String libellePersonneIntervenant = opcvm.getPersonneIntervenant().getDenomination();
        parameters.put("libellePersonneIntervenant",libellePersonneIntervenant);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierAnnexesRemunerationGestionnaireDepositaireProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierAnnexesRemunerationGestionnaireDepositaireProjections);


    }

    //EtatFinancierTrimestrielBilanTrimestriel
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielBilanTrimestriel(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielBilanTrimestrielProjection> etatFinancierTrimestrielBilanTrimestrielProjections = libraryDao.etatFinancierTrimestrielBilanTrimestriel(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Bilan_Trimestriel.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periode", periode);

        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielBilanTrimestrielProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielBilanTrimestrielProjections);


    }

    //EtatFinancierTrimestrielCompteResultat
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielCompteResultat(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielCompteResultatProjection> etatFinancierTrimestrielCompteResultatProjections = libraryDao.etatFinancierTrimestrielCompteResultat(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Compte_Resultat.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periode", periode);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielCompteResultatProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielCompteResultatProjections);


    }

    //EtatFinancierTrimestrielVariationActifNet
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielVariationActifNet(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielVariationActifNetProjection> etatFinancierTrimestrielVariationActifNetProjections = libraryDao.etatFinancierTrimestrielVariationActifNet(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Variation_Actif_Net.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periode", periode);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielVariationActifNetProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielVariationActifNetProjections);


    }

    //EtatFinancierTrimestrielNotesRevenusPlacementsMonetaires
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielNotesRevenusPlacementsMonetaires(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielNotesRevenusPlacementsMonetairesProjection> etatFinancierTrimestrielNotesRevenusPlacementsMonetairesProjections = libraryDao.etatFinancierTrimestrielNotesRevenusPlacementsMonetaires(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Notes_Revenus_Placements_Monetaires.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periode", periode);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielNotesRevenusPlacementsMonetairesProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielNotesRevenusPlacementsMonetairesProjections);


    }

    //EtatFinancierTrimestrielNoteRevenusPortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielNoteRevenusPortefeuilleTitre(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielNoteRevenusPortefeuilleTitreProjection> etatFinancierTrimestrielNoteRevenusPortefeuilleTitreProjections = libraryDao.etatFinancierTrimestrielNoteRevenusPortefeuilleTitre(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Note_Revenus_Portefeuille_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periode", periode);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielNoteRevenusPortefeuilleTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielNoteRevenusPortefeuilleTitreProjections);


    }

    //EtatFinancierTrimestrielTableauAnalyseVL
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielTableauAnalyseVL(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielTableauAnalyseVLProjection> etatFinancierTrimestrielTableauAnalyseVLProjections = libraryDao.etatFinancierTrimestrielTableauAnalyseVL(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Tableau_Analyse_VL.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        List<SocieteDeGestionDto>  societeDeGestionDtoList=societeDeGestionDao.findAll().stream().map(societeDeGestionMapper::deSocieteDeGestion).toList();
        String raisonSocial = societeDeGestionDtoList.get(0).getRaisonSociale();
        parameters.put("raisonSocial",raisonSocial);
        LocalDateTime dateEstimation = request.getDateEstimation();
        parameters.put("dateEstimation",dateEstimation);
        String format = request.getFormat();
        parameters.put("format",format);
        //String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielTableauAnalyseVLProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielTableauAnalyseVLProjections);


    }

    //EtatFinancierTrimestrielNotePortefeuilleTitre
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielNotePortefeuilleTitre(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielNotePortefeuilleTitreProjection> etatFinancierTrimestrielNotePortefeuilleTitreProjections = libraryDao.etatFinancierTrimestrielNotePortefeuilleTitre(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Note_Portefeuille_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periodicite", periode);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielNotePortefeuilleTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielNotePortefeuilleTitreProjections);


    }

    //EtatFinancierTrimestrielNotePlacementsMonetaires
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielNotePlacementsMonetaires(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielNotePlacementsMonetairesProjection> etatFinancierTrimestrielNotePlacementsMonetairesProjections = libraryDao.etatFinancierTrimestrielNotePlacementsMonetaires(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Note_Placements_Monetaires.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();
        parameters.put("dateEstimation", dateEstimation);
        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periodicite", periode);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielNotePlacementsMonetairesProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielNotePlacementsMonetairesProjections);


    }

    //EtatFinancierTrimestrielActionsAdmisesCote
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielActionsAdmisesCote(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielActionsAdmisesCoteProjection> etatFinancierTrimestrielActionsAdmisesCoteProjections = libraryDao.etatFinancierTrimestrielActionsAdmisesCote(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Actions_Admises_Cote.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periodicite", periode);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielActionsAdmisesCoteProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielActionsAdmisesCoteProjections);


    }

    //EtatFinancierTrimestrielNoteCapital
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielNoteCapital(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielNoteCapitalProjection> etatFinancierTrimestrielNoteCapitalProjections = libraryDao.etatFinancierTrimestrielNoteCapital(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Note_sur_le_Capital.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periodicite", periode);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        String libellePays = opcvm.getPays().getLibelleFr();
        parameters.put("libellePays",libellePays);
        parameters.put("capital1","Capital au 31/12/"+(dateEstimation.getYear()-1));
        parameters.put("capital2","Capital au "+(dateEstimation.format(fmtLong)));

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielNoteCapitalProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielNoteCapitalProjections);


    }

    //EtatFinancierTrimestrielMontantFraisGestion
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielMontantFraisGestion(EtatFinancierTrimestrielMontantFraisGestionRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFinancierTrimestrielMontantFraisGestionProjection> etatFinancierTrimestrielMontantFraisGestionProjections = libraryDao.etatFinancierTrimestrielMontantFraisGestion(
                request.getIdOpcvm(), request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Montant_Frais_Gestion.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        //DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        List<SocieteDeGestionDto> societeDeGestionDtoList=societeDeGestionDao.findAll().stream()
                .map(societeDeGestionMapper::deSocieteDeGestion).toList();
        String raisonSocial = societeDeGestionDtoList.get(0).getRaisonSociale();
        parameters.put("raisonSocial",raisonSocial);
        LocalDateTime dateEstimation = request.getDateEstimation();
        parameters.put("dateEstimation",dateEstimation);
        String format = request.getFormat();
        parameters.put("format",format);

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielMontantFraisGestionProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielMontantFraisGestionProjections);


    }
    //EtatFinancierTrimestrielNoteAuxEtasFinanciers
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielNoteAuxEtatsFinanciers(EtatFinancierTrimestrielMontantFraisGestionRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Note_Etats_Fianciers.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateEstimation = request.getDateEstimation();
        parameters.put("dateEstimation",dateEstimation);
        String periodicite=request.getPeriodicite();

        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        int mois=periodicite.equals("Trimestriel")?dateEstimation.getMonth().getValue() - 2:dateEstimation.getMonth().getValue() - 5;
        String periode = "(Du 01-" + (mois<10?"0"+mois:mois)+"-"+dateEstimation.getYear()
                + " au " + dateEstimation.format(fmtLong) + ")";
        parameters.put("periode1", periode);


        String periode2 =  dateEstimation.getDayOfMonth() + " "
                + dateEstimation.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        parameters.put("periode2", periode2);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(null)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                null);


    }

    //EtatFinancierTrimestrielEtatMensuelSouscriptions
    public ResponseEntity<Object> afficherEtatFinancierTrimestrielEtatMensuelSouscriptions(EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
       LocalDateTime dateEstimation = request.getDateEstimation();
        LocalDateTime dateDebut=request.getFormat().equals("Semestriel")?dateEstimation.plusMonths(-5):
                dateEstimation.plusMonths(-1).plusDays(1);
        List<EtatFinancierTrimestrielEtatMensuelSouscriptionsProjection> etatFinancierTrimestrielEtatMensuelSouscriptionsProjections = libraryDao.etatFinancierTrimestrielEtatMensuelSouscriptions(
                request.getIdOpcvm(), dateDebut, request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Etat_Financier_Trimestriel_Etat_Mensuel_Souscriptions.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        parameters.put("dateEstimation",dateEstimation);
        List<SocieteDeGestionDto>  societeDeGestionDtoList=societeDeGestionDao.findAll().stream().map(societeDeGestionMapper::deSocieteDeGestion).toList();
        String raisonSocial = societeDeGestionDtoList.get(0).getRaisonSociale();
        parameters.put("raisonSocial",raisonSocial);
        String format = request.getFormat();
        parameters.put("format",format);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());
        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        parameters.put("periodicite",format.equals("Semestriel") ? " Semestre : Du " + dateEstimation.plusMonths(-5) :
        (" Mois : Du " + dateEstimation.plusMonths(-1).plusDays(1).format(fmtLong))
                + " au " + dateEstimation.format(fmtLong) + "");
        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFinancierTrimestrielEtatMensuelSouscriptionsProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFinancierTrimestrielEtatMensuelSouscriptionsProjections);


    }

    //DeclarationCommissionActif
    public ResponseEntity<?> afficherDeclarationCommissionActif(DeclarationCommissionActifRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<DeclarationCommissionActifProjection> declarationCommissionActifPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                declarationCommissionActifPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                declarationCommissionActifPage = libraryDao.declarationCommissionActif(
                        request.getIdOpcvm(), request.getDateDebut(), request.getDateFin(), request.getTrimestre(),
                        request.getAnneeExo(), request.getTaux(), pageable
                );
            }
            List<DeclarationCommissionActifProjection> content = declarationCommissionActifPage.getContent().stream().toList();
            DataTablesResponse<DeclarationCommissionActifProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)declarationCommissionActifPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)declarationCommissionActifPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Declaration-Comission sur Actif opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherDeclarationCommissionActif(DeclarationCommissionActifRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<DeclarationCommissionActifProjection> declarationCommissionActifProjections = libraryDao.declarationCommissionActif(
                request.getIdOpcvm(), request.getDateDebut(), request.getDateFin(), request.getTrimestre(),
                request.getAnneeExo(), request.getTaux());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Declaration_Commission_sur_Actif.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(declarationCommissionActifProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                declarationCommissionActifProjections);


    }
    public ResponseEntity<?> afficherDeclarationCommissionActifListe(DeclarationCommissionActifRequest request) {
        try
        {
            List<DeclarationCommissionActifProjection> declarationCommissionActifProjections=libraryDao.declarationCommissionActif(
                    request.getIdOpcvm(), request.getDateDebut(), request.getDateFin(), request.getTrimestre(),
                    request.getAnneeExo(), request.getTaux()
            );
            return ResponseHandler.generateResponse(
                    "Solde des Comptes Comptables opcvm",
                    HttpStatus.OK,
                    declarationCommissionActifProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //PointInvestissement
    public ResponseEntity<?> afficherPointInvestissement(PointInvestissementRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointInvestissementProjection> pointInvestissementPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointInvestissementPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointInvestissementPage = libraryDao.pointInvestissement(
                        request.getDateDeb(), request.getDateFin(), request.getIdOpcvm(),request.getTypeOp(), pageable
                );
            }
            List<PointInvestissementProjection> content = pointInvestissementPage.getContent().stream().toList();
            DataTablesResponse<PointInvestissementProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointInvestissementPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointInvestissementPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Point des investissements / désinvestissements sur une période opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherPointInvestissement(PointInvestissementRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointInvestissementProjection> pointInvestissementProjections = libraryDao.pointInvestissement(
                request.getDateDeb(), request.getDateFin(), request.getIdOpcvm(),request.getTypeOp());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Point_Investissement.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        DateTimeFormatter fmtLong = DateTimeFormatter.ofPattern("dd-MM-yyyy", Locale.FRENCH);
        String periode="POINT DES "+ (request.getTypeOp().trim().equals("TOUT")?"INVESTISSEMENTS/DESINVESTISSEMENTS":request.getTypeOp().trim()) +" PERIODIQUES DU " + request.getDateDeb().format(fmtLong) + " AU " + request.getDateFin().format(fmtLong);
        parameters.put("letterDate", letterDate);
        parameters.put("periode", periode);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointInvestissementProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointInvestissementProjections);


    }
    public ResponseEntity<?> afficherPointInvestissementListe(PointInvestissementRequest request) {
        try
        {
            List<PointInvestissementProjection> pointInvestissementProjections=libraryDao.pointInvestissement(
                    request.getDateDeb(), request.getDateFin(), request.getIdOpcvm(),request.getTypeOp()
            );
            return ResponseHandler.generateResponse(
                    "Point des investissements / désinvestissements sur une période opcvm",
                    HttpStatus.OK,
                    pointInvestissementProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //PrevisionnelRemboursements
    public ResponseEntity<?> afficherPrevisionnelRemboursements(PointInvestissementRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PrevisionnelRemboursementsProjection> previsionnelRemboursementsPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                previsionnelRemboursementsPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                previsionnelRemboursementsPage = libraryDao.previsionnelRemboursements(
                         request.getIdOpcvm(),request.getEchue(),request.getTraiter(),request.getDetache(),request.getDateDeb(), request.getDateFin(), pageable
                );
            }
            List<PrevisionnelRemboursementsProjection> content = previsionnelRemboursementsPage.getContent().stream().toList();
            DataTablesResponse<PrevisionnelRemboursementsProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)previsionnelRemboursementsPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)previsionnelRemboursementsPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Previsionnel des remboursements opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public void afficherPrevisionnelRemboursements(PointInvestissementRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PrevisionnelRemboursementsProjection> previsionnelRemboursementsProjections = libraryDao.previsionnelRemboursements(
                request.getIdOpcvm(),request.getEchue(),request.getTraiter(),request.getDetache(),request.getDateDeb(), request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Previsionnel_des_Remboursements.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(previsionnelRemboursementsProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
//        return ResponseHandler.generateResponse(
//                "Ordre de bourse",
//                HttpStatus.OK,
//                previsionnelRemboursementsProjections);


    }
    public ResponseEntity<?> afficherPrevisionnelRemboursementsListe(PointInvestissementRequest request) {
        try
        {
            List<PrevisionnelRemboursementsProjection> previsionnelRemboursementsProjections=libraryDao.previsionnelRemboursements(
                    request.getIdOpcvm(),request.getEchue(),request.getTraiter(),request.getDetache(),request.getDateDeb(), request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Previsionnel des Remboursements opcvm",
                    HttpStatus.OK,
                    previsionnelRemboursementsProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //SuiviEcheanceTitre
    public ResponseEntity<?> afficherSuiviEcheanceTitre(PointInvestissementRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<SuiviEcheanceTitreProjection> suiviEcheanceTitrePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                suiviEcheanceTitrePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                suiviEcheanceTitrePage = libraryDao.suiviEcheanceTitre(
                        request.getIdOpcvm(),request.getDateEstimation(), pageable
                );
            }
            List<SuiviEcheanceTitreProjection> content = suiviEcheanceTitrePage.getContent().stream().toList();
            DataTablesResponse<SuiviEcheanceTitreProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)suiviEcheanceTitrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)suiviEcheanceTitrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Suivi echeance des titres opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public void afficherSuiviEcheanceTitre(PointInvestissementRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<SuiviEcheanceTitreProjection> suiviEcheanceTitreProjections = libraryDao.suiviEcheanceTitre(
                request.getIdOpcvm(),request.getDateEstimation());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Suivi_Echeance_Titre.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        LocalDateTime dateOuverture = request.getDateOuverture();
        String denominationOpcvm = request.getDenominationOpcvm();
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("denominationOpcvm",opcvm.getDenominationOpcvm());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(suiviEcheanceTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());



    }
    public ResponseEntity<?> afficherSuiviEcheanceTitreListe(PointInvestissementRequest request) {
        try
        {
            List<SuiviEcheanceTitreProjection> suiviEcheanceTitreProjections=libraryDao.suiviEcheanceTitre(
                    request.getIdOpcvm(), request.getDateEstimation()
            );
            return ResponseHandler.generateResponse(
                    "Suivi echeance des titres opcvm",
                    HttpStatus.OK,
                    suiviEcheanceTitreProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //AvisTransfertPart
    public ResponseEntity<?> afficherAvisTransfertPart(AvisTransfertPartRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<AvisTransfertPartProjection> avisTransfertPartPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                avisTransfertPartPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                avisTransfertPartPage = libraryDao.avisTransfertPart(
                        request.getIdOperation(),request.getIdOpcvm(),request.getDateDeb(),request.getDateFin(), pageable
                );
            }
            List<AvisTransfertPartProjection> content = avisTransfertPartPage.getContent().stream().toList();
            DataTablesResponse<AvisTransfertPartProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)avisTransfertPartPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)avisTransfertPartPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Avis de transfert de parts opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherAvisTransfertPart(AvisTransfertPartRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<AvisTransfertPartProjection> avisTransfertPartProjections = libraryDao.avisTransfertPart(
                request.getIdOperation(),request.getIdOpcvm(),request.getDateDeb(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Avis_Transfert_de_Part.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(avisTransfertPartProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                avisTransfertPartProjections);


    }
    public ResponseEntity<?> afficherAvisTransfertPartListe(AvisTransfertPartRequest request) {
        try
        {
            List<AvisTransfertPartProjection> avisTransfertPartProjections=libraryDao.avisTransfertPart(
                    request.getIdOperation(),request.getIdOpcvm(), request.getDateDeb(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Avis de transfert de parts opcvm",
                    HttpStatus.OK,
                    avisTransfertPartProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //etatSuiviActionnaire
    public ResponseEntity<?> etatSuiviActionnaire(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<EtatsSuiviActionnaireProjection> etatsSuiviActionnaireProjectionPagePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                etatsSuiviActionnaireProjectionPagePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                etatsSuiviActionnaireProjectionPagePage = libraryDao.etatSuiviActionnaire(
                        null,request.getDateDebut(),request.getDateFin(),
                        pageable
                );
            }
            List<EtatsSuiviActionnaireProjection> content = etatsSuiviActionnaireProjectionPagePage.getContent().stream().toList();
            DataTablesResponse<EtatsSuiviActionnaireProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)etatsSuiviActionnaireProjectionPagePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)etatsSuiviActionnaireProjectionPagePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> etatSuiviActionnaire(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatsSuiviActionnaireProjection> etatsSuiviActionnaireProjections = libraryDao.etatSuiviActionnaire(
                null, request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Points_Actionnaires.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


        LocalDateTime dateTime = request.getDateDebut();
        Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date dateDebut = Date.from(i);

         dateTime = request.getDateFin();
         i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
         Date dateFin = Date.from(i);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatsSuiviActionnaireProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatsSuiviActionnaireProjections);


    }
    public ResponseEntity<?> etatSuiviActionnaireListe(ReleveTitreFCPRequest request) {
        try
        {
            List<EtatsSuiviActionnaireProjection> etatsSuiviActionnaireProjectionList=libraryDao.etatSuiviActionnaire(
                    null,request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    etatsSuiviActionnaireProjectionList);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    //cours titre
    @Transactional
    public ResponseEntity<?> verifCours(CoursTitreRequest request) {
//        var parameters = request.getDatatableParameters();
        try {
            List<CoursTitreProjection> coursTitreProjections= libraryDao.coursTitreNew(
                    request.getCodePlace(),request.getDateDebut(),request.getEstVerifie1(),request.getEstVerifie2()
            );

            for(CoursTitreProjection o:coursTitreProjections){
                var q=em.createStoredProcedureQuery("[Titre].[PS_CoursTitre_UP]");
                String sortie="";
                q.registerStoredProcedureParameter("estVerifie1",Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateVerification1",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLoginVerificateur1",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("estVerifie2",Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateVerification2",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLoginVerificateur2",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("NumLigne",Long.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin",String.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient",LocalDateTime.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue",String.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie",String.class,ParameterMode.OUT);
                if(request.getNiveau()==1){
                    q.setParameter("estVerifie1",true);
                    q.setParameter("dateVerification1",LocalDateTime.now());
                    q.setParameter("userLoginVerificateur1",request.getUserLogin());
                    q.setParameter("estVerifie2",o.getEstVerifie2());
                    q.setParameter("dateVerification2",o.getDateVerification2());
                    q.setParameter("userLoginVerificateur2",o.getUserLoginVerificateur2());
                    q.setParameter("NumLigne",o.getNumLigne());
                    q.setParameter("userLogin",request.getUserLogin());
                    q.setParameter("dateDernModifClient",LocalDateTime.now());
                    q.setParameter("CodeLangue","fr-FR");
                    q.setParameter("Sortie",sortie);
                    q.executeUpdate();
                }
                else
                {
                    q.setParameter("estVerifie1",o.getEstVerifie1());
                    q.setParameter("dateVerification1",o.getDateVerification1());
                    q.setParameter("userLoginVerificateur1",o.getUserLoginVerificateur1());
                    q.setParameter("estVerifie2",true);
                    q.setParameter("dateVerification2",LocalDateTime.now());
                    q.setParameter("userLoginVerificateur2",request.getUserLogin());
                    q.setParameter("NumLigne",o.getNumLigne());
                    q.setParameter("userLogin",request.getUserLogin());
                    q.setParameter("dateDernModifClient",LocalDateTime.now());
                    q.setParameter("CodeLangue","fr-FR");
                    q.setParameter("Sortie",sortie);
                    q.executeUpdate();
                }
            }


            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    null);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> coursTitre(CoursTitreRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            List<CoursTitreProjection> coursTitreProjections;

                coursTitreProjections = libraryDao.coursTitreNew(
                        request.getCodePlace(),request.getDateDebut(),request.getEstVerifie1(),request.getEstVerifie2()
                );

//            List<CoursTitreProjection> content = coursTitreProjections.getContent().stream().toList();
            DataTablesResponse<CoursTitreProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered(coursTitreProjections.size());
            dataTablesResponse.setRecordsTotal(coursTitreProjections.size());
            dataTablesResponse.setData(coursTitreProjections);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> coursTitre(CoursTitreRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<CoursTitreProjection> coursTitreProjections = libraryDao.coursTitreNew(
                request.getCodePlace(), request.getDateDebut(),request.getEstVerifie1(),request.getEstVerifie2());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/verif_Cours.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
        parameters.put("niveau", request.getNiveau());
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(coursTitreProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                coursTitreProjections);


    }
    public ResponseEntity<?> placeCoursTitre(CoursTitreRequest request) {
            var parameters = request.getDatatableParameters();
            try {
                Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
                Page<PlaceCoursTitreProjection> placeCoursTitreProjections;
                if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                    placeCoursTitreProjections = new PageImpl<>(new ArrayList<>());
                }
                else {
                    placeCoursTitreProjections = libraryDao.placeCoursTitre(
                            request.getCodePlace(),request.getDateDebut(),request.getDateFin(),
                            pageable
                    );
                }
                List<PlaceCoursTitreProjection> content = placeCoursTitreProjections.getContent().stream().toList();
                DataTablesResponse<PlaceCoursTitreProjection> dataTablesResponse = new DataTablesResponse<>();
                dataTablesResponse.setDraw(parameters.getDraw());
                dataTablesResponse.setRecordsFiltered((int)placeCoursTitreProjections.getTotalElements());
                dataTablesResponse.setRecordsTotal((int)placeCoursTitreProjections.getTotalElements());
                dataTablesResponse.setData(content);
                return ResponseHandler.generateResponse(
                        "Portefeuille opcvm",
                        HttpStatus.OK,
                        dataTablesResponse);
            }
            catch(Exception e) {
                return ResponseHandler.generateResponse(
                        e.getMessage(),
                        HttpStatus.MULTI_STATUS,
                        e);
            }
        }


    //etatFraisFonctionnement
    public ResponseEntity<?> etatFraisFonctionnement(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<EtatFraisFonctionnementProjection> etatFraisFonctionnementProjections;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                etatFraisFonctionnementProjections = new PageImpl<>(new ArrayList<>());
            }
            else {
                etatFraisFonctionnementProjections = libraryDao.etatPointFraisFonctionnement(
                        null,request.getDateDebut(),request.getDateFin(),
                        pageable
                );
            }
            List<EtatFraisFonctionnementProjection> content = etatFraisFonctionnementProjections.getContent().stream().toList();
            DataTablesResponse<EtatFraisFonctionnementProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)etatFraisFonctionnementProjections.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)etatFraisFonctionnementProjections.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> etatFraisFonctionnement(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EtatFraisFonctionnementProjection> etatFraisFonctionnementProjections = libraryDao.etatPointFraisFonctionnement(
                null, request.getDateDebut(),request.getDateFin());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Frais_fonctionnement.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


//

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(etatFraisFonctionnementProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                etatFraisFonctionnementProjections);


    }
    public ResponseEntity<?> etatFraisFonctionnementListe(ReleveTitreFCPRequest request) {
        try
        {
            List<EtatFraisFonctionnementProjection> etatFraisFonctionnementProjections=libraryDao.etatPointFraisFonctionnement(
                    null,request.getDateDebut(),request.getDateFin()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    etatFraisFonctionnementProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    //point souscription rachat
    public ResponseEntity<?> pointSouscriptionRachat(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointSouscriptionRachatProjection> pointSouscriptionRachatProjections;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointSouscriptionRachatProjections = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointSouscriptionRachatProjections = libraryDao.pointSouscriptionRachat(
                        null,request.getDateDebut(),request.getDateFin(),request.getType(),
                        pageable
                );
            }
            List<PointSouscriptionRachatProjection> content = pointSouscriptionRachatProjections.getContent().stream().toList();
            DataTablesResponse<PointSouscriptionRachatProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointSouscriptionRachatProjections.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointSouscriptionRachatProjections.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> pointSousriptionRachat(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointSouscriptionRachatProjection> pointSouscriptionRachatProjections = libraryDao.pointSouscriptionRachat(
                null, request.getDateDebut(),request.getDateFin(),request.getType());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Poins_souscription_personne.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


//

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointSouscriptionRachatProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointSouscriptionRachatProjections);


    }
    public ResponseEntity<?> pointSouscriptionRachatListe(ReleveTitreFCPRequest request) {
        try
        {
            List<PointSouscriptionRachatProjection> pointSouscriptionRachatProjections=libraryDao.pointSouscriptionRachat(
                    null,request.getDateDebut(),request.getDateFin(),request.getType()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    pointSouscriptionRachatProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    //portefeuille actionnaire
    public ResponseEntity<?> portefeuilleActionnaire(PortefeuilleActionnaireRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PortefeuilleActionnaireProjection> portefeuilleActionnaireProjections;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                portefeuilleActionnaireProjections = new PageImpl<>(new ArrayList<>());
            }
            else {
                portefeuilleActionnaireProjections = libraryDao.portefeuilleActionnaire(
                        null,request.getIdActionnaire(),request.getDateDebutEstimation()
                        ,request.getDateEstimation(),
                        pageable
                );
            }
            List<PortefeuilleActionnaireProjection> content = portefeuilleActionnaireProjections.getContent().stream().toList();
            DataTablesResponse<PortefeuilleActionnaireProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)portefeuilleActionnaireProjections.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)portefeuilleActionnaireProjections.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> afficherPortefeuilleActionnaireListe(PortefeuilleActionnaireRequest request) {
        try
        {
            List<PortefeuilleActionnaireProjection> portefeuilleActionnaireProjections=libraryDao.portefeuilleActionnaire(
                    request.getIdOpcvm(),request.getIdActionnaire(),request.getDateDebutEstimation(),request.getDateEstimation()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    portefeuilleActionnaireProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> portefeuilleActionnaire(PortefeuilleActionnaireRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PortefeuilleActionnaireProjection> portefeuilleActionnaireProjections=libraryDao.portefeuilleActionnaire(
                request.getIdOpcvm(),request.getIdActionnaire(),request.getDateDebutEstimation(),request.getDateEstimation()
        );
        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/PortefeuilleActionnaireF1.jrxml");

        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
//        BigDecimal soldeEspece = portefeuille.get(0).getSoldeEspece();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin = dateFormatter.format(date);
        parameters.put("letterDate", letterDate);
        DateFormat dateFormatter2 = new SimpleDateFormat("yyyy/MM/dd");
        LocalDateTime dateDebut = request.getDateDebutEstimation();
        LocalDateTime dateFin = request.getDateEstimation();

//        Date dateDebutAsDate = Date.from(dateDebut.atZone(ZoneId.systemDefault()).toInstant());
//        Date dateFinAsDate = Date.from(dateFin.atZone(ZoneId.systemDefault()).toInstant());
//
//        String dateDebutStr = dateFormatter2.format(dateDebutAsDate);
//        String dateFinStr = dateFormatter2.format(dateFinAsDate);
        parameters.put("letterDate", new SimpleDateFormat("dd MMMM yyyy").format(new Date()));

// ⚠️ ici on ajoute les apostrophes manuellement pour SQL
        parameters.put("idActionnaireList", request.getIdActionnaire() );
        parameters.put("dateDebutEstimation", dateDebut );
        parameters.put("dateEstimation", dateFin);
        parameters.put("idActionnaire", request.getIdActionnaire());
        //parameters.put("TABLE_DATASOURCE", new JRBeanCollectionDataSource(portefeuilleActionnaireProjections));
        Connection connection = null;
        try {
            connection = dataSource.getConnection(); // connexion JDBC
            JasperPrint print = JasperFillManager.fillReport(
                    rapportPrincipal,
                    parameters,
                    connection
            );
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        } finally {
            if (connection != null) connection.close();
        }
//        JasperPrint print = JasperFillManager.fillReport(
//                rapportPrincipal,
//                parameters,
//                new JRBeanCollectionDataSource(portefeuilleActionnaireProjections)
//        );
//        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                portefeuilleActionnaireProjections);

    }

    //point repartition portefeuille
    public ResponseEntity<?> pointRepartitionPortefeuille(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PointRepartitionPortefeuilleProjection> pointRepartitionPortefeuilleProjections;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                pointRepartitionPortefeuilleProjections = new PageImpl<>(new ArrayList<>());
            }
            else {
                pointRepartitionPortefeuilleProjections = libraryDao.pointRepartitionPortefeuille(
                        null,request.getDateDebut(),
                        pageable
                );
            }
            List<PointRepartitionPortefeuilleProjection> content = pointRepartitionPortefeuilleProjections.getContent().stream().toList();
            DataTablesResponse<PointRepartitionPortefeuilleProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)pointRepartitionPortefeuilleProjections.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)pointRepartitionPortefeuilleProjections.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> pointRepartitionPortefeuille(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<PointRepartitionPortefeuilleProjection> pointRepartitionPortefeuilleProjections =
                libraryDao.pointRepartitionPortefeuille(
                null, request.getDateDebut());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Point_repartition_portefeuille.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(pointRepartitionPortefeuilleProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                pointRepartitionPortefeuilleProjections);


    }
    public ResponseEntity<?> pointRepartitionPortefeuilleListe(ReleveTitreFCPRequest request) {
        try
        {
            List<PointRepartitionPortefeuilleProjection> pointRepartitionPortefeuilleProjections=
                    libraryDao.pointRepartitionPortefeuille(
                    null,request.getDateDebut()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    pointRepartitionPortefeuilleProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    //point repartition portefeuille
    public ResponseEntity<?> evolutionActifNet(ReleveTitreFCPRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<EvolutionActifNetProjection> evolutionActifNetProjections;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                evolutionActifNetProjections = new PageImpl<>(new ArrayList<>());
            }
            else {
                evolutionActifNetProjections = libraryDao.evolutionAtifNet(
                        request.getDateDebut(),
                        pageable
                );
            }
            List<EvolutionActifNetProjection> content = evolutionActifNetProjections.getContent().stream().toList();
            DataTablesResponse<EvolutionActifNetProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)evolutionActifNetProjections.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)evolutionActifNetProjections.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> evolutionActifNet(ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EvolutionActifNetProjection> evolutionActifNetProjections =
                libraryDao.evolutionAtifNet(request.getDateDebut());

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Evol_Act_Net_Nbre_part.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(evolutionActifNetProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                evolutionActifNetProjections);


    }
    public ResponseEntity<?> evolutionActifNetListe(ReleveTitreFCPRequest request) {
        try
        {
            List<EvolutionActifNetProjection> evolutionActifNetProjections=
                    libraryDao.evolutionAtifNet(
                    request.getDateDebut()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    evolutionActifNetProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //evoulutionVL
    public Long mois1(String mois){
        Long A=1L;
        if(mois.equals("Janvier")){
            A=1L;
        }
        else
            if(mois.equals("Février")){
                A=2L;
            }
            else
                if(mois.equals("Mars")){
                    A=3L;
                }else
                if(mois.equals("Avril")){
                    A=4L;
                }else
                if(mois.equals("Mai")){
                    A=5L;
                }else
                if(mois.equals("Juin")){
                    A=6L;
                }else
                if(mois.equals("Juillet")){
                    A=7L;
                }else
                if(mois.equals("Aout")){
                    A=8L;
                }else
                if(mois.equals("Septembre")){
                    A=9L;
                }else
                if(mois.equals("Octobre")){
                    A=10L;
                }else
                if(mois.equals("Novembre")){
                    A=11L;
                }else
                if(mois.equals("Décembre")){
                    A=12L;
                }
          return A;
    }
    public Long mois2(String mois){
        Long A=1L;
        if(mois.equals("Janvier")){
            A=1L;
        }
        else
            if(mois.equals("Février")){
                A=2L;
            }
            else
                if(mois.equals("Mars")){
                    A=3L;
                }else
                if(mois.equals("Avril")){
                    A=4L;
                }else
                if(mois.equals("Mai")){
                    A=5L;
                }else
                if(mois.equals("Juin")){
                    A=6L;
                }else
                if(mois.equals("Juillet")){
                    A=7L;
                }else
                if(mois.equals("Aout")){
                    A=8L;
                }else
                if(mois.equals("Septembre")){
                    A=9L;
                }else
                if(mois.equals("Octobre")){
                    A=10L;
                }else
                if(mois.equals("Novembre")){
                    A=11L;
                }else
                if(mois.equals("Décembre")){
                    A=12L;
                }
          return A;
    }
    public ResponseEntity<?> evolutionVL(EvolutionVLRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<EvolutionVLProjection> evolutionVLProjections;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                evolutionVLProjections = new PageImpl<>(new ArrayList<>());
            }
            else {
                evolutionVLProjections = libraryDao.evolutionVL(
                        null,mois1(request.getMois1()),mois2(request.getMois2()),
                        pageable
                );
            }
            List<EvolutionVLProjection> content = evolutionVLProjections.getContent().stream().toList();
            DataTablesResponse<EvolutionVLProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)evolutionVLProjections.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)evolutionVLProjections.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> evolutionVL(EvolutionVLRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
        // Récupération des données
        List<EvolutionVLProjection> evolutionVLProjections = libraryDao.evolutionVL(
                null,mois1(request.getMois1()),mois2(request.getMois2()));

        // Chargement des fichiers .jrxml depuis le classpath
        rapportStream = getClass().getResourceAsStream("/Evolution_VL.jrxml");
//        subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

        if (rapportStream == null) {
            throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
        }

        // Compiler les rapports à la volée
        JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
//        JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


//

        // Préparation des paramètres
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
//        String dateFin2 = dateFormatter.format(dateFin);
//        String dateDebut2 = dateFormatter.format(dateDebut);
        parameters.put("letterDate", letterDate);
        parameters.put("mois1", request.getMois1());
        parameters.put("mois2", request.getMois2());
//        parameters.put("dateFin", dateFin2);
//        parameters.put("dateDebut", dateDebut2);
//        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
//        parameters.put("description", opcvm.getDenominationOpcvm());
//        parameters.put("idOpcvm", opcvm.getIdOpcvm().toString());

        // Remplissage du rapport
        JasperPrint print = JasperFillManager.fillReport(
                rapportPrincipal,
                parameters,
                new JRBeanCollectionDataSource(evolutionVLProjections)
        );
        JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                evolutionVLProjections);


    }
    public ResponseEntity<?> evolutionVLListe(EvolutionVLRequest request) {
        try
        {
            List<EvolutionVLProjection> evolutionVLProjections = libraryDao.evolutionVL(
                    null,mois1(request.getMois1()),mois2(request.getMois2()));
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    evolutionVLProjections);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    //fiche client
    public ResponseEntity<?> rechercherPersonnePhysiqueMoraleSelonType(Long idOpcvm,String libelleQualite,String statutCompte,String type,String valeur) {

        try {

            List<PersonnePhysiqueMoraleProjection> list = libraryDao.rechercherPersonnePhysiqueMoraleSelonType(
                    idOpcvm,libelleQualite,statutCompte,type,valeur);

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> ficheClientEtat(FicheClientRequest request, HttpServletResponse response) throws IOException, JRException {

        List<FicheClientProjection> list=libraryDao.afficherFicheClient(request.getIdActionnaire());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);

        InputStream inputStream ;
        if(request.getEstPH()){
            inputStream = getClass().getResourceAsStream("/fiche_Client_PH.jrxml");
            if (inputStream == null) {
                throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
            }
        }
        else
        {
            inputStream = getClass().getResourceAsStream("/fiche_Client_PM.jrxml");
            if (inputStream == null) {
                throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
            }
        }


        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Fiche client",
                HttpStatus.OK,
                list);
    }
    //point trésorerie
    public ResponseEntity<Object> pointTresorerie(BeginEndDateParameter parameter, HttpServletResponse response) throws IOException, JRException {

        List<PointTresorerieProjection> list=libraryDao.pointTresorerie(parameter.getStartDate());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        parameters.put("dateEstimation", LocalDateTime.now());
        InputStream inputStream = getClass().getResourceAsStream("/PointTresorerie.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Point de trésorerie",
                HttpStatus.OK,
                list);
    }
    //releve actionnaire
    public ResponseEntity<?> afficherPersonnePhysiqueMorale(Long idOpcvm,String libelleQualite,String statutCompte) {

        try {

            List<PersonnePhysiqueMoraleProjection> list = libraryDao.afficherPersonnePhysiqueMorale(
                    idOpcvm,libelleQualite,statutCompte);

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> afficherPersonnePhysiqueMorale(Long idOpcvm,String libelleQualite,String statutCompte,String valeur) {

        try {

            List<PersonnePhysiqueMoraleProjection> list = libraryDao.afficherPersonnePhysiqueMorale(
                    idOpcvm,libelleQualite,statutCompte,valeur);

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> afficherPersonnePhysiqueMoraleSelonType(Long idOpcvm,String libelleQualite,String statutCompte,String type) {

        try {

            List<PersonnePhysiqueMoraleProjection> list = libraryDao.afficherPersonnePhysiqueMoraleSelonType(
                    idOpcvm,libelleQualite,statutCompte,type);

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> releveActionnaire(HistoriqueActionnaireRequest request) {

        try {

            List<ReleveActionnaireProjection> list = libraryDao.releveActionnaire(
                    request.getIdActionnaire(),request.getDateDebut(),request.getDateFin());

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> releveActionnaire(HistoriqueActionnaireRequest parameter, HttpServletResponse response) throws IOException, JRException {

        List<ReleveActionnaireProjection> list=libraryDao.releveActionnaire(parameter.getIdActionnaire(),parameter.getDateDebut(),parameter.getDateFin());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        parameters.put("dateDebut", parameter.getDateDebut());
        parameters.put("dateFin", parameter.getDateFin());
        InputStream inputStream = getClass().getResourceAsStream("/ReleveActionnaire.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Releve actionnaire",
                HttpStatus.OK,
                list);
    }
    //suivi clients
    public ResponseEntity<?> suiviClient(HistoriqueActionnaireRequest request) {

        try {

            List<EtatsSuiviClientProjection> list = libraryDao.etatSuiviClient(
                    request.getIdActionnaire(),null,request.getDateFin());

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> suiviClient(HistoriqueActionnaireRequest parameter, HttpServletResponse response) throws IOException, JRException {

        List<EtatsSuiviClientProjection> list=libraryDao.etatSuiviClient(parameter.getIdActionnaire(),
                null,parameter.getDateFin());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        InputStream inputStream = getClass().getResourceAsStream("/EtatsSuivis.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Releve actionnaire",
                HttpStatus.OK,
                list);
    }
    //performance portefeuille actionnaire
    public ResponseEntity<Object> perfomancePortefeuilleAcctionnaire(HistoriqueActionnaireRequest parameter, HttpServletResponse response) throws IOException, JRException {

        List<PerformancePortefeuilleActionnaireProjection> list=libraryDao.performancePortefeuilleActionnaire(null,parameter.getIdActionnaire(),
                parameter.getDateDebut(),parameter.getDateFin());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        InputStream inputStream = getClass().getResourceAsStream("/PerformancePortefeuilleActionnaire.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Performance portefeuille actionnaire",
                HttpStatus.OK,
                list);
    }
    //Historique actionnaire
    public ResponseEntity<?> historiqueActionnaire(HistoriqueActionnaireRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<HistoriqueActionnaireProjection> historiqueActionnairePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                historiqueActionnairePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                historiqueActionnairePage = libraryDao.historiqueActionnaire(
                        request.getDateDebut(),request.getDateFin(),
                        pageable
                );
            }
            List<HistoriqueActionnaireProjection> content = historiqueActionnairePage.getContent().stream().toList();
            DataTablesResponse<HistoriqueActionnaireProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)historiqueActionnairePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)historiqueActionnairePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> historiqueActionnaireListe(HistoriqueActionnaireRequest request) {

        try {

            List<HistoriqueActionnaireProjection> historiqueActionnaireProjetions = libraryDao.historiqueActionnaireListe(
                        request.getDateDebut(),request.getDateFin());

            return ResponseHandler.generateResponse(
                    "Historique actionnaire",
                    HttpStatus.OK,
                    historiqueActionnaireProjetions);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> historiqueActionnaire(BeginEndDateParameter parameter, HttpServletResponse response) throws IOException, JRException {

        List<HistoriqueActionnaireProjection> list=libraryDao.historiqueActionnaire(parameter.getStartDate(),parameter.getEndDate());
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        InputStream inputStream = getClass().getResourceAsStream("/HistoriqueActionnaire.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Historique actionnaire",
                HttpStatus.OK,
                list);
    }
    //procédure comptable
    public ResponseEntity<Object> procedureComptable(HttpServletResponse response) throws IOException, JRException {

        List<ProcedureComptableProjection> list=libraryDao.procedureComptable();
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        InputStream inputStream = getClass().getResourceAsStream("/ProcedureComptable.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Procedures comptable",
                HttpStatus.OK,
                list);
    }
    //portefeuille
    public ResponseEntity<?> afficherPortefeuille(ConstatationChargeListeRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PortefeuilleOpcvmProjection> operationPortefeuilleOpcvmPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationPortefeuilleOpcvmPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                operationPortefeuilleOpcvmPage = libraryDao.portefeuilleOPCVM(
                        request.getIdOpcvm(),request.getDateOperation(),
                        pageable
                );
            }
            List<PortefeuilleOpcvmProjection> content = operationPortefeuilleOpcvmPage.getContent().stream().toList();
            DataTablesResponse<PortefeuilleOpcvmProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationPortefeuilleOpcvmPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationPortefeuilleOpcvmPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<?> afficherPortefeuilleListe(ConstatationChargeListeRequest request) {
       try
       {
            List<PortefeuilleOpcvmProjection> operationPortefeuilleOpcvm=libraryDao.portefeuilleOPCVM(
                    request.getIdOpcvm(),request.getDateOperation()
            );
            return ResponseHandler.generateResponse(
                    "Portefeuille opcvm",
                    HttpStatus.OK,
                    operationPortefeuilleOpcvm);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public ResponseEntity<Object> afficherPortefeuille(DifferenceEstimationRequest request, HttpServletResponse response) throws JRException, IOException {

        InputStream rapportStream = null;
        InputStream subreportStream = null;
//        try {
            // Récupération des données
            List<PortefeuilleOpcvmProjection> portefeuille = libraryDao.portefeuilleOPCVM(
                    request.getIdOpcvm(), request.getDateOperation());
            if (portefeuille.size() == 1)
            {

                //if(Convert.ToDecimal(dt1.Rows[0].ItemArray[3])==0)
                //{
                //    dt1.Rows.RemoveAt(dt1.Rows.Count - 1);
                //}

            }
            else
            {
                portefeuille.remove(portefeuille.size()-1);
            }
            // Chargement des fichiers .jrxml depuis le classpath
            rapportStream = getClass().getResourceAsStream("/portefeuille_paysage.jrxml");
            subreportStream = getClass().getResourceAsStream("/operationDetachement.jrxml");

            if (rapportStream == null || subreportStream == null) {
                throw new RuntimeException("Fichiers .jrxml introuvables dans le classpath !");
            }

            // Compiler les rapports à la volée
            JasperReport rapportPrincipal = JasperCompileManager.compileReport(rapportStream);
            JasperReport subreport = JasperCompileManager.compileReport(subreportStream);


            LocalDateTime dateTime = request.getDateOperation();
            Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(i);
            List<OperationDetachementProjection> operationDetachementListe = libraryDao.operationDetachementListe(
                    request.getIdOpcvm(),  date);

            // Vérification des données
//            if (portefeuille == null || portefeuille.isEmpty()) {
//                throw new RuntimeException("Aucune donnée de portefeuille trouvée");
//            }
//            if (operationDetachementListe == null || operationDetachementListe.isEmpty()) {
//                throw new RuntimeException("Aucune donnée d'opération de détachement trouvée");
//            }

            // Préparation des paramètres
            Map<String, Object> parameters = new HashMap<>();
            BigDecimal soldeEspece = portefeuille.get(0).getSoldeEspece();
            DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
            String letterDate = dateFormatter.format(new Date());
            String dateFin = dateFormatter.format(date);
            parameters.put("letterDate", letterDate);
            parameters.put("dateFin", dateFin);
            OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
            parameters.put("designation", opcvm.getDenominationOpcvm());
            parameters.put("type", opcvm.getClassification().getLibelleClassification());
            parameters.put("agrement", opcvm.getAgrement());
            parameters.put("espece", soldeEspece);
            parameters.put("ALL_LIGNES", new JRBeanCollectionDataSource(operationDetachementListe));
            parameters.put("SUBREPORT_REF", subreport); // Passer le sous-rapport compilé
            parameters.put("SUBREPORT_NAME", "operationDetachement.jrxml"); // Chemin relatif pour le sous-rapport

            // Remplissage du rapport
            JasperPrint print = JasperFillManager.fillReport(
                    rapportPrincipal,
                    parameters,
                    new JRBeanCollectionDataSource(portefeuille)
            );
            JasperExportManager.exportReportToPdfStream(print, response.getOutputStream());
            return ResponseHandler.generateResponse(
                    "Ordre de bourse",
                    HttpStatus.OK,
                    portefeuille);
            // Exportation en PDF
//            ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
//            JasperExportManager.exportReportToPdfStream(print, pdfOutputStream);
//            byte[] pdfBytes = pdfOutputStream.toByteArray();
//
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_PDF)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=portefeuille.pdf")
//                    .contentLength(pdfBytes.length)
//                    .body(pdfBytes);
//        } catch (Exception e) {
//            throw new RuntimeException("Erreur lors de la génération du rapport : " + e.getMessage(), e);
//        } finally {
//            // Fermer les InputStreams pour éviter les fuites de ressources
//            try {
//                if (rapportStream != null) rapportStream.close();
//                if (subreportStream != null) subreportStream.close();
//            } catch (Exception e) {
//                // Gérer les erreurs de fermeture si nécessaire
//            }
//        }


    }

    //Récupération séance en cours
    public SeanceOpcvm currentSeance(Long idOpcvm) {
        return libraryDao.currentSeance(idOpcvm);
    }

    //Récupération solde compte
    public ResponseEntity<?> soldeToutCompte(SoldeToutCompteRequest request) {
        try {
            String codePlan = "PCIA";
            String numCompteComptable = "";
            switch (request.getCode().trim()) {
                case "FRAISDEPOSIT" -> {
                    numCompteComptable = "3062100";
                }
                case "FRAISCREPMF", "REVCREPMF" -> {
                    numCompteComptable = "3064100";
                }
                case "FRAISCONSACTIF" -> {
                    numCompteComptable = "3065100";
                }
                case "HONOCAC" -> {
                    numCompteComptable = "3063100";
                }
                case "FRAISGEST" -> {
                    numCompteComptable = "3061100";
                }
                case "RACHAT" -> {
                    numCompteComptable = "3052200";
                }
                case "SOUSCRIPTION" -> {
                    numCompteComptable = "3052100";
                }
                case "TAFA" -> {
                    numCompteComptable = "3356101";
                }
                default -> {
                }
            }
            var soldeToutCompte = libraryDao.soldeToutCompte(
                    codePlan,
                    request.getIdOpcvm(),
                    numCompteComptable,
                    request.getDateEstimation()
            );

            return ResponseHandler.generateResponse(
                "Solde du compte " + numCompteComptable,
                HttpStatus.OK,
                soldeToutCompte
            );
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //Récupération des comptes à mouvementer
    public List<Mouvement> chargerLigneMvt(ChargerLigneMvtRequest chargerLigneMvtRequest) {
        List<LigneMvtClotureProjection> ligneMvtClotureProjections = libraryDao.chargerLigneMvt(
                chargerLigneMvtRequest.getCodeNatureOperation(),
                chargerLigneMvtRequest.getValeurCodeAnalytique(),
                chargerLigneMvtRequest.getValeurFormule(),
                chargerLigneMvtRequest.getIdOpcvm(),
                chargerLigneMvtRequest.getIdActionnaire(),
                chargerLigneMvtRequest.getIdTitre()
        );
        return ligneMvtClotureProjections.stream().map(l -> {
            Mouvement mvt = new Mouvement();
            mvt.setOperation(chargerLigneMvtRequest.getOperation());
            if(l.getCodePlan() != null && !l.getCodePlan().isEmpty()) {
                Plan plan = planService.afficherSelonId(l.getCodePlan());
                mvt.setPlan(plan);
            }
            if(l.getIdOpcvm() != null) {
                Opcvm opcvm = opcvmService.afficherSelonId(l.getIdOpcvm());
                mvt.setOpcvm(opcvm);
            }
            String codeIb = l.getCodeIb();
            if(codeIb != null && !codeIb.isEmpty()) {
                Ib ib = ibService.afficherSelonId(codeIb.trim());
                mvt.setIb(ib);
                mvt.setiB(codeIb.trim());
            }
            mvt.setIdActionnaire(chargerLigneMvtRequest.getIdActionnaire());
            if(chargerLigneMvtRequest.getIdActionnaire() != null) {
                Personne actionnaire = personneDao.findById(chargerLigneMvtRequest.getIdActionnaire()).orElse(null);
                mvt.setActionnaire(actionnaire);
            }
            mvt.setPosition(StringUtils.hasLength(l.getCodePosition()) ? l.getCodePosition().trim() : null);
            mvt.setCodeModeleEcriture(StringUtils.hasLength(l.getCodeModeleEcriture()) ? l.getCodeModeleEcriture().trim() : null);
            mvt.setNumCompteComptable(StringUtils.hasLength(l.getNumCompteComptable()) ? l.getNumCompteComptable().trim() : null);
            mvt.setNumeroOdreModele(l.getNumeroOrdreModele());
            mvt.setNumeroOdreLigneMvt(l.getNumeroOrdreLigneMvt());
            mvt.setSensMvt(StringUtils.hasLength(l.getSensMvt()) ? l.getSensMvt().trim() : null);
            mvt.setValeur(l.getValeur());
            mvt.setRubrique(StringUtils.hasLength(l.getCodeRubrique()) ? l.getCodeRubrique().trim() : null);
            if(l.getCodeTypeFormule() != null && !l.getCodeTypeFormule().isEmpty()) {
                mvt.setTypeValeur(l.getCodeTypeFormule().trim());
            }
            mvt.setDateDernModifClient(LocalDateTime.now());
            return mvt;
        }).toList();
    }

    //Prise en charge comptable des restitutions de reliquats
    public OperationRestitutionReliquatDto genererEcritureComptable(OperationRestitutionReliquatDto op) {
        //Création de la transaction
        Transaction transaction = new Transaction();
        Long idTransaction = transactionDao.getIdTransactionByCodeAndDate(
                op.getOpcvm().getIdOpcvm(),
                op.getNatureOperation().getCodeNatureOperation(),
                op.getDateOperation()
        );
        if(idTransaction == null || idTransaction.equals(0L)) {
            transaction.setDateTransaction(op.getDateOperation());
            transaction.setOpcvm(opcvmMapper.deOpcvmDto(op.getOpcvm()));
            transaction.setIdSeance(op.getIdSeance());
            transaction.setNatureOperation(natureOperationMapper.deNatureOperationDto(op.getNatureOperation()));
            transaction.setDateDernModifClient(LocalDateTime.now());
            transaction = transactionDao.save(transaction);
        }
        else
        {
            op.setIdTransaction(idTransaction);
            transaction.setIdTransaction(idTransaction);
        }
        //Mettre à jour la valeur de la transaction dans opération
        OperationRestitutionReliquat operation = operationRestitutionReliquatMapper.deDto(op);
        operation.setTransaction(transaction);
        operation = em.merge(operation);

        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                operation.getNatureOperation() != null ? operation.getNatureOperation().getCodeNatureOperation() : null,
                operation.getValeurCodeAnalytique(),
                operation.getValeurFormule(),
                operation.getOpcvm() != null ? operation.getOpcvm().getIdOpcvm() : null,
                operation.getActionnaire() != null ? operation.getActionnaire().getIdPersonne() : null,
                operation.getTitre() != null ? operation.getTitre().getIdTitre() : null,
                operation
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        //Insertion des formules et leurs valeurs
        String[] tabsFormules = op.getValeurFormule().split(";");
        Map<String, String> formules = new HashMap<>();
        for (String code : tabsFormules) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    formules.put(tabFormules[i], tabFormules[i + 1]);
                    OperationFormule operationFormule = new OperationFormule();
                    CleOperationFormule cle = new CleOperationFormule();
                    cle.setIdFormule(Long.valueOf(tabFormules[i]));
                    cle.setIdOperation(operation.getIdOperation());
                    operationFormule.setIdOperationFormule(cle);
                    Formule formule = formuleDao.findById(Long.valueOf(tabFormules[i])).orElse(null);
                    Formule formule1 = new Formule();
                    formule1.setIdFormule(Long.valueOf(tabFormules[i]));
                    operationFormule.setFormule(formule);
                    Operation op1 = new Operation();
                    op1.setIdOperation(operation.getIdOperation());
                    operationFormule.setOperation(operation);
//                    operationFormule.setValeur(BigDecimal.valueOf(Long.parseLong(tabFormules[i + 1])));
                    operationFormule.setValeur(new BigDecimal(tabFormules[i + 1]));
                    operationFormule.setDateDernModifClient(LocalDateTime.now());
                    operationFormuleDao.save(operationFormule);
                }
            }
        }

        //Insertion des codes analytiques et leurs valeurs
        String[] tabCodeAnalytiques = op.getValeurCodeAnalytique().split(";");
        Map<String, String> codesAnalytiques = new HashMap<>();
        for (String code : tabCodeAnalytiques) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    codesAnalytiques.put(tabFormules[i], tabFormules[i + 1]);
                    CleOperationCodeAnalytique cle = new CleOperationCodeAnalytique();
                    cle.setIdOperation(operation.getIdOperation());
                    cle.setCodeAnalytique(tabFormules[i + 1]);
                    cle.setTypeCodeAnalytique(tabFormules[i]);
                    OperationCodeAnalytique operationCodeAnalytique = new OperationCodeAnalytique();
                    operationCodeAnalytique.setIdOperationCodeAnalytique(cle);
                    operationCodeAnalytique.setOperation(operation);
                    operationCodeAnalytique.setDateDernModifClient(LocalDateTime.now());
                    operationCodeAnalytiqueDao.save(operationCodeAnalytique);
                }
            }
        }

        //Insertion des lignes de mouvement
        mouvementDao.saveAll(mouvements);

        //Journalisation comptable de l'opération
        String codeJournal = mouvementDao.getCodeJournalByIdOp(operation.getIdOperation());
        if(!StringUtils.hasLength(codeJournal)) {
            if (operation.getNatureOperation() != null) {
                codeJournal = natureOperationDao.getCodeJournalByCodeNatureOp(operation.getNatureOperation().getCodeNatureOperation());
            }
        }
        OperationJournal operationJournal = new OperationJournal();
        CleOperationJournal cle = new CleOperationJournal();
        cle.setIdOperation(operation.getIdOperation());
        cle.setCodeJournal(codeJournal);
        operationJournal.setIdOperationJournal(cle);
        Journal journal = journalDao.findById(codeJournal).orElse(null);
        operationJournal.setJournal(journal);
        operationJournal.setOperation(operation);
        operationJournal.setDateDernModifClient(LocalDateTime.now());
        operationJournal.setDateCreationServeur(LocalDateTime.now());
        operationJournalDao.save(operationJournal);

        return operationRestitutionReliquatMapper.aEntite(operation);
    }

    //Prise en charge comptable des souscriptions
    public OperationSouscriptionRachatDto genererEcritureComptable(OperationSouscriptionRachatDto op) {
        //Création de la transaction
        Transaction transaction = new Transaction();
        Long idTransaction = transactionDao.getIdTransactionByCodeAndDate(
                op.getOpcvm().getIdOpcvm(),
                op.getNatureOperation().getCodeNatureOperation(),
                op.getDateOperation()
        );
        if(idTransaction == null || idTransaction.equals(0L)) {
            transaction.setDateTransaction(op.getDateOperation());
            transaction.setOpcvm(opcvmMapper.deOpcvmDto(op.getOpcvm()));
            transaction.setIdSeance(op.getIdSeance());
            transaction.setNatureOperation(natureOperationMapper.deNatureOperationDto(op.getNatureOperation()));
            transaction.setDateDernModifClient(LocalDateTime.now());
            transaction = transactionDao.save(transaction);
        }
        else
        {
            op.setIdTransaction(idTransaction);
            transaction.setIdTransaction(idTransaction);
        }
        //Mettre à jour la valeur de la transaction dans opération
        OperationSouscriptionRachat operation = souscriptionRachatMapper.deOperationSouscriptionRachatDto(op);
        operation.setTransaction(transaction);
        operation = em.merge(operation);

        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                operation.getNatureOperation() != null ? operation.getNatureOperation().getCodeNatureOperation() : null,
                operation.getValeurCodeAnalytique(),
                operation.getValeurFormule(),
                operation.getOpcvm() != null ? operation.getOpcvm().getIdOpcvm() : null,
                operation.getActionnaire() != null ? operation.getActionnaire().getIdPersonne() : null,
                operation.getTitre() != null ? operation.getTitre().getIdTitre() : null,
                operation
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        //Insertion des formules et leurs valeurs
        String[] tabsFormules = op.getValeurFormule().split(";");
        Map<String, String> formules = new HashMap<>();
        for (String code : tabsFormules) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    formules.put(tabFormules[i], tabFormules[i + 1]);
                    OperationFormule operationFormule = new OperationFormule();
                    CleOperationFormule cle = new CleOperationFormule();
                    cle.setIdFormule(Long.valueOf(tabFormules[i]));
                    cle.setIdOperation(operation.getIdOperation());
                    operationFormule.setIdOperationFormule(cle);
                    Formule formule = formuleDao.findById(Long.valueOf(tabFormules[i])).orElse(null);
                    Formule formule1 = new Formule();
                    formule1.setIdFormule(Long.valueOf(tabFormules[i]));
                    operationFormule.setFormule(formule);
                    Operation op1 = new Operation();
                    op1.setIdOperation(operation.getIdOperation());
                    operationFormule.setOperation(operation);
//                    operationFormule.setValeur(BigDecimal.valueOf(Long.parseLong(tabFormules[i + 1])));
                    operationFormule.setValeur(new BigDecimal(tabFormules[i + 1]));
                    operationFormule.setDateDernModifClient(LocalDateTime.now());
                    operationFormuleDao.save(operationFormule);
                }
            }
        }

        //Insertion des codes analytiques et leurs valeurs
        String[] tabCodeAnalytiques = op.getValeurCodeAnalytique().split(";");
        Map<String, String> codesAnalytiques = new HashMap<>();
        for (String code : tabCodeAnalytiques) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    codesAnalytiques.put(tabFormules[i], tabFormules[i + 1]);
                    CleOperationCodeAnalytique cle = new CleOperationCodeAnalytique();
                    cle.setIdOperation(operation.getIdOperation());
                    cle.setCodeAnalytique(tabFormules[i + 1]);
                    cle.setTypeCodeAnalytique(tabFormules[i]);
                    OperationCodeAnalytique operationCodeAnalytique = new OperationCodeAnalytique();
                    operationCodeAnalytique.setIdOperationCodeAnalytique(cle);
                    operationCodeAnalytique.setOperation(operation);
                    operationCodeAnalytique.setDateDernModifClient(LocalDateTime.now());
                    operationCodeAnalytiqueDao.save(operationCodeAnalytique);
                }
            }
        }

        //Insertion des lignes de mouvement
        mouvementDao.saveAll(mouvements);

        //Journalisation comptable de l'opération
        String codeJournal = mouvementDao.getCodeJournalByIdOp(operation.getIdOperation());
        if(!StringUtils.hasLength(codeJournal)) {
            if (operation.getNatureOperation() != null) {
                codeJournal = natureOperationDao.getCodeJournalByCodeNatureOp(operation.getNatureOperation().getCodeNatureOperation());
            }
        }
        OperationJournal operationJournal = new OperationJournal();
        CleOperationJournal cle = new CleOperationJournal();
        cle.setIdOperation(operation.getIdOperation());
        cle.setCodeJournal(codeJournal);
        operationJournal.setIdOperationJournal(cle);
        Journal journal = journalDao.findById(codeJournal).orElse(null);
        operationJournal.setJournal(journal);
        operationJournal.setOperation(operation);
        operationJournal.setDateDernModifClient(LocalDateTime.now());
        operationJournal.setDateCreationServeur(LocalDateTime.now());
        operationJournalDao.save(operationJournal);

        return souscriptionRachatMapper.deOperationSouscriptionRachat(operation);
    }

    //Prise en charge comptable globale
    public OperationDto genererEcritureComptable(OperationDto op) {
        //Création de la transaction
        Transaction transaction = new Transaction();
        Long idTransaction = transactionDao.getIdTransactionByCodeAndDate(
                op.getOpcvm().getIdOpcvm(),
                op.getNatureOperation().getCodeNatureOperation(),
                op.getDateOperation()
        );
        if(idTransaction == null || idTransaction.equals(0L)) {
            transaction.setDateTransaction(op.getDateOperation());
            transaction.setOpcvm(opcvmMapper.deOpcvmDto(op.getOpcvm()));
            transaction.setIdSeance(op.getIdSeance());
            transaction.setNatureOperation(natureOperationMapper.deNatureOperationDto(op.getNatureOperation()));
            transaction.setDateDernModifClient(LocalDateTime.now());
            transaction = transactionDao.save(transaction);
        }
        else
        {
            op.setIdTransaction(idTransaction);
            transaction.setIdTransaction(idTransaction);
        }
        //Mettre à jour la valeur de la transaction dans opération
        Operation operation = operationMapper.deOperationDto(op);
        operation.setTransaction(transaction);
        operation = em.merge(operation);

        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                operation.getNatureOperation() != null ? operation.getNatureOperation().getCodeNatureOperation() : null,
                operation.getValeurCodeAnalytique(),
                operation.getValeurFormule(),
                operation.getOpcvm() != null ? operation.getOpcvm().getIdOpcvm() : null,
                operation.getActionnaire() != null ? operation.getActionnaire().getIdPersonne() : null,
                operation.getTitre() != null ? operation.getTitre().getIdTitre() : null,
                operation
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        //Insertion des formules et leurs valeurs
        String[] tabsFormules = op.getValeurFormule().split(";");
        Map<String, String> formules = new HashMap<>();
        for (String code : tabsFormules) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    formules.put(tabFormules[i], tabFormules[i + 1]);
                    OperationFormule operationFormule = new OperationFormule();
                    CleOperationFormule cle = new CleOperationFormule();
                    cle.setIdFormule(Long.valueOf(tabFormules[i]));
                    cle.setIdOperation(operation.getIdOperation());
                    operationFormule.setIdOperationFormule(cle);
                    Formule formule = formuleDao.findById(Long.valueOf(tabFormules[i])).orElse(null);
                    Formule formule1 = new Formule();
                    formule1.setIdFormule(Long.valueOf(tabFormules[i]));
                    operationFormule.setFormule(formule);
                    Operation op1 = new Operation();
                    op1.setIdOperation(operation.getIdOperation());
                    operationFormule.setOperation(operation);
                    operationFormule.setValeur(BigDecimal.valueOf(Long.parseLong(tabFormules[i + 1])));
                    operationFormule.setDateDernModifClient(LocalDateTime.now());
                    operationFormuleDao.save(operationFormule);
                }
            }
        }

        //Insertion des codes analytiques et leurs valeurs
        String[] tabCodeAnalytiques = op.getValeurCodeAnalytique().split(";");
        Map<String, String> codesAnalytiques = new HashMap<>();
        for (String code : tabCodeAnalytiques) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    codesAnalytiques.put(tabFormules[i], tabFormules[i + 1]);
                    CleOperationCodeAnalytique cle = new CleOperationCodeAnalytique();
                    cle.setIdOperation(operation.getIdOperation());
                    cle.setCodeAnalytique(tabFormules[i + 1]);
                    cle.setTypeCodeAnalytique(tabFormules[i]);
                    OperationCodeAnalytique operationCodeAnalytique = new OperationCodeAnalytique();
                    operationCodeAnalytique.setIdOperationCodeAnalytique(cle);
                    operationCodeAnalytique.setOperation(operation);
                    operationCodeAnalytique.setDateDernModifClient(LocalDateTime.now());
                    operationCodeAnalytiqueDao.save(operationCodeAnalytique);
                }
            }
        }

        //Insertion des lignes de mouvement
        mouvementDao.saveAll(mouvements);

        //Journalisation comptable de l'opération
        String codeJournal = mouvementDao.getCodeJournalByIdOp(operation.getIdOperation());
        if(!StringUtils.hasLength(codeJournal)) {
            if (operation.getNatureOperation() != null) {
                codeJournal = natureOperationDao.getCodeJournalByCodeNatureOp(operation.getNatureOperation().getCodeNatureOperation());
            }
        }
        OperationJournal operationJournal = new OperationJournal();
        CleOperationJournal cle = new CleOperationJournal();
        cle.setIdOperation(operation.getIdOperation());
        cle.setCodeJournal(codeJournal);
        operationJournal.setIdOperationJournal(cle);
        Journal journal = journalDao.findById(codeJournal).orElse(null);
        operationJournal.setJournal(journal);
        operationJournal.setOperation(operation);
        operationJournal.setDateDernModifClient(LocalDateTime.now());
        operationJournal.setDateCreationServeur(LocalDateTime.now());
        operationJournalDao.save(operationJournal);

        return operationMapper.deOperation(operation);
    }

    //Précalcul des souscriptions
    public DataTablesResponse<PrecalculSouscriptionProjection> precalculSouscription(PrecalculSouscriptionRequest precalcul) {
        try {
            Pageable pageable = PageRequest.of(
                    precalcul.getDatatableParameters().getStart() / precalcul.getDatatableParameters().getLength(),
                    precalcul.getDatatableParameters().getLength());
            Page<PrecalculSouscriptionProjection> results;
            results = libraryDao.precalculSouscription(
                    precalcul.getIdSeance(), precalcul.getIdOpcvm(), precalcul.getIdPersonne(), pageable
            );
            List<PrecalculSouscriptionProjection> content = results.getContent().stream().toList();
            DataTablesResponse<PrecalculSouscriptionProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(precalcul.getDatatableParameters().getDraw());
            dataTablesResponse.setRecordsFiltered((int)results.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)results.getTotalElements());
            dataTablesResponse.setData(content);
            return dataTablesResponse;
        }
        catch (Exception e) {
            System.out.println("Erreur lors du précalcul.");
            return null;
        }
    }

    // Prise en charge comptable des transferts de parts
    public OperationTransfertPartDto genererEcritureComptable(OperationTransfertPartDto op) {
        //Création de la transaction
        Transaction transaction = new Transaction();
        Long idTransaction = transactionDao.getIdTransactionByCodeAndDate(
            op.getOpcvm().getIdOpcvm(),
            op.getNatureOperation().getCodeNatureOperation(),
            op.getDateOperation()
        );
        if(idTransaction == null || idTransaction.equals(0L)) {
            transaction.setDateTransaction(op.getDateOperation());
            transaction.setOpcvm(opcvmMapper.deOpcvmDto(op.getOpcvm()));
            transaction.setIdSeance(op.getIdSeance());
            transaction.setNatureOperation(natureOperationMapper.deNatureOperationDto(op.getNatureOperation()));
            transaction.setDateDernModifClient(LocalDateTime.now());
            transaction = transactionDao.save(transaction);
        }
        else
        {
            op.setIdTransaction(idTransaction);
            transaction.setIdTransaction(idTransaction);
        }
        //Mettre à jour la valeur de la transaction dans opération
        OperationTransfertPart operation = transfertPartMapper.deDto(op);
        Personne demandeur = personneDao.findById(op.getDemandeur().getIdPersonne()).orElse(null);
        operation.setDemandeur(demandeur);
        Personne beneficiaire = personneDao.findById(op.getBeneficiaire().getIdPersonne()).orElse(null);
        operation.setBeneficiaire(beneficiaire);
        operation.setTransaction(transaction);
        operation.setDateDernModifClient(LocalDateTime.now());
        operation = em.merge(operation);

        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                operation.getNatureOperation() != null ? operation.getNatureOperation().getCodeNatureOperation() : null,
                operation.getValeurCodeAnalytique(),
                operation.getValeurFormule(),
                operation.getOpcvm() != null ? operation.getOpcvm().getIdOpcvm() : null,
                operation.getActionnaire() != null ? operation.getActionnaire().getIdPersonne() : null,
                operation.getTitre() != null ? operation.getTitre().getIdTitre() : null,
                operation
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        //Insertion des formules et leurs valeurs
        String[] tabsFormules = op.getValeurFormule().split(";");
        Map<String, String> formules = new HashMap<>();
        for (String code : tabsFormules) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    formules.put(tabFormules[i], tabFormules[i + 1]);
                    OperationFormule operationFormule = new OperationFormule();
                    CleOperationFormule cle = new CleOperationFormule();
                    cle.setIdFormule(Long.valueOf(tabFormules[i]));
                    cle.setIdOperation(operation.getIdOperation());
                    operationFormule.setIdOperationFormule(cle);
                    Formule formule = formuleDao.findById(Long.valueOf(tabFormules[i])).orElse(null);
                    Formule formule1 = new Formule();
                    formule1.setIdFormule(Long.valueOf(tabFormules[i]));
                    operationFormule.setFormule(formule);
                    Operation op1 = new Operation();
                    op1.setIdOperation(operation.getIdOperation());
                    operationFormule.setOperation(operation);
//                    operationFormule.setValeur(BigDecimal.valueOf(Long.parseLong(tabFormules[i + 1])));
                    operationFormule.setValeur(new BigDecimal(tabFormules[i + 1]));
                    operationFormule.setDateDernModifClient(LocalDateTime.now());
                    operationFormuleDao.save(operationFormule);
                }
            }
        }

        //Insertion des codes analytiques et leurs valeurs
        String[] tabCodeAnalytiques = op.getValeurCodeAnalytique().split(";");
        Map<String, String> codesAnalytiques = new HashMap<>();
        for (String code : tabCodeAnalytiques) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    codesAnalytiques.put(tabFormules[i], tabFormules[i + 1]);
                    CleOperationCodeAnalytique cle = new CleOperationCodeAnalytique();
                    cle.setIdOperation(operation.getIdOperation());
                    cle.setCodeAnalytique(tabFormules[i + 1]);
                    cle.setTypeCodeAnalytique(tabFormules[i]);
                    OperationCodeAnalytique operationCodeAnalytique = new OperationCodeAnalytique();
                    operationCodeAnalytique.setIdOperationCodeAnalytique(cle);
                    operationCodeAnalytique.setOperation(operation);
                    operationCodeAnalytique.setDateDernModifClient(LocalDateTime.now());
                    operationCodeAnalytiqueDao.save(operationCodeAnalytique);
                }
            }
        }

        //Insertion des lignes de mouvement
        mouvementDao.saveAll(mouvements);

        //Journalisation comptable de l'opération
        String codeJournal = mouvementDao.getCodeJournalByIdOp(operation.getIdOperation());
        if(!StringUtils.hasLength(codeJournal)) {
            if (operation.getNatureOperation() != null) {
                codeJournal = natureOperationDao.getCodeJournalByCodeNatureOp(operation.getNatureOperation().getCodeNatureOperation());
            }
        }
        OperationJournal operationJournal = new OperationJournal();
        CleOperationJournal cle = new CleOperationJournal();
        cle.setIdOperation(operation.getIdOperation());
        cle.setCodeJournal(codeJournal);
        operationJournal.setIdOperationJournal(cle);
        Journal journal = journalDao.findById(codeJournal).orElse(null);
        operationJournal.setJournal(journal);
        operationJournal.setOperation(operation);
        operationJournal.setDateDernModifClient(LocalDateTime.now());
        operationJournal.setDateCreationServeur(LocalDateTime.now());
        operationJournalDao.save(operationJournal);

        return transfertPartMapper.deEntite(operation);
    }

    //Prise en charge comptable des constatations de charges
    public OperationConstatationChargeDto genererEcritureComptable(OperationConstatationChargeDto op) {
        //Création de la transaction
        Transaction transaction = new Transaction();
        Long idTransaction = transactionDao.getIdTransactionByCodeAndDate(
                op.getOpcvm().getIdOpcvm(),
                op.getNatureOperation().getCodeNatureOperation(),
                op.getDateOperation()
        );
        if(idTransaction == null || idTransaction.equals(0L)) {
            transaction.setDateTransaction(op.getDateOperation());
            transaction.setOpcvm(opcvmMapper.deOpcvmDto(op.getOpcvm()));
            transaction.setIdSeance(op.getIdSeance());
            transaction.setNatureOperation(natureOperationMapper.deNatureOperationDto(op.getNatureOperation()));
            transaction.setDateDernModifClient(LocalDateTime.now());
            transaction = transactionDao.save(transaction);
            idTransaction = transaction.getIdTransaction();
        }
        else
        {
            transaction.setIdTransaction(idTransaction);
        }
        op.setIdTransaction(idTransaction);
        //Mettre à jour la valeur de la transaction dans opération
        OperationConstatationCharge operation = constatationChargeMapper.deDto(op);
        operation.setTransaction(transaction);
        operation.setDateDernModifClient(LocalDateTime.now());
        operation.setDateDernModifServeur(LocalDateTime.now());
        operation = em.merge(operation);

        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                operation.getNatureOperation() != null ? operation.getNatureOperation().getCodeNatureOperation() : null,
                operation.getValeurCodeAnalytique(),
                operation.getValeurFormule(),
                operation.getOpcvm() != null ? operation.getOpcvm().getIdOpcvm() : null,
                operation.getActionnaire() != null ? operation.getActionnaire().getIdPersonne() : null,
                operation.getTitre() != null ? operation.getTitre().getIdTitre() : null,
                operation
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        //Insertion des formules et leurs valeurs
        String[] tabsFormules = op.getValeurFormule().split(";");
        Map<String, String> formules = new HashMap<>();
        for (String code : tabsFormules) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    formules.put(tabFormules[i], tabFormules[i + 1]);
                    OperationFormule operationFormule = new OperationFormule();
                    CleOperationFormule cle = new CleOperationFormule();
                    cle.setIdFormule(Long.valueOf(tabFormules[i]));
                    cle.setIdOperation(operation.getIdOperation());
                    operationFormule.setIdOperationFormule(cle);
                    Formule formule = formuleDao.findById(Long.valueOf(tabFormules[i])).orElse(null);
                    Formule formule1 = new Formule();
                    formule1.setIdFormule(Long.valueOf(tabFormules[i]));
                    operationFormule.setFormule(formule);
                    Operation op1 = new Operation();
                    op1.setIdOperation(operation.getIdOperation());
                    operationFormule.setOperation(operation);
//                    operationFormule.setValeur(BigDecimal.valueOf(Long.parseLong(tabFormules[i + 1])));
                    operationFormule.setValeur(new BigDecimal(tabFormules[i + 1]));
                    operationFormule.setDateDernModifClient(LocalDateTime.now());
                    operationFormuleDao.save(operationFormule);
                }
            }
        }

        //Insertion des codes analytiques et leurs valeurs
        String[] tabCodeAnalytiques = op.getValeurCodeAnalytique().split(";");
        Map<String, String> codesAnalytiques = new HashMap<>();
        for (String code : tabCodeAnalytiques) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    codesAnalytiques.put(tabFormules[i], tabFormules[i + 1]);
                    CleOperationCodeAnalytique cle = new CleOperationCodeAnalytique();
                    cle.setIdOperation(operation.getIdOperation());
                    cle.setCodeAnalytique(tabFormules[i + 1]);
                    cle.setTypeCodeAnalytique(tabFormules[i]);
                    OperationCodeAnalytique operationCodeAnalytique = new OperationCodeAnalytique();
                    operationCodeAnalytique.setIdOperationCodeAnalytique(cle);
                    operationCodeAnalytique.setOperation(operation);
                    operationCodeAnalytique.setDateDernModifClient(LocalDateTime.now());
                    operationCodeAnalytiqueDao.save(operationCodeAnalytique);
                }
            }
        }

        //Insertion des lignes de mouvement
        mouvementDao.saveAll(mouvements);

        //Journalisation comptable de l'opération
        String codeJournal = mouvementDao.getCodeJournalByIdOp(operation.getIdOperation());
        if(!StringUtils.hasLength(codeJournal)) {
            if (operation.getNatureOperation() != null) {
                codeJournal = natureOperationDao.getCodeJournalByCodeNatureOp(operation.getNatureOperation().getCodeNatureOperation());
            }
        }
        OperationJournal operationJournal = new OperationJournal();
        CleOperationJournal cle = new CleOperationJournal();
        cle.setIdOperation(operation.getIdOperation());
        cle.setCodeJournal(codeJournal);
        operationJournal.setIdOperationJournal(cle);
        Journal journal = journalDao.findById(codeJournal).orElse(null);
        operationJournal.setJournal(journal);
        operationJournal.setOperation(operation);
        operationJournal.setDateDernModifClient(LocalDateTime.now());
        operationJournal.setDateCreationServeur(LocalDateTime.now());
        operationJournalDao.save(operationJournal);

        return constatationChargeMapper.deEntite(operation);
    }

    //Prise en charge comptable des paiements de commission et taxes
    public OperationCommissionDto genererEcritureComptable(OperationCommissionDto op) {
        //Création de la transaction
        Transaction transaction = new Transaction();
        Long idTransaction = transactionDao.getIdTransactionByCodeAndDate(
                op.getOpcvm().getIdOpcvm(),
                op.getNatureOperation().getCodeNatureOperation(),
                op.getDateOperation()
        );
        if(idTransaction == null || idTransaction.equals(0L)) {
            transaction.setDateTransaction(op.getDateOperation());
            transaction.setOpcvm(opcvmMapper.deOpcvmDto(op.getOpcvm()));
            transaction.setIdSeance(op.getIdSeance());
            transaction.setNatureOperation(natureOperationMapper.deNatureOperationDto(op.getNatureOperation()));
            transaction.setDateDernModifClient(LocalDateTime.now());
            transaction = transactionDao.save(transaction);
            idTransaction = transaction.getIdTransaction();
        }
        else
        {
            transaction.setIdTransaction(idTransaction);
        }
        transaction.setEstVerifie(false);
        op.setIdTransaction(idTransaction);
        //Mettre à jour la valeur de la transaction dans opération
        OperationCommission operation = commissionMapper.deDto(op);
        operation.setTransaction(transaction);
        operation.setDateDernModifClient(LocalDateTime.now());
        operation.setDateDernModifServeur(LocalDateTime.now());
        operation = em.merge(operation);

        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                operation.getNatureOperation() != null ? operation.getNatureOperation().getCodeNatureOperation() : null,
                operation.getValeurCodeAnalytique(),
                operation.getValeurFormule(),
                operation.getOpcvm() != null ? operation.getOpcvm().getIdOpcvm() : null,
                operation.getActionnaire() != null ? operation.getActionnaire().getIdPersonne() : null,
                operation.getTitre() != null ? operation.getTitre().getIdTitre() : null,
                operation
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        //Insertion des formules et leurs valeurs
        String[] tabsFormules = op.getValeurFormule().split(";");
        Map<String, String> formules = new HashMap<>();
        for (String code : tabsFormules) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    formules.put(tabFormules[i], tabFormules[i + 1]);
                    OperationFormule operationFormule = new OperationFormule();
                    CleOperationFormule cle = new CleOperationFormule();
                    cle.setIdFormule(Long.valueOf(tabFormules[i]));
                    cle.setIdOperation(operation.getIdOperation());
                    operationFormule.setIdOperationFormule(cle);
                    Formule formule = formuleDao.findById(Long.valueOf(tabFormules[i])).orElse(null);
                    Formule formule1 = new Formule();
                    formule1.setIdFormule(Long.valueOf(tabFormules[i]));
                    operationFormule.setFormule(formule);
                    Operation op1 = new Operation();
                    op1.setIdOperation(operation.getIdOperation());
                    operationFormule.setOperation(operation);
//                    operationFormule.setValeur(BigDecimal.valueOf(Long.parseLong(tabFormules[i + 1])));
                    operationFormule.setValeur(new BigDecimal(tabFormules[i + 1]));
                    operationFormule.setDateDernModifClient(LocalDateTime.now());
                    operationFormuleDao.save(operationFormule);
                }
            }
        }

        //Insertion des codes analytiques et leurs valeurs
        String[] tabCodeAnalytiques = op.getValeurCodeAnalytique().split(";");
        Map<String, String> codesAnalytiques = new HashMap<>();
        for (String code : tabCodeAnalytiques) {
            String[] tabFormules = code.split(":");
            for (int i = 0; i < tabFormules.length; i++) {
                if((i+1) < tabFormules.length) {
                    codesAnalytiques.put(tabFormules[i], tabFormules[i + 1]);
                    CleOperationCodeAnalytique cle = new CleOperationCodeAnalytique();
                    cle.setIdOperation(operation.getIdOperation());
                    cle.setCodeAnalytique(tabFormules[i + 1]);
                    cle.setTypeCodeAnalytique(tabFormules[i]);
                    OperationCodeAnalytique operationCodeAnalytique = new OperationCodeAnalytique();
                    operationCodeAnalytique.setIdOperationCodeAnalytique(cle);
                    operationCodeAnalytique.setOperation(operation);
                    operationCodeAnalytique.setDateDernModifClient(LocalDateTime.now());
                    operationCodeAnalytiqueDao.save(operationCodeAnalytique);
                }
            }
        }

        //Insertion des lignes de mouvement
        mouvementDao.saveAll(mouvements);

        //Journalisation comptable de l'opération
        String codeJournal = mouvementDao.getCodeJournalByIdOp(operation.getIdOperation());
        if(!StringUtils.hasLength(codeJournal)) {
            if (operation.getNatureOperation() != null) {
                codeJournal = natureOperationDao.getCodeJournalByCodeNatureOp(operation.getNatureOperation().getCodeNatureOperation());
            }
        }
        OperationJournal operationJournal = new OperationJournal();
        CleOperationJournal cle = new CleOperationJournal();
        cle.setIdOperation(operation.getIdOperation());
        cle.setCodeJournal(codeJournal);
        operationJournal.setIdOperationJournal(cle);
        Journal journal = journalDao.findById(codeJournal).orElse(null);
        operationJournal.setJournal(journal);
        operationJournal.setOperation(operation);
        operationJournal.setDateDernModifClient(LocalDateTime.now());
        operationJournal.setDateCreationServeur(LocalDateTime.now());
        operationJournalDao.save(operationJournal);

        return commissionMapper.deEntite(operation);
    }

    //Récupération des détails d'une opération comptable
    public ResponseEntity<Object> afficherDetailsEcriture(Long idOperation) {
        try {
            return ResponseHandler.generateResponse(
                    "Détails écriture",
                    HttpStatus.OK,
                    libraryDao.afficherDetailsEcriture(idOperation));
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //Récupération du régistre de l'actionnaire
    public ResponseEntity<?> registreActionnaire(RegistreActionnaireRequest request) {
        try {
            return ResponseHandler.generateResponse(
                "Registre actionnaire",
                HttpStatus.OK,
                libraryDao.registreActionnaire(
                    request.getIdOpcvm(),
                    request.getIdActionnaire(),
                    request.getDateEstimation()
                )
            );
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    public ResponseEntity<?> registreActionnaires(RegistreActionnaireRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<RegistreActionnaireProjection> registreActionnairesPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                registreActionnairesPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                registreActionnairesPage = libraryDao.registreActionnaires(
                    request.getIdOpcvm(),
                    request.getIdActionnaire(),
                    request.getDateEstimation(),
                    pageable
                );
            }
            List<RegistreActionnaireProjection> content = registreActionnairesPage.getContent().stream().toList();
            DataTablesResponse<RegistreActionnaireProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)registreActionnairesPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)registreActionnairesPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Registre actionnaire",
                    HttpStatus.OK,
                    dataTablesResponse
            );
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    //Récupération du cump de l'actionnaire
    public ResponseEntity<?> cumpActionnaire(CumpRequest request) {
        try {
            return ResponseHandler.generateResponse(
                    "Cump actionnaire",
                    HttpStatus.OK,
                    libraryDao.cumpActionnaire(
                            request.getIdOpcvm(),
                            request.getIdActionnaire(),
                            request.getDateEstimation()
                    ));
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    public ResponseEntity<Object> registreActionnaireExportJasperReport(
            HttpServletResponse response, RegistreActionPDFRequest request) {
        try {
            List<RegistreActionnaireProjection> list = libraryDao.registreActionnaire(
                request.getIdOpcvm(),
                request.getIdActionnaire(),
                request.getDateEstimation()
            );
//            Map<String, Object> parameters = new HashMap<>();
//            DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//            String letterDate = dateFormatter.format(new Date());
//            parameters.put("letterDate", letterDate);
//            File file = ResourceUtils.getFile("classpath:registre_actionnaire.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);//
//            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            Map<String, Object> parameters = new HashMap<>();
            DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
            String letterDate = dateFormatter.format(new Date());
            parameters.put("letterDate", letterDate);

            // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
            InputStream inputStream = getClass().getResourceAsStream("/registre_actionnaire.jrxml");
            if (inputStream == null) {
                throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export vers le flux de sortie HTTP
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            return ResponseHandler.generateResponse(
                    "PDF Régistre Actionnaire",
                    HttpStatus.OK,
                    list);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
