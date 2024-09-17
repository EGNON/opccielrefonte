package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.SystemeDinformationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SystemeDinformationDto;
import com.ged.entity.standard.SystemeDinformation;
import com.ged.mapper.standard.SystemeDinformationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.SystemeDinformationService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SystemeDinformationServiceImpl implements SystemeDinformationService {
    private final SystemeDinformationDao systemeDinformationDao;
    private final SystemeDinformationMapper systemeDinformationMapper;

    public SystemeDinformationServiceImpl(SystemeDinformationDao SystemeDinformationDao, SystemeDinformationMapper SystemeDinformationMapper) {
        this.systemeDinformationDao = SystemeDinformationDao;
        this.systemeDinformationMapper = SystemeDinformationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"logiciel");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<SystemeDinformation> SystemeDinformationPage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
////                ObjectMapper mapper = new ObjectMapper();
////                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
//                SystemeDinformationPage = SystemeDinformationDao.findByLibelleContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
//            }
//            else {
                SystemeDinformationPage = systemeDinformationDao.findAll(pageable);
//            }
            List<SystemeDinformationDto> content = SystemeDinformationPage.getContent().stream().map(systemeDinformationMapper::deSystemeDinformation).collect(Collectors.toList());
            DataTablesResponse<SystemeDinformationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int) SystemeDinformationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int) SystemeDinformationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des systèmes d'informations par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"logiciel");
            return ResponseHandler.generateResponse(
                    "Liste de tous les systèmes d'informations",
                    HttpStatus.OK,
                    systemeDinformationDao.findAll(sort).stream().map(systemeDinformationMapper::deSystemeDinformation).collect(Collectors.toList()));
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
    public List<SystemeDinformationDto> afficherListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"logiciel");
        return systemeDinformationDao.findAll(sort).stream().map(systemeDinformationMapper::deSystemeDinformation).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> afficherSystemeDinformations(int page, int size) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"logiciel");
            PageRequest pageRequest = PageRequest.of(page, size,sort);
            Page<SystemeDinformation> SystemeDinformationPage = systemeDinformationDao.findAll(pageRequest);
            return ResponseHandler.generateResponse(
                    "Liste des systèmes d'informations de " + page + " à " + size,
                    HttpStatus.OK,
                    new PageImpl<>(SystemeDinformationPage.getContent().stream().map(systemeDinformationMapper::deSystemeDinformation).collect(Collectors.toList()),
                            pageRequest, SystemeDinformationPage.getTotalElements()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherSystemeDinformation(Long id) {
        try {
//            System.out.println(SystemeDinformationMapper.deSystemeDinformation(afficherSystemeDinformationSelonId(id)).getDescription());
            return ResponseHandler.generateResponse(
                    "système d'information dont ID = " + id.toString(),
                    HttpStatus.OK,
                    systemeDinformationMapper.deSystemeDinformation(afficherSystemeDinformationSelonId(id)));
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
    public SystemeDinformation afficherSystemeDinformationSelonId(Long idSystemeDinformation) {
        return systemeDinformationDao.findById(idSystemeDinformation).orElseThrow(() -> new EntityNotFoundException(SystemeDinformation.class, "id", idSystemeDinformation.toString()));
    }

    @Override
    public ResponseEntity<Object> creerSystemeDinformation(SystemeDinformationDto SystemeDinformationDto) {
        try {
            SystemeDinformation SystemeDinformation = systemeDinformationMapper.deSystemeDinformationDto(SystemeDinformationDto);
            SystemeDinformation SystemeDinformationSaved = systemeDinformationDao.save(SystemeDinformation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    systemeDinformationMapper.deSystemeDinformation(SystemeDinformationSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifierSystemeDinformation(SystemeDinformationDto SystemeDinformationDto) {
        try {
//            if(!SystemeDinformationDao.existsById(SystemeDinformationDto.getIdSystemeDinformation()))
//                throw  new EntityNotFoundException(SystemeDinformation.class, "id", SystemeDinformationDto.getIdSystemeDinformation().toString());
            SystemeDinformation SystemeDinformation = systemeDinformationMapper.deSystemeDinformationDto(SystemeDinformationDto);
            SystemeDinformation = systemeDinformationDao.save(SystemeDinformation);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    systemeDinformationMapper.deSystemeDinformation(SystemeDinformation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimerSystemeDinformation(Long id) {
        try {
            systemeDinformationDao.deleteById(id);
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
