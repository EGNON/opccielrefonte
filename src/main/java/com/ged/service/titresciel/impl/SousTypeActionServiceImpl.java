package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.SousTypeActionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.SousTypeActionDto;
import com.ged.entity.titresciel.SousTypeAction;
import com.ged.mapper.titresciel.SousTypeActionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.SousTypeActionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SousTypeActionServiceImpl implements SousTypeActionService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final SousTypeActionDao sousTypeActionDao;
    private final SousTypeActionMapper sousTypeActionMapper;

    public SousTypeActionServiceImpl(SousTypeActionDao SousTypeActionDao, SousTypeActionMapper SousTypeActionMapper){
        this.sousTypeActionDao = SousTypeActionDao;

        this.sousTypeActionMapper = SousTypeActionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleSousTypeAction");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<SousTypeAction> SousTypeActionPage;
            SousTypeActionPage = sousTypeActionDao.findAll(pageable);
            List<SousTypeActionDto> content = SousTypeActionPage.getContent().stream().map(sousTypeActionMapper::deSousTypeAction).collect(Collectors.toList());
            DataTablesResponse<SousTypeActionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)SousTypeActionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)SousTypeActionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des sous type action par page datatable",
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
    public Page<SousTypeActionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleSousTypeAction");
            List<SousTypeActionDto> SousTypeActions = sousTypeActionDao.findAll().stream().map(sousTypeActionMapper::deSousTypeAction).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des sous type action",
                    HttpStatus.OK,
                    SousTypeActions);
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
    public SousTypeAction afficherSelonId(Long id) {
        return sousTypeActionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(SousTypeAction.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "sous type action dont ID = " + id,
                    HttpStatus.OK,
                    sousTypeActionMapper.deSousTypeAction(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(SousTypeActionDto SousTypeActionDto) {
        try {
            SousTypeAction SousTypeAction = sousTypeActionMapper.deSousTypeActionDto(SousTypeActionDto);
            SousTypeAction = sousTypeActionDao.save(SousTypeAction);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    sousTypeActionMapper.deSousTypeAction(SousTypeAction));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(SousTypeActionDto SousTypeActionDto) {
        try {
            if(!sousTypeActionDao.existsById(SousTypeActionDto.getIdSousTypeAction()))
                throw  new EntityNotFoundException(SousTypeAction.class, "ID", SousTypeActionDto.getIdSousTypeAction().toString());
            SousTypeAction SousTypeAction = sousTypeActionMapper.deSousTypeActionDto(SousTypeActionDto);
            SousTypeAction = sousTypeActionDao.save(SousTypeAction);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    sousTypeActionMapper.deSousTypeAction(SousTypeAction));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOperation) {
        try {
            sousTypeActionDao.deleteById(idOperation);
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
