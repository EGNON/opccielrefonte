package com.ged.service.lab.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.CritereAlerteDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.CritereAlerteDto;
import com.ged.entity.standard.CritereAlerte;
import com.ged.mapper.standard.CritereAlerteMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.lab.CritereAlerteService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CritereAlerteServiceImpl implements CritereAlerteService {
    private final CritereAlerteDao critereAlerteDao;
    private final CritereAlerteMapper critereAlerteMapper;

    public CritereAlerteServiceImpl(CritereAlerteDao critereAlerteDao, CritereAlerteMapper critereAlerteMapper) {
        this.critereAlerteDao = critereAlerteDao;
        this.critereAlerteMapper = critereAlerteMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.DESC,"dateAlerte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<CritereAlerte> critereAlertePage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
////                ObjectMapper mapper = new ObjectMapper();
////                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
//                critereAlertePage = CritereAlerteDao.findByLibelleContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
//            }
//            else {
                critereAlertePage = critereAlerteDao.findAll(pageable);
//            }
            List<CritereAlerteDto> content = critereAlertePage.getContent().stream().map(critereAlerteMapper::deCritereAlerte).collect(Collectors.toList());
            DataTablesResponse<CritereAlerteDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int) critereAlertePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int) critereAlertePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des alertes par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.DESC,"dateAlerte");
            return ResponseHandler.generateResponse(
                    "Liste de tous les dégrés",
                    HttpStatus.OK,
                    critereAlerteDao.findAll(sort).stream().map(critereAlerteMapper::deCritereAlerte).collect(Collectors.toList()));
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
    public List<CritereAlerteDto> afficherListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"dateAlerte");
        return critereAlerteDao.findAll(sort).stream().map(critereAlerteMapper::deCritereAlerte).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> afficherCritereAlertes(int page, int size) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"dateAlerte");
            PageRequest pageRequest = PageRequest.of(page, size,sort);
            Page<CritereAlerte> CritereAlertePage = critereAlerteDao.findAll(pageRequest);
            return ResponseHandler.generateResponse(
                    "Liste des alertes de " + page + " à " + size,
                    HttpStatus.OK,
                    new PageImpl<>(CritereAlertePage.getContent().stream().map(critereAlerteMapper::deCritereAlerte).collect(Collectors.toList()),
                            pageRequest, CritereAlertePage.getTotalElements()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherCritereAlerte(Long id) {
        try {
//            System.out.println(critereAlerteMapper.deCritereAlerte(afficherCritereAlerteSelonId(id)).getDescription());
            return ResponseHandler.generateResponse(
                    "Alerte dont ID = " + id.toString(),
                    HttpStatus.OK,
                    critereAlerteMapper.deCritereAlerte(afficherCritereAlerteSelonId(id)));
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
    public CritereAlerte afficherCritereAlerteSelonId(Long idCritereAlerte) {
        return critereAlerteDao.findById(idCritereAlerte).orElseThrow(() -> new EntityNotFoundException(CritereAlerte.class, "id", idCritereAlerte.toString()));
    }

    @Override
    public ResponseEntity<Object> creerCritereAlerte(CritereAlerteDto CritereAlerteDto) {
        try {
            CritereAlerte CritereAlerte = critereAlerteMapper.deCritereAlerteDto(CritereAlerteDto);
            CritereAlerte CritereAlerteSaved = critereAlerteDao.save(CritereAlerte);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    critereAlerteMapper.deCritereAlerte(CritereAlerteSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifierCritereAlerte(CritereAlerteDto CritereAlerteDto) {
        try {
//            if(!CritereAlerteDao.existsById(CritereAlerteDto.getIdCritereAlerte()))
//                throw  new EntityNotFoundException(CritereAlerte.class, "id", CritereAlerteDto.getIdCritereAlerte().toString());
            CritereAlerte CritereAlerte = critereAlerteMapper.deCritereAlerteDto(CritereAlerteDto);
            CritereAlerte = critereAlerteDao.save(CritereAlerte);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    critereAlerteMapper.deCritereAlerte(CritereAlerte));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimerCritereAlerte(Long id) {
        try {
            critereAlerteDao.deleteById(id);
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
