package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.OperationTransfertPartDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationTransfertPartDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.request.TransfertPartAddRequest;
import com.ged.dto.request.TransfertPartListeRequest;
import com.ged.entity.opcciel.OperationTransfertPart;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationTransfertPartMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationTransfertPartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationTransfertPartServiceImpl implements OperationTransfertPartService {
    private final OperationTransfertPartDao operationTransfertPartDao;
    private final OperationTransfertPartMapper transfertPartMapper;
    private final AppService appService;
    private final NatureOperationDao natureOperationDao;
    private final NatureOperationMapper natureOperationMapper;

    public OperationTransfertPartServiceImpl(OperationTransfertPartDao operationTransfertPartDao, OperationTransfertPartMapper transfertPartMapper, AppService appService, NatureOperationDao natureOperationDao, NatureOperationMapper natureOperationMapper) {
        this.operationTransfertPartDao = operationTransfertPartDao;
        this.transfertPartMapper = transfertPartMapper;
        this.appService = appService;
        this.natureOperationDao = natureOperationDao;
        this.natureOperationMapper = natureOperationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(TransfertPartListeRequest request) {
        var parameters = request.getDatatableParameters();
        try {
            List<OperationTransfertPartDto> listeOpTransDep;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                listeOpTransDep = new ArrayList<>();
            }
            else {
                listeOpTransDep = operationTransfertPartDao.afficherTousTransfertDepart(
                            request.getIdOpcvm()
                        ).stream().map(transfertPartMapper::deEntite).toList();
            }
            DataTablesResponse<OperationTransfertPartDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered(listeOpTransDep.size());
            dataTablesResponse.setRecordsTotal(listeOpTransDep.size());
            dataTablesResponse.setData(listeOpTransDep);
            return ResponseHandler.generateResponse(
                    "Liste des transferts départs",
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
    public ResponseEntity<Object> creer(OperationTransfertPartDto opDto) {
        try {
            OperationTransfertPart op = transfertPartMapper.deDto(opDto);
            op = operationTransfertPartDao.save(op);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    transfertPartMapper.deEntite(op));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OperationTransfertPartDto opDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> creerTransfert(TransfertPartAddRequest request) {
        var montant = request.getCumpEntre().multiply(request.getQteTrans());
        var seance = appService.currentSeance(request.getOpcvm().getIdOpcvm());
        // Transfert départ
        OperationTransfertPartDto departDto = new OperationTransfertPartDto();
        departDto.setIdTitre(0L);
        departDto.setIdSeance(request.getIdSeance());
        departDto.setDemandeur(request.getDemandeur());
        departDto.setActionnaire(request.getDemandeur());
        departDto.setIdActionnaire(request.getDemandeur().getIdPersonne());
        departDto.setBeneficiaire(request.getBeneficiaire());
        departDto.setQteTransfert(request.getQteTrans());
        departDto.setCumpEntre(request.getCumpEntre());
        departDto.setQteInitiale(request.getQteAvtD());
        NatureOperationDto nature = natureOperationMapper.deNatureOperation(
            natureOperationDao.findByCodeNatureOperationIgnoreCase("TRSFT_PART_DEPAR").orElse(null)
        );
        departDto.setNatureOperation(nature);
        departDto.setOpcvm(request.getOpcvm());
        departDto.setLibelleOperation(
            "TRANSFERT DEPART DE " + request.getQteTrans().toString() +
            " PARTS PAR " + request.getDemandeur().getDenomination()
        );
        departDto.setMontant(montant);
        departDto.setValeurCodeAnalytique(
            "OPC:" + request.getOpcvm().getIdOpcvm().toString() +
            ";ACT:" + request.getDemandeur().getIdPersonne().toString()
        );
        departDto.setValeurFormule(
            "21:" + departDto.getMontant().toString() +
            ";104:" + request.getQteTrans().toString()
        );
        departDto.setReferencePiece("");
        departDto.setDateOperation(request.getDateOperation());
        departDto.setDatePiece(seance.getDateFermeture());
        departDto.setDateSaisie(LocalDateTime.now());
        departDto.setDateValeur(request.getDateOperation());
        departDto.setDateDernModifClient(LocalDateTime.now());
        departDto = appService.genererEcritureComptable(departDto);
        // Transfert arrivé
        OperationTransfertPartDto arriveDto = new OperationTransfertPartDto();
        if(departDto.getIdOperation() != null && departDto.getIdOperation().intValue() > 0) {
            arriveDto.setIdOpDepart(departDto.getIdOperation());
            arriveDto.setIdTitre(0L);
            arriveDto.setIdSeance(request.getIdSeance());
            arriveDto.setDemandeur(request.getDemandeur());
            arriveDto.setActionnaire(request.getBeneficiaire());
            arriveDto.setIdActionnaire(request.getDemandeur().getIdPersonne());
            arriveDto.setBeneficiaire(request.getBeneficiaire());
            arriveDto.setQteTransfert(request.getQteTrans());
            arriveDto.setCumpEntre(request.getCumpEntre());
            arriveDto.setQteInitiale(request.getQteAvtB());
            NatureOperationDto natureArr = natureOperationMapper.deNatureOperation(
                natureOperationDao.findByCodeNatureOperationIgnoreCase("TRSFT_PART_ARV").orElse(null)
            );
            arriveDto.setNatureOperation(natureArr);
            arriveDto.setOpcvm(request.getOpcvm());
            arriveDto.setLibelleOperation(
                "TRANSFERT ARRIVE DE " + request.getQteTrans().toString() +
                " PARTS A " + request.getBeneficiaire().getDenomination()
            );
            arriveDto.setMontant(montant);
            arriveDto.setValeurCodeAnalytique(
                "OPC:" + request.getOpcvm().getIdOpcvm().toString() +
                ";ACT:" + request.getDemandeur().getIdPersonne().toString()
            );
            arriveDto.setValeurFormule(
                "21:" + arriveDto.getMontant().toString() +
                ";104:" + request.getQteTrans().toString()
            );
            arriveDto.setReferencePiece("");
            arriveDto.setDateOperation(request.getDateOperation());
            arriveDto.setDatePiece(seance.getDateFermeture());
            arriveDto.setDateSaisie(LocalDateTime.now());
            arriveDto.setDateValeur(request.getDateOperation());
            arriveDto.setDateDernModifClient(LocalDateTime.now());
            arriveDto = appService.genererEcritureComptable(arriveDto);
        }
        return ResponseHandler.generateResponse(
            "Enregistrement effectué avec succès",
            HttpStatus.OK,
            departDto.getIdOperation() + ";" + arriveDto.getIdOperation()
        );
    }
}
