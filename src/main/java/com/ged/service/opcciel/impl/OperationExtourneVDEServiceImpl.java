package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationExtourneVDEDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.ObligationDao;
import com.ged.dao.titresciel.TcnDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.OperationExtourneVDEDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ExtourneVDERequest;
import com.ged.entity.opcciel.CleOperationExtourneVDE;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationExtourneVDE;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.opcciel.OperationExtourneVDEMapper;
import com.ged.mapper.opcciel.SeanceOpcvmMapper;
import com.ged.projection.OperationExtourneVDEProjection;
import com.ged.projection.PortefeuilleOpcvmProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationExtourneVDEService;
import com.ged.service.standard.impl.MailSenderServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationExtourneVDEServiceImpl implements OperationExtourneVDEService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final FiltersSpecification<OperationExtourneVDE> ExtourneVDEFiltersSpecification;
    private final OperationExtourneVDEDao operationExtourneVDEDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final OpcvmMapper opcvmMapper;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final TcnDao tcnDao;
    private final ObligationDao obligationDao;
    private final TransactionDao transactionDao;
    private final MailSenderServiceImpl mailSenderService;
    private final NatureOperationDao natureOperationDao;
    private final OperationExtourneVDEMapper operationExtourneVDEMapper;

    public OperationExtourneVDEServiceImpl(FiltersSpecification<OperationExtourneVDE> ExtourneVDEFiltersSpecification, OperationExtourneVDEDao operationExtourneVDEDao, PersonneDao personneDao, OpcvmDao opcvmDao, OpcvmMapper opcvmMapper, SeanceOpcvmDao seanceOpcvmDao, SeanceOpcvmMapper seanceOpcvmMapper, LibraryDao libraryDao, TitreDao titreDao, TcnDao tcnDao, ObligationDao obligationDao, TransactionDao transactionDao, MailSenderServiceImpl mailSenderService, NatureOperationDao natureOperationDao, OperationExtourneVDEMapper operationExtourneVDEMapper){
        this.ExtourneVDEFiltersSpecification = ExtourneVDEFiltersSpecification;
        this.operationExtourneVDEDao = operationExtourneVDEDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.opcvmMapper = opcvmMapper;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.tcnDao = tcnDao;
        this.obligationDao = obligationDao;
        this.transactionDao = transactionDao;
        this.mailSenderService = mailSenderService;
        this.natureOperationDao = natureOperationDao;

        this.operationExtourneVDEMapper = operationExtourneVDEMapper;
    }
    @Override
    public ResponseEntity<Object> jaspertReportVDE(Long idSeance,Long idOpcvm,Boolean estVerifie,Boolean estVerifie1,Boolean estVerifie2,Long niveau, HttpServletResponse response) throws IOException, JRException {

        List<OperationExtourneVDEProjection> list=libraryDao.operationExtourneVDE(idSeance, idOpcvm, estVerifie, estVerifie1, estVerifie2);

        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        DateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateFormatee = date.format(formatter);
        String letterDate = dateFormatter.format(new Date());
        SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeance(idOpcvm,idSeance));
        parameters.put("letterDate", letterDate);
        parameters.put("niveau", niveau.toString());
        parameters.put("dateOuverture", seanceOpcvmDto.getDateOuverture().format(formatter).toString());
        parameters.put("dateFermeture", seanceOpcvmDto.getDateFermeture().format(formatter).toString());

        OpcvmDto opcvmDto=opcvmMapper.deOpcvm(opcvmDao.findById(idOpcvm).orElseThrow());
        parameters.put("VL", opcvmDto.getValeurLiquidativeActuelle().toString());
        parameters.put("designationOpcvm", opcvmDto.getDenominationOpcvm());

        File file = ResourceUtils.getFile("classpath:verificationVDEN1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Extourne VDE",
                HttpStatus.OK,
                list);
    }
 @Override
    public ResponseEntity<Object> creer(ExtourneVDERequest extourneVDERequest)  {

        List<OperationExtourneVDEProjection> list=libraryDao.operationExtourneVDE(extourneVDERequest.getIdSeance()
                , extourneVDERequest.getIdOpcvm(), extourneVDERequest.getEstVerifie(), extourneVDERequest.getEstVerifie1()
                , extourneVDERequest.getEstVerifie2());
       for(OperationExtourneVDEProjection o:list){
            operationExtourneVDEDao.modifier(LocalDateTime.now(), extourneVDERequest.getUserLogin1(),
                    true, extourneVDERequest.getIdSeance(),extourneVDERequest.getIdOpcvm(),o.getIdTitre());
       }
        return ResponseHandler.generateResponse(
                "Extourne VDE",
                HttpStatus.OK,
                null);
    }

    @Override
    public ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request) {
        try {
            DatatableParameters parameters=request.getDatatableParameters();
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationExtourneVDE> operationExtourneVDEPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationExtourneVDEPage = operationExtourneVDEDao.findByOpcvmAndSupprimer(opcvm,false,pageable);
//            } else {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                operationExtourneVDEPage = operationExtourneVDEDao.afficherListe(request.getIdOpcvm()
                        ,request.getIdSeance(),pageable);
//            }

//            List<OperationExtourneVDEDto> content = operationExtourneVDEPage.getContent().stream().map(operationExtourneVDEMapper::deOperationExtourneVDEProjection).collect(Collectors.toList());
            List<OperationExtourneVDEDto> content = operationExtourneVDEPage.getContent().stream().map(operationExtourneVDEMapper::deOperationExtourneVDE).collect(Collectors.toList());
            for(OperationExtourneVDEDto o:content){
                System.out.println(o.getValeurVDECours());
                System.out.println(o.getValeurVDEInteret());
            }
            DataTablesResponse<OperationExtourneVDEDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationExtourneVDEPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationExtourneVDEPage.getTotalElements());
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm) {
        try {
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationExtourneVDEDto> operationExtourneVDEDtos =
                    operationExtourneVDEDao.findByOpcvmAndSupprimer(opcvm,false).stream().map(operationExtourneVDEMapper::deOperationExtourneVDE).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                        "Liste des VDE ",
                    HttpStatus.OK,
                    operationExtourneVDEDtos);
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
    public OperationExtourneVDE afficherSelonId(CleOperationExtourneVDE id) {
        return operationExtourneVDEDao.findById(id).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleOperationExtourneVDE id) {
        try {
//            System.out.println(id);
            OperationExtourneVDE operationExtourneVDE=afficherSelonId(id);
            return ResponseHandler.generateResponse(
                    "Operation ExtourneVDE dont ID = " + id,
                    HttpStatus.OK,
                    operationExtourneVDEMapper.deOperationExtourneVDE(operationExtourneVDE));
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
    public ResponseEntity<Object> creer(OperationExtourneVDEDto operationExtourneVDEDto) {
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
            q.setParameter("idOpcvm", operationExtourneVDEDto.getOpcvm().getIdOpcvm());
            q.setParameter("idSeance", operationExtourneVDEDto.getIdSeance());
            q.setParameter("dateValeur", operationExtourneVDEDto.getDateValeur());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("userLogin", operationExtourneVDEDto.getUserLogin());
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
    public ResponseEntity<Object> modifier(OperationExtourneVDEDto operationExtourneVDEDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationExtourneVDE_UP_New]");

            q.registerStoredProcedureParameter("IdExtourneVDE", Long.class, ParameterMode.IN);
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
                                            Long idExtourneVDE ) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[EvenementSurValeur].[PS_OperationExtourneVDE_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idExtourneVDE", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idExtourneVDE",idExtourneVDE);
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
//            operationExtourneVDEDao.deleteById(id);
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
