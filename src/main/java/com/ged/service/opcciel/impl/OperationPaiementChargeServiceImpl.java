package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.OperationConstatationChargeDao;
import com.ged.dao.opcciel.OperationPaiementChargeDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.opcciel.OperationPaiementChargeDto;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.OperationPaiementCharge;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationConstatationChargeMapper;
import com.ged.mapper.opcciel.OperationPaiementChargeMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationConstatationChargeService;
import com.ged.service.opcciel.OperationPaiementChargeService;
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
public class OperationPaiementChargeServiceImpl implements OperationPaiementChargeService {

    private final ObjectsValidator<ConstatationChargeAddRequest> requestAddValidator;
    private final OperationPaiementChargeDao operationPaiementChargeDao;
    private final OperationConstatationChargeDao operationConstatationChargeDao;
    private final OperationPaiementChargeMapper operationPaiementChargeMapper;
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

//            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
//            Page<OperationConstatationCharge> operationConstatationChargePage;
//            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
//                operationConstatationChargePage = new PageImpl<>(new ArrayList<>());
//            }
//            else {
//                SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(request.getIdOpcvm());
//                operationConstatationChargePage = operationPaiementChargeDao.afficherListe(
//                    request.getIdOpcvm(),
//                    pageable
//                );
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
    public OperationPaiementCharge afficherSelonId(Long id) {
        return operationPaiementChargeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public ResponseEntity<?> creer(Long [] idOperation,ConstatationChargeListeRequest listeRequest) {

        try {
            for(int i=0;i<idOperation.length;i++) {

                String codeNatureOp = "";
                String libelleOp = "";
                OperationConstatationCharge request=operationConstatationChargeDao.afficherSelonId(idOperation[i]);
                switch (request.getCodeCharge().trim()) {
                    case "FRAISDEPOSIT" -> {
                        codeNatureOp = "PMT_REM_DEPOSI";
                        libelleOp = "PAIEMENT COMMISSION DEPOSITAIRE";
                    }
                    case "FRAISCREPMF", "REVCREPMF" -> {
                        ;
                        codeNatureOp = "PMT_RED_CREPMF";
                        libelleOp = "PAIEMENT REDEVANCE CREPMF";
                    }
                    case "FRAISCONSACTIF" -> {
                        codeNatureOp = "PMT_COM_CONSACT";
                        libelleOp = "PAIEMENT COMMISSION DE CONSERVATION SUR ACTIF";
                    }
                    case "HONOCAC" -> {
                        codeNatureOp = "PMT_HONCAC";
                        libelleOp = "PAIEMENT HONORAIRE CAC";
                    }
                    case "FRAISGEST" -> {
                        codeNatureOp = "PMT_REM_GEST";
                        libelleOp = "PAIEMENT REMUNERATION DU GESTIONNAIRE";
                    }
                    default -> {
                    }
                }
                var q = em.createStoredProcedureQuery("[Operation].PS_OperationPaiementCharge_IP_New");
                String sortie = "";
                q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeCharge", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("estGenere", Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


                q.setParameter("idOperation", 0);
                q.setParameter("idTransaction", 0);
                q.setParameter("idSeance", request.getIdSeance());
                q.setParameter("idActionnaire", 0);
                q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
                q.setParameter("codeNatureOperation", codeNatureOp);
                q.setParameter("dateOperation", listeRequest.getDateOperation());
                q.setParameter("libelleOperation", libelleOp);
                q.setParameter("dateSaisie",LocalDateTime.now());
                q.setParameter("datePiece", listeRequest.getDateOperation());
                q.setParameter("dateValeur", listeRequest.getDateOperation());
                q.setParameter("referencePiece", "");
                q.setParameter("montant", request.getMontantCharge());
                q.setParameter("codeCharge", request.getCodeCharge());
                q.setParameter("estGenere", true);
                q.setParameter("valeurFormule", "2:" + request.getMontantCharge().toString());
                q.setParameter("valeurCodeAnalytique", "OPC:" + request.getOpcvm().getIdOpcvm());
                q.setParameter("userLogin", request.getUserLogin());
                q.setParameter("dateDernModifClient", LocalDateTime.now());
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie", sortie);
                String[] s = new String[20];
                try {
                    q.execute();
                    String result = (String) q.getOutputParameterValue("Sortie");
                    s = result.split("#");
                    if (!s[s.length - 1].trim().equals("")) {
                        SeanceOpcvm seanceOpcvm = seanceOpcvmDao.afficherSeanceEnCours(request.getOpcvm().getIdOpcvm());
                        operationConstatationChargeDao.modifier(seanceOpcvm.getIdSeanceOpcvm().getIdSeance(), request.getIdOperation());
                    }
                } catch (Exception e) {

                }
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
