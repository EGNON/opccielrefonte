package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
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
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
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

@RequiredArgsConstructor
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

    public Utilisateur currentUser(Principal principal) {
        String username = principal.getName();
        return utilisateurDao.findByUsernameAndEstActif(username, true).orElse(null);
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
