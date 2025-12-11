package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.lab.GelDegelDao;
import com.ged.dao.opcciel.DepotRachatDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationSouscriptionRachatDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.OperationDao;
import com.ged.dao.standard.*;
import com.ged.dao.titresciel.TcnDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.opcciel.comptabilite.VerifDepSouscriptionIntRachatDto;
import com.ged.dto.request.PrecalculSouscriptionRequest;
import com.ged.dto.request.VerificationListeDepotRequest;
import com.ged.dto.standard.PaysDto;
import com.ged.dto.standard.PhForm;
import com.ged.dto.standard.PmForm;
import com.ged.dto.titresciel.TcnDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.lab.GelDegel;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.*;
import com.ged.entity.titresciel.Registraire;
import com.ged.entity.titresciel.Tcn;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.DepotRachatMapper;
import com.ged.mapper.opcciel.OperationMapper;
import com.ged.mapper.opcciel.OperationSouscriptionRachatMapper;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.titresciel.TcnMapper;
import com.ged.mapper.titresciel.TitreMapper;
import com.ged.projection.*;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.DepotRachatService;
import com.ged.service.opcciel.SeanceOpcvmService;
import com.ged.validator.DepotRachatValidator;
import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepotRachatImpl implements DepotRachatService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    private EntityManager em;
    private final DepotRachatDao depotRachatDao;
    private final OpcvmDao opcvmDao;
    private final GelDegelDao gelDegelDao;
    private final NatureOperationDao natureOperationDao;
    private final DepotRachatMapper depotRachatMapper;
    private final PersonneDao personneDao;
    private final PersonnePhysiqueDao personnePhysiqueDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final PaysDao paysDao;
    private final ActionnaireOpcvmDao actionnaireOpcvmDao;
    private final ActionnaireCommissionDao actionnaireCommissionDao;
    private final ProfessionDao professionDao;
    private final ProfilCommissionSousRachDao profilCommissionSousRachDao;
    private final PaysMapper paysMapper;
    private final LibraryDao libraryDao;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final QualiteDao qualiteDao;
    private final StatutPersonneDao statutPersonneDao;
    private final SeanceOpcvmService seanceOpcvmService;
    private final AppService appService;
    private final OperationSouscriptionRachatMapper souscriptionRachatMapper;
    private final OperationSouscriptionRachatDao souscriptionRachatDao;
    private final TitreDao titreDao;
    private final TitreMapper titreMapper;
    private final TcnDao tcnDao;
    private final TcnMapper tcnMapper;

    private static ThreadLocal<String> newClient = new ThreadLocal<>();
//    @PersistenceContext
//    EntityManager em;

    public DepotRachatImpl(
            DepotRachatDao DepotRachatDao,
            OpcvmDao opcvmDao, GelDegelDao gelDegelDao,
            NatureOperationDao natureOperationDao,
            DepotRachatMapper DepotRachatMapper,
            PersonneDao personneDao, PersonnePhysiqueDao personnePhysiqueDao, PersonneMoraleDao personneMoraleDao, PaysDao paysDao, ActionnaireOpcvmDao actionnaireOpcvmDao, ActionnaireCommissionDao actionnaireCommissionDao, ProfessionDao professionDao, ProfilCommissionSousRachDao profilCommissionSousRachDao, PaysMapper paysMapper,
            LibraryDao libraryDao, SeanceOpcvmDao seanceOpcvmDao, QualiteDao qualiteDao, StatutPersonneDao statutPersonneDao,
            SeanceOpcvmService seanceOpcvmService, AppService appService, OperationSouscriptionRachatMapper souscriptionRachatMapper, OperationSouscriptionRachatDao souscriptionRachatDao, OperationDao operationDao, OperationMapper operationMapper, TitreDao titreDao, TitreMapper titreMapper, TcnDao tcnDao, TcnMapper tcnMapper) {
        this.depotRachatDao = DepotRachatDao;
        this.opcvmDao = opcvmDao;
        this.gelDegelDao = gelDegelDao;
        this.natureOperationDao = natureOperationDao;

        this.depotRachatMapper = DepotRachatMapper;
        this.personneDao = personneDao;
        this.personnePhysiqueDao = personnePhysiqueDao;
        this.personneMoraleDao = personneMoraleDao;
        this.paysDao = paysDao;
        this.actionnaireOpcvmDao = actionnaireOpcvmDao;
        this.actionnaireCommissionDao = actionnaireCommissionDao;
        this.professionDao = professionDao;
        this.profilCommissionSousRachDao = profilCommissionSousRachDao;
        this.paysMapper = paysMapper;
        this.libraryDao = libraryDao;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.qualiteDao = qualiteDao;
        this.statutPersonneDao = statutPersonneDao;
        this.seanceOpcvmService = seanceOpcvmService;
        this.appService = appService;
        this.souscriptionRachatMapper = souscriptionRachatMapper;
        this.souscriptionRachatDao = souscriptionRachatDao;
        this.titreDao = titreDao;
        this.titreMapper = titreMapper;
        this.tcnDao = tcnDao;
        this.tcnMapper = tcnMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTousLesDepots(DatatableParameters parameters, Long idOpcvm, Long idSeance) {
        try {
            System.out.println(idOpcvm + ";" + idSeance);
            Opcvm opcvm = opcvmDao.findById(idOpcvm).orElseThrow();
            Pageable pageable = PageRequest.of(
                    parameters.getStart() / parameters.getLength(), parameters.getLength());
            Page<DepotRachat> DepotRachatPage;
            DepotRachatPage = depotRachatDao.listeDesDepotSeance(idOpcvm, idSeance, pageable);
            List<DepotRachatDto> content = DepotRachatPage.getContent().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            DataTablesResponse<DepotRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherDepotRachatTransfert(DatatableParameters parameters, Long idOpcvm, Long idSeance) {
        try {
//            System.out.println(idOpcvm + ";" + idSeance);
//            Opcvm opcvm = opcvmDao.findById(idOpcvm).orElseThrow();
            Pageable pageable = PageRequest.of(
                    parameters.getStart() / parameters.getLength(), parameters.getLength());
            Page<DepotRachatTransfertProjection> DepotRachatPage;
            DepotRachatPage = depotRachatDao.depotRachatTransfert(idSeance, idOpcvm,null,null, pageable);
            List<DepotRachatTransfertProjection> content = DepotRachatPage.getContent().stream().toList();
            DataTablesResponse<DepotRachatTransfertProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> listeDepotAVerifier(VerificationListeDepotRequest verificationListeDepotRequest) {
        try {
            Pageable pageable = PageRequest.of(
                    verificationListeDepotRequest.getDatatableParameters().getStart() / verificationListeDepotRequest.getDatatableParameters().getLength(),
                    verificationListeDepotRequest.getDatatableParameters().getLength());
            Page<DepotRachat> DepotRachatPage;
            DepotRachatPage = depotRachatDao.tousLesDepotsSouscription(
                    verificationListeDepotRequest.getOpcvmDto().getIdOpcvm(),
                    verificationListeDepotRequest.getSeanceOpcvmDto().getIdSeanceOpcvm().getIdSeance(),
                    verificationListeDepotRequest.getEstVerifier(),
                    verificationListeDepotRequest.getEstVerifie1(),
                    verificationListeDepotRequest.getEstVerifie2(),
                    pageable);
            List<DepotRachatDto> content = DepotRachatPage.getContent().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            DataTablesResponse<DepotRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(verificationListeDepotRequest.getDatatableParameters().getDraw());
            dataTablesResponse.setRecordsFiltered((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setData(content);

            return ResponseHandler.generateResponse(
                    "LISTE DE VERIFICATION DES SOUSCRIPTIONS",
                    HttpStatus.OK,
                    dataTablesResponse
            );
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }


    @Override
    public ResponseEntity<Object> importDepotPH(List<PhForm> phForms) {
        try {
            String message="";
            for (int i = 1; i < phForms.size(); i++)
            {
                List<Personne> obj = new ArrayList<>();
                obj = personneDao.findByNumeroCpteDeposit(phForms.get(i).getNumeroCpteDeposit());
                if (obj.size() != 0)
                {
                    BigDecimal solde = libraryDao.solde(phForms.get(i).getOpcvm().getIdOpcvm(), obj.get(0).getIdPersonne());
                    if (phForms.get(i).getMontantSouscrit().doubleValue() > (

                            phForms.get(i).getMontantSouscrit().
                                    add(solde==null?BigDecimal.ZERO:solde)).doubleValue())
                    {
                        message=
                                "ATTENTION: COMPTE N°" + phForms.get(i).getNumeroCpteDeposit() + " \n le montant souscrit ne peut excéder " +
                                        "le total du montant déposé et du solde espèce du client";

                        i=phForms.size();
                        return ResponseHandler.generateResponse(
                                "IMPORT DEPOT POUR SOUSCRIPTIONS",
                                HttpStatus.OK,
                                message
                        );
                    }

                    {
                        GelDegel gelDegel=gelDegelDao.verifierPersonneGele(obj.get(0).getIdPersonne());
                        if(gelDegel!=null){
                            message=
                                    "ATTENTION: COMPTE N°" + phForms.get(i).getNumeroCpteDeposit() + " \n les avoirs de ce compte sont gelés";

                            i=phForms.size();
                            return ResponseHandler.generateResponse(
                                    "IMPORT DEPOT POUR SOUSCRIPTIONS",
                                    HttpStatus.OK,
                                    message
                            );
                        }
                    }
                }
            }
            Long idPersonne=0L;
            for (int j = 1; j < phForms.size(); j++)
            {
                {
                    List<Personne> obj = new ArrayList<>();
                    obj = personneDao.findByNumeroCpteDeposit(phForms.get(j).getNumeroCpteDeposit());
                    if (obj.size() == 0)
                    {
                        idPersonne = CreerActionnaire(phForms.get(j));
                    }
                    else
                    {
                        idPersonne = obj.get(0).getIdPersonne();
                        List<ActionnaireOpcvm> lstOpcvmActionnaire=actionnaireOpcvmDao.afficherParOpcvmEtPersonne(
                                phForms.get(j).getOpcvm().getIdOpcvm(),idPersonne
                        );
                        if (lstOpcvmActionnaire.size() == 0)
                        {
                            ActionnaireOpcvm objs = new ActionnaireOpcvm();
                            Opcvm opcvm=new Opcvm();
                            opcvm.setIdOpcvm(phForms.get(j).getOpcvm().getIdOpcvm());
                            objs.setOpcvm(opcvm);
                            objs.setPersonne(obj.get(0));
                            CleActionnaireOpcvm cleActionnaireOpcvm=new CleActionnaireOpcvm();
                            cleActionnaireOpcvm.setIdOpcvm(phForms.get(j).getOpcvm().getIdOpcvm());
                            cleActionnaireOpcvm.setIdPersonne(idPersonne);
                            objs.setCleActionnaireOpcvm(cleActionnaireOpcvm);
                            ActionnaireOpcvm sortieActOpcvm = actionnaireOpcvmDao.save(objs);
                            if (sortieActOpcvm!=null) {
                                List<ProfilCommissionSousRach> profilCommissionSousRaches = profilCommissionSousRachDao.findByOpcvm(opcvm);
                                for (int s = 0; s < profilCommissionSousRaches.size(); s++) {
                                    List<ActionnaireCommission> actionnaireCommissions = actionnaireCommissionDao.afficherSelonOpcvm(
                                            phForms.get(j).getOpcvm().getIdOpcvm(), obj.get(0).getIdPersonne(), profilCommissionSousRaches.get(s).getTypeCommission(),
                                            profilCommissionSousRaches.get(s).getCodeProfil()
                                    );

                                    if (actionnaireCommissions.size() == 0) {
                                        ActionnaireCommission objActionnaireCommission = new ActionnaireCommission();
                                        objActionnaireCommission.setOpcvm(opcvm);
                                        objActionnaireCommission.setPersonne(obj.get(0));
                                        objActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(s).getTypeCommission());
                                        objActionnaireCommission.setCodeProfil(profilCommissionSousRaches.get(s).getCodeProfil());
                                        objActionnaireCommission.setDate(LocalDateTime.now());
                                        CleActionnaireCommission cleActionnaireCommission=new CleActionnaireCommission();
                                        cleActionnaireCommission.setIdOpcvm(opcvm.getIdOpcvm());
                                        cleActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(s).getTypeCommission());
                                        cleActionnaireCommission.setIdPersonne(idPersonne);
                                        objActionnaireCommission.setCleActionnaireCommission(cleActionnaireCommission);
                                        actionnaireCommissionDao.save(objActionnaireCommission);
                                    }
                                }
                            }
                        }
                    }


                    DepotRachat objDepot = new DepotRachat();
                    if (phForms.get(j).getDistributeur()!=null) {
                        Personne personne = personneDao.findById(phForms.get(j).getDistributeur().getIdPersonne()).orElseThrow();
                        objDepot.setPersonne(personne);
                    }
                    if (idPersonne != null && idPersonne!=0) {
                        Personne personne = personneDao.findById(idPersonne).orElseThrow();
                        objDepot.setActionnaire(personne);
                    }
                    if (phForms.get(j).getOpcvm() != null) {
                        Opcvm opcvm = opcvmDao.findById(phForms.get(j).getOpcvm().getIdOpcvm()).orElseThrow();
                        objDepot.setOpcvm(opcvm);
                    }

                    objDepot.setIdTransaction(0L);
                    objDepot.setMontant(phForms.get(j).getMontantSouscrit());
                    LocalDateTime dateOperation=LocalDateTime.now();
                    SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(phForms.get(j).getOpcvm().getIdOpcvm());
                    dateOperation=seanceOpcvm.getDateFermeture();
                    objDepot.setDateOperation(dateOperation);
                    objDepot.setDateSaisie(LocalDateTime.now());
                    objDepot.setDateValeur(dateOperation);
                    objDepot.setDatePiece(dateOperation);
                    objDepot.setEstOD(false);
                    objDepot.setEstGenere(false);
                    objDepot.setEstVerifier(false);
                    objDepot.setDateVerification(LocalDateTime.parse("2014-12-20T00:00:00"));
                    objDepot.setNomVerificateur("");
                    objDepot.setReferencePiece("");
                    objDepot.setModeVL("CONNU");
                    objDepot.setLibelleOperation("DEPOT DE " + phForms.get(j).getMontantSouscrit()+" FCFA POUR SOUSCRIPTION ");
                    objDepot.setIdSeance(seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
                    objDepot.setQuantite(BigDecimal.ZERO);
                    objDepot.setEcriture("A");
                    objDepot.setType("S");
                    NatureOperation natureOperation = natureOperationDao.findByCodeNatureOperationIgnoreCase(
                            "DEP_SOUS").orElseThrow();
                    objDepot.setNatureOperation(natureOperation);
                    objDepot.setValeurCodeAnalytique("OPC:" + phForms.get(j).getOpcvm().getIdOpcvm().toString() +
                            ";ACT:" + idPersonne.toString());
                    objDepot.setValeurFormule("2:" + phForms.get(j).getMontantSouscrit().toString().replace(',', '.'));

                    objDepot.setMontantSouscrit(phForms.get(j).getMontantSouscrit());
                    objDepot.setEstVerifie1(false);
                    objDepot.setEstVerifie2(false);
                    objDepot.setUserLoginVerificateur1("");
                    objDepot.setUserLoginVerificateur2("");
                    objDepot = depotRachatDao.save(objDepot);
                    message="Enregistrement effectué avec succès";
                }


            }

            return ResponseHandler.generateResponse(
                    "IMPORT DEPOT POUR SOUSCRIPTIONS",
                    HttpStatus.OK,
                    message
            );
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    private Long CreerActionnaire(PhForm phForms){
        Long id = 0L;

        PersonnePhysique obj = new PersonnePhysique();
        //obj.IdPersonne = 1;//rds_IdPersonne.Value.ToString();
        obj.setCivilite("");
        obj.setSexe(phForms.getSexe());
        obj.setNom(phForms.getNom());
        obj.setNomPersonnePhysique(phForms.getNom());
        obj.setPrenom(phForms.getPrenom());
        obj.setPrenomPersonnePhysique(phForms.getPrenom());
        obj.setDenomination(phForms.getNom()+" "+phForms.getPrenom());
        obj.setNumeroCpteDeposit(phForms.getNumeroCpteDeposit());
        obj.setNumCompteSgi(phForms.getNumeroCpteDeposit());
        try
        {
            obj.setDateNaissance((phForms.getDateNaissance() == null) ? LocalDateTime.parse("1988-01-01T00:00:00") : phForms.getDateNaissance());
        }
        catch (Exception e)
        {
            obj.setDateNaissance(LocalDateTime.parse("1988-01-01T00:00:00"));
        }
        Pays paysDto=new Pays();
        paysDto=paysDao.rechercherPays("AUCUN");
        obj.setPaysNationalite(paysDto);
        obj.setLieuNaissance(phForms.getLieuNaissance());
        obj.setEstMineur(false);
        obj.setSecteur(null);
        try
        {
            Profession profession=new Profession();
            obj.setProfession((phForms.getProfession() == null) ? null:professionDao.findByLibelleProfession(phForms.getProfession().getLibelleProfession()));
        }
        catch(Exception e)
        {
            obj.setProfession(null);
        }
//        obj.setEstActifPersonne = true;
//        obj.PhotoPersonnePhysique = "";
//        obj.setSignaturePersonnePhysique = "";
        obj.setPaysResidence(paysDto);
        obj.setLibelleTypePersonne("PH");
        obj.setTypePersonne("PH");
//        obj.LibelleQualite = "ACTIONNAIRES";

        obj.setFixe1(phForms.getFixe1());
        obj.setMobile1(phForms.getMobile1());
        obj.setMobile2("");
        obj.setEmailPerso(phForms.getEmailPerso());
        obj.setBp("");
//        obj.setNomPrenomMere = Convert.ToString(gv_Liste.Rows[index].Cells["Nom et Prénom de la mère"].Value);
        obj.setNumeroPiece(phForms.getNumeroPiece());
        obj.setTypePiece(phForms.getTypePiece());
        obj.setDateExpirationPiece(LocalDateTime.parse("1900-01-01T00:00:00"));
        obj.setNumCompteDepositaire(phForms.getNumeroCpteDeposit());

        obj.setStatutCompte("OUVERT");
        PersonnePhysique sortie = personnePhysiqueDao.save(obj);
//        message =LGO.Business.Tools.SplitMessage(sortie);

        if (sortie!=null)
        {
            StatutPersonne statutPersonne=new StatutPersonne();
            statutPersonne.setPersonne(sortie);
            Qualite qualite=qualiteDao.findByLibelleQualiteContains("actionnaires");
            statutPersonne.setQualite(qualite);
            CleStatutPersonne cleStatutPersonne=new CleStatutPersonne();
            cleStatutPersonne.setIdPersonne(sortie.getIdPersonne());
            cleStatutPersonne.setIdQualite(qualite.getIdQualite());
            statutPersonne.setIdStatutPersonne(cleStatutPersonne);
            statutPersonneDao.save(statutPersonne);

            ActionnaireOpcvm objs = new ActionnaireOpcvm();
            Opcvm opcvm=new Opcvm();
            opcvm.setIdOpcvm(phForms.getOpcvm().getIdOpcvm());
            objs.setOpcvm(opcvm);
            objs.setPersonne(sortie);
            CleActionnaireOpcvm cleActionnaireOpcvm=new CleActionnaireOpcvm();
            cleActionnaireOpcvm.setIdOpcvm(phForms.getOpcvm().getIdOpcvm());
            cleActionnaireOpcvm.setIdPersonne(sortie.getIdPersonne());
            id= sortie.getIdPersonne();
            objs.setCleActionnaireOpcvm(cleActionnaireOpcvm);
            ActionnaireOpcvm sortieActOpcvm = actionnaireOpcvmDao.save(objs);

            if (sortieActOpcvm!=null) {
                List<ProfilCommissionSousRach> profilCommissionSousRaches = profilCommissionSousRachDao.findByOpcvm(opcvm);
                for (int j = 0; j < profilCommissionSousRaches.size(); j++) {
                    List<ActionnaireCommission> actionnaireCommissions = actionnaireCommissionDao.afficherSelonOpcvm(
                            phForms.getOpcvm().getIdOpcvm(), sortie.getIdPersonne(), profilCommissionSousRaches.get(j).getTypeCommission(),
                            profilCommissionSousRaches.get(j).getCodeProfil()
                    );

                    if (actionnaireCommissions.size() == 0) {
                        ActionnaireCommission objActionnaireCommission = new ActionnaireCommission();
                        objActionnaireCommission.setOpcvm(opcvm);
                        objActionnaireCommission.setPersonne(sortie);
                        objActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(j).getTypeCommission());
                        objActionnaireCommission.setCodeProfil(profilCommissionSousRaches.get(j).getCodeProfil());
                        objActionnaireCommission.setDate(LocalDateTime.now());
                        CleActionnaireCommission cleActionnaireCommission=new CleActionnaireCommission();
                        cleActionnaireCommission.setIdOpcvm(opcvm.getIdOpcvm());
                        cleActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(j).getTypeCommission());
                        cleActionnaireCommission.setIdPersonne(sortie.getIdPersonne());
                        objActionnaireCommission.setCleActionnaireCommission(cleActionnaireCommission);
                        actionnaireCommissionDao.save(objActionnaireCommission);
                    }
                }
            }
        }

        if(id!=0)
        {
            setNewClient(getNewClient()+ id + ",");
        }

        return id;
    }

    public static String getNewClient() {
        return newClient.get();
    }

    public static void setNewClient(String newClient) {
        DepotRachatImpl.newClient.set(newClient);
    }

    /**
     * @param pmForms
     * @return
     */
    @Override
    public ResponseEntity<Object> importDepotPM(List<PmForm> pmForms) {
        try {
            String message="";
            for (int i = 1; i < pmForms.size(); i++)
            {
                List<Personne> obj = new ArrayList<>();
                obj = personneDao.findByNumeroCpteDeposit(pmForms.get(i).getNumeroCpteDeposit());
                if (obj.size() != 0)
                {
                    BigDecimal solde = libraryDao.solde(pmForms.get(i).getOpcvm().getIdOpcvm(), obj.get(0).getIdPersonne());
                    if (pmForms.get(i).getMontantSouscrit().doubleValue() > (

                            pmForms.get(i).getMontantSouscrit().
                                    add(solde==null?BigDecimal.ZERO:solde)).doubleValue())
                    {
                        message=
                                "ATTENTION: COMPTE N°" + pmForms.get(i).getNumeroCpteDeposit() + " \n le montant souscrit ne peut excéder " +
                                        "le total du montant déposé et du solde espèce du client";

                        i=pmForms.size();
                        return ResponseHandler.generateResponse(
                                "IMPORT DEPOT POUR SOUSCRIPTIONS",
                                HttpStatus.OK,
                                message
                        );
                    }
                    {
                        GelDegel gelDegel=gelDegelDao.verifierPersonneGele(obj.get(0).getIdPersonne());
                        if(gelDegel!=null){
                            message=
                                    "ATTENTION: COMPTE N°" + pmForms.get(i).getNumeroCpteDeposit() + " \n les avoirs de ce compte sont gelés";

                            i=pmForms.size();
                            return ResponseHandler.generateResponse(
                                    "IMPORT DEPOT POUR SOUSCRIPTIONS",
                                    HttpStatus.OK,
                                    message
                            );
                        }
                    }
                }
            }
            Long idPersonne=0L;
        for (int j = 1; j < pmForms.size(); j++)
        {
            {
                List<Personne> obj = new ArrayList<>();
                obj = personneDao.findByNumeroCpteDeposit(pmForms.get(j).getNumeroCpteDeposit());
                if (obj.size() == 0)
                {
                    idPersonne = CreerActionnaireMo(pmForms.get(j));
                }
                else
                {
                    idPersonne = obj.get(0).getIdPersonne();
                    List<ActionnaireOpcvm> lstOpcvmActionnaire=actionnaireOpcvmDao.afficherParOpcvmEtPersonne(
                            pmForms.get(j).getOpcvm().getIdOpcvm(),idPersonne
                    );
                    if (lstOpcvmActionnaire.size() == 0)
                    {
                        ActionnaireOpcvm objs = new ActionnaireOpcvm();
                        Opcvm opcvm=new Opcvm();
                        opcvm.setIdOpcvm(pmForms.get(j).getOpcvm().getIdOpcvm());
                        objs.setOpcvm(opcvm);
                        objs.setPersonne(obj.get(0));
                        CleActionnaireOpcvm cleActionnaireOpcvm=new CleActionnaireOpcvm();
                        cleActionnaireOpcvm.setIdOpcvm(pmForms.get(j).getOpcvm().getIdOpcvm());
                        cleActionnaireOpcvm.setIdPersonne(idPersonne);
                        objs.setCleActionnaireOpcvm(cleActionnaireOpcvm);
                        ActionnaireOpcvm sortieActOpcvm = actionnaireOpcvmDao.save(objs);
                        if (sortieActOpcvm!=null) {
                            List<ProfilCommissionSousRach> profilCommissionSousRaches = profilCommissionSousRachDao.findByOpcvm(opcvm);
                            for (int s = 0; s < profilCommissionSousRaches.size(); s++) {
                                List<ActionnaireCommission> actionnaireCommissions = actionnaireCommissionDao.afficherSelonOpcvm(
                                        pmForms.get(j).getOpcvm().getIdOpcvm(), obj.get(0).getIdPersonne(), profilCommissionSousRaches.get(s).getTypeCommission(),
                                        profilCommissionSousRaches.get(s).getCodeProfil()
                                );

                                if (actionnaireCommissions.size() == 0) {
                                    ActionnaireCommission objActionnaireCommission = new ActionnaireCommission();
                                    objActionnaireCommission.setOpcvm(opcvm);
                                    objActionnaireCommission.setPersonne(obj.get(0));
                                    objActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(s).getTypeCommission());
                                    objActionnaireCommission.setCodeProfil(profilCommissionSousRaches.get(s).getCodeProfil());
                                    objActionnaireCommission.setDate(LocalDateTime.now());
                                    CleActionnaireCommission cleActionnaireCommission=new CleActionnaireCommission();
                                    cleActionnaireCommission.setIdOpcvm(opcvm.getIdOpcvm());
                                    cleActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(s).getTypeCommission());
                                    cleActionnaireCommission.setIdPersonne(idPersonne);
                                    objActionnaireCommission.setCleActionnaireCommission(cleActionnaireCommission);
                                    actionnaireCommissionDao.save(objActionnaireCommission);
                                }
                            }
                        }
                    }

                }
                DepotRachat objDepot = new DepotRachat();
                if (pmForms.get(j).getDistributeur()!=null) {
                    Personne personne = personneDao.findById(pmForms.get(j).getDistributeur().getIdPersonne()).orElseThrow();
                    objDepot.setPersonne(personne);
                }
                if (idPersonne != null && idPersonne!=0) {
                    Personne personne = personneDao.findById(idPersonne).orElseThrow();
                    objDepot.setActionnaire(personne);
                }
                if (pmForms.get(j).getOpcvm() != null) {
                    Opcvm opcvm = opcvmDao.findById(pmForms.get(j).getOpcvm().getIdOpcvm()).orElseThrow();
                    objDepot.setOpcvm(opcvm);
                }

                objDepot.setIdTransaction(0L);
                objDepot.setMontant(pmForms.get(j).getMontantSouscrit());
                LocalDateTime dateOperation=LocalDateTime.now();
                SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(pmForms.get(j).getOpcvm().getIdOpcvm());
                dateOperation=seanceOpcvm.getDateFermeture();
                objDepot.setDateOperation(dateOperation);
                objDepot.setDateSaisie(LocalDateTime.now());
                objDepot.setDateValeur(dateOperation);
                objDepot.setDatePiece(dateOperation);
                objDepot.setEstOD(false);
                objDepot.setEstGenere(false);
                objDepot.setEstVerifier(false);
                objDepot.setDateVerification(LocalDateTime.parse("2014-12-20T00:00:00"));
                objDepot.setNomVerificateur("");
                objDepot.setReferencePiece("");
                objDepot.setModeVL("CONNU");
                objDepot.setLibelleOperation("DEPOT DE " + pmForms.get(j).getMontantSouscrit()+" FCFA POUR SOUSCRIPTION ");
                objDepot.setIdSeance(seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
                objDepot.setQuantite(BigDecimal.ZERO);
                objDepot.setEcriture("A");
                objDepot.setType("S");
                NatureOperation natureOperation = natureOperationDao.findByCodeNatureOperationIgnoreCase(
                        "DEP_SOUS").orElseThrow();
                objDepot.setNatureOperation(natureOperation);
                objDepot.setValeurCodeAnalytique("OPC:" + pmForms.get(j).getOpcvm().getIdOpcvm().toString() +
                        ";ACT:" + idPersonne.toString());
                objDepot.setValeurFormule("2:" + pmForms.get(j).getMontantSouscrit().toString().replace(',', '.'));

                objDepot.setMontantSouscrit(pmForms.get(j).getMontantSouscrit());
                objDepot.setEstVerifie1(false);
                objDepot.setEstVerifie2(false);
                objDepot.setUserLoginVerificateur1("");
                objDepot.setUserLoginVerificateur2("");
                objDepot = depotRachatDao.save(objDepot);
                message="Enregistrement effectué avec succès";
            }


        } return ResponseHandler.generateResponse(
                    "IMPORT DEPOT POUR SOUSCRIPTIONS",
                    HttpStatus.OK,
                    message
            );
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }
    public Long CreerActionnaireMo(PmForm pmForm){
        Long id = 0L;

        PersonneMorale obj = new PersonneMorale();
        obj.setNumeroAgrementPersonneMorale("");
        obj.setNumeroINSAE("");
        obj.setNumCompteDepositaire(pmForm.getNumeroCpteDeposit());
        obj.setNumeroCpteDeposit(pmForm.getNumeroCpteDeposit());
        obj.setSiglePersonneMorale("");
        obj.setSigle("");
        obj.setStatutCompte("OUVERT");
        obj.setRaisonSociale(pmForm.getRaisonSociale());
        obj.setDenomination(pmForm.getRaisonSociale());
        obj.setCapitalSocial(BigDecimal.ZERO);
//        try
//        {
//            obj.setFormeJuridique = lstFormeJuridiqueOPC.Find(l => l.LibelleFormeJuridique.Trim() ==
//                    Convert.ToString(gv_ListeMo.Rows[index].Cells["Forme Juridique"].Value).Trim()).CodeFormeJuridique.Trim();
//        }
//        catch { obj.CodeFormeJuridique = "AUTRES"; }
        obj.setCodeSecteur("");
        obj.setSecteur(null);
//        obj.setEstActifPersonne = true;
        Pays paysDto=new Pays();
        paysDto=paysDao.rechercherPays("AUCUN");
        obj.setPaysResidence(paysDto);
        obj.setDateCreationPM(LocalDateTime.parse("1800-01-01T00:00:00"));
        obj.setLibelleTypePersonne("PM");
        obj.setTypePersonne("PM");
//        obj.LibelleQualite = Convert.ToString((gv_ListeMo.Rows[index].Cells["Qualité"].Value == null || gv_ListeMo.Rows[index].Cells["Qualité"].Value == "") ? "ACTIONNAIRES" : gv_ListeMo.Rows[index].Cells["Qualité"].Value);
//        if (obj.LibelleQualite=="")
//        {
//            obj.LibelleQualite = "ACTIONNAIRES";
//        }
        obj.setTelephoneFixe1(pmForm.getFixe1());
        obj.setTelephoneMobile1(pmForm.getMobile1());
        obj.setTelephoneMobile2("");
        obj.setEmailPerso(pmForm.getEmailPerso());
        //obj.setBoitePostale(pmForm.getb); = Convert.ToString(gv_ListeMo.Rows[index].Cells["Boîte Postale"].Value);
        //obj.AdresseComplete = "";
        obj.setTauxRetroCourRach(BigDecimal.ZERO);
        obj.setTauxRetroCourSous(BigDecimal.ZERO);
        obj.setNumCompteSgi(pmForm.getNumeroCpteDeposit());

        PersonneMorale sortie =personneMoraleDao.save(obj);

            if (sortie != null) {
                StatutPersonne statutPersonne = new StatutPersonne();
                statutPersonne.setPersonne(sortie);
                Qualite qualite = qualiteDao.findByLibelleQualiteContains("actionnaires");
                statutPersonne.setQualite(qualite);
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(sortie.getIdPersonne());
                cleStatutPersonne.setIdQualite(qualite.getIdQualite());
                statutPersonne.setIdStatutPersonne(cleStatutPersonne);
                statutPersonneDao.save(statutPersonne);

                ActionnaireOpcvm objs = new ActionnaireOpcvm();
                Opcvm opcvm = new Opcvm();
                opcvm.setIdOpcvm(pmForm.getOpcvm().getIdOpcvm());
                objs.setOpcvm(opcvm);
                objs.setPersonne(sortie);
                CleActionnaireOpcvm cleActionnaireOpcvm = new CleActionnaireOpcvm();
                cleActionnaireOpcvm.setIdOpcvm(pmForm.getOpcvm().getIdOpcvm());
                cleActionnaireOpcvm.setIdPersonne(sortie.getIdPersonne());
                id = sortie.getIdPersonne();
                objs.setCleActionnaireOpcvm(cleActionnaireOpcvm);
                ActionnaireOpcvm sortieActOpcvm = actionnaireOpcvmDao.save(objs);

                if (sortieActOpcvm != null) {
                    List<ProfilCommissionSousRach> profilCommissionSousRaches = profilCommissionSousRachDao.findByOpcvm(opcvm);
                    for (int j = 0; j < profilCommissionSousRaches.size(); j++) {
                        List<ActionnaireCommission> actionnaireCommissions = actionnaireCommissionDao.afficherSelonOpcvm(
                                pmForm.getOpcvm().getIdOpcvm(), sortie.getIdPersonne(), profilCommissionSousRaches.get(j).getTypeCommission(),
                                profilCommissionSousRaches.get(j).getCodeProfil()
                        );

                        if (actionnaireCommissions.size() == 0) {
                            ActionnaireCommission objActionnaireCommission = new ActionnaireCommission();
                            objActionnaireCommission.setOpcvm(opcvm);
                            objActionnaireCommission.setPersonne(sortie);
                            objActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(j).getTypeCommission());
                            objActionnaireCommission.setCodeProfil(profilCommissionSousRaches.get(j).getCodeProfil());
                            objActionnaireCommission.setDate(LocalDateTime.now());
                            CleActionnaireCommission cleActionnaireCommission = new CleActionnaireCommission();
                            cleActionnaireCommission.setIdOpcvm(opcvm.getIdOpcvm());
                            cleActionnaireCommission.setTypeCommission(profilCommissionSousRaches.get(j).getTypeCommission());
                            cleActionnaireCommission.setIdPersonne(sortie.getIdPersonne());
                            objActionnaireCommission.setCleActionnaireCommission(cleActionnaireCommission);
                            actionnaireCommissionDao.save(objActionnaireCommission);
                        }
                    }
                }
            }

            if (id != 0) {
                setNewClient(getNewClient() + id + ",");
            }

        return id;
    }
    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters, long idOpcvm, long idSeance, String codeNatureOperation) {
        try {
            Opcvm opcvm = new Opcvm();
            opcvm = opcvmDao.findById(idOpcvm).orElseThrow();
            NatureOperation natureOperation = new NatureOperation();
            natureOperation = natureOperationDao.findById(codeNatureOperation).orElseThrow();
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleDepotRachat");
            Pageable pageable = PageRequest.of(
                    parameters.getStart() / parameters.getLength(), parameters.getLength());
            Page<DepotRachat> DepotRachatPage;
//            DepotRachatPage = depotRachatDao.listeDesDepotSeance(pageable);
            DepotRachatPage = depotRachatDao.findByOpcvmAndIdSeanceAndNatureOperation(
                    opcvm, idSeance, natureOperation, pageable);
            List<DepotRachatDto> content = DepotRachatPage.getContent().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            DataTablesResponse<DepotRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int) DepotRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int) DepotRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherTous(long idOpcvm,
                                               long idSeance,
                                               String codeNatureOperation,
                                               Boolean estVerifier,
                                               Boolean estVerifie1,
                                               Boolean estVerifie2) {
        try {
            Opcvm opcvm = new Opcvm();
            opcvm = opcvmDao.findById(idOpcvm).orElseThrow();
            NatureOperation natureOperation = new NatureOperation();
            natureOperation = natureOperationDao.findById(codeNatureOperation).orElseThrow();
            List<DepotRachatDto> depotRachatDtos = depotRachatDao.findByOpcvmAndIdSeanceAndNatureOperationAndEstVerifierAndEstVerifie1AndEstVerifie2AndSupprimer(opcvm, idSeance, natureOperation, estVerifier, estVerifie1, estVerifie2, false)
                    .stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());

            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats",
                    HttpStatus.OK,
                    depotRachatDtos);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Page<DepotRachatDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleDepotRachat");
            List<DepotRachatDto> DepotRachats = depotRachatDao.findAll().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats ",
                    HttpStatus.OK,
                    DepotRachats);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public DepotRachat afficherSelonId(Long idOperation) {
        return depotRachatDao.findById(idOperation).orElseThrow(() -> new EntityNotFoundException(DepotRachat.class, "id", idOperation.toString()));
    }

    @Override
    public List<NbrePartProjection> afficherNbrePart(Long idOpcvm,
                                                     Long idActionnaire) {
        Sort sort = Sort.by(Sort.Direction.ASC, "dateOuverture");
        SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(idOpcvm);
        LocalDateTime dateEstimation = seanceOpcvm.getDateFermeture();
        List<NbrePartProjection> list = libraryDao.afficherNbrePart(idActionnaire, idOpcvm, false,
                true, true, dateEstimation);
        /*var q = em.createNativeQuery("SELECT * FROM [Operation].[FT_NbrePart](:idActionnaire,:idOpcvm,:estLevee," +
                ":estVerifie1,:estVerifie2,:dateEstimation)");
        SeanceOpcvm seanceOpcvm=seanceOpcvmService.afficherSeanceEnCours(idOpcvm);
        LocalDateTime dateEstimation=seanceOpcvm.getDateFermeture();
        q.setParameter("idActionnaire", idActionnaire);
        q.setParameter("idOpcvm", idOpcvm);
        q.setParameter("estLevee",false);
        q.setParameter("estVerifie1", true);
        q.setParameter("estVerifie2", true);
        q.setParameter("dateEstimation", dateEstimation);

        try {
            // Execute query
            //list = q.getResultList();
            list=q.getResultList();



        } finally {
            try
            {

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/

        return list;
    }

    @Override
    public List<PrecalculRachatProjection> afficherPrecalculRachat(Long idSeance,Long idOpcvm, Long idPersonne) {
        List<PrecalculRachatProjection> list = libraryDao.afficherPrecalculRachat(idSeance, idOpcvm, idPersonne);
        List<PrecalculRachatProjection> precalculRachatProjections=new ArrayList<>();
        for(PrecalculRachatProjection o:list){
            if(o.getQtePart().compareTo(BigDecimal.valueOf(0))==1){
                precalculRachatProjections.add(o);
            }
        }
        return precalculRachatProjections;
    }

    @Override
    public List<FT_DepotRachatProjection> afficherFT_DepotRachat(Long IdOpcvm,boolean niveau1,boolean niveau2) {
        SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(IdOpcvm);
        Long idSeance = seanceOpcvm.getIdSeanceOpcvm().getIdSeance();
        List<FT_DepotRachatProjection> list = libraryDao.afficherFT_DepotRachat(idSeance,
                null, IdOpcvm, "INT_RACH",
                niveau1, niveau2);

        return list;
    }

    @Override
    public List<FT_DepotRachatProjection> afficherDepotRachatTransfert(Long IdOpcvm, boolean niveau1, boolean niveau2) {
        SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(IdOpcvm);
        Long idSeance = seanceOpcvm.getIdSeanceOpcvm().getIdSeance();
        List<FT_DepotRachatProjection> list = libraryDao.afficherFT_DepotRachat(idSeance,
                null, IdOpcvm,
                "TRANS_TIT_ACT;TRANS_TIT_OBLC;TRANS_TIT_OBLNC;TRANS_TIT_TCN;TRANS_TIT_FCP",
                niveau1, niveau2);

        return list;
    }

    @Override
    public List<FT_DepotRachatProjection> verifIntentionRachat(Long IdOpcvm, boolean niveau1, boolean niveau2, HttpServletResponse response) throws IOException, JRException {
        SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(IdOpcvm);
        Long idSeance = seanceOpcvm.getIdSeanceOpcvm().getIdSeance();
        List<FT_DepotRachatProjection> list = libraryDao.afficherFT_DepotRachat(idSeance,
                null, IdOpcvm, "INT_RACH",
                niveau1, niveau2);

        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/verificationIntentionRachat.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return list;
    }

    @Override
    public List<FT_DepotRachatProjection> verifSouscriptionTRansfertTitre(Long IdOpcvm, boolean niveau1, boolean niveau2, HttpServletResponse response) throws IOException, JRException {
        SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(IdOpcvm);
        Long idSeance = seanceOpcvm.getIdSeanceOpcvm().getIdSeance();
        List<FT_DepotRachatProjection> list = libraryDao.afficherFT_DepotRachat(idSeance,
                null, IdOpcvm, "TRANS_TIT_ACT;TRANS_TIT_OBLC;TRANS_TIT_OBLNC;TRANS_TIT_TCN;TRANS_TIT_FCP",
                niveau1, niveau2);

        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/verificationIntentionRachat.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return list;
    }

    @Override
    public List<FT_DepotRachatProjection> verifIntentionRachatN1N2(Long IdOpcvm, boolean niveau1, boolean niveau2, HttpServletResponse response) throws IOException, JRException {
        SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(IdOpcvm);
        Long idSeance = seanceOpcvm.getIdSeanceOpcvm().getIdSeance();
        List<FT_DepotRachatProjection> list = libraryDao.afficherFT_DepotRachat(idSeance,
                null, IdOpcvm, "INT_RACH",
                niveau1, niveau2);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:verificationIntentionRachatN1N2.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        InputStream inputStream = getClass().getResourceAsStream("/verificationIntentionRachatN1N2.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return list;
    }

    @Override
    public ResponseEntity<Object> afficher(Long idOperation) {
        try {
            return ResponseHandler.generateResponse(
                    "DepotRachat dont ID = " + idOperation,
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat2(afficherSelonId(idOperation)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }

    @Override
    public ResponseEntity<Object> calculer(DepotRachatDto depotRachatDto) {
        String sortie="";
        String codeTypeTitre="";
        String codeClasseTitre="";
        BigDecimal montantBrut =BigDecimal.valueOf(0);
        BigDecimal interetCourru = BigDecimal.valueOf(0);
        String codeRole = "";
        BigDecimal commissionSGI = BigDecimal.valueOf(0);
        BigDecimal irvm = BigDecimal.valueOf(0);
        BigDecimal tafCommissionSGI = BigDecimal.valueOf(0);
        BigDecimal montantNet = BigDecimal.valueOf(0);
        BigDecimal plusOuMoinsValue = BigDecimal.valueOf(0);
        BigDecimal quantiteDisponible=BigDecimal.valueOf(10000000);
        BigDecimal interetPrecompte = BigDecimal.ZERO;
        TitreDto titreDto=titreMapper.deTitre(titreDao.findById(depotRachatDto.getTitre().getIdTitre()).orElseThrow());
        String t=titreDto.getTypeTitre().getCodeTypeTitre().trim();


        codeClasseTitre = titreDto.getTypeTitre().getClasseTitre().getCodeClasseTitre().trim();
        codeTypeTitre =titreDto.getTypeTitre().getCodeTypeTitre().trim();

        BigDecimal cours=BigDecimal.ZERO;
        BigDecimal quantite=BigDecimal.ZERO;
        if(depotRachatDto.getCours()!=null){
            cours=depotRachatDto.getCours();
        }
        if(depotRachatDto.getQuantite()!=null){
            quantite=depotRachatDto.getQuantite();
        }
        montantBrut = cours.multiply(quantite);
        montantBrut = new BigDecimal(montantBrut.doubleValue()).setScale(0,RoundingMode.HALF_UP);
        if (codeClasseTitre.trim().equalsIgnoreCase("OBLIGATION") ||
                codeClasseTitre.trim().equalsIgnoreCase("TCN"))
        {
            LocalDateTime dateTime=LocalDateTime.parse(depotRachatDto.getDateOperation().toString().substring(0,10)+"T00:00:00");;
            Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(i);
            interetCourru = new BigDecimal(Double.valueOf(libraryDao.interetCouru(titreDto.getIdTitre(),date, false,null).toString()) *
                    Double.valueOf(depotRachatDto.getQuantite().toString())).setScale(0,RoundingMode.HALF_UP);

            if (codeClasseTitre.trim().equalsIgnoreCase("TCN"))
            {
                TcnDto otcn = new TcnDto();
                otcn =tcnMapper.deTcn(tcnDao.findById(titreDto.getIdTitre()).orElseThrow());
                dateTime=LocalDateTime.parse(otcn.getDateDernierPaiement().toString().substring(0,10)+"T00:00:00");;
                i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                date = Date.from(i);

                BigDecimal toto = (libraryDao.interetCouru((titreDto.getIdTitre()),
                        date, false,null).multiply(
                        quantite)
                ).setScale(0,RoundingMode.HALF_UP);

                interetPrecompte = toto;
            }
        }


        montantNet = montantBrut.add(interetCourru).subtract(
                ((codeClasseTitre.trim().equalsIgnoreCase("TCN")) ? interetPrecompte : BigDecimal.ZERO));

        depotRachatDto.setMontantBrut(montantBrut);
        depotRachatDto.setMontant(montantNet);
        depotRachatDto.setInteretCouru(interetCourru);
        depotRachatDto.setInteretPrecompte(interetPrecompte);


        return ResponseHandler.generateResponse(
                "Liste !",
                HttpStatus.OK,
                depotRachatDto);
    }

    @Override
    public ResponseEntity<Object> creer(DepotRachatDto DepotRachatDto, String type) {
        try {
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(DepotRachatDto);
            if (DepotRachatDto.getPersonne() != null) {
                Personne personne = personneDao.findById(DepotRachatDto.getPersonne().getIdPersonne()).orElseThrow();
                DepotRachat.setPersonne(personne);
            }
            if (DepotRachatDto.getActionnaire() != null) {
                Personne personne = personneDao.findById(DepotRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                DepotRachat.setActionnaire(personne);
            }
            if (DepotRachatDto.getOpcvm() != null) {
                Opcvm opcvm = opcvmDao.findById(DepotRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                DepotRachat.setOpcvm(opcvm);
            }
            NatureOperation natureOperation = natureOperationDao.findById(DepotRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
            DepotRachat.setNatureOperation(natureOperation);

            DepotRachat = depotRachatDao.save(DepotRachat);
            System.out.println("Dep === " + DepotRachat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(DepotRachatDto DepotRachatDto) {
        try {
            List<String> errors = DepotRachatValidator.validate(DepotRachatDto);
            if(!errors.isEmpty()) {
                return ResponseHandler.generateResponse(
                    "Certains champs sont obligatoires.",
                    HttpStatus.BAD_REQUEST,
                    errors
                );
            }
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(DepotRachatDto);
            if (DepotRachatDto.getPersonne() != null) {
                Personne personne = personneDao.findById(DepotRachatDto.getPersonne().getIdPersonne()).orElseThrow();
                if (personne != null)
                    DepotRachat.setPersonne(personne);
            }
            if (DepotRachatDto.getActionnaire() != null) {
                Personne personne = personneDao.findById(DepotRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                DepotRachat.setActionnaire(personne);
            }
            if (DepotRachatDto.getOpcvm() != null) {
                Opcvm opcvm = opcvmDao.findById(DepotRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                if (opcvm != null)
                    DepotRachat.setOpcvm(opcvm);
            }
            NatureOperation natureOperation = natureOperationDao.findById(DepotRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
            if (natureOperation != null)
                DepotRachat.setNatureOperation(natureOperation);

            DepotRachat = depotRachatDao.save(DepotRachat);
            System.out.println("Dep === " + DepotRachat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creerDepotRachatTransfert(DepotRachatDto depotRachatDto) {
        try {
            String sortie="";
            StoredProcedureQuery q = em.createStoredProcedureQuery("[Parametre].[PS_DepotRachat_IP_New]");
            q.registerStoredProcedureParameter("IdOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantSouscrit", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdActionnaire", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdPersonne", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("modeVL", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estGenere", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estVerifier", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("nomVerificateur", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateVerification", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qte", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("cours", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commission", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interetCouru", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interetPrecompte", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(depotRachatDto.getOpcvm().getIdOpcvm());
            LocalDateTime dateEstimation = seanceOpcvm.getDateFermeture();
            String codeNAtureOperation="";
            String valeurFormule="";
            String codeClasseTitre="";
            String codeTypeTitre="";
            TitreDto titreDto=titreMapper.deTitre(titreDao.findById(depotRachatDto.getTitre().getIdTitre()).orElseThrow());
            codeClasseTitre=titreDto.getTypeTitre().getClasseTitre().getCodeClasseTitre();
            codeTypeTitre=titreDto.getTypeTitre().getCodeTypeTitre();
            if (codeClasseTitre.trim().equalsIgnoreCase("ACTION"))
            {
               codeNAtureOperation = "TRANS_TIT_ACT";

                valeurFormule = "2:" + (depotRachatDto.getQte().multiply(depotRachatDto.getCours()).toString().replace(',', '.') +
                        ";9:" + (depotRachatDto.getCommission()).toString().replace(',', '.') +
                        ";55:" + depotRachatDto.getQte().toString().replace(',', '.')) ;
            }
            else if (codeClasseTitre.trim().equalsIgnoreCase("TCN"))
            {
                codeNAtureOperation = "TRANS_TIT_TCN";
                valeurFormule = "2:" + (depotRachatDto.getQte().multiply(depotRachatDto.getCours())).toString().replace(',', '.') +
                        ";9:" + (depotRachatDto.getCommission()).toString().replace(',', '.') +
                        ";30:" + depotRachatDto.getInteretPrecompte().toString().replace(',', '.') +
                        ";28:" + depotRachatDto.getInteretCouru().toString().replace(',', '.') +
                        ";55:" + depotRachatDto.getQte().toString().replace(',', '.') +
                        ";4:" + ((depotRachatDto.getMontant())).toString().replace(',', '.');
            }
            else if (codeClasseTitre.trim().equalsIgnoreCase("PART FCP"))
            {
                codeNAtureOperation = "TRANS_TIT_FCP";
                valeurFormule = "2:" + ((depotRachatDto.getQte().multiply(depotRachatDto.getCours()).toString().replace(',', '.') +
                        ";55:" + depotRachatDto.getQte().toString().replace(',', '.') +
                        ";9:" + (depotRachatDto.getCommission()).toString().replace(',', '.') +
                        ";4:" + (depotRachatDto.getQte().multiply(depotRachatDto.getCours()).toString().replace(',', '.'))));
            }
            else if (codeClasseTitre.trim().equalsIgnoreCase("OBLIGATION"))
            {
                codeNAtureOperation = codeTypeTitre.trim().equalsIgnoreCase("OBLIGATN") ? "TRANS_TIT_OBLNC" : "TRANS_TIT_OBLC";
                valeurFormule = "2:" + ((depotRachatDto.getQte().multiply(depotRachatDto.getCours()).toString().replace(',', '.') +
                        ";9:" + depotRachatDto.getCommission().toString().replace(',', '.') +
                        ";55:" + depotRachatDto.getQte().toString().replace(',', '.') +
                        ";4:" + ((depotRachatDto.getMontant())).toString().replace(',', '.') +
                        ";28:" + depotRachatDto.getInteretCouru().toString().replace(',', '.')));
            }

            q.setParameter("IdOperation", 0);
            q.setParameter("IdTransaction",0);
            q.setParameter("codeNatureOperation", codeNAtureOperation);
            q.setParameter("dateOperation", depotRachatDto.getDateOperation());
            q.setParameter("libelleOperation", depotRachatDto.getLibelleOperation());
            q.setParameter("dateSaisie", depotRachatDto.getDateSaisie());
            q.setParameter("datePiece", depotRachatDto.getDateOperation());
            q.setParameter("dateValeur", depotRachatDto.getDateOperation());
            q.setParameter("referencePiece",depotRachatDto.getReferencePiece());
            q.setParameter("montant", depotRachatDto.getMontant());
            q.setParameter("montantSouscrit", depotRachatDto.getMontant());
            q.setParameter("quantite", depotRachatDto.getQuantite());
            q.setParameter("ecriture", "A");
            q.setParameter("estOD", false);
            q.setParameter("type", "S");
            q.setParameter("IdActionnaire", depotRachatDto.getActionnaire().getIdPersonne());
            q.setParameter("IdSeance", seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
            q.setParameter("IdPersonne", depotRachatDto.getPersonne().getIdPersonne());
            q.setParameter("IdOpcvm", depotRachatDto.getOpcvm().getIdOpcvm());
            q.setParameter("modeVL", depotRachatDto.getModeVL());
            q.setParameter("estGenere", false);
            q.setParameter("estVerifier",false);
            q.setParameter("nomVerificateur", "");
            q.setParameter("dateVerification", LocalDateTime.parse("2014-12-20T00:00:00"));
            q.setParameter("idTitre", depotRachatDto.getTitre().getIdTitre());
            q.setParameter("qte",depotRachatDto.getQte());
            q.setParameter("cours", depotRachatDto.getCours());
            q.setParameter("commission", depotRachatDto.getCommission());
            q.setParameter("interetCouru", depotRachatDto.getInteretCouru());
            q.setParameter("interetPrecompte", depotRachatDto.getInteretPrecompte());
            q.setParameter("valeurFormule", valeurFormule);
            q.setParameter("valeurCodeAnalytique", "TIT:" + depotRachatDto.getTitre().getIdTitre().toString() +
                    ";ACT:" + depotRachatDto.getActionnaire().getIdPersonne().toString() +
                    ";OPC:" + depotRachatDto.getOpcvm().getIdOpcvm().toString());
            q.setParameter("userLogin",depotRachatDto.getUserLogin());
            q.setParameter("dateDernModifClient", LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);

            try {
                // Execute query
                q.execute();
            } finally {
                try {

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(VerifDepSouscriptionIntRachatDto verifDepSouscriptionIntRachatDto) {
        try {
            StoredProcedureQuery q = em.createStoredProcedureQuery("[Parametre].[PS_VerifDepSouscriptionIntRachat]");
            q.registerStoredProcedureParameter("IdSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdPersonne", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("niveau", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateVerif", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLoginVerif", String.class, ParameterMode.IN);

            SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(verifDepSouscriptionIntRachatDto.getIdOpcvm());
            LocalDateTime dateEstimation = seanceOpcvm.getDateFermeture();
            q.setParameter("IdSeance", seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
            q.setParameter("IdPersonne", null);
            q.setParameter("IdOpcvm", verifDepSouscriptionIntRachatDto.getIdOpcvm());
            q.setParameter("codeNatureOperation", verifDepSouscriptionIntRachatDto.getCodeNatureOperation());
            q.setParameter("niveau", verifDepSouscriptionIntRachatDto.getNiveau());
            q.setParameter("dateVerif", LocalDateTime.now());
            q.setParameter("userLoginVerif", verifDepSouscriptionIntRachatDto.getUserLoginVerif());
//            System.out.println("idseance="+seanceOpcvm.getIdSeanceOpcvm().getIdSeance());
//            System.out.println("idOpcvm="+verifDepSouscriptionIntRachatDto.getIdOpcvm());
//            System.out.println("niveau="+verifDepSouscriptionIntRachatDto.getNiveau());
//            System.out.println("userloginverif="+verifDepSouscriptionIntRachatDto.getUserLoginVerif());
            try {
                // Execute query
                q.executeUpdate();
            } finally {
                try {

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(Long[] ids,String userLogin) {
        try {
            String sortie="";
            for(Long o:ids)
            {
                var q = em.createStoredProcedureQuery("[Comptabilite].[PS_Operation_IP_New]");
                q.registerStoredProcedureParameter("IdOperation", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

                DepotRachat depotRachat=depotRachatDao.findById(o).orElseThrow();
//                SeanceOpcvm seanceOpcvm = seanceOpcvmService.afficherSeanceEnCours(depotRachat.getIdSeance());
//
//                LocalDateTime dateEstimation = seanceOpcvm.getDateFermeture();
                q.setParameter("IdOperation", 0);
                q.setParameter("idOpcvm", depotRachat.getOpcvm().getIdOpcvm());
                if(depotRachat.getActionnaire() != null && depotRachat.getActionnaire().getIdPersonne() != null) {
                    q.setParameter("idActionnaire", depotRachat.getActionnaire().getIdPersonne());
                }
                q.setParameter("idTitre",null);
                q.setParameter("IdTransaction",0);
                q.setParameter("idSeance",depotRachat.getIdSeance());
                q.setParameter("codeNatureOperation",depotRachat.getNatureOperation().getCodeNatureOperation());
                q.setParameter("dateOperation",depotRachat.getDateOperation());
                q.setParameter("libelleOperation",depotRachat.getLibelleOperation());
                q.setParameter("dateSaisie", depotRachat.getDateSaisie());
                q.setParameter("datePiece", depotRachat.getDatePiece());
                q.setParameter("dateValeur", depotRachat.getDateValeur());
                q.setParameter("referencePiece", depotRachat.getReferencePiece());
                q.setParameter("montant", depotRachat.getMontant());
                if(depotRachat.getEcriture()!=null && depotRachat!=null)
                    q.setParameter("ecriture",depotRachat.getEcriture());
                else
                    q.setParameter("ecriture","A");

                q.setParameter("estOD",false);
                q.setParameter("type", "R");
                q.setParameter("valeurFormule", "6:" + depotRachat.getQuantite().toString().replace(',', '.'));
                if(depotRachat.getActionnaire() != null && depotRachat.getActionnaire().getIdPersonne() != null) {
                    q.setParameter("valeurCodeAnalytique", "OPC:" + depotRachat.getOpcvm().getIdOpcvm().toString() +
                            ";ACT:" + depotRachat.getActionnaire().getIdPersonne().toString());
                }
                q.setParameter("userLogin", userLogin);
                q.setParameter("dateDernModifClient",LocalDateTime.now());
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie",sortie);

                try {
                    // Execute query
                    q.execute();
                    String result=(String) q.getOutputParameterValue("Sortie");
                    String[] s=result.split("#");

                    depotRachat.setIdDepotRachat(o);
                    depotRachat.setIdOperation(Long.valueOf(s[s.length-1]));
                    depotRachat.setEstVerifie2(true);
                    depotRachat.setDateVerification2(LocalDateTime.now());
                    depotRachat.setUserLoginVerificateur2(userLogin);
                    depotRachatDao.save(depotRachat);
                    //System.out.println("idOperation="+s[s.length-1]);
                } finally {
                    try {

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto, String type, Long id) {
        try {
            if(!depotRachatDao.existsById(id))
                throw  new EntityNotFoundException(DepotRachat.class, "ID", id.toString());
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(depotRachatDto);
            if(depotRachatDto.getPersonne()!=null)
            {
                Personne personne=personneDao.findById(depotRachatDto.getPersonne().getIdPersonne()).orElseThrow();
                DepotRachat.setPersonne(personne);
            }
            if(depotRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(depotRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                DepotRachat.setActionnaire(personne);
            }
            if(depotRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(depotRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                DepotRachat.setOpcvm(opcvm);
            }
            if(depotRachatDto.getNatureOperation() != null) {
                NatureOperation natureOperation=natureOperationDao.findById(depotRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                DepotRachat.setNatureOperation(natureOperation);
            }


            DepotRachat = depotRachatDao.save(DepotRachat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto) {
        try {
            if(!depotRachatDao.existsById(depotRachatDto.getIdDepotRachat()))
                throw  new EntityNotFoundException(DepotRachat.class, "ID", depotRachatDto.getIdDepotRachat().toString());
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(depotRachatDto);

            if(depotRachatDto.getPersonne()!=null)
            {
                Personne personne=personneDao.findById(depotRachatDto.getPersonne().getIdPersonne()).orElseThrow();
                if(personne!=null)
                    DepotRachat.setPersonne(personne);
            }
            if(depotRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(depotRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                DepotRachat.setActionnaire(personne);
            }
            if(depotRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(depotRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();

                    DepotRachat.setOpcvm(opcvm);
            }
            if(depotRachatDto.getNatureOperation()!=null) {
                NatureOperation natureOperation = natureOperationDao.findById(depotRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();

                DepotRachat.setNatureOperation(natureOperation);
            }
            if(depotRachatDto.getTitre() != null) {
                Titre titre=titreDao.findById(depotRachatDto.getTitre().getIdTitre()).orElseThrow();
                DepotRachat.setTitre(titre);
            }
            DepotRachat = depotRachatDao.save(DepotRachat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(Long[] id,String userLogin) {
        try {
            DepotRachat DepotRachat=new DepotRachat();
            for(Long idDepotRachat:id){
                DepotRachat = depotRachatDao.findById(idDepotRachat).orElseThrow();
                if(DepotRachat!=null){
                    DepotRachat.setIdDepotRachat(idDepotRachat);
                    DepotRachat.setEstVerifier(true);
                    DepotRachat.setDateVerification(LocalDateTime.now());
                    DepotRachat.setNomVerificateur(userLogin);
                    DepotRachat = depotRachatDao.save(DepotRachat);
                }
            }

            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> confirmerListeVerifDepot(List<DepotRachatDto> depotRachatDtos) {
        try {
            List<DepotRachat> depotRachats = depotRachatDtos.stream().map(depotRachatMapper::deDepotRachatDto).toList();
            if(depotRachats.size() > 0) {
                depotRachatDtos = depotRachatDao.saveAll(depotRachats).stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            }
            return ResponseHandler.generateResponse(
                    "Liste des dépôts confirmée avec succès.",
                    HttpStatus.OK,
                    depotRachatDtos);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> confirmerListeVerifNiv2Depot(List<DepotRachatDto> depotRachatDtos) {
        try {
            List<DepotRachat> depotRachats = depotRachatDtos.stream().map(depotRachatMapper::deDepotRachatDto).toList();
            if(depotRachats.size() > 0) {
                for (DepotRachat d : depotRachats) {
                    DepotRachatDto depotRachatDto = depotRachatMapper.deDepotRachat(d);
                    OperationDto op = new OperationDto();
                    op.setIdActionnaire(d.getActionnaire().getIdPersonne());
                    op.setIdTitre(0L);
                    op.setActionnaire(depotRachatDto.getActionnaire());
                    op.setNatureOperation(depotRachatDto.getNatureOperation());
                    op.setDateOperation(depotRachatDto.getDateOperation());
                    op.setDatePiece(depotRachatDto.getDatePiece());
                    op.setDateSaisie(depotRachatDto.getDateSaisie());
                    op.setDateValeur(depotRachatDto.getDateValeur());
                    op.setOpcvm(depotRachatDto.getOpcvm());
                    op.setIdSeance(depotRachatDto.getIdSeance());
                    op.setMontant(depotRachatDto.getMontant());
                    op.setReferencePiece(depotRachatDto.getReferencePiece());
                    op.setValeurCodeAnalytique("OPC:" + depotRachatDto.getOpcvm().getIdOpcvm()
                            + ";ACT:" + depotRachatDto.getActionnaire().getIdPersonne());
                    op.setValeurFormule("2:" + depotRachatDto.getMontant());
                    op.setLibelleOperation(depotRachatDto.getLibelleOperation());
                    op.setType(depotRachatDto.getType());
                    op = appService.genererEcritureComptable(op);
                    d.setIdOperation(op.getIdOperation());
                    depotRachatDao.save(d);
                    System.out.println("Résultat final => " + d);
                }
            }
            return ResponseHandler.generateResponse(
                    "Liste des dépôts confirmée avec succès.",
                    HttpStatus.OK,
                    depotRachatDtos);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> precalculSouscription(PrecalculSouscriptionRequest precalcul) {
        try {
            return ResponseHandler.generateResponse(
                    "Précalcul effectué avec succès.",
                    HttpStatus.OK,
                    appService.precalculSouscription(precalcul));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> precalculSouscriptionListe(PrecalculSouscriptionRequest precalcul) {
        try {
            return ResponseHandler.generateResponse(
                    "Précalcul effectué avec succès.",
                    HttpStatus.OK,
                    libraryDao.precalculSouscription(precalcul.getIdSeance(),precalcul.getIdOpcvm(),precalcul.getIdPersonne()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> genererSouscription(List<OperationSouscriptionRachatDto> souscriptionRachatDtos) {
        try {
//            List<OperationSouscriptionRachat> souscriptionRachats = souscriptionRachatDtos.stream().map(souscriptionRachatDto -> {
//                souscriptionRachatDto = appService.genererEcritureComptable(souscriptionRachatDto);
//                String str = "DEP_SOUS;TRANS_TIT_ACT;TRANS_TIT_OBLC;TRANS_TIT_OBLNC;TRANS_TIT_TCN;TRANS_TIT_FCP";
//                List<DepotRachat> depotRachats = depotRachatDao.getAllDepotSouscToValidate(
//                    souscriptionRachatDto.getIdSeance(),
//                    souscriptionRachatDto.getIdOpcvm(),
//                    souscriptionRachatDto.getActionnaire().getIdPersonne(),
//                    Arrays.stream(str.split(";")).toList()
//                ).stream().map(depotRachat -> {
//                    depotRachat.setEstGenere(true);
//                    depotRachat = depotRachatDao.save(depotRachat);
//                    return depotRachat;
//                }).toList();
//                return souscriptionRachatMapper.deOperationSouscriptionRachatDto(souscriptionRachatDto);
//            }).toList();
            String sortie="";
            Long idPersonne=0L;
            Long idSeance=0L;
            Long idOpcvm=0L;
            for(OperationSouscriptionRachatDto o:souscriptionRachatDtos) {

                var q = em.createStoredProcedureQuery("[Operation].[PS_OperationSouscriptionRachat_IP]");
                q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idPersonne", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montantSousALiquider", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("SousRachatPart", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("commisiionSousRachat", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("TAFCommissionSousRachat", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("retrocessionSousRachat", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("TAFRetrocessionSousRachat", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("commissionSousRachatRetrocedee", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("modeValeurLiquidative", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("coursVL", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("nombrePartSousRachat", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulResultatExoEnCours", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulSommeNonDistribuable", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulReportANouveau", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulautreResultatBeneficiaire", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulautreResultatDeficitaire", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulResultatEnInstanceBeneficiaire", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulResultatEnInstanceDeficitaire", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("regulExoDistribution", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("fraisSouscriptionRachat", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("reste", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("quantiteSouhaite", Integer.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montantDepose", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montantConvertiEnPart", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("estRetrocede", Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("resteRembourse", Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("rachatPaye", Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

                q.setParameter("idOperation", 0);
                q.setParameter("idTransaction", 0);
                q.setParameter("idSeance", o.getIdSeance());
                q.setParameter("idActionnaire", o.getActionnaire().getIdPersonne());
                q.setParameter("idOpcvm", o.getOpcvm().getIdOpcvm());
                q.setParameter("idPersonne", o.getPersonne().getIdPersonne());
                q.setParameter("codeNatureOperation", o.getCodeNatureOperation());
                q.setParameter("dateOperation", o.getDateOperation());
                q.setParameter("libelleOperation","Souscription de " + o.getNombrePartSousRachat().toString() + " Part(s)");
                q.setParameter("dateSaisie", LocalDateTime.now());
                q.setParameter("datePiece", o.getDateOperation());
                q.setParameter("dateValeur", o.getDateValeur());
                q.setParameter("referencePiece", "");
                q.setParameter("montantSousALiquider", o.getMontantSousALiquider());
                q.setParameter("SousRachatPart", o.getSousRachatPart());
                q.setParameter("commisiionSousRachat",o.getCommisiionSousRachat());
                q.setParameter("TAFCommissionSousRachat", o.gettAFCommissionSousRachat());
                q.setParameter("retrocessionSousRachat", o.getRetrocessionSousRachat());
                q.setParameter("TAFRetrocessionSousRachat", o.gettAFRetrocessionSousRachat());
                q.setParameter("commissionSousRachatRetrocedee", o.getCommissionSousRachatRetrocedee());
                q.setParameter("modeValeurLiquidative",o.getModeValeurLiquidative());
                q.setParameter("coursVL", o.getCoursVL());
                q.setParameter("nombrePartSousRachat", o.getNombrePartSousRachat());
                q.setParameter("regulResultatExoEnCours", o.getRegulResultatExoEnCours());
                q.setParameter("regulSommeNonDistribuable",o.getRegulSommeNonDistribuable());
                q.setParameter("regulReportANouveau", o.getRegulReportANouveau());
                q.setParameter("regulautreResultatBeneficiaire", o.getRegulautreResultatBeneficiaire());
                q.setParameter("regulautreResultatDeficitaire",o.getRegulautreResultatDeficitaire());
                q.setParameter("regulResultatEnInstanceBeneficiaire", o.getRegulResultatEnInstanceBeneficiaire());
                q.setParameter("regulResultatEnInstanceDeficitaire",o.getRegulResultatEnInstanceDeficitaire());
                q.setParameter("regulExoDistribution", o.getRegulExoDistribution());
                q.setParameter("fraisSouscriptionRachat", o.getFraisSouscriptionRachat());
                q.setParameter("reste", o.getReste());
                q.setParameter("quantiteSouhaite",o.getQuantiteSouhaite());
                q.setParameter("montantDepose",o.getMontantDepose());
                q.setParameter("montantConvertiEnPart", o.getMontantConvertiEnPart());
                q.setParameter("estRetrocede", false);
                q.setParameter("resteRembourse",false);
                q.setParameter("rachatPaye", false);
                q.setParameter("ecriture", "A");
                q.setParameter("valeurFormule", ("10:" + o.getCommisiionSousRachat() +
                        ";17:" + o.getCommissionSousRachatRetrocedee() +
                        ";26:" + o.getFraisSouscriptionRachat() +
                        ";35:" + o.getMontantConvertiEnPart() +
                        ";36:" + o.getMontantDepose() +
                        ";41:" + o.getMontantSousALiquider() +
                        ";43:" + o.getNombrePartSousRachat() +
                        ";59:" + o.getQuantiteSouhaite() +
                        ";60:" + o.getRegulautreResultatBeneficiaire() +
                        ";61:" + o.getRegulautreResultatDeficitaire() +
                        ";62:" + o.getRegulExoDistribution() +
                        ";63:" + o.getRegulReportANouveau() +
                        ";64:" + o.getRegulResultatEnInstanceBeneficiaire() +
                        ";65:" + o.getRegulResultatEnInstanceDeficitaire() +
                        ";66:" + o.getRegulResultatExoEnCours() +
                        ";67:" + o.getRegulSommeNonDistribuable() +
                        ";72:" + o.getReste() +
                        ";74:" + o.getRetrocessionSousRachat() +
                        ";80:" + o.getSousRachatPart() +
                        ";123:" + o.gettAFCommissionSousRachat() +
                        ";84:" + o.gettAFRetrocessionSousRachat()).replace(',','.'));
                q.setParameter("valeurCodeAnalytique", "OPC:" + o.getOpcvm().getIdOpcvm().toString() +
                        ";ACT:" + o.getActionnaire().getIdPersonne().toString());
                q.setParameter("userLogin", o.getUserLogin());
                q.setParameter("dateDernModifClient", LocalDateTime.now());
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie",sortie);
                idPersonne=o.getPersonne().getIdPersonne();
                idSeance=o.getIdSeance();
                idOpcvm=o.getOpcvm().getIdOpcvm();

                q.execute();
            }
            var    q=em.createStoredProcedureQuery("[Parametre].[PS_ValiderSousRachat]");
            q.registerStoredProcedureParameter("idSeance",Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm",Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("idPersonne",Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation",String.class,ParameterMode.IN);

            q.setParameter("idSeance",idSeance);
            q.setParameter("idOpcvm",idOpcvm);
            q.setParameter("idPersonne",idPersonne);
            q.setParameter("codeNatureOperation","DEP_SOUS;TRANS_TIT_ACT;TRANS_TIT_OBLC;TRANS_TIT_OBLNC;TRANS_TIT_TCN;TRANS_TIT_FCP");
            q.executeUpdate();

            return ResponseHandler.generateResponse(
                    "Génération effectuée avec succès.",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOperation) {
        try {
            depotRachatDao.deleteById(idOperation);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
