package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.OperationConstatationChargeDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationConstatationChargeMapper;
import com.ged.projection.OperationConstatationChargeProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationConstatationChargeService;
import com.ged.validator.ObjectsValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperationConstatationChargeServiceImpl implements OperationConstatationChargeService {

    private final ObjectsValidator<ConstatationChargeAddRequest> requestAddValidator;
    private final OperationConstatationChargeDao constatationChargeDao;
    private final OperationConstatationChargeMapper constatationChargeMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final NatureOperationDao natureOperationDao;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final AppService appService;
    @PersistenceContext
    private EntityManager em;
    @Override
    public ResponseEntity<?> afficherTous(ConstatationChargeListeRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationConstatationCharge> operationConstatationChargePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationConstatationChargePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                operationConstatationChargePage = constatationChargeDao.afficherListe(
                    request.getIdOpcvm(),
                    pageable
                );
            }
            List<OperationConstatationChargeDto> content = operationConstatationChargePage.getContent().stream().map(constatationChargeMapper::deEntite).toList();
            DataTablesResponse<OperationConstatationChargeDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationConstatationChargePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationConstatationChargePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des constatations",
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
    public ResponseEntity<?> afficherConstationCharge(Long idOpcvm,Long idSeance) {
//        var parameters = request.getDatatableParameters();
        try {
//            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            List<OperationConstatationChargeProjection> operationConstatationChargePage;
//            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
//                operationConstatationChargePage = new PageImpl<>(new ArrayList<>());
//            }
//            else {
                SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeance(idOpcvm, idSeance);
                SeanceOpcvm seanceOpcvmEnCours=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
                Long idSeanceCourant=0L;
                Long idSeanceEnCours=0L;
                if(seanceOpcvm!=null)
                    idSeanceCourant=seanceOpcvm.getIdSeanceOpcvm().getIdSeance();

                if(seanceOpcvmEnCours!=null)
                    idSeanceEnCours=seanceOpcvmEnCours.getIdSeanceOpcvm().getIdSeance();

                if( idSeanceCourant.equals(idSeanceEnCours))
                    operationConstatationChargePage = constatationChargeDao.afficherListeOperation(
                            idOpcvm,
                            idSeance).stream().collect(Collectors.toList());
                else
                    operationConstatationChargePage = constatationChargeDao.afficherListeOperation(
                            idOpcvm
                    ).stream().collect(Collectors.toList());
//            }
//            List<OperationConstatationChargeDto> content = operationConstatationChargePage.getContent().stream().map(constatationChargeMapper::deEntite).toList();
//            DataTablesResponse<OperationConstatationChargeDto> dataTablesResponse = new DataTablesResponse<>();
//            dataTablesResponse.setDraw(parameters.getDraw());
//            dataTablesResponse.setRecordsFiltered((int)operationConstatationChargePage.getTotalElements());
//            dataTablesResponse.setRecordsTotal((int)operationConstatationChargePage.getTotalElements());
//            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des constatations",
                    HttpStatus.OK,
                    operationConstatationChargePage);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public OperationConstatationCharge afficherSelonId(Long id) {
        return constatationChargeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<?> creer(ConstatationChargeAddRequest request) {
        var violations = requestAddValidator.validate(request);
        if (!violations.isEmpty()) {
            return ResponseHandler.generateResponse(
                "Oops, le formulaire est mal rempli",
                HttpStatus.OK,
                String.join(" | ", violations)
            );
        }
//        OperationConstatationChargeDto opDto = new OperationConstatationChargeDto();
//        opDto.setIdTitre(0L);
//        opDto.setIdSeance(request.getIdSeance());
//        opDto.setIdActionnaire(0L);
//        opDto.setOpcvm(request.getOpcvm());
//        opDto.setType("PR");
//        opDto.setEcriture("A");
//        NatureOperationDto nature = natureOperationMapper.deNatureOperation(
//                natureOperationDao.findByCodeNatureOperationIgnoreCase(codeNatureOp).orElse(null)
//        );
//        opDto.setCodeCharge(request.getCharge().getIdCharge().getCodeCharge());
//        opDto.setNatureOperation(nature);
//        opDto.setLibelleOperation(libelleOp);
//        opDto.setMontant(request.getMontant());
//        opDto.setMontantCharge(request.getMontantCharge());
//        opDto.setValeurCodeAnalytique("OPC:" + request.getOpcvm().getIdOpcvm());
//        opDto.setValeurFormule("2:" + request.getMontant().toString() + ";5:" + request.getMontantCharge().toString());
//        opDto.setReferencePiece("");
//        opDto.setDateOperation(request.getDateOperation());
//        opDto.setDateSolde(request.getDateSolde());
//        opDto.setDatePiece(request.getDatePiece());
//        opDto.setDateSaisie(request.getDateSaisie());
//        opDto.setDateValeur(request.getDateValeur());
//        opDto.setDateDernModifClient(LocalDateTime.now());
//        opDto.setEstVerifie1(false);
//        opDto.setEstVerifie2(false);
//        opDto.setEstPayee(false);
//        opDto.setEstExtournee(false);
//        opDto.setEstOpExtournee(false);
//        opDto.setEstOD(false);
//        opDto = appService.genererEcritureComptable(opDto);
        try {
            String codeNatureOp = "";
            String libelleOp = "";
            switch (request.getCharge().getIdCharge().getCodeCharge().trim()) {
                case "FRAISDEPOSIT" -> {
                    codeNatureOp = "CONST_REM_DEPOSI";
                    libelleOp = "CONSTATATION COMMISSION DEPOSITAIRE";
                }
                case "FRAISCREPMF", "REVCREPMF" -> {
                    codeNatureOp = "CONST_RED_CREPMF";
                    libelleOp = "CONSTATATION REDEVANCE CREPMF";
                }
                case "FRAISCONSACTIF" -> {
                    codeNatureOp = "CONST_COM_CONSAC";
                    libelleOp = "CONSTATATION COMMISSION DE CONSERVATION SUR ACTIF";
                }
                case "HONOCAC" -> {
                    codeNatureOp = "CONST_HONCAC";
                    libelleOp = "CONSTATATION HONORAIRE CAC";
                }
                case "FRAISGEST" -> {
                    codeNatureOp = "CONST_REM_GEST";
                    libelleOp = "CONSTATATION REMUNERATION DU GESTIONNAIRE";
                }
                default -> {
                }
            }
            var q=em.createStoredProcedureQuery("[Operation].[PS_OperationConstatationCharge_IP_New]");
            String sortie="";
            q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSolde", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantCharge", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeCharge", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estPayee", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("idOperation",0);
            q.setParameter("idTransaction", 0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
            q.setParameter("codeNatureOperation", codeNatureOp);
            q.setParameter("dateSolde", request.getDateSolde());
            q.setParameter("dateOperation", request.getDateOperation());
            q.setParameter("libelleOperation", libelleOp);
            q.setParameter("dateSaisie", request.getDateSaisie());
            q.setParameter("datePiece", request.getDatePiece());
            q.setParameter("dateValeur",request.getDateValeur());
            q.setParameter("referencePiece","");
            q.setParameter("montant", request.getMontant());
            q.setParameter("montantCharge", request.getMontantCharge());
            q.setParameter("codeCharge", request.getCharge().getIdCharge().getCodeCharge());
            q.setParameter("estPayee", false);
            q.setParameter("valeurFormule", "2:" + request.getMontant().toString() + ";5:" + request.getMontantCharge().toString());
            q.setParameter("valeurCodeAnalytique", "OPC:" + request.getOpcvm().getIdOpcvm());
            q.setParameter("userLogin", request.getUserLogin());
            q.setParameter("dateDernModifClient", LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);

            try
            {
                q.execute();
            }
            catch (Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès",
                    HttpStatus.OK,
                    null
            );
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(
                e.getMessage(),
                HttpStatus.MULTI_STATUS,
                e
            );
        }
    }

    @Override
    public ResponseEntity<?> modifier(ConstatationChargeEditRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        return null;
    }
}
