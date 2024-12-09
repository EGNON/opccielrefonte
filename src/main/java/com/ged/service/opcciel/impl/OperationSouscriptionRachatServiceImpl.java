package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationSouscriptionRachatDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OperationSouscriptionRachatMapper;
import com.ged.projection.AvisOperationProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationSouscriptionRachatService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationSouscriptionRachatServiceImpl implements OperationSouscriptionRachatService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final OperationSouscriptionRachatDao operationSouscriptionRachatDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final TransactionDao transactionDao;
    private final NatureOperationDao natureOperationDao;
    private final OperationSouscriptionRachatMapper operationSouscriptionRachatMapper;

    public OperationSouscriptionRachatServiceImpl(OperationSouscriptionRachatDao operationSouscriptionRachatDao, PersonneDao personneDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, TransactionDao transactionDao, NatureOperationDao natureOperationDao, OperationSouscriptionRachatMapper operationSouscriptionRachatMapper){
        this.operationSouscriptionRachatDao = operationSouscriptionRachatDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.transactionDao = transactionDao;
        this.natureOperationDao = natureOperationDao;

        this.operationSouscriptionRachatMapper = operationSouscriptionRachatMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<OperationSouscriptionRachat> operationSouscriptionRachatPage;
           /* if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                planPage = planDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {*/
            operationSouscriptionRachatPage = operationSouscriptionRachatDao.findBySupprimer(false,pageable);
            //}

            List<OperationSouscriptionRachatDto> content = operationSouscriptionRachatPage.getContent().stream().map(operationSouscriptionRachatMapper::deOperationSouscriptionRachat).collect(Collectors.toList());
            DataTablesResponse<OperationSouscriptionRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationSouscriptionRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationSouscriptionRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations souscriptions rachats par page datatable",
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
    public ResponseEntity<Object> afficherTous() {
        try {
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationSouscriptionRachatDto> operationSouscriptionRachatDtos = operationSouscriptionRachatDao.findBySupprimer(false).stream().map(operationSouscriptionRachatMapper::deOperationSouscriptionRachat).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des opérations souscriptions rachats ",
                    HttpStatus.OK,
                    operationSouscriptionRachatDtos);
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
    public OperationSouscriptionRachat afficherSelonId(Long id) {
        return operationSouscriptionRachatDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Plan.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Operation souscription rachat dont ID = " + id,
                    HttpStatus.OK,
                    operationSouscriptionRachatMapper.deOperationSouscriptionRachat(afficherSelonId(id)));
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
    public ResponseEntity<Object> listeOperationSouscriptionRachat(Long idOpcvm, String codeNatureOperation, LocalDateTime dateDebut, LocalDateTime dateFin) {
        try {
            String sortie="";
            List<Object[]> list;
            OperationSouscriptionRachatDto2 operationSouscriptionRachatDto2=new OperationSouscriptionRachatDto2();
            List<OperationSouscriptionRachatDto2> operationSouscriptionRachatTab=new ArrayList<>();

                var q = em.createStoredProcedureQuery("[Operation].[PS_OperationSouscriptionRachat_SP_OPCCIEL2]");

                q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDebut", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateFin", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);

                q.setParameter("idOpcvm", idOpcvm);
                q.setParameter("codeNatureOperation", codeNatureOperation);
                q.setParameter("dateDebut",dateDebut);
                q.setParameter("dateFin",dateFin);
                q.setParameter("supprimer",false);

                try {
                    // Execute query
                    list=q.getResultList();
                    for(Object[] o:list){
                        operationSouscriptionRachatDto2=new OperationSouscriptionRachatDto2();
                        operationSouscriptionRachatDto2.setIdOperation(Long.valueOf(o[0].toString()));
                        operationSouscriptionRachatDto2.setIdTransaction(Long.valueOf(o[1].toString()));
                        operationSouscriptionRachatDto2.setIdSeance(Long.valueOf(o[2].toString()));
                        operationSouscriptionRachatDto2.setIdActionnaire(Long.valueOf(o[3].toString()));
                        operationSouscriptionRachatDto2.setNumCompteSgi(o[4].toString());
                        operationSouscriptionRachatDto2.setNomSigle(o[5].toString());
                        operationSouscriptionRachatDto2.setPrenomRaison(o[6].toString());
                        operationSouscriptionRachatDto2.setMail(o[7].toString());
                        operationSouscriptionRachatDto2.setPhone(o[8].toString());
                        operationSouscriptionRachatDto2.setIdOpcvm(Long.valueOf(o[9].toString()));
                        operationSouscriptionRachatDto2.setDenominationOpcvm(o[10].toString());
                        operationSouscriptionRachatDto2.setIdPersonne(Long.valueOf(o[11].toString()));
                        operationSouscriptionRachatDto2.setCodeNatureOperation(o[12].toString());
                        operationSouscriptionRachatDto2.setDateOperation(LocalDateTime.parse(o[13].toString().replace(' ','T')));
                        operationSouscriptionRachatDto2.setLibelleOperation(o[14].toString());
                        operationSouscriptionRachatDto2.setDateSaisie(LocalDateTime.parse(o[15].toString().replace(' ','T')));
                        operationSouscriptionRachatDto2.setDatePiece(LocalDateTime.parse(o[16].toString().replace(' ','T')));
                        operationSouscriptionRachatDto2.setDateValeur(LocalDateTime.parse(o[17].toString().replace(' ','T')));
                        operationSouscriptionRachatDto2.setReferencePiece(o[18].toString());
                        operationSouscriptionRachatDto2.setMontantSousALiquider(BigDecimal.valueOf(Double.valueOf(o[19].toString())));
                        operationSouscriptionRachatDto2.setSousRachatPart(BigDecimal.valueOf(Double.valueOf(o[20].toString())));
                        operationSouscriptionRachatDto2.setCommisiionSousRachat(BigDecimal.valueOf(Double.valueOf(o[21].toString())));
                        operationSouscriptionRachatDto2.settAFCommissionSousRachat(BigDecimal.valueOf(Double.valueOf(o[22].toString())));
                        operationSouscriptionRachatDto2.setRetrocessionSousRachat(BigDecimal.valueOf(Double.valueOf(o[23].toString())));
                        operationSouscriptionRachatDto2.settAFRetrocessionSousRachat(BigDecimal.valueOf(Double.valueOf(o[24].toString())));
                        operationSouscriptionRachatDto2.setCommissionSousRachatRetrocedee(BigDecimal.valueOf(Double.valueOf(o[25].toString())));
                        operationSouscriptionRachatDto2.setModeValeurLiquidative(o[26].toString());
                        operationSouscriptionRachatDto2.setCoursVL(BigDecimal.valueOf(Double.valueOf(o[27].toString())));
                        operationSouscriptionRachatDto2.setNombrePartSousRachat(BigDecimal.valueOf(Double.valueOf(o[28].toString())));
                        operationSouscriptionRachatDto2.setRegulResultatExoEnCours(BigDecimal.valueOf(Double.valueOf(o[29].toString())));
                        operationSouscriptionRachatDto2.setRegulSommeNonDistribuable(BigDecimal.valueOf(Double.valueOf(o[30].toString())));
                        operationSouscriptionRachatDto2.setRegulReportANouveau(BigDecimal.valueOf(Double.valueOf(o[31].toString())));
                        operationSouscriptionRachatDto2.setRegulautreResultatBeneficiaire(BigDecimal.valueOf(Double.valueOf(o[32].toString())));
                        operationSouscriptionRachatDto2.setRegulautreResultatDeficitaire(BigDecimal.valueOf(Double.valueOf(o[33].toString())));
                        operationSouscriptionRachatDto2.setRegulResultatEnInstanceBeneficiaire(BigDecimal.valueOf(Double.valueOf(o[34].toString())));
                        operationSouscriptionRachatDto2.setRegulResultatEnInstanceDeficitaire(BigDecimal.valueOf(Double.valueOf(o[35].toString())));
                        operationSouscriptionRachatDto2.setRegulExoDistribution(BigDecimal.valueOf(Double.valueOf(o[36].toString())));
                        operationSouscriptionRachatDto2.setFraisSouscriptionRachat(BigDecimal.valueOf(Double.valueOf(o[37].toString())));
                        operationSouscriptionRachatDto2.setReste(BigDecimal.valueOf(Double.valueOf(o[38].toString())));
                        operationSouscriptionRachatDto2.setQuantiteSouhaite(Long.valueOf(o[39].toString()));
                        operationSouscriptionRachatDto2.setMontantDepose(BigDecimal.valueOf(Double.valueOf(o[40].toString())));
                        operationSouscriptionRachatDto2.setMontantConvertiEnPart(BigDecimal.valueOf(Double.valueOf(o[41].toString())));
                        operationSouscriptionRachatDto2.setEstRetrocede(Boolean.valueOf(o[42].toString()));
                        operationSouscriptionRachatDto2.setResteRembourse(Boolean.valueOf(o[43].toString()));
                        operationSouscriptionRachatDto2.setRachatPaye(Boolean.valueOf(o[44].toString()));
                        operationSouscriptionRachatDto2.setEcriture(o[45].toString());
                        operationSouscriptionRachatTab.add(operationSouscriptionRachatDto2);
                    }
                } finally {
                    try {

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }



            return ResponseHandler.generateResponse(
                    "Liste opération souscription rachat !",
                    HttpStatus.OK,
                    operationSouscriptionRachatTab);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(OperationSouscriptionRachatDto operationSouscriptionRachatDto) {
        try {

            operationSouscriptionRachatDto.setSupprimer(false);
            OperationSouscriptionRachat  operationSouscriptionRachat =operationSouscriptionRachatMapper.deOperationSouscriptionRachatDto(operationSouscriptionRachatDto);
            if(operationSouscriptionRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(operationSouscriptionRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                operationSouscriptionRachat.setActionnaire(personne);
            }
            if(operationSouscriptionRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(operationSouscriptionRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                operationSouscriptionRachat.setOpcvm(opcvm);
            }
            if(operationSouscriptionRachatDto.getNatureOperation()!=null){
                NatureOperation natureOperation=natureOperationDao.findById(operationSouscriptionRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                operationSouscriptionRachat.setNatureOperation(natureOperation);
            }

            if(operationSouscriptionRachatDto.getTitre()!=null)
            {
                Titre titre=titreDao.findById(operationSouscriptionRachatDto.getTitre().getIdTitre()).orElseThrow();
                operationSouscriptionRachat.setTitre(titre);
            }
            if(operationSouscriptionRachatDto.getTransaction()!=null){
                Transaction transaction=transactionDao.findById(operationSouscriptionRachatDto.getTransaction().getIdTransaction()).orElseThrow();
                operationSouscriptionRachat.setTransaction(transaction);
            }
            operationSouscriptionRachat = operationSouscriptionRachatDao.save(operationSouscriptionRachat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    operationSouscriptionRachatMapper.deOperationSouscriptionRachat(operationSouscriptionRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> avisOperation(String idOperation) {
        try {
            List<AvisOperationProjection> list=libraryDao.avisOper(idOperation);
            return ResponseHandler.generateResponse(
                    "Avis opération",
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

    @Override
    public ResponseEntity<Object> creer(OperationSouscriptionRachatDto2[] operationSouscriptionRachatTab) {
        try {
            String sortie="";
            for(OperationSouscriptionRachatDto2 o:operationSouscriptionRachatTab)
            {
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
                q.registerStoredProcedureParameter("quantiteSouhaite", Long.class, ParameterMode.IN);
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


                q.setParameter("idOperation",0);
                q.setParameter("idTransaction",0);
                q.setParameter("idSeance",o.getIdSeance());
                q.setParameter("idActionnaire",o.getIdActionnaire());
                q.setParameter("idOpcvm", o.getIdOpcvm());
                q.setParameter("idPersonne",o.getIdPersonne());
                q.setParameter("codeNatureOperation", o.getCodeNatureOperation());
                q.setParameter("dateOperation",o.getDateOperation());
                q.setParameter("libelleOperation", o.getLibelleOperation());
                q.setParameter("dateSaisie",o.getDateSaisie());
                q.setParameter("datePiece", o.getDatePiece());
                q.setParameter("dateValeur", o.getDateValeur());
                q.setParameter("referencePiece", o.getReferencePiece());
                q.setParameter("montantSousALiquider", o.getMontantSousALiquider());
                q.setParameter("SousRachatPart",o.getSousRachatPart());
                q.setParameter("commisiionSousRachat", o.getCommisiionSousRachat());
                q.setParameter("TAFCommissionSousRachat", o.gettAFCommissionSousRachat());
                q.setParameter("retrocessionSousRachat",o.getRetrocessionSousRachat());
                q.setParameter("TAFRetrocessionSousRachat", o.gettAFRetrocessionSousRachat());
                q.setParameter("commissionSousRachatRetrocedee",o.getCommissionSousRachatRetrocedee());
                q.setParameter("modeValeurLiquidative", o.getModeValeurLiquidative());
                q.setParameter("coursVL", o.getCoursVL());
                q.setParameter("nombrePartSousRachat", o.getNombrePartSousRachat());
                q.setParameter("regulResultatExoEnCours", o.getRegulResultatExoEnCours());
                q.setParameter("regulSommeNonDistribuable", o.getRegulSommeNonDistribuable());
                q.setParameter("regulReportANouveau", o.getRegulReportANouveau());
                q.setParameter("regulautreResultatBeneficiaire",o.getRegulautreResultatBeneficiaire());
                q.setParameter("regulautreResultatDeficitaire", o.getRegulautreResultatDeficitaire());
                q.setParameter("regulResultatEnInstanceBeneficiaire", o.getRegulResultatEnInstanceBeneficiaire());
                q.setParameter("regulResultatEnInstanceDeficitaire", o.getRegulResultatEnInstanceDeficitaire());
                q.setParameter("regulExoDistribution", o.getRegulExoDistribution());
                q.setParameter("fraisSouscriptionRachat",o.getFraisSouscriptionRachat());
                q.setParameter("reste",o.getReste());
                q.setParameter("quantiteSouhaite", o.getQuantiteSouhaite());
                q.setParameter("montantDepose",o.getMontantDepose());
                q.setParameter("montantConvertiEnPart", o.getMontantConvertiEnPart());
                q.setParameter("estRetrocede", o.getEstRetrocede());
                q.setParameter("resteRembourse", o.getResteRembourse());
                q.setParameter("rachatPaye",o.getRachatPaye());
                q.setParameter("ecriture", o.getEcriture());
                q.setParameter("valeurFormule",o.getValeurFormule());
                q.setParameter("valeurCodeAnalytique", o.getValeurCodeAnalytique());
                q.setParameter("userLogin", o.getUserLogin());
                q.setParameter("dateDernModifClient",LocalDateTime.now());
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie",sortie);

                try {
                    // Execute query
                    q.execute();
//                    String result=(String) q.getOutputParameterValue("Sortie");
//                    String[] s=result.split("#");


                    //System.out.println("idOperation="+s[s.length-1]);
                } finally {
                    try {

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
            int taille=operationSouscriptionRachatTab.length;
            if(taille!=0){
                var q = em.createStoredProcedureQuery("[Parametre].[PS_ValiderSousRachat]");
                q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idPersonne", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);

                q.setParameter("idSeance",operationSouscriptionRachatTab[0].getIdSeance());
                q.setParameter("idOpcvm",operationSouscriptionRachatTab[0].getIdOpcvm());
                q.setParameter("idPersonne",operationSouscriptionRachatTab[0].getIdPersonne());
                q.setParameter("codeNatureOperation","INT_RACH");
                q.execute();
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
    public ResponseEntity<Object> modifier(OperationSouscriptionRachatDto operationSouscriptionRachatDto) {
        try {
            if(!operationSouscriptionRachatDao.existsById(operationSouscriptionRachatDto.getIdOperation()))
                throw  new EntityNotFoundException(OperationSouscriptionRachat.class, "code",operationSouscriptionRachatDto.getIdOperation().toString());
            operationSouscriptionRachatDto.setSupprimer(false);
            OperationSouscriptionRachat operationSouscriptionRachat =operationSouscriptionRachatMapper.deOperationSouscriptionRachatDto(operationSouscriptionRachatDto);
            if(operationSouscriptionRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(operationSouscriptionRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                operationSouscriptionRachat.setActionnaire(personne);
            }
            if(operationSouscriptionRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(operationSouscriptionRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                operationSouscriptionRachat.setOpcvm(opcvm);
            }
            if(operationSouscriptionRachatDto.getNatureOperation()!=null){
                NatureOperation natureOperation=natureOperationDao.findById(operationSouscriptionRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                operationSouscriptionRachat.setNatureOperation(natureOperation);
            }

            if(operationSouscriptionRachatDto.getTitre()!=null)
            {
                Titre titre=titreDao.findById(operationSouscriptionRachatDto.getTitre().getIdTitre()).orElseThrow();
                operationSouscriptionRachat.setTitre(titre);
            }
            if(operationSouscriptionRachatDto.getTransaction()!=null){
                Transaction transaction=transactionDao.findById(operationSouscriptionRachatDto.getTransaction().getIdTransaction()).orElseThrow();
                operationSouscriptionRachat.setTransaction(transaction);
            }
            operationSouscriptionRachat=operationSouscriptionRachatDao.save(operationSouscriptionRachat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    operationSouscriptionRachatMapper.deOperationSouscriptionRachat(operationSouscriptionRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            operationSouscriptionRachatDao.deleteById(id);
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
