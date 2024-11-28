package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ChargerLigneMvtRequest;
import com.ged.dto.request.PrecalculSouscriptionRequest;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.*;
import com.ged.entity.standard.Personne;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.opcciel.OperationMapper;
import com.ged.mapper.opcciel.OperationSouscriptionRachatMapper;
import com.ged.projection.LigneMvtClotureProjection;
import com.ged.projection.PrecalculSouscriptionProjection;
import com.ged.service.opcciel.IbService;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.opcciel.PlanService;
import com.ged.service.opcciel.TypeFormuleService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppService {
    @PersistenceContext
    EntityManager em;
    private final LibraryDao libraryDao;
    private final PlanService planService;
    private final OpcvmService opcvmService;
    private final IbService ibService;
    private final TypeFormuleService typeFormuleService;
    private final MouvementDao mouvementDao;
    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final TransactionDao transactionDao;
    private final OperationMapper operationMapper;
    private final OperationDao operationDao;
    private final FormuleDao formuleDao;
    private final OperationFormuleDao operationFormuleDao;
    private final OperationCodeAnalytiqueDao operationCodeAnalytiqueDao;
    private final NatureOperationDao natureOperationDao;
    private final JournalDao journalDao;
    private final OperationJournalDao operationJournalDao;
    private final PersonneDao personneDao;
    private final OperationSouscriptionRachatMapper souscriptionRachatMapper;

    public AppService(
            LibraryDao libraryDao, PlanService planService, OpcvmService opcvmService,
            IbService ibService, TypeFormuleService typeFormuleService,
            MouvementDao mouvementDao, OpcvmMapper opcvmMapper,
            NatureOperationMapper natureOperationMapper, TransactionDao transactionDao,
            OperationMapper operationMapper, OperationDao operationDao,
            FormuleDao formuleDao, OperationFormuleDao operationFormuleDao,
            OperationCodeAnalytiqueDao operationCodeAnalytiqueDao,
            NatureOperationDao natureOperationDao, JournalDao journalDao,
            OperationJournalDao operationJournalDao, PersonneDao personneDao, OperationSouscriptionRachatMapper souscriptionRachatMapper) {
        this.libraryDao = libraryDao;
        this.planService = planService;
        this.opcvmService = opcvmService;
        this.ibService = ibService;
        this.typeFormuleService = typeFormuleService;
        this.mouvementDao = mouvementDao;
        this.opcvmMapper = opcvmMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.transactionDao = transactionDao;
        this.operationMapper = operationMapper;
        this.operationDao = operationDao;
        this.formuleDao = formuleDao;
        this.operationFormuleDao = operationFormuleDao;
        this.operationCodeAnalytiqueDao = operationCodeAnalytiqueDao;
        this.natureOperationDao = natureOperationDao;
        this.journalDao = journalDao;
        this.operationJournalDao = operationJournalDao;
        this.personneDao = personneDao;
        this.souscriptionRachatMapper = souscriptionRachatMapper;
    }

    public SeanceOpcvm currentSeance(Long idOpcvm) {
        return libraryDao.currentSeance(idOpcvm);
    }

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
//                TypeFormule typeFormule = typeFormuleService.afficherSelonId(l.getCodeTypeFormule().trim());
                mvt.setTypeValeur(l.getCodeTypeFormule().trim());
//                mvt.setTypeFormule(typeFormule);
            }
            mvt.setDateDernModifClient(LocalDateTime.now());
            return mvt;
        }).toList();
    }

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

        System.out.println("Formules => " + formules);
        System.out.println("Codes analytiques => " + codesAnalytiques);

        return souscriptionRachatMapper.deOperationSouscriptionRachat(operation);
    }

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

        System.out.println("Formules => " + formules);
        System.out.println("Codes analytiques => " + codesAnalytiques);

        return operationMapper.deOperation(operation);
    }

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
}
