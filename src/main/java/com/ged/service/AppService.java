package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

//@RequiredArgsConstructor
@Service
public class AppService {
    @PersistenceContext
    EntityManager em;
    private final UtilisateurDao utilisateurDao;
    private final LibraryDao libraryDao;
    private final PlanService planService;
    private final OpcvmService opcvmService;
    private final IbService ibService;
    private final MouvementDao mouvementDao;
    private final OpcvmMapper opcvmMapper;
    private final OpcvmDao opcvmDao;
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

    public AppService(UtilisateurDao utilisateurDao, LibraryDao libraryDao, PlanService planService, OpcvmService opcvmService, IbService ibService, MouvementDao mouvementDao, OpcvmMapper opcvmMapper, OpcvmDao opcvmDao, NatureOperationMapper natureOperationMapper, TransactionDao transactionDao, OperationMapper operationMapper, FormuleDao formuleDao, OperationFormuleDao operationFormuleDao, OperationCodeAnalytiqueDao operationCodeAnalytiqueDao, NatureOperationDao natureOperationDao, JournalDao journalDao, OperationJournalDao operationJournalDao, PersonneDao personneDao, OperationSouscriptionRachatMapper souscriptionRachatMapper, OperationRestitutionReliquatMapper operationRestitutionReliquatMapper, OperationTransfertPartMapper transfertPartMapper, OperationConstatationChargeMapper constatationChargeMapper, OperationCommissionMapper commissionMapper) {
        this.utilisateurDao = utilisateurDao;
        this.libraryDao = libraryDao;
        this.planService = planService;
        this.opcvmService = opcvmService;
        this.ibService = ibService;
        this.mouvementDao = mouvementDao;
        this.opcvmMapper = opcvmMapper;
        this.opcvmDao = opcvmDao;
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
