package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationDifferenceEstimationDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.*;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.EcritureManuelDto;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.dto.request.OperationRequest;
import com.ged.dto.request.VerificationEcritureRequest;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.opcciel.OperationDifferenceEstimationMapper;
import com.ged.mapper.opcciel.OperationMapper;
import com.ged.mapper.opcciel.SeanceOpcvmMapper;
import com.ged.projection.*;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationComptableServiceImpl implements OperationService {
    private final OperationDao operationDao;
    private final OperationDifferenceEstimationDao operationDifferenceEstimationDao;
    private final OperationDifferenceEstimationMapper operationDifferenceEstimationMapper;
    private final OperationMapper operationMapper;
    private final LibraryDao libraryDao;
    private final ExerciceDao exerciceDao;
    private final IbDao ibDao;
    private final DetailModeleDao detailModeleDao;
    private final CorrespondanceDao correspondanceDao;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final OpcvmDao opcvmDao;
    private final OpcvmMapper opcvmMapper;
    @PersistenceContext
    EntityManager em;

    public OperationComptableServiceImpl(OperationDao operationDao, OperationDifferenceEstimationDao operationDifferenceEstimationDao, OperationDifferenceEstimationMapper operationDifferenceEstimationMapper, OperationMapper operationMapper, LibraryDao libraryDao, ExerciceDao exerciceDao, IbDao ibDao, DetailModeleDao detailModeleDao, CorrespondanceDao correspondanceDao, SeanceOpcvmDao seanceOpcvmDao, SeanceOpcvmMapper seanceOpcvmMapper, OpcvmDao opcvmDao, OpcvmMapper opcvmMapper) {
        this.operationDao = operationDao;
        this.operationDifferenceEstimationDao = operationDifferenceEstimationDao;
        this.operationDifferenceEstimationMapper = operationDifferenceEstimationMapper;
        this.operationMapper = operationMapper;
        this.libraryDao = libraryDao;
        this.exerciceDao = exerciceDao;
        this.ibDao = ibDao;
        this.detailModeleDao = detailModeleDao;
        this.correspondanceDao = correspondanceDao;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.opcvmDao = opcvmDao;
        this.opcvmMapper = opcvmMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(ConsultationEcritureRequest request) {
        try {
            DatatableParameters parameters = request.getDatatableParameters();
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ConsultationEcritureProjection> operationPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                System.out.println("datefin="+request.getDateFin());
                System.out.println("datedebut="+request.getDateDebut());
                operationPage = libraryDao.listeOperationsFiltree(
                    request.getIdOpcvm() == 0L ? null : request.getIdOpcvm(),
                    request.getIdOperation() == 0L ? null : request.getIdOperation(),
                    request.getIdTransaction() == 0L ? null :  request.getIdTransaction(),
                    request.getNatureOperation() != null ? request.getNatureOperation().getCodeNatureOperation().trim() : null,
                    request.getDateDeb(),
                   request.getDateFn(),
                    pageable
                );

//                operationPage = operationDao.listeOperationsFiltree(
//                    request.getIdOpcvm() == 0L ? null : request.getIdOpcvm(),
//                    request.getIdOperation() == 0L ? null : request.getIdOperation(),
//                    request.getIdTransaction() == 0L ? null :  request.getIdTransaction(),
//                    request.getNatureOperation() != null ? request.getNatureOperation().getCodeNatureOperation().trim() : null,
//                    pageable
//                );
            }
            List<ConsultationEcritureProjection> content = operationPage.getContent().stream().toList();
            DataTablesResponse<ConsultationEcritureProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations",
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

    @Override
    public ResponseEntity<Object> afficherPaiementCommissionInvestissement(ConsultationEcritureRequest request) {
        try {
            DatatableParameters parameters = request.getDatatableParameters();
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Operation> operationPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationPage = new PageImpl<>(new ArrayList<>());
            }
            else {

                operationPage = operationDao.afficherPaiementCommissionInvestissement(
                        request.getIdOpcvm() == 0L ? null : request.getIdOpcvm(),
                        pageable
                );
            }
            List<OperationDto> content = operationPage.getContent().stream().map(operationMapper::deOperation).toList();
            DataTablesResponse<OperationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations",
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

    @Override
    public ResponseEntity<Object> creer(OperationRequest request) {
        try {
            String sortie="";
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
            q.registerStoredProcedureParameter("estOD",  Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type",  String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule",  String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique",  String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("IdOperation", 0);
            q.setParameter("idOpcvm",request.getOpcvm().getIdOpcvm());
            q.setParameter("idActionnaire",0);
            q.setParameter("idTitre", 0);
            q.setParameter("IdTransaction", 0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("codeNatureOperation", "RETROCOMINVEST");
            q.setParameter("dateOperation",request.getDateOperation());
            q.setParameter("libelleOperation", request.getLibelleOperation());
            q.setParameter("dateSaisie", LocalDateTime.now());
            q.setParameter("datePiece", request.getDatePiece());
            q.setParameter("dateValeur", request.getDateOperation());
            q.setParameter("referencePiece", "");
            q.setParameter("montant", request.getMontant());
            q.setParameter("ecriture", "");
            q.setParameter("estOD", false);
            q.setParameter("type", "COM");
            q.setParameter("valeurFormule", "2:" + request.getMontant().toString().replace(',', '.'));
            q.setParameter("valeurCodeAnalytique",  "OPC:" + request.getOpcvm().getIdOpcvm().toString());

            q.setParameter("userLogin", request.getUserLogin());
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
    public String verifierEtape(Long niveau,Long idOpcvm){

            String etape = "";
            SeanceOpcvm lstSO = seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
            if (lstSO != null)
            {
                if(lstSO.getIdSeanceOpcvm().getIdSeance()==0)
                {
                    String[] tab = new String[7];
                    tab[0] = "CONSTITUTION DU CAPITAL";
                    tab[1] = "VERIFICATION DE LA CONSTITUTION DU CAPITAL";
                    tab[2] = "VERIFICATION NIVEAU 1 (CC)";
                    tab[3] = "VERIFICATION NIVEAU 2 (CC)";
                    tab[4] = "VALORISATION DES POSTES COMPTABLES";
                    tab[5] = "VERIFICATION NIVEAU 1 (PC)";
                    tab[6] = "VERIFICATION NIVEAU 2 (PC)";

                    if(lstSO.getNiveau()<niveau)
                    {
                        for (int i = lstSO.getNiveau().intValue(); i < niveau-1; i++)
                        {
                            etape += tab[i] + "\r\n";
                        }
                    }

                }

                else
                {
                    String[] tab = new String[13];
                    tab[0] = "GENERATION DIFFERENCES D'ESTIMATION";
                    tab[1] = "VERIFICATION NIVEAU 1 (DE)";
                    tab[2] = "VERIFICATION NIVEAU 2 (DE)";
                    tab[3] = "VERIFICATION NIVEAU 1 JEUX D'ECRITURE(DE)";
                    tab[4] = "VERIFICATION NIVEAU 2 JEUX D'ECRITURE(DE)";
                    tab[5] = "AMORTISSEMENT DES CHARGES";
                    tab[6] = "VERIFICATION NIVEAU 1 (CHARGES)";
                    tab[7] = "VERIFICATION NIVEAU 2 (CHARGES)";
                    tab[8] = "VERIFICATION NIVEAU 1 JEUX D'ECRITURE(CHARGES)";
                    tab[9] = "VERIFICATION NIVEAU 2 JEUX D'ECRITURE(CHARGES)";
                    tab[10] = "VALORISATION DES POSTES COMPTABLES";
                    tab[11] = "VERIFICATION NIVEAU 1 (PC)";
                    tab[12] = "VERIFICATION NIVEAU 2 (PC)";


                    if (lstSO.getNiveau() < niveau)
                    {
                        for (int i = lstSO.getNiveau().intValue(); i < niveau-1 ; i++)
                        {
                            etape += tab[i] + "\n";
                        }
                    }
                }

            }

            return etape;


    }
    @Override
        public ResponseEntity<Object> apercuVerificationDE1(Long idOpcvm,Long idSeance,Boolean estVerifie1,Boolean estVerifie2,Long niv, HttpServletResponse response) throws IOException, JRException {
            List<OperationDifferenceEstimationProjection> list=libraryDao.operationDifferenceEstimation(
                    idSeance,idOpcvm,estVerifie1,estVerifie2,false);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        DateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateFormatee = date.format(formatter);
        String letterDate = dateFormatter.format(new Date());
        SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeance(idOpcvm,idSeance));
        parameters.put("letterDate", letterDate);
        parameters.put("niveau", niv.toString());
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
                    "Ordre de bourse",
                    HttpStatus.OK,
                    list);

        }

    @Override
    public ResponseEntity<Object> creerTout(OperationRequest request) {
        try {
            String sortie="";
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
            q.registerStoredProcedureParameter("estOD",  Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type",  String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule",  String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique",  String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("IdOperation", 0);
            q.setParameter("idOpcvm",request.getOpcvm().getIdOpcvm());
            q.setParameter("idActionnaire",request.getIdActionnaire());
            q.setParameter("idTitre", request.getIdTitre());
            q.setParameter("IdTransaction", 0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("codeNatureOperation", request.getCodeNatureOperation());
            q.setParameter("dateOperation",request.getDateOperation());
            q.setParameter("libelleOperation", request.getLibelleOperation());
            q.setParameter("dateSaisie", LocalDateTime.now());
            q.setParameter("datePiece", request.getDatePiece());
            q.setParameter("dateValeur", request.getDateOperation());
            q.setParameter("referencePiece", request.getReferencePiece());
            q.setParameter("montant", request.getMontant());
            q.setParameter("ecriture", "");
            q.setParameter("estOD", false);
            q.setParameter("type", request.getType());
            q.setParameter("valeurFormule", request.getValeurFormule());
            q.setParameter("valeurCodeAnalytique",  request.getValeurCodeAnalytique());

            q.setParameter("userLogin", request.getUserLogin());
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
    public ResponseEntity<Object> actionnaireBanque(Long idOpcvm,String code) {
        try {
            String sortie="";
            List<DetailModeleProjection> list = detailModeleDao.listeParProjection(code);
            EcritureManuelDto ecritureManuelDto=new EcritureManuelDto();
            ecritureManuelDto.setActionnaire(false);
            List<CorrespondanceProjection> correspondanceProjections=new ArrayList<>();
            ExerciceProjection exercice=exerciceDao.exerciceEnCours(idOpcvm);

            for(DetailModeleProjection o:list){
                correspondanceProjections=correspondanceDao.listeSelonCompteComptableEtPlan(exercice.getPlan().getCodePlan(),o.getNumCompteComptable());
                if (correspondanceProjections.size() != 0)
                {
                    for(CorrespondanceProjection cr:correspondanceProjections){
                        Ib ib=ibDao.findById(cr.getIb().getCodeIB()).orElseThrow();
//                        lstIb = PO.Ib.Liste(lstCorrespondance[0].CodeIb.Trim(), null, null, null, null, null, null, null, null, false, null);
                        if (ib!= null)
                        {
                            if (ib.getTypeIb().getCodeTypeIb().equals("ACTIONNAIRE")) {
                                ecritureManuelDto.setActionnaire(true);
                                ecritureManuelDto.setBanque(false);
                            }
                            if (ib.getTypeIb().getCodeTypeIb().equals("BANQUE") ||
                                    ib.getTypeIb().getCodeTypeIb().equals("OPCVM") ) {

                                ecritureManuelDto.setActionnaire(false);
                                ecritureManuelDto.setBanque(true);
                            }
                        }
                    }

                }
            }

            return ResponseHandler.generateResponse(
                    "actionnaire,banque !",
                    HttpStatus.OK,
                    ecritureManuelDto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> listeVerificationEcriturePage(VerificationEcritureRequest request) {
        try {
            DatatableParameters parameters = request.getDatatableParameters();
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ListeVerificationEcritureProjection> operationPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                System.out.println("datefin="+LocalDateTime.parse(request.getDateFin().toString().substring(0,10)+"T23:59:59"));
                System.out.println("datedebut="+LocalDateTime.parse(request.getDateDebut().toString().substring(0,10)+"T00:00:00"));
//                operationPage = operationDao.listeOperationsFiltree(
//

                operationPage = libraryDao.listeVerificationEcriture(
                        request.getIdOpcvm() == 0L ? null : request.getIdOpcvm(),
                        request.getIdSeance() == 0L ? null : request.getIdSeance(),
                        request.getTypeOperationDto() != null ? request.getTypeOperationDto().getCodeTypeOperation().trim() : null,
                        request.getDateDebut(),
                        request.getDateFin(),
                        request.getEstVerifie1(),
                        request.getEstVerifie2(),
                        pageable
                );
            }
            List<ListeVerificationEcritureProjection> content = operationPage.getContent().stream().toList();
            DataTablesResponse<ListeVerificationEcritureProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations",
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
    @Override
    public ResponseEntity<Object> listeVerificationEcritureListe(VerificationEcritureRequest request) {
        try {

            List<ListeVerificationEcritureProjection> operationListe;

            operationListe = libraryDao.listeVerificationEcriture(
                        request.getIdOpcvm() == 0L ? null : request.getIdOpcvm(),
                        request.getIdSeance() == 0L ? null : request.getIdSeance(),
                        request.getTypeOperationDto() != null ? request.getTypeOperationDto().getCodeTypeOperation().trim() : null,
                        request.getDateDebut(),
                        request.getDateFin(),
                        request.getEstVerifie1(),
                        request.getEstVerifie2()
                );


            return ResponseHandler.generateResponse(
                    "Liste des opérations",
                    HttpStatus.OK,
                    operationListe);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> validerVerificationEcritureN1(Long[] list,
                                                                String userLogin,
                                                                String codeTypeOperation,
                                                                String form,
                                                                Long idOpcvm) {
        try {

            for(int i=0;i<list.length;i++){
                operationDao.modifier(userLogin,"",true,false,LocalDateTime.now(),
                        LocalDateTime.parse("2050-12-31T00:00"),list[i]);
            }
            if (codeTypeOperation != "null")
            {
                //mise à jour de l'étape de la séance

                switch (codeTypeOperation)
                {
                    case "DE":
                        if (!form.equals("Extourne"))
                        {
                            SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                            if(seanceOpcvm!=null){
                                seanceOpcvmDao.modifier(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),4L);
                            }
                        }
                        break;
                    case "CHARGE":
                        SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                        if(seanceOpcvm!=null){
                            seanceOpcvmDao.modifier(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),9L);
                        }
                        break;
                }

            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès",
                    HttpStatus.OK,
                    null);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> validerVerificationEcritureN2(Long[] list,
                                                                String userLogin,
                                                                String codeTypeOperation,
                                                                String form,
                                                                Long idOpcvm) {
        try {

            for(int i=0;i<list.length;i++){
//                Operation operation=operationDao.findById(list[i]).orElseThrow();
                //if(operation!=null)
                    operationDao.modifier(
                            userLogin,true,
                            LocalDateTime.now(),list[i]);
            }
            if (codeTypeOperation != "null")
            {
                //mise à jour de l'étape de la séance

                switch (codeTypeOperation)
                {
                    case "DE":
                        if (!form.equals("Extourne"))
                        {
                            SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                            if(seanceOpcvm!=null){
                                seanceOpcvmDao.modifier(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),5L);
                            }
                        }
                        break;
                    case "CHARGE":
                        SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                        if(seanceOpcvm!=null){
                            seanceOpcvmDao.modifier(idOpcvm,seanceOpcvm.getIdSeanceOpcvm().getIdSeance(),10L);
                        }
                        break;
                }

            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès",
                    HttpStatus.OK,
                    null);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> verificationEcritureNiveau1(VerificationEcritureRequest verificationEcritureRequest,String codeTypeOperation) {
        try {
            String operation= libraryDao.verifOperationDesequilibre(verificationEcritureRequest.getIdOpcvm(), null,null);
            if(operation!="" && operation!=null){
                return ResponseHandler.generateResponse(
            "Opérations déséquilibrées",
                    HttpStatus.OK,
                    "Les opérations d'id " + operation.trim() + " sont déséquilibrées");
            }
            else {
                if (libraryDao.listeVerificationEcriture(verificationEcritureRequest.getIdOpcvm(),
                        verificationEcritureRequest.getCodeTypeOperation() == null ? null : codeTypeOperation, verificationEcritureRequest.getDateDebut()
                        , verificationEcritureRequest.getDateFin(), false, false, null).size() == 0) {
                    return ResponseHandler.generateResponse(
                            "Opérations déséquilibrées",
                            HttpStatus.OK,
                            "Aucune vérification Niveau 1 en attente");
                }
                else
                {
                    return ResponseHandler.generateResponse(
                            "Opérations déséquilibrées",
                            HttpStatus.OK,
                            "Cliquez sur aperçu pour visualiser avant de passer à la confirmation des écritures niveau 1");
                }

            }

        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> verificationEcritureNiveau2(VerificationEcritureRequest verificationEcritureRequest, String codeTypeOperation) {
        try {
            String operation= libraryDao.verifOperationDesequilibre(verificationEcritureRequest.getIdOpcvm(), null,null);
            if(operation!="" && operation!=null){
                return ResponseHandler.generateResponse(
                        "Opérations déséquilibrées",
                        HttpStatus.OK,
                        "Les opérations d'id " + operation.trim() + " sont déséquilibrées");
            }
            else {
                if (libraryDao.listeVerificationEcriture(verificationEcritureRequest.getIdOpcvm(),
                        verificationEcritureRequest.getCodeTypeOperation() == null ? null : codeTypeOperation, verificationEcritureRequest.getDateDebut()
                        , verificationEcritureRequest.getDateFin(), true, false, null).size() == 0) {
                    return ResponseHandler.generateResponse(
                            "Opérations déséquilibrées",
                            HttpStatus.OK,
                            "Aucune vérification Niveau 2 en attente");
                } else {
                    if (libraryDao.listeVerificationEcriture(verificationEcritureRequest.getIdOpcvm(),
                            verificationEcritureRequest.getCodeTypeOperation() == null ? null : codeTypeOperation, verificationEcritureRequest.getDateDebut()
                            , verificationEcritureRequest.getDateFin(), false, false, null).size() != 0) {
                        return ResponseHandler.generateResponse(
                                "Opérations déséquilibrées",
                                HttpStatus.OK,
                                "Veuillez d'abord effectuer la vérification Niveau 1.");
                    } else {
                        return ResponseHandler.generateResponse(
                                "Opérations déséquilibrées",
                                HttpStatus.OK,
                                "Cliquez sur aperçu pour visualiser avant de passer à la confirmation des écritures niveau 2");
                    }

                }
            }

        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> verificationeEcritureNiveauPrint(VerificationEcritureRequest verificationEcritureRequest,
                                                                   HttpServletResponse response,
                                                                   String niveau,
                                                                   String codeTypeOperation) throws IOException, JRException {
        List<ListeVerificationEcritureProjection> list=libraryDao.listeVerificationEcriture(verificationEcritureRequest.getIdOpcvm(),
                verificationEcritureRequest.getCodeTypeOperation() == null ? null : codeTypeOperation, verificationEcritureRequest.getDateDebut()
                , verificationEcritureRequest.getDateFin(), verificationEcritureRequest.getEstVerifie1(),
                verificationEcritureRequest.getEstVerifie2(), null);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        parameters.put("niveau", niveau);
//        File file = ResourceUtils.getFile("classpath:verificationEcritureComptable.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        InputStream inputStream = getClass().getResourceAsStream("/verificationEcritureComptable.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Verification ecriture",
                HttpStatus.OK,
                list);
    }
}
