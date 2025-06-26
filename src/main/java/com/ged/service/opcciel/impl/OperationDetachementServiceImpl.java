package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationDetachementDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.ObligationDao;
import com.ged.dao.titresciel.TcnDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.titresciel.Obligation;
import com.ged.entity.titresciel.Tcn;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OperationDetachementMapper;
import com.ged.projection.OperationDetachementProjection;
import com.ged.projection.PortefeuilleOpcvmProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationDetachementService;
import com.ged.service.standard.impl.MailSenderServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationDetachementServiceImpl implements OperationDetachementService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final FiltersSpecification<OperationDetachement> DetachementFiltersSpecification;
    private final OperationDetachementDao operationDetachementDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final TcnDao tcnDao;
    private final ObligationDao obligationDao;
    private final TransactionDao transactionDao;
    private final MailSenderServiceImpl mailSenderService;
    private final NatureOperationDao natureOperationDao;
    private final OperationDetachementMapper operationDetachementMapper;

    public OperationDetachementServiceImpl(FiltersSpecification<OperationDetachement> DetachementFiltersSpecification, OperationDetachementDao operationDetachementDao, PersonneDao personneDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, TcnDao tcnDao, ObligationDao obligationDao, TransactionDao transactionDao, MailSenderServiceImpl mailSenderService, NatureOperationDao natureOperationDao, OperationDetachementMapper operationDetachementMapper){
        this.DetachementFiltersSpecification = DetachementFiltersSpecification;
        this.operationDetachementDao = operationDetachementDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.tcnDao = tcnDao;
        this.obligationDao = obligationDao;
        this.transactionDao = transactionDao;
        this.mailSenderService = mailSenderService;
        this.natureOperationDao = natureOperationDao;

        this.operationDetachementMapper = operationDetachementMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationDetachementProjection> operationDetachementPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                operationDetachementPage = libraryDao.operationDetachementListeRecherche(idOpcvm,null,parameters.getSearch().getValue(),pageable);
            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                operationDetachementPage = libraryDao.operationDetachementListe(idOpcvm,null,pageable);
            }

//            List<OperationDetachementDto> content = operationDetachementPage.getContent().stream().map(operationDetachementMapper::deOperationDetachementProjection).collect(Collectors.toList());
            List<OperationDetachementProjection> content = operationDetachementPage.getContent().stream().collect(Collectors.toList());
            DataTablesResponse<OperationDetachementProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationDetachementPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationDetachementPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des détachements par page datatable",
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm,boolean estPaye,String typeEvenement) {
        try {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationDetachementDto> operationDetachementDtos = libraryDao.operationDetachementListe(idOpcvm,estPaye,typeEvenement).stream().map(operationDetachementMapper::deOperationDetachementProjection).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des détachements ",
                    HttpStatus.OK,
                    operationDetachementDtos);
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
    public OperationDetachementProjection afficherSelonId(Long id) {
        return operationDetachementDao.afficherSelonId(id);
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            System.out.println(id);
            OperationDetachementProjection operationDetachement=afficherSelonId(id);
            return ResponseHandler.generateResponse(
                    "Operation detachement dont ID = " + id,
                    HttpStatus.OK,
                    operationDetachementMapper.deOperationDetachementProjection(operationDetachement));
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
    public ResponseEntity<Object> afficherTitre(Long idOpcvm, LocalDateTime dateEstimation, String typeEvenement) {
        try {
//            List<PortefeuilleOpcvmProjection> portefeuilleOpcvmProjections=new ArrayList<>();//libraryDao.portefeuilleOPCVM(idOpcvm, dateEstimation);
            List<PortefeuilleOpcvmProjection> list=new ArrayList<>();
            if(typeEvenement.equals("DIVIDENDE")){
                list=libraryDao.portefeuilleOPCVMAction(idOpcvm, dateEstimation);
            }
            else
            {
                list=libraryDao.portefeuilleOPCVMAutres(idOpcvm, dateEstimation);
            }
//            for(PortefeuilleOpcvmProjection o:portefeuilleOpcvmProjections){
//                if(typeEvenement.equals("DIVIDENDE")){
//                    if(o.getCodeTypeTitre().equals("ACTION")){
//                        list.add(o);
//                    }
//                }
//                else
//                {
//                    if(o.getCodeTypeTitre().equals("OBLIGATI") ||
//                      o.getCodeTypeTitre().equals("BOT")  ||
//                      o.getCodeTypeTitre().equals("BIT")  ||
//                      o.getCodeTypeTitre().equals("CED") ||
//                      o.getCodeTypeTitre().equals("BEFI") ||
//                      o.getCodeTypeTitre().equals("OBLIGATN")){
//                      list.add(o);
//                    }
//                }
//            }
            return ResponseHandler.generateResponse(
                    "PortefeuilleOPCVM  ",
                    HttpStatus.OK,
                    list);
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
    public ResponseEntity<Object> creer(OperationDetachementDto operationDetachementDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDetachement_IP_New]");

            q.registerStoredProcedureParameter("IdDetachement", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateReelle", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typeEvenement", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typePayement", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdIntervenant_New", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteDetenue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("couponDividendeUnitaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteAmortie", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("nominalRemb", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("capitalRembourse", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantTotalARecevoir", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            BigDecimal nbrePeriode=libraryDao.nbrePeriode(operationDetachementDto.getTitre().getIdTitre(),
                    operationDetachementDto.getOpcvm().getIdOpcvm());

            BigDecimal cump = libraryDao.cump(operationDetachementDto.getOpcvm().getIdOpcvm(),
                    operationDetachementDto.getTitre().getIdTitre(),
                    operationDetachementDto.getDateOperation());
            BigDecimal plusV = BigDecimal.valueOf(0);
            BigDecimal moinsV = BigDecimal.valueOf(0);
            plusV =new BigDecimal(operationDetachementDto.getCapitalRembourse().doubleValue()
                    -new BigDecimal((operationDetachementDto.getLibelleModeAmortissement().trim().equals("SUR VALEUR") ?
                        (operationDetachementDto.getCapitalRembourse().doubleValue()!=0?
                                (new BigDecimal((operationDetachementDto.getQteDetenue().doubleValue()*cump.doubleValue())/
                                        nbrePeriode.doubleValue()).setScale(0,RoundingMode.HALF_UP)) :BigDecimal.valueOf(0).doubleValue()) :
                        (cump.doubleValue()* operationDetachementDto.getQuantiteAmortie().doubleValue())).toString()).setScale(0,RoundingMode.HALF_UP).doubleValue());

            if (plusV.doubleValue() <0)
            {
                moinsV =BigDecimal.valueOf(-1 * plusV.doubleValue());
                plusV =BigDecimal.valueOf(0);
            }
            Titre titre=titreDao.findById(operationDetachementDto.getTitre().getIdTitre()).orElseThrow();
            if (operationDetachementDto.getTypeEvenement().trim().toUpperCase().equals("DIVIDENDE"))
            {
                operationDetachementDto.setLibelleOperation("ANNONCE DE DIVIDENDE SUR " + titre.getSymbolTitre().trim());
                operationDetachementDto.setValeurFormule
                        ("77:" + operationDetachementDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";27:" + 0 +
                        ";75:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.'));
                operationDetachementDto.setCodeNatureOperation("ANDIV");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATI"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR OBLIGATION COTEE:"
                        + titre.getSymbolTitre().trim());
                operationDetachementDto.setValeurFormule("28:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.') +
                        ";32:" + moinsV.toString().replace(',', '.') +
                        ";21:" + new BigDecimal(operationDetachementDto.getLibelleModeAmortissement().trim().equals("SUR VALEUR") ?
                        (operationDetachementDto.getCapitalRembourse().doubleValue()!=0?
                                (new BigDecimal((cump.doubleValue() * operationDetachementDto.getQteDetenue().doubleValue()) / nbrePeriode.doubleValue()).setScale(0,RoundingMode.HALF_UP).doubleValue()) : 0) : (cump.doubleValue() *
                        operationDetachementDto.getQuantiteAmortie().doubleValue())).setScale(0,RoundingMode.HALF_UP).toString().replace(',', '.') +
                        ";46:" + plusV.toString().replace(',', '.') +
                        ";105:" + (operationDetachementDto.getCapitalRembourse()).toString().replace(',', '.') +
                        ";51:" + operationDetachementDto.getQuantiteAmortie().toString().replace(',', '.'));
                operationDetachementDto.setCodeNatureOperation("DET_COUPOBLICOTE");
            }

            else if ( titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATN"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR OBLIGATION NON COTEE:" +
                        titre.getSymbolTitre().trim());
                operationDetachementDto.setValeurFormule("28:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.') +
                        ";32:" + moinsV.toString().replace(',', '.') +
                        ";21:" + new BigDecimal((operationDetachementDto.getLibelleModeAmortissement().trim().equals("SUR VALEUR") ?
                        (operationDetachementDto.getCapitalRembourse().doubleValue() != 0 ?
                        (new BigDecimal((cump.doubleValue() * operationDetachementDto.getQteDetenue().doubleValue()) / nbrePeriode.doubleValue()).setScale(0,RoundingMode.HALF_UP)) : 0) :
                        (cump.doubleValue() *
                        operationDetachementDto.getQuantiteAmortie().doubleValue())).toString()).setScale(0,RoundingMode.HALF_UP).toString().replace(',', '.') +
                        ";46:" + plusV.toString().replace(',', '.') +
                        ";105:" + (operationDetachementDto.getCapitalRembourse()).toString().replace(',', '.') +
                        ";51:" + operationDetachementDto.getQuantiteAmortie().toString().replace(',', '.'));
                operationDetachementDto.setCodeNatureOperation("DET_COUPOBLINCOT");
            }

            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BIT"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR BILLET DE TRESORERIE:" +
                        titre.getSymbolTitre().trim());
                operationDetachementDto.setValeurFormule("21:" + new BigDecimal(cump.doubleValue() *
                        operationDetachementDto.getQuantiteAmortie().doubleValue()).toString().replace(',', '.') +
                        ";51:" + operationDetachementDto.getQuantiteAmortie().toString().replace(',', '.') +
                        ";30:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.') +
                        ";4:" + operationDetachementDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";32:" + moinsV.toString().replace(',', '.') +
                        ";47:" + plusV.toString().replace(',', '.') +
                        ";108:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.'));
                operationDetachementDto.setCodeNatureOperation("DET_COUPON_BDT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BOT"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR BON DE TRESOR:" +
                        titre.getSymbolTitre().trim());
                operationDetachementDto.setValeurFormule("21:" + new BigDecimal((cump.doubleValue() *
                        operationDetachementDto.getQuantiteAmortie().doubleValue())).setScale(0,RoundingMode.HALF_UP).toString().replace(',', '.') +
                        ";51:" + operationDetachementDto.getQuantiteAmortie().toString().replace(',', '.') +
                        ";30:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.'));
               operationDetachementDto.setCodeNatureOperation("DET_COUP_BT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BEFI"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR BEFI:" +
                        titre.getSymbolTitre().trim());
                operationDetachementDto.setValeurFormule("21:" + new BigDecimal((cump.doubleValue() *
                        (operationDetachementDto.getLibelleModeAmortissement().trim().equals("SUR VALEUR") ?
                        (operationDetachementDto.getTypePayement().trim().equals("INTERET") ? BigDecimal.valueOf(0).doubleValue() : (operationDetachementDto.getQteDetenue().doubleValue() / nbrePeriode.doubleValue())):
                                (operationDetachementDto.getTypePayement().trim().equals("INTERET") ? BigDecimal.valueOf(0).doubleValue() : operationDetachementDto.getQuantiteAmortie().doubleValue())))).toString().replace(',', '.') +
                        ";51:" + operationDetachementDto.getQuantiteAmortie().toString().replace(',', '.') +
                        ";32:" + moinsV.toString().replace(',', '.') +
                        ";47:" + plusV.toString().replace(',', '.') +
                        ";105:" + operationDetachementDto.getCapitalRembourse().toString().replace(',', '.') +
                        ";108:" + operationDetachementDto.getMontantBrut().toString().replace(',', '.'));
                operationDetachementDto.setCodeNatureOperation("DET_COUPON_BEFI");
            }

//            q.setParameter("idOperation", 0);
            q.setParameter("IdDetachement", 0);
            q.setParameter("IdTransaction",0);
            q.setParameter("idSeance", operationDetachementDto.getIdSeance());
            q.setParameter("codeNatureOperation", operationDetachementDto.getCodeNatureOperation());
            q.setParameter("dateReelle", operationDetachementDto.getDateReelle());
            q.setParameter("dateOperation", operationDetachementDto.getDateOperation());
            q.setParameter("dateSaisie", operationDetachementDto.getDateSaisie());
            q.setParameter("dateValeur", operationDetachementDto.getDateValeur());
            q.setParameter("datePiece", operationDetachementDto.getDatePiece());
            q.setParameter("referencePiece", operationDetachementDto.getReferencePiece());
            q.setParameter("montant", operationDetachementDto.getMontant());
            q.setParameter("ecriture", operationDetachementDto.getEcriture());
            q.setParameter("libelleOperation",operationDetachementDto.getLibelleOperation());
            q.setParameter("estOD", operationDetachementDto.getEstOD());
            q.setParameter("type", operationDetachementDto.getType());
            q.setParameter("typeEvenement", operationDetachementDto.getTypeEvenement());
            q.setParameter("typePayement", operationDetachementDto.getTypePayement());
            q.setParameter("IdIntervenant_New", operationDetachementDto.getIntervenant().getIdPersonne());
            q.setParameter("idTitre", operationDetachementDto.getTitre().getIdTitre());
            q.setParameter("qteDetenue", operationDetachementDto.getQteDetenue());
            q.setParameter("couponDividendeUnitaire", operationDetachementDto.getCouponDividendeUnitaire());
            q.setParameter("montantBrut", operationDetachementDto.getMontantBrut());
            q.setParameter("quantiteAmortie", operationDetachementDto.getQuantiteAmortie());
            q.setParameter("nominalRemb", operationDetachementDto.getNominalRemb());
            q.setParameter("capitalRembourse", operationDetachementDto.getCapitalRembourse());
            q.setParameter("montantTotalARecevoir", operationDetachementDto.getMontantTotalARecevoir());
            q.setParameter("idOpcvm", operationDetachementDto.getOpcvm().getIdOpcvm());
            q.setParameter("valeurFormule",operationDetachementDto.getValeurFormule());
            q.setParameter("valeurCodeAnalytique",operationDetachementDto.getValeurCodeAnalytique());
            q.setParameter("userLogin", operationDetachementDto.getUserLogin());
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
    public ResponseEntity<Object> valeurOuQte(OperationDetachementDto operationDetachementDto) {
        try {
            OperationDetachementDto operationDetachementDtoResult=new OperationDetachementDto();
            operationDetachementDtoResult=operationDetachementDto;
            Titre titre=titreDao.findById(operationDetachementDto.getTitre().getIdTitre()).orElseThrow();
            if (titre != null)
            {
                LocalDateTime dateTime =LocalDateTime.now();
                Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                java.util.Date date = Date.from(i);
                if(operationDetachementDto.getDateReelle()==null)
                {
                    dateTime=LocalDateTime.parse(operationDetachementDto.getDateOperation().toString().substring(0,10)+"T00:00:00");
                    i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                    date = Date.from(i);
                }
                else
                {
                    dateTime=LocalDateTime.parse(operationDetachementDto.getDateReelle().toString().substring(0,10)+"T00:00:00");
                    i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                    date = Date.from(i);
                }
                System.out.println("date="+date);
                operationDetachementDtoResult.setNominalRemb(titre.getNominal());
                operationDetachementDtoResult.setCouponDividendeUnitaire(libraryDao.interetCouru(operationDetachementDto.getTitre().getIdTitre(),
                        date,false,operationDetachementDto.getOpcvm().getIdOpcvm()));

                //region controler si il s'agit d'un titre amorti sur valeur ou quantité
                String LibelleModeAmortissement="";
                String codeTypeTitre=titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase();
                if ( titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATI") ||
                        titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATN"))
                {
                    Obligation objObligation=obligationDao.findById(titre.getIdTitre()).orElseThrow();
                    if (objObligation != null)
                    {
                        LibelleModeAmortissement = objObligation.getModeAmortissement().getLibelleModeAmortissement().trim();
                    }
                }
                else
                if (
                    codeTypeTitre.equals("BOT") || codeTypeTitre.equals("BIT") ||
                            codeTypeTitre.equals("CED") ||
                            codeTypeTitre.equals("BEFI"))
                {
                    Tcn objTCN = tcnDao.findById(titre.getIdTitre()).orElseThrow();
                    if (objTCN != null)
                    {
                        LibelleModeAmortissement = objTCN.getModeAmortissement().getLibelleModeAmortissement().trim();
                    }
                }
                {

                    switch (LibelleModeAmortissement)
                    {
                        case "SUR QUANTITE":
//                            meb_NominalRemb.ReadOnly = true;
//                            meb_qtiteAMORT.ReadOnly = false;
                            operationDetachementDtoResult.setbNominalRemb(true);
                            operationDetachementDtoResult.setbQtiteAMORT(false);
                            operationDetachementDtoResult.setQuantiteAmortie(libraryDao.qteAmortie(titre.getIdTitre(),
                                    operationDetachementDto.getDateReelle(),operationDetachementDto.getQteDetenue().longValue()));
                            operationDetachementDtoResult.setbMtantRemb(true);
                            operationDetachementDtoResult.setbMtantRecevoir(true);
//                            meb_MtantRemb.ReadOnly = true;
//                            meb_MtantRecevoir.ReadOnly = true;
                            break;
                        case "SUR VALEUR":
                            dateTime =operationDetachementDto.getDateReelle();
                            i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                            date = Date.from(i);
                            operationDetachementDtoResult.setQuantiteAmortie(BigDecimal.valueOf(0));
                            operationDetachementDtoResult.setNominalRemb(libraryDao.nominalRembourse(titre.getIdTitre(),
                                    date));

                            dateTime=libraryDao.derniereEcheance(titre.getIdTitre());
                            i=dateTime.atZone(ZoneId.systemDefault()).toInstant();
                            java.util.Date derniereEcheance = Date.from(i);

                            i=operationDetachementDto.getDateReelle().atZone(ZoneId.systemDefault()).toInstant();
                            java.util.Date dateEcheance = Date.from(i);
                            operationDetachementDtoResult.setbNominalRemb(false);
                            operationDetachementDtoResult.setbQtiteAMORT(true);
                            operationDetachementDtoResult.setbMtantRemb(false);
                            operationDetachementDtoResult.setbMtantRecevoir(false);

                            if (dateEcheance.equals(derniereEcheance))
                                operationDetachementDtoResult.setQuantiteAmortie(operationDetachementDto.getQteDetenue());
                            break;
                    }
                }

                BigDecimal capitalRemb=BigDecimal.valueOf(0);
                if (LibelleModeAmortissement.equals("SUR VALEUR"))
                {
                    capitalRemb=(new BigDecimal(operationDetachementDtoResult.getQteDetenue().doubleValue()*
                            operationDetachementDtoResult.getNominalRemb().doubleValue()));
                }
                else
                {
                    capitalRemb=(new BigDecimal(operationDetachementDtoResult.getQuantiteAmortie().doubleValue()*
                            operationDetachementDtoResult.getNominalRemb().doubleValue()));
                }

                BigDecimal couponDividendeTotal=BigDecimal.valueOf(0);
                if(operationDetachementDtoResult.getQteDetenue()!=null && operationDetachementDtoResult.getCouponDividendeUnitaire()!=null)
                    couponDividendeTotal=(new BigDecimal(operationDetachementDtoResult.getQteDetenue().doubleValue()*
                        operationDetachementDtoResult.getCouponDividendeUnitaire().doubleValue()).setScale(0, RoundingMode.HALF_UP));

                if(operationDetachementDto.getTypePayement().equals("CAPITAL + INTERET")){
                    operationDetachementDtoResult.setCapitalRembourse(capitalRemb);
                    operationDetachementDtoResult.setCouponDividendeTotal(couponDividendeTotal);
                }
                else
                    if(operationDetachementDto.getTypePayement().equals("INTERET")){
                        operationDetachementDtoResult.setNominalRemb(BigDecimal.valueOf(0));
                        operationDetachementDtoResult.setCapitalRembourse(BigDecimal.valueOf(0));
                        operationDetachementDtoResult.setCouponDividendeTotal(couponDividendeTotal);
                    }
                    else
                        if(operationDetachementDto.getTypePayement().equals("CAPITAL")) {
                            operationDetachementDtoResult.setCouponDividendeUnitaire(BigDecimal.valueOf(0));
                            operationDetachementDtoResult.setCouponDividendeTotal(BigDecimal.valueOf(0));
                            operationDetachementDtoResult.setCapitalRembourse(capitalRemb);
                        }
                        else
                        if(operationDetachementDto.getTypePayement().equals("INTERET MORATOIRE"))
                        {
                            operationDetachementDtoResult.setCapitalRembourse(capitalRemb);
                        }
                operationDetachementDtoResult.setMontantTotalARecevoir(new BigDecimal(operationDetachementDtoResult.getCapitalRembourse().doubleValue()
                +operationDetachementDtoResult.getCouponDividendeTotal().doubleValue()));
                        operationDetachementDtoResult.setLibelleModeAmortissement(LibelleModeAmortissement);
            }
            return ResponseHandler.generateResponse(
                    "Valeur ou qté",
                    HttpStatus.OK,
                    operationDetachementDtoResult);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OperationDetachementDto operationDetachementDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDetachement_UP_New]");

            q.registerStoredProcedureParameter("IdDetachement", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typeEvenement", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typePayement", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdIntervenant_New", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteDetenue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("couponDividendeUnitaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteAmortie", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("nominalRemb", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("capitalRembourse", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantTotalARecevoir", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estPaye", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            Titre titre=titreDao.findById(operationDetachementDto.getTitre().getIdTitre()).orElseThrow();
            if (operationDetachementDto.getTypeEvenement().trim().toUpperCase().equals("DIVIDENDE"))
            {
                operationDetachementDto.setLibelleOperation("ANNONCE DE DIVIDENDE SUR " + titre.getSymbolTitre().trim());

                operationDetachementDto.setCodeNatureOperation("ANDIV");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATI"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR OBLIGATION COTEE:"
                        + titre.getSymbolTitre().trim());

                operationDetachementDto.setCodeNatureOperation("DET_COUPOBLICOTE");
            }

            else if ( titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATN"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR OBLIGATION NON COTEE:" +
                        titre.getSymbolTitre().trim());

                operationDetachementDto.setCodeNatureOperation("DET_COUPOBLINCOT");
            }

            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BIT"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR BILLET DE TRESORERIE:" +
                        titre.getSymbolTitre().trim());

                operationDetachementDto.setCodeNatureOperation("DET_COUPON_BDT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BOT"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR BON DE TRESOR:" +
                        titre.getSymbolTitre().trim());

                operationDetachementDto.setCodeNatureOperation("DET_COUP_BT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BEFI"))
            {
                operationDetachementDto.setLibelleOperation("DETACHEMENT DE COUPON SUR BEFI:" +
                        titre.getSymbolTitre().trim());

                operationDetachementDto.setCodeNatureOperation("DET_COUPON_BEFI");
            }
//            q.setParameter("idOperation", 0);
            q.setParameter("IdDetachement", operationDetachementDto.getIdOperation());
            q.setParameter("IdTransaction",0);
            q.setParameter("idSeance", operationDetachementDto.getIdSeance());
            q.setParameter("codeNatureOperation", operationDetachementDto.getCodeNatureOperation());
            q.setParameter("dateOperation", operationDetachementDto.getDateOperation());
            q.setParameter("dateSaisie", operationDetachementDto.getDateSaisie());
            q.setParameter("dateValeur", operationDetachementDto.getDateValeur());
            q.setParameter("datePiece", operationDetachementDto.getDatePiece());
            q.setParameter("referencePiece", operationDetachementDto.getReferencePiece());
            q.setParameter("montant", operationDetachementDto.getMontant());
            q.setParameter("ecriture", operationDetachementDto.getEcriture());
            q.setParameter("libelleOperation",operationDetachementDto.getLibelleOperation());
            q.setParameter("estOD", operationDetachementDto.getEstOD());
            q.setParameter("type", operationDetachementDto.getType());
            q.setParameter("typeEvenement", operationDetachementDto.getTypeEvenement());
            q.setParameter("typePayement", operationDetachementDto.getTypePayement());
            q.setParameter("IdIntervenant_New", operationDetachementDto.getIntervenant().getIdPersonne());
            q.setParameter("idTitre", operationDetachementDto.getTitre().getIdTitre());
            q.setParameter("qteDetenue", operationDetachementDto.getQteDetenue());
            q.setParameter("couponDividendeUnitaire", operationDetachementDto.getCouponDividendeUnitaire());
            q.setParameter("montantBrut", operationDetachementDto.getMontantBrut());
            q.setParameter("quantiteAmortie", operationDetachementDto.getQuantiteAmortie());
            q.setParameter("nominalRemb", operationDetachementDto.getNominalRemb());
            q.setParameter("capitalRembourse", operationDetachementDto.getCapitalRembourse());
            q.setParameter("montantTotalARecevoir", operationDetachementDto.getMontantTotalARecevoir());
            q.setParameter("idOpcvm", operationDetachementDto.getOpcvm().getIdOpcvm());
            q.setParameter("estPaye",operationDetachementDto.getEstPaye());
            q.setParameter("userLogin", operationDetachementDto.getUserLogin());
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

    @Override
    public ResponseEntity<Object> supprimer(String userLogin,
                                            Long idDetachement ) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDetachement_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idDetachement", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idDetachement",idDetachement);
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);

            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
//                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
//            operationDetachementDao.deleteById(id);
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
