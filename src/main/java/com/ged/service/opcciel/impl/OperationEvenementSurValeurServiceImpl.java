package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationDetachementDao;
import com.ged.dao.opcciel.OperationEvenementSurValeurDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.ObligationDao;
import com.ged.dao.titresciel.TcnDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationEvenementSurValeurDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationEvenementSurValeur;
import com.ged.entity.titresciel.Obligation;
import com.ged.entity.titresciel.Tcn;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OperationEvenementSurValeurMapper;
import com.ged.projection.OperationEvenementSurValeurProjection;
import com.ged.projection.PortefeuilleOpcvmProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationEvenementSurValeurService;
import com.ged.service.standard.impl.MailSenderServiceImpl;
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
import org.thymeleaf.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationEvenementSurValeurServiceImpl implements OperationEvenementSurValeurService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final FiltersSpecification<OperationEvenementSurValeur> EvenementSurValeurFiltersSpecification;
    private final OperationEvenementSurValeurDao operationEvenementSurValeurDao;
    private final OperationDetachementDao operationDetachementDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final TcnDao tcnDao;
    private final ObligationDao obligationDao;
    private final OperationEvenementSurValeurMapper operationEvenementSurValeurMapper;

    public OperationEvenementSurValeurServiceImpl(FiltersSpecification<OperationEvenementSurValeur> EvenementSurValeurFiltersSpecification, OperationEvenementSurValeurDao operationEvenementSurValeurDao,  OperationDetachementDao operationDetachementDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, TcnDao tcnDao, ObligationDao obligationDao, OperationEvenementSurValeurMapper operationEvenementSurValeurMapper){
        this.EvenementSurValeurFiltersSpecification = EvenementSurValeurFiltersSpecification;
        this.operationEvenementSurValeurDao = operationEvenementSurValeurDao;
        this.operationDetachementDao = operationDetachementDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.tcnDao = tcnDao;
        this.obligationDao = obligationDao;

        this.operationEvenementSurValeurMapper = operationEvenementSurValeurMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationEvenementSurValeurProjection> operationEvenementSurValeurPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                operationEvenementSurValeurPage = libraryDao.operationEvenementSurValeurListeRecherche(idOpcvm,parameters.getSearch().getValue(),pageable);
            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                operationEvenementSurValeurPage = libraryDao.operationEvenementSurValeurListe(idOpcvm,pageable);
            }

//            List<OperationEvenementSurValeurDto> content = operationEvenementSurValeurPage.getContent().stream().map(operationEvenementSurValeurMapper::deOperationEvenementSurValeurProjection).collect(Collectors.toList());
            List<OperationEvenementSurValeurProjection> content = operationEvenementSurValeurPage.getContent().stream().collect(Collectors.toList());
            DataTablesResponse<OperationEvenementSurValeurProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationEvenementSurValeurPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationEvenementSurValeurPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des évenements sur valeur par page datatable",
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm) {
        try {
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationEvenementSurValeurDto> operationEvenementSurValeurDtos = operationEvenementSurValeurDao.findBySupprimerAndOpcvm(false,opcvm).stream().map(operationEvenementSurValeurMapper::deOperationEvenementSurValeur).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des détachements ",
                    HttpStatus.OK,
                    operationEvenementSurValeurDtos);
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
    public OperationEvenementSurValeurProjection afficherSelonId(Long id) {
        return operationEvenementSurValeurDao.afficherSelonId(id);
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            System.out.println(id);
            OperationEvenementSurValeurProjection operationEvenementSurValeur=afficherSelonId(id);
            return ResponseHandler.generateResponse(
                    "Operation EvenementSurValeur dont ID = " + id,
                    HttpStatus.OK,
                    operationEvenementSurValeurMapper.deOperationEvenementSurValeurProjection(operationEvenementSurValeur));
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
    public ResponseEntity<Object> creer(OperationEvenementSurValeurDto operationEvenementSurValeurDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationEvenementSurValeur_IP_New]");

            q.registerStoredProcedureParameter("IdAvis", Long.class, ParameterMode.IN);
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
            q.registerStoredProcedureParameter("commission", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("irvm", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteAmortie", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("nominalRemb", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("capitalRembourse", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interetMoratoireSurCapital", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interetMoratoireSurInteret", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CommissionSurInteretMoratoire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantTotalARecevoir", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idDetachement", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referenceAvis", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            BigDecimal cump = libraryDao.cump(operationEvenementSurValeurDto.getOpcvm().getIdOpcvm(),
                    operationEvenementSurValeurDto.getTitre().getIdTitre(),
                    operationEvenementSurValeurDto.getDateOperation());
            BigDecimal plusV = BigDecimal.valueOf(0);
            BigDecimal moinsV = BigDecimal.valueOf(0);
            plusV =new BigDecimal(operationEvenementSurValeurDto.getCapitalRembourse().doubleValue()
                    -new BigDecimal(operationEvenementSurValeurDto.getCapitalRembourse().doubleValue()-
                    new BigDecimal(cump.doubleValue()*operationEvenementSurValeurDto.getQuantiteAmortie().doubleValue()).
                            setScale(0,RoundingMode.HALF_UP).doubleValue()).setScale(0,RoundingMode.HALF_UP).doubleValue());

            if (plusV.doubleValue() <0)
            {
                moinsV =BigDecimal.valueOf(-1 * plusV.doubleValue());
                plusV =BigDecimal.valueOf(0);
            }

            Titre titre=titreDao.findById(operationEvenementSurValeurDto.getTitre().getIdTitre()).orElseThrow();
            if (operationEvenementSurValeurDto.getTypeEvenement().trim().toUpperCase().equals("DIVIDENDE"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE DIVIDENDE SUR " + titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setValeurFormule
                        ("77:" + operationEvenementSurValeurDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";27:" + operationEvenementSurValeurDto.getIrvm().toString().replace(',','.')+
                        ";75:" + operationEvenementSurValeurDto.getMontantBrut().toString().replace(',', '.'));
                operationEvenementSurValeurDto.setCodeNatureOperation("ENCAIS_DIV");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATI"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON OBLIGATION COTEE:"
                        + titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setValeurFormule("31:" + new BigDecimal(operationEvenementSurValeurDto.getInteretMoratoireSurInteret().doubleValue() +
                        operationEvenementSurValeurDto.getInteretMoratoireSurCapital().doubleValue()).toString().replace(',', '.') +
                        ";27:" + operationEvenementSurValeurDto.getIrvm().toString().replace(',', '.') +
                        ";106:" + operationEvenementSurValeurDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";75:" + operationEvenementSurValeurDto.getMontantBrut().toString().replace(',', '.') +
                        ";92:" + operationEvenementSurValeurDto.getCapitalRembourse().toString().replace(',', '.'));
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_COUPON_OBLC");
            }

            else if ( titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATN"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON OBLIGATION NON COTEE:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setValeurFormule("31:" + new BigDecimal(operationEvenementSurValeurDto.getInteretMoratoireSurInteret().doubleValue() +
                        operationEvenementSurValeurDto.getInteretMoratoireSurCapital().doubleValue()).toString().replace(',', '.') +
                        ";27:" + operationEvenementSurValeurDto.getIrvm().toString().replace(',', '.') +
                        ";106:" + operationEvenementSurValeurDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";75:" + operationEvenementSurValeurDto.getMontantBrut().toString().replace(',', '.') +
                        ";92:" + operationEvenementSurValeurDto.getCapitalRembourse().toString().replace(',', '.'));
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_COUPON_OBLNC");
            }

            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BIT"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON SUR BILLET DE TRESORERIE:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setValeurFormule("31:" + new BigDecimal(operationEvenementSurValeurDto.getInteretMoratoireSurInteret().doubleValue() +
                        operationEvenementSurValeurDto.getInteretMoratoireSurCapital().doubleValue()).toString().replace(',', '.') +
                        ";27:" + operationEvenementSurValeurDto.getIrvm().toString().replace(',', '.') +
                        ";106:" + operationEvenementSurValeurDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";75:" + operationEvenementSurValeurDto.getMontantBrut().toString().replace(',', '.') +
                        ";92:" + operationEvenementSurValeurDto.getCapitalRembourse().toString().replace(',', '.'));
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_BDT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BOT"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT BON DE TRESOR:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setValeurFormule("21:" + new BigDecimal(operationEvenementSurValeurDto.getNominalRemb().doubleValue() *
                        operationEvenementSurValeurDto.getQuantiteAmortie().doubleValue()).
                        setScale(0,RoundingMode.HALF_UP).toString().replace(',', '.') +
                        ";76:" + new BigDecimal(operationEvenementSurValeurDto.getMontantBrut().doubleValue()
                        - operationEvenementSurValeurDto.getIrvm().doubleValue()).toString().replace(',', '.') +
                        ";68:" + new BigDecimal(operationEvenementSurValeurDto.getNominalRemb().doubleValue() *
                        operationEvenementSurValeurDto.getQuantiteAmortie().doubleValue()).
                        setScale(0,RoundingMode.HALF_UP).toString().replace(',', '.') +
                        ";27:" + operationEvenementSurValeurDto.getIrvm().toString().replace(',', '.') +
                        ";75:" + (operationEvenementSurValeurDto.getMontantBrut()).toString().replace(',', '.'));
               operationEvenementSurValeurDto.setCodeNatureOperation("ENC_BT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BEFI"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON SUR BEFI:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setValeurFormule("31:" + new BigDecimal(operationEvenementSurValeurDto.getInteretMoratoireSurInteret().doubleValue() +
                        operationEvenementSurValeurDto.getInteretMoratoireSurCapital().doubleValue()).toString().replace(',', '.') +
                        ";27:" + operationEvenementSurValeurDto.getIrvm().toString().replace(',', '.') +
                        ";106:" + operationEvenementSurValeurDto.getMontantTotalARecevoir().toString().replace(',', '.') +
                        ";75:" + operationEvenementSurValeurDto.getMontantBrut().toString().replace(',', '.') +
                        ";92:" + operationEvenementSurValeurDto.getCapitalRembourse().toString().replace(',', '.'));
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_BEFI");
            }

//            q.setParameter("idOperation", 0);
            q.setParameter("IdAvis",0);
            q.setParameter("IdTransaction", 0);
            q.setParameter("idSeance", operationEvenementSurValeurDto.getIdSeance());
            q.setParameter("codeNatureOperation", operationEvenementSurValeurDto.getCodeNatureOperation());
            q.setParameter("dateOperation", operationEvenementSurValeurDto.getDateOperation());
            q.setParameter("dateSaisie", operationEvenementSurValeurDto.getDateSaisie());
            q.setParameter("dateValeur", operationEvenementSurValeurDto.getDateValeur());
            q.setParameter("datePiece", operationEvenementSurValeurDto.getDatePiece());
            q.setParameter("referencePiece", operationEvenementSurValeurDto.getReferencePiece());
            q.setParameter("montant", operationEvenementSurValeurDto.getMontant());
            q.setParameter("ecriture", operationEvenementSurValeurDto.getEcriture());
            q.setParameter("libelleOperation", operationEvenementSurValeurDto.getLibelleOperation());
            q.setParameter("estOD", operationEvenementSurValeurDto.getEstOD());
            q.setParameter("type", operationEvenementSurValeurDto.getType());
            q.setParameter("typeEvenement", operationEvenementSurValeurDto.getTypeEvenement());
            q.setParameter("typePayement", operationEvenementSurValeurDto.getTypePayement());
            q.setParameter("IdIntervenant_New", operationEvenementSurValeurDto.getIntervenant().getIdPersonne());
            q.setParameter("idTitre", operationEvenementSurValeurDto.getTitre().getIdTitre());
            q.setParameter("qteDetenue", operationEvenementSurValeurDto.getQteDetenue());
            q.setParameter("couponDividendeUnitaire", operationEvenementSurValeurDto.getCouponDividendeUnitaire());
            q.setParameter("montantBrut", operationEvenementSurValeurDto.getMontantBrut());
            q.setParameter("commission", operationEvenementSurValeurDto.getCommission());
            q.setParameter("irvm", operationEvenementSurValeurDto.getIrvm());
            q.setParameter("quantiteAmortie", operationEvenementSurValeurDto.getQuantiteAmortie());
            q.setParameter("nominalRemb",operationEvenementSurValeurDto.getNominalRemb());
            q.setParameter("capitalRembourse", operationEvenementSurValeurDto.getCapitalRembourse());
            q.setParameter("interetMoratoireSurCapital",operationEvenementSurValeurDto.getInteretMoratoireSurCapital());
            q.setParameter("interetMoratoireSurInteret", operationEvenementSurValeurDto.getInteretMoratoireSurInteret());
            q.setParameter("CommissionSurInteretMoratoire", operationEvenementSurValeurDto.getCommissionSurInteretMoratoire());
            q.setParameter("montantTotalARecevoir", operationEvenementSurValeurDto.getMontantTotalARecevoir());
            q.setParameter("idOpcvm", operationEvenementSurValeurDto.getOpcvm().getIdOpcvm());
            q.setParameter("valeurFormule", operationEvenementSurValeurDto.getValeurFormule());
            q.setParameter("valeurCodeAnalytique", operationEvenementSurValeurDto.getValeurCodeAnalytique());
            q.setParameter("idDetachement", operationEvenementSurValeurDto.getOperationDetachement().getIdOperation());
            q.setParameter("referenceAvis", operationEvenementSurValeurDto.getReferenceAvis());
            q.setParameter("userLogin", operationEvenementSurValeurDto.getUserLogin());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
//                String result=(String) q.getOutputParameterValue("Sortie");
//                s=result.split("#");

                operationDetachementDao.modifier(operationEvenementSurValeurDto.getOperationDetachement().getIdOperation());
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
    public ResponseEntity<Object> creer(OperationEvenementSurValeurDto[] operationEvenementSurValeurDto) {
        try {
                if(operationEvenementSurValeurDto[0].getTypePayement().equals("CAPITAL + INTERET") ||
                        operationEvenementSurValeurDto[0].getTypePayement().equals("INTERET"))
                        annuler(operationEvenementSurValeurDto[0]);

                creer(operationEvenementSurValeurDto[1]);

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
    public ResponseEntity<Object> annuler(OperationEvenementSurValeurDto operationDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationAnnulation_IP_New]");

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
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);


            Titre titre=titreDao.findById(operationDto.getTitre().getIdTitre()).orElseThrow();
            String codeTypetitre=titre.getTypeTitre().getCodeTypeTitre().toUpperCase().trim();
            if (codeTypetitre.equals("ACTION"))
            {
                operationDto.setLibelleOperation("ANNULATION DETACHEMENT DE COUPON SUR DIVIDENDE:" +
                        titre.getSymbolTitre().trim());
                operationDto.setValeurFormule("76:" + operationDto.getMontantBrut().toString().replace(',', '.'));
               operationDto.setCodeNatureOperation("ANUL_DET_ANDIV");
            }
            else
            if (codeTypetitre.equals("OBLIGATI"))
            {
                operationDto.setLibelleOperation("ANNULATION DETACHEMENT DE COUPON SUR OBLIGATION COTEE:" +
                        titre.getSymbolTitre().trim());
                operationDto.setValeurFormule("28:" + operationDto.getMontantBrut().toString().replace(',', '.'));
                operationDto.setCodeNatureOperation("ANUL_DET_COC");
            }
            else
            if (codeTypetitre.equals("OBLIGATN"))
            {
                operationDto.setLibelleOperation("ANNULATION DETACHEMENT DE COUPON SUR OBLIGATION NON COTEE:" +
                        titre.getSymbolTitre().trim());
                operationDto.setValeurFormule("28:" + operationDto.getMontantBrut().toString().replace(',', '.'));
                operationDto.setCodeNatureOperation("ANUL_DET_COC");
            }
            else if (codeTypetitre.equals("BOT") )
            {
                operationDto.setLibelleOperation("ANNULATION DETACHEMENT DE COUPON SUR BON DE TRESOR:" +
                        titre.getSymbolTitre().trim());
                operationDto.setValeurFormule("30:" + operationDto.getMontantBrut().toString().replace(',', '.'));
                operationDto.setCodeNatureOperation("ANUL_DET_CBT");
            }
            if (codeTypetitre.equals("BIT"))
            {
                operationDto.setLibelleOperation("ANNULATION DETACHEMENT DE COUPON SUR BILLET DE TRESORERIE:" +
                        titre.getSymbolTitre().trim());
                operationDto.setValeurFormule("108:" + operationDto.getMontantBrut().toString().replace(',', '.'));
                operationDto.setCodeNatureOperation("ANUL_DET_CBDT");
            }
            else
            if (codeTypetitre.equals("BEFI"))
            {
                operationDto.setLibelleOperation("ANNULATION DETACHEMENT DE COUPON SUR BEFI:" +
                        titre.getSymbolTitre().trim());
                operationDto.setValeurFormule("108:" + operationDto.getMontantBrut().toString().replace(',', '.'));
                operationDto.setCodeNatureOperation("ANUL_DET_BEFI");
            }

//            q.setParameter("idOperation", 0);
            q.setParameter("IdTransaction", 0);
            q.setParameter("idSeance", operationDto.getIdSeance());
            q.setParameter("codeNatureOperation", operationDto.getCodeNatureOperation());
            q.setParameter("dateOperation", operationDto.getDateOperation());
            q.setParameter("dateSaisie",operationDto.getDateSaisie());
            q.setParameter("dateValeur", operationDto.getDateValeur());
            q.setParameter("datePiece", operationDto.getDatePiece());
            q.setParameter("referencePiece", operationDto.getReferenceAvis());
            q.setParameter("montant", operationDto.getMontantBrut());
            q.setParameter("ecriture","A");
            q.setParameter("libelleOperation", operationDto.getLibelleOperation());
            q.setParameter("estOD", false);
            q.setParameter("type", "DC");
            q.setParameter("idTitre", operationDto.getTitre().getIdTitre());
            q.setParameter("idOpcvm", operationDto.getOpcvm().getIdOpcvm());
            q.setParameter("valeurFormule", operationDto.getValeurFormule());
            q.setParameter("valeurCodeAnalytique", operationDto.getValeurCodeAnalytique());

            q.setParameter("userLogin", operationDto.getUserLogin());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
//                String result=(String) q.getOutputParameterValue("Sortie");
//                s=result.split("#");
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
    public ResponseEntity<Object> valeurOuQte(OperationEvenementSurValeurDto operationEvenementSurValeurDto) {
        try {
            OperationEvenementSurValeurDto operationEvenementSurValeurDtoResult=new OperationEvenementSurValeurDto();
            operationEvenementSurValeurDtoResult=operationEvenementSurValeurDto;
            Titre titre=titreDao.findById(operationEvenementSurValeurDto.getTitre().getIdTitre()).orElseThrow();
            if (titre != null)
            {
                LocalDateTime dateTime =LocalDateTime.now();
                Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                Date date = Date.from(i);
                if(operationEvenementSurValeurDto.getDateReelle()==null)
                {
                    dateTime=LocalDateTime.parse(operationEvenementSurValeurDto.getDateOperation().toString().substring(0,10)+"T00:00:00");
                    i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                    date = Date.from(i);
                }
                else
                {
                    dateTime=LocalDateTime.parse(operationEvenementSurValeurDto.getDateReelle().toString().substring(0,10)+"T00:00:00");
                    i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                    date = Date.from(i);
                }
                System.out.println("date="+date);
                operationEvenementSurValeurDtoResult.setNominalRemb(titre.getNominal());
                operationEvenementSurValeurDtoResult.setCouponDividendeUnitaire(libraryDao.interetCouru(operationEvenementSurValeurDto.getTitre().getIdTitre(),
                        date,false,operationEvenementSurValeurDto.getOpcvm().getIdOpcvm()));

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
//                            operationEvenementSurValeurDtoResult.setbNominalRemb(true);
//                            operationEvenementSurValeurDtoResult.setbQtiteAMORT(false);
                            operationEvenementSurValeurDtoResult.setQuantiteAmortie(libraryDao.qteAmortie(titre.getIdTitre(),
                                    operationEvenementSurValeurDto.getDateReelle(),operationEvenementSurValeurDto.getQteDetenue().longValue()));
//                            operationEvenementSurValeurDtoResult.setbMtantRemb(true);
//                            operationEvenementSurValeurDtoResult.setbMtantRecevoir(true);
//                            meb_MtantRemb.ReadOnly = true;
//                            meb_MtantRecevoir.ReadOnly = true;
                            break;
                        case "SUR VALEUR":
                            dateTime =operationEvenementSurValeurDto.getDateReelle();
                            i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                            date = Date.from(i);
                            operationEvenementSurValeurDtoResult.setQuantiteAmortie(BigDecimal.valueOf(0));
                            operationEvenementSurValeurDtoResult.setNominalRemb(libraryDao.nominalRembourse(titre.getIdTitre(),
                                    date));

                            dateTime=libraryDao.derniereEcheance(titre.getIdTitre());
                            i=dateTime.atZone(ZoneId.systemDefault()).toInstant();
                            Date derniereEcheance = Date.from(i);

                            i=operationEvenementSurValeurDto.getDateReelle().atZone(ZoneId.systemDefault()).toInstant();
                            Date dateEcheance = Date.from(i);
//                            operationEvenementSurValeurDtoResult.setbNominalRemb(false);
//                            operationEvenementSurValeurDtoResult.setbQtiteAMORT(true);
//                            operationEvenementSurValeurDtoResult.setbMtantRemb(false);
//                            operationEvenementSurValeurDtoResult.setbMtantRecevoir(false);

                            if (dateEcheance.equals(derniereEcheance))
                                operationEvenementSurValeurDtoResult.setQuantiteAmortie(operationEvenementSurValeurDto.getQteDetenue());
                            break;
                    }
                }

                BigDecimal capitalRemb=BigDecimal.valueOf(0);
                if (LibelleModeAmortissement.equals("SUR VALEUR"))
                {
                    capitalRemb=(new BigDecimal(operationEvenementSurValeurDtoResult.getQteDetenue().doubleValue()*
                            operationEvenementSurValeurDtoResult.getNominalRemb().doubleValue()));
                }
                else
                {
                    capitalRemb=(new BigDecimal(operationEvenementSurValeurDtoResult.getQuantiteAmortie().doubleValue()*
                            operationEvenementSurValeurDtoResult.getNominalRemb().doubleValue()));
                }

                BigDecimal couponDividendeTotal=BigDecimal.valueOf(0);
                if(operationEvenementSurValeurDtoResult.getQteDetenue()!=null && operationEvenementSurValeurDtoResult.getCouponDividendeUnitaire()!=null)
                    couponDividendeTotal=(new BigDecimal(operationEvenementSurValeurDtoResult.getQteDetenue().doubleValue()*
                        operationEvenementSurValeurDtoResult.getCouponDividendeUnitaire().doubleValue()).setScale(0, RoundingMode.HALF_UP));

                if(operationEvenementSurValeurDto.getTypePayement().equals("CAPITAL + INTERET")){
                    operationEvenementSurValeurDtoResult.setCapitalRembourse(capitalRemb);
//                    operationEvenementSurValeurDtoResult.setCouponDividendeTotal(couponDividendeTotal);
                }
                else
                    if(operationEvenementSurValeurDto.getTypePayement().equals("INTERET")){
                        operationEvenementSurValeurDtoResult.setNominalRemb(BigDecimal.valueOf(0));
                        operationEvenementSurValeurDtoResult.setCapitalRembourse(BigDecimal.valueOf(0));
//                        operationEvenementSurValeurDtoResult.setCouponDividendeTotal(couponDividendeTotal);
                    }
                    else
                        if(operationEvenementSurValeurDto.getTypePayement().equals("CAPITAL")) {
                            operationEvenementSurValeurDtoResult.setCouponDividendeUnitaire(BigDecimal.valueOf(0));
//                            operationEvenementSurValeurDtoResult.setCouponDividendeTotal(BigDecimal.valueOf(0));
                            operationEvenementSurValeurDtoResult.setCapitalRembourse(capitalRemb);
                        }
                        else
                        if(operationEvenementSurValeurDto.getTypePayement().equals("INTERET MORATOIRE"))
                        {
                            operationEvenementSurValeurDtoResult.setCapitalRembourse(capitalRemb);
                        }
//                operationEvenementSurValeurDtoResult.setMontantTotalARecevoir(new BigDecimal(operationEvenementSurValeurDtoResult.getCapitalRembourse().doubleValue()
//                +operationEvenementSurValeurDtoResult.getCouponDividendeTotal().doubleValue()));
//                        operationEvenementSurValeurDtoResult.setLibelleModeAmortissement(LibelleModeAmortissement);
            }
            return ResponseHandler.generateResponse(
                    "Valeur ou qté",
                    HttpStatus.OK,
                    operationEvenementSurValeurDtoResult);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OperationEvenementSurValeurDto operationEvenementSurValeurDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationEvenementSurValeur_UP_New]");

            q.registerStoredProcedureParameter("IdAvis", Long.class, ParameterMode.IN);
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
            q.registerStoredProcedureParameter("commission", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("irvm", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteAmortie", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("nominalRemb", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("capitalRembourse", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interetMoratoireSurCapital", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interetMoratoireSurInteret", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CommissionSurInteretMoratoire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantTotalARecevoir", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idDetachement", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referenceAvis", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);


            Titre titre=titreDao.findById(operationEvenementSurValeurDto.getTitre().getIdTitre()).orElseThrow();
            if (operationEvenementSurValeurDto.getTypeEvenement().trim().toUpperCase().equals("DIVIDENDE"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE DIVIDENDE SUR " + titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setCodeNatureOperation("ENCAIS_DIV");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATI"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON OBLIGATION COTEE:"
                        + titre.getSymbolTitre().trim());
               operationEvenementSurValeurDto.setCodeNatureOperation("ENC_COUPON_OBLC");
            }

            else if ( titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("OBLIGATN"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON OBLIGATION NON COTEE:" +
                        titre.getSymbolTitre().trim());
               operationEvenementSurValeurDto.setCodeNatureOperation("ENC_COUPON_OBLNC");
            }

            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BIT"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON SUR BILLET DE TRESORERIE:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_BDT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BOT"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT BON DE TRESOR:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_BT");
            }
            else if (titre.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("BEFI"))
            {
                operationEvenementSurValeurDto.setLibelleOperation("ENCAISSEMENT DE COUPON SUR BEFI:" +
                        titre.getSymbolTitre().trim());
                operationEvenementSurValeurDto.setCodeNatureOperation("ENC_BEFI");
            }

            q.setParameter("IdAvis", operationEvenementSurValeurDto.getIdOperation());
            q.setParameter("IdTransaction",0);
            q.setParameter("idSeance", operationEvenementSurValeurDto.getIdSeance());
            q.setParameter("codeNatureOperation", operationEvenementSurValeurDto.getCodeNatureOperation());
            q.setParameter("dateOperation", operationEvenementSurValeurDto.getDateOperation());
            q.setParameter("dateSaisie", operationEvenementSurValeurDto.getDateSaisie());
            q.setParameter("dateValeur",operationEvenementSurValeurDto.getDateValeur());
            q.setParameter("datePiece", operationEvenementSurValeurDto.getDatePiece());
            q.setParameter("referencePiece", operationEvenementSurValeurDto.getReferencePiece());
            q.setParameter("montant", operationEvenementSurValeurDto.getMontant());
            q.setParameter("ecriture", operationEvenementSurValeurDto.getEcriture());
            q.setParameter("libelleOperation", operationEvenementSurValeurDto.getLibelleOperation());
            q.setParameter("estOD", operationEvenementSurValeurDto.getEstOD());
            q.setParameter("type", operationEvenementSurValeurDto.getType());
            q.setParameter("typeEvenement", operationEvenementSurValeurDto.getTypeEvenement());
            q.setParameter("typePayement", operationEvenementSurValeurDto.getTypePayement());
            q.setParameter("IdIntervenant_New", operationEvenementSurValeurDto.getIntervenant().getIdPersonne());
            q.setParameter("idTitre",operationEvenementSurValeurDto.getTitre().getIdTitre());
            q.setParameter("qteDetenue",operationEvenementSurValeurDto.getQteDetenue());
            q.setParameter("couponDividendeUnitaire", operationEvenementSurValeurDto.getCouponDividendeUnitaire());
            q.setParameter("montantBrut", operationEvenementSurValeurDto.getMontantBrut());
            q.setParameter("commission", operationEvenementSurValeurDto.getCommission());
            q.setParameter("irvm", operationEvenementSurValeurDto.getIrvm());
            q.setParameter("quantiteAmortie", operationEvenementSurValeurDto.getQuantiteAmortie());
            q.setParameter("nominalRemb", operationEvenementSurValeurDto.getNominalRemb());
            q.setParameter("capitalRembourse", operationEvenementSurValeurDto.getCapitalRembourse());
            q.setParameter("interetMoratoireSurCapital", operationEvenementSurValeurDto.getInteretMoratoireSurCapital());
            q.setParameter("interetMoratoireSurInteret", operationEvenementSurValeurDto.getInteretMoratoireSurInteret());
            q.setParameter("CommissionSurInteretMoratoire", operationEvenementSurValeurDto.getCommissionSurInteretMoratoire());
            q.setParameter("montantTotalARecevoir", operationEvenementSurValeurDto.getMontantTotalARecevoir());
            q.setParameter("idOpcvm", operationEvenementSurValeurDto.getOpcvm().getIdOpcvm());
            q.setParameter("idDetachement", operationEvenementSurValeurDto.getOperationDetachement().getIdOperation());
            q.setParameter("referenceAvis", operationEvenementSurValeurDto.getReferenceAvis());
            q.setParameter("userLogin", operationEvenementSurValeurDto.getUserLogin());
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
                                            Long idAvis ) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationEvenementSurValeur_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idAvis",idAvis);
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
//            operationEvenementSurValeurDao.deleteById(id);
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
