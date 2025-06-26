package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.OperationCommissionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationCommissionDto;
import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.request.CommissionAddRequest;
import com.ged.dto.request.CommissionEditRequest;
import com.ged.dto.request.CommissionListeRequest;
import com.ged.entity.opcciel.OperationCommission;
import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationCommissionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationCommissionService;
import com.ged.validator.ObjectsValidator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationCommissionServiceImpl implements OperationCommissionService {

    private final ObjectsValidator<CommissionAddRequest> requestAddValidator;
    private final NatureOperationMapper natureOperationMapper;
    private final NatureOperationDao natureOperationDao;
    private final OperationCommissionMapper commissionMapper;
    private final OperationCommissionDao commissionDao;
    private final AppService appService;
    @PersistenceContext
    EntityManager em;

    @Override
    public ResponseEntity<?> afficherTous(CommissionListeRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationCommission> operationCommissionPage = new PageImpl<>(new ArrayList<>());
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {

            }
            else {
                operationCommissionPage = commissionDao.afficherListe(
                    request.getIdOpcvm(),
                    pageable
                );
            }

            List<OperationCommissionDto> content = operationCommissionPage.getContent().stream().map(commissionMapper::deEntite).toList();
            DataTablesResponse<OperationCommissionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationCommissionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationCommissionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des paiements",
                    HttpStatus.OK,
                    dataTablesResponse
            );
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
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> creer(CommissionAddRequest request) {
        var violations = requestAddValidator.validate(request);
        if (!violations.isEmpty()) {
            return ResponseHandler.generateResponse(
                    "Oops, le formulaire est mal rempli",
                    HttpStatus.OK,
                    String.join(" | ", violations)
            );
        }
        try {
            String codeNatureOp = "";
            String libelleOp = "";
            switch (request.getTypeCommission().trim()) {
                case "RACHAT" -> {
                    codeNatureOp = "PAICOMRACH";
                    libelleOp = "PAIEMENT DE COMMISSION DE RACHAT";
                }
                case "SOUSCRIPTION" -> {
                    codeNatureOp = "PAICOMSOUS";
                    libelleOp = "PAIEMENT DE COMMISSION DE SOUSCRIPTION";
                }
                case "TAFA" -> {
                    codeNatureOp = "PAITAFA";
                    libelleOp = "PAIEMENT DE LA TAFA";
                }
                default -> {
                    return null;
                }
            }
            var q=em.createStoredProcedureQuery("[Operation].[PS_OperationCommission_IP_New]");
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
            q.registerStoredProcedureParameter("montantCommission", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("typeCommission", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            q.setParameter("idOperation",0);
            q.setParameter("idTransaction", 0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("idOpcvm",request.getOpcvm().getIdOpcvm());
            q.setParameter("codeNatureOperation", codeNatureOp);
            q.setParameter("dateSolde", request.getDateSolde());
            q.setParameter("dateOperation", request.getDateOperation());
            q.setParameter("libelleOperation", libelleOp);
            q.setParameter("dateSaisie", request.getDateSaisie());
            q.setParameter("datePiece",request.getDatePiece());
            q.setParameter("dateValeur", request.getDateValeur());
            q.setParameter("referencePiece", "");
            q.setParameter("montant", request.getMontant());
            q.setParameter("montantCommission",request.getMontantCommission());
            q.setParameter("typeCommission",request.getTypeCommission());
            q.setParameter("valeurFormule","2:" + request.getMontantCommission().toString() );
            q.setParameter("valeurCodeAnalytique", "OPC:" + request.getOpcvm().getIdOpcvm());
            q.setParameter("userLogin", request.getUserLogin());
            q.setParameter("dateDernModifClient", LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);
//            OperationCommissionDto opDto = new OperationCommissionDto();
//            opDto.setIdTitre(0L);
//            opDto.setIdSeance(request.getIdSeance());
//            opDto.setIdActionnaire(0L);
//            opDto.setOpcvm(request.getOpcvm());
//            opDto.setType("PR");
//            opDto.setEcriture("A");
//            opDto.setTypeCommission(request.getTypeCommission());
//            NatureOperationDto nature = natureOperationMapper.deNatureOperation(
//                    natureOperationDao.findByCodeNatureOperationIgnoreCase(codeNatureOp).orElse(null)
//            );
//            opDto.setNatureOperation(nature);
//            opDto.setLibelleOperation(libelleOp);
//            opDto.setMontant(request.getMontant());
//            opDto.setMontantCommission(request.getMontantCommission());
//            opDto.setValeurCodeAnalytique("OPC:" + request.getOpcvm().getIdOpcvm());
//            opDto.setValeurFormule("2:" + request.getMontantCommission().toString());
//            opDto.setReferencePiece(null);
//            opDto.setDateOperation(request.getDateOperation());
//            opDto.setDateSolde(request.getDateSolde());
//            opDto.setDatePiece(request.getDatePiece());
//            opDto.setDateSaisie(request.getDateSaisie());
//            opDto.setDateValeur(request.getDateValeur());
//            opDto.setDateDernModifClient(LocalDateTime.now());
//            opDto.setEstVerifie1(false);
//            opDto.setEstVerifie2(false);
//            opDto.setEstExtournee(false);
//            opDto.setEstOpExtournee(false);
//            opDto.setEstOD(false);
//            opDto = appService.genererEcritureComptable(opDto);
            try
            {
                q.execute();
            }
            catch(Exception e)
            {

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
    public ResponseEntity<?> modifier(CommissionEditRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<?> supprimer(Long id) {
        return null;
    }
}
