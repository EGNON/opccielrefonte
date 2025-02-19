package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.OperationConstatationChargeDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstitutionChargeAddRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import com.ged.entity.opcciel.OperationTransfertPart;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationConstatationChargeMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationConstatationChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OperationConstatationChargeServiceImpl implements OperationConstatationChargeService {

    private final OperationConstatationChargeDao constatationChargeDao;
    private final OperationConstatationChargeMapper constatationChargeMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final NatureOperationDao natureOperationDao;
    private final AppService appService;

    @Override
    public ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request) {
        var parameters = request.getDatatableParameters();
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
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
    public OperationTransfertPart afficherSelonId(Long id) {
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> creer(ConstitutionChargeAddRequest request) {
        try {
//            var seance = appService.currentSeance(request.getOpcvm().getIdOpcvm());
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
            OperationConstatationChargeDto opDto = new OperationConstatationChargeDto();
            opDto.setIdTitre(0L);
            opDto.setIdSeance(request.getIdSeance());
            opDto.setIdActionnaire(0L);
            opDto.setOpcvm(request.getOpcvm());
            opDto.setType("PR");
            opDto.setEcriture("A");
            NatureOperationDto nature = natureOperationMapper.deNatureOperation(
                natureOperationDao.findByCodeNatureOperationIgnoreCase(codeNatureOp).orElse(null)
            );
            opDto.setCodeCharge(request.getCharge().getIdCharge().getCodeCharge());
            opDto.setNatureOperation(nature);
            opDto.setLibelleOperation(libelleOp);
            opDto.setMontant(request.getMontant());
            opDto.setMontantCharge(request.getMontantCharge());
            opDto.setValeurCodeAnalytique("OPC:" + request.getOpcvm().getIdOpcvm());
            opDto.setValeurFormule("2:" + request.getMontant().toString() + ";5:" + request.getMontantCharge().toString());
            opDto.setReferencePiece("");
            opDto.setDateOperation(request.getDateOperation());
            opDto.setDateSolde(request.getDateSolde());
            opDto.setDatePiece(request.getDatePiece());
            opDto.setDateSaisie(request.getDateSaisie());
            opDto.setDateValeur(request.getDateValeur());
            opDto.setDateDernModifClient(LocalDateTime.now());
            opDto.setEstVerifie1(false);
            opDto.setEstVerifie2(false);
            opDto.setEstPayee(false);
            opDto.setEstExtournee(false);
            opDto.setEstOpExtournee(false);
            opDto.setEstOD(false);
            opDto = appService.genererEcritureComptable(opDto);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès",
                    HttpStatus.OK,
                    opDto
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
    public ResponseEntity<Object> modifier(OperationConstatationChargeDto opDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        return null;
    }
}
