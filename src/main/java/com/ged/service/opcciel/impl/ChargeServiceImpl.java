package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.ChargeDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.dto.request.ChargeListeRequest;
import com.ged.entity.opcciel.Charge;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.mapper.opcciel.ChargeMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ChargeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChargeServiceImpl implements ChargeService {
    private final ChargeDao chargeDao;
    private final OpcvmDao opcvmDao;
    private final NatureOperationDao natureOperationDao;
    private final ChargeMapper chargeMapper;

    public ChargeServiceImpl(ChargeDao chargeDao, OpcvmDao opcvmDao, NatureOperationDao natureOperationDao, ChargeMapper chargeMapper) {
        this.chargeDao = chargeDao;
        this.opcvmDao = opcvmDao;
        this.natureOperationDao = natureOperationDao;
        this.chargeMapper = chargeMapper;
    }

    @Override
    public ResponseEntity<?> afficherListeCharges(Long idOpcvm) {
        try {
            List<ChargeDto> listeCharges = chargeDao.afficherChargeParIdOpcvm(idOpcvm).stream().map(chargeMapper::deCharge).toList();
            return ResponseHandler.generateResponse(
                    "Liste de toutes les charges",
                    HttpStatus.OK,
                    listeCharges);
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
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCharge");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Charge> chargePage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
//                chargePage = chargeDao.rechercher(parameters.getSearch().getValue(), pageable);
//            }
//            else {
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                chargePage = chargeDao.findByOpcvm(opcvm,pageable);
          //  }
            List<ChargeDto> content = chargePage.getContent().stream().map(chargeMapper::deCharge).collect(Collectors.toList());
            DataTablesResponse<ChargeDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)chargePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)chargePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des charges par page datatable",
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
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"numCharge");
            return ResponseHandler.generateResponse(
                    "Liste de toutes les charges",
                    HttpStatus.OK,
                    chargeDao.findAll().stream().map(chargeMapper::deCharge).collect(Collectors.toList()));
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
    public Charge afficherSelonId(Long idCharge) {
        return chargeDao.findById(idCharge).orElseThrow(() -> new EntityNotFoundException(Charge.class, "code", idCharge.toString()));
    }

    @Override
    public ResponseEntity<Object>  afficherChargeSelonId(Long idCharge) {
        try {
            return ResponseHandler.generateResponse(
                    "Charge  dont id = " + idCharge,
                    HttpStatus.OK,
                    chargeMapper.deChargeProjection(chargeDao.rechercherChargeSelonId(idCharge)));
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
    public ResponseEntity<Object> afficher(Long codeCharge) {
        try {
            return ResponseHandler.generateResponse(
                    "Charge  dont id = " + codeCharge,
                    HttpStatus.OK,
                    chargeMapper.deCharge(afficherSelonId(codeCharge)));
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
    public ResponseEntity<Object> creer(ChargeDto chargeDto) {
        try {
            Charge charge = chargeMapper.deChargeDto(chargeDto);

            if(chargeDto.getOpcvm()!=null) {
                Opcvm opcvm = opcvmDao.findById(chargeDto.getOpcvm().getIdOpcvm()).orElseThrow();
                charge.setOpcvm(opcvm);
            }
            if(chargeDto.getNatureOperation()!=null) {
                NatureOperation natureOperation = natureOperationDao.findById(chargeDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                charge.setNatureOperation(natureOperation);
            }
            charge.setSupprimer(false);
            charge = chargeDao.save(charge);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    chargeMapper.deCharge(charge));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ChargeDto chargeDto) {
        try {

//            Charge charge = chargeMapper.deChargeDto(chargeDto);
//            charge.setSupprimer(false);
//            if(chargeDto.getOpcvm()!=null) {
//                Opcvm opcvm = opcvmDao.findById(chargeDto.getOpcvm().getIdOpcvm()).orElseThrow();
//                charge.setOpcvm(opcvm);
//            }
//            if(chargeDto.getNatureOperation()!=null) {
//                NatureOperation natureOperation = natureOperationDao.findById(chargeDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
//                charge.setNatureOperation(natureOperation);
//            }
//
//            charge = chargeDao.save(charge);
            chargeDao.modifierCharge(chargeDto.getIdCharge(),chargeDto.getMontant(),chargeDto.getDesignation(),
                    chargeDto.getTypeCharge(),chargeDto.getCodeCharge(),chargeDto.getNatureOperation().getCodeNatureOperation(),
                    chargeDto.isAppliquerSurActifNet());
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
    public ResponseEntity<Object> supprimer(Long codeCharge) {
        try {
            chargeDao.supprimerCharge(codeCharge);
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
