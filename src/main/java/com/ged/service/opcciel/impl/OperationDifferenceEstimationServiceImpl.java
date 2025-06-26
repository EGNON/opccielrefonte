package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationDifferenceEstimationDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.ObligationDao;
import com.ged.dao.titresciel.TcnDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.CleOperationDifferenceEstimation;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationDifferenceEstimation;
import com.ged.mapper.opcciel.OperationDifferenceEstimationMapper;
import com.ged.projection.PortefeuilleOpcvmProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationDifferenceEstimationService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationDifferenceEstimationServiceImpl implements OperationDifferenceEstimationService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final FiltersSpecification<OperationDifferenceEstimation> DifferenceEstimationFiltersSpecification;
    private final OperationDifferenceEstimationDao operationDifferenceEstimationDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final TcnDao tcnDao;
    private final ObligationDao obligationDao;
    private final TransactionDao transactionDao;
    private final MailSenderServiceImpl mailSenderService;
    private final NatureOperationDao natureOperationDao;
    private final OperationDifferenceEstimationMapper operationDifferenceEstimationMapper;

    public OperationDifferenceEstimationServiceImpl(FiltersSpecification<OperationDifferenceEstimation> DifferenceEstimationFiltersSpecification, OperationDifferenceEstimationDao operationDifferenceEstimationDao, PersonneDao personneDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, TcnDao tcnDao, ObligationDao obligationDao, TransactionDao transactionDao, MailSenderServiceImpl mailSenderService, NatureOperationDao natureOperationDao, OperationDifferenceEstimationMapper operationDifferenceEstimationMapper){
        this.DifferenceEstimationFiltersSpecification = DifferenceEstimationFiltersSpecification;
        this.operationDifferenceEstimationDao = operationDifferenceEstimationDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.tcnDao = tcnDao;
        this.obligationDao = obligationDao;
        this.transactionDao = transactionDao;
        this.mailSenderService = mailSenderService;
        this.natureOperationDao = natureOperationDao;

        this.operationDifferenceEstimationMapper = operationDifferenceEstimationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request) {
        try {
            DatatableParameters parameters=request.getDatatableParameters();
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationDifferenceEstimation> operationDifferenceEstimationPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationDifferenceEstimationPage = operationDifferenceEstimationDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                operationDifferenceEstimationPage = operationDifferenceEstimationDao.afficherListe(request.getIdOpcvm()
                        ,request.getIdSeance(),pageable);
//            }

//            List<OperationDifferenceEstimationDto> content = operationDifferenceEstimationPage.getContent().stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimationProjection).collect(Collectors.toList());
            List<OperationDifferenceEstimationDto> content = operationDifferenceEstimationPage.getContent().stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimation).collect(Collectors.toList());
            DataTablesResponse<OperationDifferenceEstimationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationDifferenceEstimationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationDifferenceEstimationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des VDE par page datatable",
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm,Long idSeance) {
        try {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
//            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationDifferenceEstimationDto> operationDifferenceEstimationDtos =
                    operationDifferenceEstimationDao.afficherListe(idOpcvm,idSeance).stream().map(operationDifferenceEstimationMapper::deOperationDifferenceEstimation).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                        "Liste des VDE ",
                    HttpStatus.OK,
                    operationDifferenceEstimationDtos);
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
    public OperationDifferenceEstimation afficherSelonId(CleOperationDifferenceEstimation id) {
        return operationDifferenceEstimationDao.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleOperationDifferenceEstimation id) {
        try {
//            System.out.println(id);
            OperationDifferenceEstimation operationDifferenceEstimation=afficherSelonId(id);
            return ResponseHandler.generateResponse(
                    "Operation DifferenceEstimation dont ID = " + id,
                    HttpStatus.OK,
                    operationDifferenceEstimationMapper.deOperationDifferenceEstimation(operationDifferenceEstimation));
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
    public ResponseEntity<Object> creer(OperationDifferenceEstimationDto operationDifferenceEstimationDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[Comptabilite].[PS_ExtournerDifferenceEstimation]");

            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("sortie", String.class, ParameterMode.OUT);


//            q.setParameter("idOperation", 0);
            q.setParameter("idOpcvm", operationDifferenceEstimationDto.getOpcvm().getIdOpcvm());
            q.setParameter("idSeance", operationDifferenceEstimationDto.getIdSeance());
            q.setParameter("dateValeur", operationDifferenceEstimationDto.getDateValeur());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("userLogin", operationDifferenceEstimationDto.getUserLogin());
            q.setParameter("codeLangue", "fr-FR");
            q.setParameter("sortie",sortie);
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
    public ResponseEntity<Object> modifier(OperationDifferenceEstimationDto operationDifferenceEstimationDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDifferenceEstimation_UP_New]");

            q.registerStoredProcedureParameter("IdDifferenceEstimation", Long.class, ParameterMode.IN);
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

            return ResponseHandler.generateResponse(
                    "e.getMessage()",
                    HttpStatus.MULTI_STATUS,
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
                                            Long idDifferenceEstimation ) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationDifferenceEstimation_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idDifferenceEstimation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idDifferenceEstimation",idDifferenceEstimation);
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
//            operationDifferenceEstimationDao.deleteById(id);
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
