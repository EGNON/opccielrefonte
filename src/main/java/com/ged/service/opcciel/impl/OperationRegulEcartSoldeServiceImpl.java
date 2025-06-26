package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OperationRegulEcartSoldeDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.OperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationRegulEcartSoldeDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.OperationRegulEcartSolde;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationRegulEcartSoldeMapper;
import com.ged.projection.OperationRegulEcartSoldeProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationRegulEcartSoldeService;
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
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OperationRegulEcartSoldeServiceImpl implements OperationRegulEcartSoldeService {

    private final OperationRegulEcartSoldeDao RegulEcartSoldeDao;
    private final OperationRegulEcartSoldeMapper RegulEcartSoldeMapper;
    private final LibraryDao libraryDao;
    private final NatureOperationDao natureOperationDao;
    private final OperationDao operationDao;
    private final AppService appService;
    @PersistenceContext
    private EntityManager em;
    @Override
    public ResponseEntity<?> afficherTous(ConstatationChargeListeRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationRegulEcartSoldeProjection> operationRegulEcartSoldePage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationRegulEcartSoldePage = new PageImpl<>(new ArrayList<>());
            }
            else {
                operationRegulEcartSoldePage = libraryDao.operationRegulEcartSolde(
                    request.getIdOpcvm(),false,
                    pageable
                );
            }
            List<OperationRegulEcartSoldeProjection> content = operationRegulEcartSoldePage.getContent().stream().toList();
            DataTablesResponse<OperationRegulEcartSoldeProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationRegulEcartSoldePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationRegulEcartSoldePage.getTotalElements());
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
    public OperationRegulEcartSolde afficherSelonId(Long id) {
        return RegulEcartSoldeDao.afficherSelonId(id);
    }

    @Override
    public ResponseEntity<?> afficher(Long id) {
        try {

            return ResponseHandler.generateResponse(
                    "regul ecart solde dont id="+id,
                    HttpStatus.OK,
                    RegulEcartSoldeMapper.deEntite(afficherSelonId(id)));
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> creer(OperationRegulEcartSoldeDto request) {

        try {
            String codeNatureOp = "";
            String libelleOp = "";
            var q=em.createStoredProcedureQuery("[Operation].[PS_OperationRegulEcartSolde_IP_New]");
            String sortie="";
            q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("SoldeEspeceDepositaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);
            if(request.getEcart().doubleValue()>0)
            {
               codeNatureOp = "REGUL_PRDT_HAO";
                libelleOp = "REGULARISATION SUIVANT PRODUIT HORS ACTIVITE ORDINAIRE";
            }
            else
            {
                codeNatureOp = "REGUL_CHG_HAO";
                libelleOp = "REGULARISATION SUIVANT CHARGE HORS ACTIVITE ORDINAIRE";
            }
            q.setParameter("idOperation",0);
            q.setParameter("idTransaction", 0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
            q.setParameter("codeNatureOperation", codeNatureOp);
            q.setParameter("dateOperation", request.getDateOperation());
            q.setParameter("libelleOperation",libelleOp);
            q.setParameter("dateSaisie", LocalDateTime.now());
            q.setParameter("datePiece", request.getDateOperation());
            q.setParameter("dateValeur", request.getDateOperation());
            q.setParameter("referencePiece", "");
            q.setParameter("montant", Math.abs(request.getEcart().doubleValue()));
            q.setParameter("SoldeEspeceDepositaire", request.getSoldeEspeceDepositaire());
            q.setParameter("valeurFormule", "2:" + String.valueOf(Math.abs(request.getEcart().doubleValue())).replace(',', '.'));
            q.setParameter("valeurCodeAnalytique", "OPC:" + request.getOpcvm().getIdOpcvm().toString());
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
    public ResponseEntity<?> modifier(OperationRegulEcartSoldeDto request) {
        try {
            String codeNatureOp = "";
            String libelleOp = "";


            var q=em.createStoredProcedureQuery("[Operation].[PS_OperationRegulEcartSolde_UP_New]");
            String sortie="";
            q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);

            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("SoldeEspeceDepositaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            if(request.getEcart().doubleValue()>0)
            {
                codeNatureOp = "REGUL_PRDT_HAO";
                libelleOp = "REGULARISATION SUIVANT PRODUIT HORS ACTIVITE ORDINAIRE";
            }
            else
            {
                codeNatureOp = "REGUL_CHG_HAO";
                libelleOp = "REGULARISATION SUIVANT CHARGE HORS ACTIVITE ORDINAIRE";
            }
            q.setParameter("idOperation",request.getIdOperation());
            q.setParameter("idTransaction", 0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
            q.setParameter("codeNatureOperation", codeNatureOp);
            q.setParameter("dateOperation", request.getDateOperation());
            q.setParameter("libelleOperation",libelleOp);
            q.setParameter("dateSaisie", LocalDateTime.now());
            q.setParameter("datePiece", request.getDateOperation());
            q.setParameter("dateValeur", request.getDateOperation());
            q.setParameter("referencePiece", "");
            q.setParameter("montant", Math.abs(request.getEcart().doubleValue()));
            q.setParameter("SoldeEspeceDepositaire", request.getSoldeEspeceDepositaire());
            q.setParameter("valeurFormule", "2:" + String.valueOf(Math.abs(request.getEcart().doubleValue())).replace(',', '.'));
            q.setParameter("valeurCodeAnalytique", "OPC:" + request.getOpcvm().getIdOpcvm().toString());
            q.setParameter("userLogin", request.getUserLogin());
            q.setParameter("dateDernModifClient", LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);

            try
            {
//                operationDao.modifier(request.getIdOperation(), "2:" + String.valueOf(Math.abs(request.getEcart().doubleValue())).replace(',', '.'),
//                        "OPC:" + request.getOpcvm().getIdOpcvm().toString(),request.getMontant());
                q.execute();

            }
            catch (Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Modification effectué avec succès",
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
    public ResponseEntity<Object> supprimer(String userLogin,Long idOperation) {
        try {
            String codeNatureOp = "";
            String libelleOp = "";


            var q=em.createStoredProcedureQuery("[Operation].[PS_OperationRegulEcartSolde_DP_New]");
            String sortie="";

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("userLogin", userLogin);
            q.setParameter("idOperation",idOperation);
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);

            try
            {
                q.executeUpdate();
//                operationDao.modifier(request.getIdOperation(), "2:" + String.valueOf(Math.abs(request.getEcart().doubleValue())).replace(',', '.'),
//                        "OPC:" + request.getOpcvm().getIdOpcvm().toString(),request.getMontant());
            }
            catch (Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
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
}
