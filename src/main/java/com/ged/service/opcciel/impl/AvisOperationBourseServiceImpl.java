package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.AvisOperationBourseDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.TarificationOrdinaireDao;
import com.ged.dao.titresciel.DepositaireDao;
import com.ged.dao.titresciel.RegistraireDao;
import com.ged.dao.titresciel.TcnDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.AvisOperationBourseDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.AvisOperationBourse;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.titresciel.Registraire;
import com.ged.entity.titresciel.Tcn;
import com.ged.mapper.opcciel.AvisOperationBourseMapper;
import com.ged.projection.TarificationProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.AvisOperationBourseService;
import com.ged.service.opcciel.SeanceOpcvmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AvisOperationBourseServiceImpl implements AvisOperationBourseService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final AvisOperationBourseDao AvisOperationBourseDao;
    private final OpcvmDao opcvmDao;
    private final SeanceOpcvmService seanceOpcvmService;
    private final TcnDao tcnDao;
    private final LibraryDao libraryDao;
    private final AvisOperationBourseMapper AvisOperationBourseMapper;
    private final DepositaireDao depositaireDao;
    private final RegistraireDao registraireDao;
    private final TarificationOrdinaireDao tarificationOrdinaireDao;

    public AvisOperationBourseServiceImpl(com.ged.dao.opcciel.AvisOperationBourseDao avisOperationBourseDao, OpcvmDao opcvmDao, SeanceOpcvmService seanceOpcvmService, TcnDao tcnDao, LibraryDao libraryDao, com.ged.mapper.opcciel.AvisOperationBourseMapper avisOperationBourseMapper, DepositaireDao depositaireDao, RegistraireDao registraireDao, TarificationOrdinaireDao tarificationOrdinaireDao){
        AvisOperationBourseDao = avisOperationBourseDao;
        this.opcvmDao = opcvmDao;
        this.seanceOpcvmService = seanceOpcvmService;
        this.tcnDao = tcnDao;
        this.libraryDao = libraryDao;
        AvisOperationBourseMapper = avisOperationBourseMapper;
        this.depositaireDao = depositaireDao;
        this.registraireDao = registraireDao;
        this.tarificationOrdinaireDao = tarificationOrdinaireDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOpcvm,Long idOrdre,DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<AvisOperationBourse> AvisOperationBoursePage;
            AvisOperationBoursePage = AvisOperationBourseDao.afficherAvisOperationBourse(idOpcvm,idOrdre,pageable);

            List<AvisOperationBourseDto> content = AvisOperationBoursePage.getContent().stream().map(AvisOperationBourseMapper::deAvisOperationBourse).collect(Collectors.toList());
            DataTablesResponse<AvisOperationBourseDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)AvisOperationBoursePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)AvisOperationBoursePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des AvisOperationBourse par page datatable",
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm,Long idOrdre) {
        try {
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            List<AvisOperationBourseDto> AvisOperationBourseDtos = AvisOperationBourseDao.afficherAvisOperationBourse(idOpcvm,idOrdre).stream().map(AvisOperationBourseMapper::deAvisOperationBourse).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des AvisOperationBourses de bourse",
                    HttpStatus.OK,
                    AvisOperationBourseDtos);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherReglementLivraison(Long idOpcvm) {
        try {
            List<AvisOperationBourseDto> AvisOperationBourseDtos = AvisOperationBourseDao.afficherReglementLivraison(idOpcvm).stream().map(AvisOperationBourseMapper::deAvisOperationBourse).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des AvisOperationBourses de bourse",
                    HttpStatus.OK,
                    AvisOperationBourseDtos);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherGenerationReglementLivraison(Long idOpcvm) {
        try {
            String dateFermeture=seanceOpcvmService.afficherSeanceEnCours(idOpcvm).getDateFermeture().toString().substring(0,10);
            List<AvisOperationBourseDto> AvisOperationBourseDtos = AvisOperationBourseDao.afficherReglementLivraison(idOpcvm,dateFermeture).stream().map(AvisOperationBourseMapper::deAvisOperationBourse).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des AvisOperationBourses de bourse",
                    HttpStatus.OK,
                    AvisOperationBourseDtos);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> enregistrerReglementLivraison(String idOperation,String userLogin) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_ReglementLivraison]");
            q.registerStoredProcedureParameter("idOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("idOperation", idOperation);
            q.setParameter("userLogin", userLogin);
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

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
    public AvisOperationBourse afficherSelonId(Long id) {
        return AvisOperationBourseDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Plan.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "AvisOperationBourse  dont ID = " + id,
                    HttpStatus.OK,
                    AvisOperationBourseMapper.deAvisOperationBourse(afficherSelonId(id)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }
    @Override
    public ResponseEntity<Object> creer(AvisOperationBourseDto avisOperationBourseDto) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_AvisOperationBourse_IP]");

            q.registerStoredProcedureParameter("IdAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referenceAvis", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateReceptionLivraisonPrevu", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("coursLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionPlace", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionDepositaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionSGI", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("TAF", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IRVM", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interet", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("plusOuMoinsValue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("IdAvis", avisOperationBourseDto.getIdAvis());
            q.setParameter("idSeance",avisOperationBourseDto.getIdSeance());
            q.setParameter("IdTransaction", avisOperationBourseDto.getIdTransaction());
            q.setParameter("codeNatureOperation",avisOperationBourseDto.getNatureOperation().getCodeNatureOperation());
            q.setParameter("dateOperation", avisOperationBourseDto.getDateOperation());
            q.setParameter("dateSaisie", avisOperationBourseDto.getDateSaisie());
            q.setParameter("dateValeur", avisOperationBourseDto.getDateValeur());
            q.setParameter("datePiece", avisOperationBourseDto.getDatePiece());
            q.setParameter("referencePiece", avisOperationBourseDto.getReferencePiece());
            q.setParameter("libelleOperation", avisOperationBourseDto.getLibelleOperation());
            q.setParameter("montant", avisOperationBourseDto.getMontant());
            q.setParameter("ecriture", avisOperationBourseDto.getEcriture());
            q.setParameter("estOD", avisOperationBourseDto.getEstOD());
            q.setParameter("type", avisOperationBourseDto.getType());
            q.setParameter("referenceAvis", avisOperationBourseDto.getReferenceAvis());
            q.setParameter("IdOrdre", avisOperationBourseDto.getOrdre().getIdOrdre());
            q.setParameter("dateReceptionLivraisonPrevu", avisOperationBourseDto.getDateReceptionLivraisonPrevu());
            q.setParameter("quantiteLimite", avisOperationBourseDto.getQuantiteLimite());
            q.setParameter("coursLimite", avisOperationBourseDto.getCoursLimite());
            q.setParameter("commissionPlace",avisOperationBourseDto.getCommissionPlace());
            q.setParameter("commissionDepositaire", avisOperationBourseDto.getCommissionDepositaire());
            q.setParameter("commissionSGI", avisOperationBourseDto.getCommissionSGI());
            q.setParameter("TAF", avisOperationBourseDto.gettAF());
            q.setParameter("IRVM", avisOperationBourseDto.getiRVM());
            q.setParameter("interet", avisOperationBourseDto.getInteret());
            q.setParameter("plusOuMoinsValue", avisOperationBourseDto.getPlusOuMoinsValue());
            q.setParameter("montantBrut", avisOperationBourseDto.getMontantBrut());
            q.setParameter("valeurFormule", avisOperationBourseDto.getValeurFormule());
            q.setParameter("valeurCodeAnalytique", avisOperationBourseDto.getValeurCodeAnalytique());
            q.setParameter("userLogin", avisOperationBourseDto.getUserLogin());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    s[s.length-1]);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> calculer(AvisOperationBourseDto avisOperationBourseDto) {
        try {
            BigDecimal montantBrut = BigDecimal.valueOf(0);
            BigDecimal interetCourru = BigDecimal.valueOf(0);
            String codeRole = "";
            BigDecimal commissionDepo = BigDecimal.valueOf(0);
            BigDecimal commissionPlace = BigDecimal.valueOf(0);
            BigDecimal commissionSGI = BigDecimal.valueOf(0);
            BigDecimal irvm = BigDecimal.valueOf(0);
            BigDecimal tafCommissionSGI = BigDecimal.valueOf(0);
            BigDecimal montantNet = BigDecimal.valueOf(0);
            BigDecimal plusOuMoinsValue = BigDecimal.valueOf(0);
            BigDecimal interetPrecompte = BigDecimal.valueOf(0);
            String codeNatureTcn = "";
            String codeClasseTitre = "";

            if (avisOperationBourseDto.getOrdre() != null) {
                codeRole = avisOperationBourseDto.getOrdre().getRole().trim();
                montantBrut = new BigDecimal(Double.valueOf(avisOperationBourseDto.getCoursLimite().toString()) *
                        Double.valueOf(avisOperationBourseDto.getQuantiteLimite().toString())).setScale(0, RoundingMode.HALF_UP);

                codeClasseTitre = avisOperationBourseDto.getOrdre().getTitre().getTypeTitre().getClasseTitre().getCodeClasseTitre().trim().toUpperCase();
                if (codeClasseTitre.equals("OBLIGATION") ||
                        codeClasseTitre.equals("TCN")) {
                    interetCourru = new BigDecimal(Double.valueOf(libraryDao.interetCouru(avisOperationBourseDto.getOrdre().getTitre().getIdTitre(),
                            avisOperationBourseDto.getDateOperation(), false, null).toString()) *
                            Double.valueOf(avisOperationBourseDto.getQuantiteLimite().toString())).setScale(0, RoundingMode.HALF_UP);

                    if (codeClasseTitre.equals("TCN")) {
                        Tcn otcn = new Tcn();
                        otcn = tcnDao.findById(avisOperationBourseDto.getOrdre().getTitre().getIdTitre()).orElseThrow();
                        codeNatureTcn = otcn.getNatureTcn().getCodeNatureTcn();

                        if (codeRole.trim().toUpperCase().equals("SOUSCRIPTION")) {
                            interetCourru = BigDecimal.valueOf(0);
                        }

                        if (codeNatureTcn.equals("PREC")) {
                            BigDecimal toto = new BigDecimal(Double.valueOf(libraryDao.interetCouru(
                                    avisOperationBourseDto.getOrdre().getTitre().getIdTitre(),
                                    otcn.getDateDernierPaiement(), false, null).toString()) *
                                    Double.valueOf(avisOperationBourseDto.getQuantiteLimite().toString())
                            );

                            interetPrecompte = toto;
                        } else
                            interetPrecompte = BigDecimal.valueOf(0);
                    }
                }

                //DEPOSITAIRE
                Long idDepositaire = depositaireDao.findByIdPersonne(avisOperationBourseDto.getOrdre().getTitre().getDepositaire().getIdPersonne()).getIdDepositaire();
                TarificationProjection objTOD = tarificationOrdinaireDao.afficherTarificationD(codeClasseTitre, idDepositaire, codeRole);
                if (objTOD != null) {
                    if (objTOD.getBorneInferieur() < montantBrut.doubleValue() &&
                            montantBrut.doubleValue() < objTOD.getBorneSuperieur()) {
                        commissionDepo = new BigDecimal(
                                Double.valueOf((montantBrut.doubleValue() + interetCourru.doubleValue())) * objTOD.getTaux() / 100).setScale(0,RoundingMode.HALF_UP);
                    } else {
                        commissionDepo = new BigDecimal(objTOD.getForfait());
                    }
                }

                //commisison de la place
                String codePlace = avisOperationBourseDto.getOrdre().getTitre().getPlace().getCodePlace();
                TarificationProjection objTOP = tarificationOrdinaireDao.afficherTarificationP(codeClasseTitre, codePlace, codeRole);
                if (objTOP != null) {
                    if (objTOP.getBorneInferieur() < montantBrut.doubleValue() &&
                            montantBrut.doubleValue() < objTOP.getBorneSuperieur()) {
                        commissionPlace = new BigDecimal(
                                Double.valueOf((montantBrut.doubleValue() + interetCourru.doubleValue())) * objTOP.getTaux() / 100).setScale(0,RoundingMode.HALF_UP);
                    } else {
                        commissionPlace = new BigDecimal(objTOP.getForfait());
                    }
                }

                //commisison de la SGI
                Registraire registraire = registraireDao.findByIdPersonne(avisOperationBourseDto.getOrdre().getPersonne().getIdPersonne());
                TarificationProjection objTOS = tarificationOrdinaireDao.afficherTarificationS(codeClasseTitre,
                        registraire.getIdRegistraire(), codeRole, avisOperationBourseDto.getOrdre().getOpcvm().getIdOpcvm());
                if (objTOS != null) {
                    if (objTOS.getBorneInferieur() < montantBrut.doubleValue() &&
                            montantBrut.doubleValue() < objTOS.getBorneSuperieur()) {
                        commissionSGI = new BigDecimal(
                                Double.valueOf((montantBrut.doubleValue() + interetCourru.doubleValue())) * objTOS.getTaux() / 100).setScale(0,RoundingMode.HALF_UP);
                    } else {
                        commissionSGI = new BigDecimal(objTOS.getForfait());
                    }
                }

                //plue value
                if (codeRole.trim().toUpperCase().equals("VENTE")) {
                    if (codeClasseTitre.trim().toUpperCase().equals("TCN")) {
                        plusOuMoinsValue = BigDecimal.valueOf(0);
                    } else {
                        //calculer la plus value
                        plusOuMoinsValue = new BigDecimal(montantBrut.doubleValue() - new BigDecimal(
                                Double.valueOf(avisOperationBourseDto.getCump().toString()) *
                                        Double.valueOf(avisOperationBourseDto.getQuantiteLimite().toString())).
                                setScale(0, RoundingMode.HALF_UP).doubleValue());
                    }
                    //calcul du montant net
                    //montantNet = montantBrut + interetCourru - commissionPlace - commissionSGI - tafCommissionSGI -
                    //   commissionDepo - irvm - ((codeClasseTitre.Trim().ToUpper() == "TCN") ? interetPrecompte : 0);
                    montantNet = new BigDecimal(montantBrut.doubleValue() +
                            interetCourru.doubleValue() - commissionPlace.doubleValue()
                            - commissionSGI.doubleValue() - tafCommissionSGI.doubleValue() -
                            commissionDepo.doubleValue() - irvm.doubleValue()
                            - interetPrecompte.doubleValue());

                } else {
                    //montantNet = montantBrut + commissionPlace + commissionSGI + tafCommissionSGI +
                    //    commissionDepo + interetCourru - ((codeClasseTitre.Trim().ToUpper() == "TCN")?interetPrecompte:0);
                    montantNet = new BigDecimal(montantBrut.doubleValue()
                            + commissionPlace.doubleValue() + commissionSGI.doubleValue()
                            + tafCommissionSGI.doubleValue() +
                            commissionDepo.doubleValue() + interetCourru.doubleValue() - interetPrecompte.doubleValue());
                }
            }

            avisOperationBourseDto.setMontantBrut(montantBrut);
            avisOperationBourseDto.setMontantNet(montantNet);
            avisOperationBourseDto.setPlusOuMoinsValue(plusOuMoinsValue);
            avisOperationBourseDto.setCommissionDepositaire(commissionDepo);
            avisOperationBourseDto.setCommissionPlace(commissionPlace);
            avisOperationBourseDto.setCommissionSGI(commissionSGI);
            avisOperationBourseDto.setInteret(interetCourru);
            avisOperationBourseDto.setInteretPrecompte(interetPrecompte);
            avisOperationBourseDto.settAF(tafCommissionSGI);

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    avisOperationBourseDto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(AvisOperationBourseDto avisOperationBourseDto) {
        try {
            String sortie = "";
            var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_AvisOperationBourse_UP_New]");

            q.registerStoredProcedureParameter("IdAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referenceAvis", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateReceptionLivraisonPrevu", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("coursLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionPlace", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionDepositaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionSGI", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("TAF", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IRVM", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interet", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("plusOuMoinsValue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("IdAvis", avisOperationBourseDto.getIdAvis());
            q.setParameter("IdTransaction", avisOperationBourseDto.getIdTransaction());
            q.setParameter("codeNatureOperation", avisOperationBourseDto.getNatureOperation().getCodeNatureOperation());
            q.setParameter("dateOperation", avisOperationBourseDto.getDateOperation());
            q.setParameter("dateSaisie", avisOperationBourseDto.getDateSaisie());
            q.setParameter("dateValeur", avisOperationBourseDto.getDateValeur());
            q.setParameter("datePiece", avisOperationBourseDto.getDatePiece());
            q.setParameter("referencePiece", avisOperationBourseDto.getReferencePiece());
            q.setParameter("libelleOperation", avisOperationBourseDto.getLibelleOperation());
            q.setParameter("montant", avisOperationBourseDto.getMontant());
            q.setParameter("ecriture", avisOperationBourseDto.getEcriture());
            q.setParameter("estOD", avisOperationBourseDto.getEstOD());
            q.setParameter("type", avisOperationBourseDto.getType());
            q.setParameter("referenceAvis", avisOperationBourseDto.getReferenceAvis());
            q.setParameter("IdOrdre", avisOperationBourseDto.getOrdre().getIdOrdre());
            q.setParameter("dateReceptionLivraisonPrevu", avisOperationBourseDto.getDateReceptionLivraisonPrevu());
            q.setParameter("quantiteLimite", avisOperationBourseDto.getQuantiteLimite());
            q.setParameter("coursLimite", avisOperationBourseDto.getCoursLimite());
            q.setParameter("commissionPlace", avisOperationBourseDto.getCommissionPlace());
            q.setParameter("commissionDepositaire", avisOperationBourseDto.getCommissionDepositaire());
            q.setParameter("commissionSGI", avisOperationBourseDto.getCommissionSGI());
            q.setParameter("TAF", avisOperationBourseDto.gettAF());
            q.setParameter("IRVM", avisOperationBourseDto.getiRVM());
            q.setParameter("interet", avisOperationBourseDto.getInteret());
            q.setParameter("plusOuMoinsValue", avisOperationBourseDto.getPlusOuMoinsValue());
            q.setParameter("montantBrut", avisOperationBourseDto.getMontantBrut());

            q.setParameter("userLogin", avisOperationBourseDto.getUserLogin());
            q.setParameter("dateDernModifClient", avisOperationBourseDto.getDateDernModifClient());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);
            String[] s = new String[20];
            try {
                q.executeUpdate();
                String result = (String) q.getOutputParameterValue("Sortie");
                s = result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            } catch (Exception e) {

            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

//    @Override
//    public ResponseEntity<Object> validation(Long[] id,String userLogin) {
//        try {
//            String sortie = "";
//            for(int i=0;i<id.length;i++){
//                var q = em.createStoredProcedureQuery("[AvisOperationBourseDeBourse].[PS_AvisOperationBourse_UP1_New]");
//
//                q.registerStoredProcedureParameter("IdAvisOperationBourse", Long.class, ParameterMode.IN);
//                q.registerStoredProcedureParameter("statut", String.class, ParameterMode.IN);
//                q.registerStoredProcedureParameter("estEnvoye", Boolean.class, ParameterMode.IN);
//                q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
//                q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
//                q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);
//
//                q.setParameter("IdAvisOperationBourse", id[i]);
//                q.setParameter("statut", "ENVOYE");
//                q.setParameter("estEnvoye", true);
//                q.setParameter("userLogin", userLogin);
//                q.setParameter("CodeLangue", "fr-FR");
//                q.setParameter("Sortie",sortie);
//                String[] s = new String[20];
//                try {
//                    q.executeUpdate();
//                    String result = (String) q.getOutputParameterValue("Sortie");
//                    s = result.split("#");
//                    //System.out.println("idOperation="+s[s.length-1]);
//                } catch (Exception e) {
//
//                }
//
//            }
//            return ResponseHandler.generateResponse(
//                    "Validation effectuée avec succès !",
//                    HttpStatus.OK,
//                    null);
//
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> impressionAvisOperationBourseBourse(Long idOpcvm, Long idSeance) {
//        try {
//            List<AvisOperationBourseProjection> AvisOperationBourseProjections=AvisOperationBourseDao.afficherListeAvisOperationBourse(idOpcvm,idSeance);
//            return ResponseHandler.generateResponse(
//                    "Impression effectuée avec succès !",
//                    HttpStatus.OK,
//                    AvisOperationBourseProjections);
//
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }
//
//    @Override
//    public ResponseEntity<Object> jaspertReportAvisOperationBourseBourse(String[] numeroAvisOperationBourse, HttpServletResponse response) throws IOException, JRException {
//        String num="";
//        for(int i=0;i<numeroAvisOperationBourse.length;i++){
//            if(i==0){
//                num=numeroAvisOperationBourse[i];
//            }
//            else
//                num=num+";"+numeroAvisOperationBourse[i];
//        }
//        List<AvisOperationBourseProjection> list=libraryDao.listeAvisOperationBourseApercu(num);
//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:AvisOperationBourseDeBourse.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//        return ResponseHandler.generateResponse(
//                "AvisOperationBourse de bourse",
//                HttpStatus.OK,
//                list);
//    }

    @Override
    public ResponseEntity<Object> supprimer(Long idAvis,String userLogin) {
        try {
            String sortie = "";
            var q = em.createStoredProcedureQuery("[AvisOperationBourseDeBourse].[PS_AvisOperationBourse_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idAvis",idAvis);
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s = new String[20];
            try {
                q.execute();
                String result = (String) q.getOutputParameterValue("Sortie");
                s = result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            } catch (Exception e) {

            }
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès !",
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
