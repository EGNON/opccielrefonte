package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.DepotRachatDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.mapper.opcciel.DepotRachatMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.DepotRachatService;
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
public class DepotRachatImpl implements DepotRachatService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final DepotRachatDao depotRachatDao;
    private final DepotRachatMapper depotRachatMapper;

    public DepotRachatImpl(DepotRachatDao DepotRachatDao, DepotRachatMapper DepotRachatMapper){
        this.depotRachatDao = DepotRachatDao;

        this.depotRachatMapper = DepotRachatMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleDepotRachat");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<DepotRachat> DepotRachatPage;
            DepotRachatPage = depotRachatDao.findAll(pageable);
            List<DepotRachatDto> content = DepotRachatPage.getContent().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            DataTablesResponse<DepotRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats par page datatable",
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
    public Page<DepotRachatDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleDepotRachat");
            List<DepotRachatDto> DepotRachats = depotRachatDao.findAll().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats ",
                    HttpStatus.OK,
                    DepotRachats);
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
    public DepotRachat afficherSelonId(Long idOperation) {
        return depotRachatDao.findById(idOperation).orElseThrow(() -> new EntityNotFoundException(DepotRachat.class, "id",idOperation.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long idOperation) {
        try {
            return ResponseHandler.generateResponse(
                    "DepotRachat dont ID = " + idOperation,
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(afficherSelonId(idOperation)));
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
    public ResponseEntity<Object> creer(DepotRachatDto DepotRachatDto) {
        try {
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(DepotRachatDto);
            DepotRachat = depotRachatDao.save(DepotRachat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto) {
        try {
            if(!depotRachatDao.existsById(depotRachatDto.getIdOperation()))
                throw  new EntityNotFoundException(DepotRachat.class, "ID", depotRachatDto.getIdOperation().toString());
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(depotRachatDto);
            DepotRachat = depotRachatDao.save(DepotRachat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
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
            depotRachatDao.deleteById(idOperation);
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
