package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.opcciel.OperationRestitutionReliquatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationTransfertPartDto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ChargerLigneMvtRequest;
import com.ged.dto.request.OperationTransfertPartRequest;
import com.ged.dto.request.PrecalculSouscriptionRequest;
import com.ged.dto.request.SoldeToutCompteRequest;
import com.ged.entity.opcciel.*;
import com.ged.entity.opcciel.comptabilite.*;
import com.ged.entity.security.Utilisateur;
import com.ged.entity.standard.Personne;
import com.ged.mapper.opcciel.*;
import com.ged.projection.LigneMvtClotureProjection;
import com.ged.projection.PrecalculSouscriptionProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.IbService;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.opcciel.PlanService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Utilisateur currentUser(Principal principal) {
        String username = principal.getName();
        return utilisateurDao.findByUsernameAndEstActif(username, true).orElse(null);
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
            switch (request.getCodeCharge().trim()) {
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
    public ResponseEntity<?> registreActionnaire(OperationTransfertPartRequest request) {
        try {
            return ResponseHandler.generateResponse(
                    "Registre actionnaire",
                    HttpStatus.OK,
                    libraryDao.registreActionnaire(
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

    //Récupération du cump de l'actionnaire
    public ResponseEntity<?> cumpActionnaire(OperationTransfertPartRequest request) {
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
}
