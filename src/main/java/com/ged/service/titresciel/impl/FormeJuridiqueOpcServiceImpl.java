package com.ged.service.titresciel.impl;

import com.ged.dao.titresciel.FormeJuridiqueOpcDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.FormeJuridiqueOpcDto;
import com.ged.entity.titresciel.FormeJuridiqueOpc;
import com.ged.mapper.titresciel.FormeJuridiqueOpcMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.FormeJuridiqueOpcService;
import jakarta.persistence.EntityNotFoundException;
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
public class FormeJuridiqueOpcServiceImpl implements FormeJuridiqueOpcService {
    private final FormeJuridiqueOpcDao formeJuridiqueOpcDao;
    private final FormeJuridiqueOpcMapper formeJuridiqueOpcMapper;

    public FormeJuridiqueOpcServiceImpl(FormeJuridiqueOpcDao formeJuridiqueOpcDao, FormeJuridiqueOpcMapper formeJuridiqueOpcMapper) {
        this.formeJuridiqueOpcDao = formeJuridiqueOpcDao;
        this.formeJuridiqueOpcMapper = formeJuridiqueOpcMapper;
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "FormeJuridiqueOpc dont ID = " + id.toString(),
                    HttpStatus.OK,
                    formeJuridiqueOpcMapper.deFormeJuridiqueOpc(afficherSelonId(id)));
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
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<FormeJuridiqueOpc> formeJuridiqueOpcPage = formeJuridiqueOpcDao.findAll(pageable);

            List<FormeJuridiqueOpcDto> content = formeJuridiqueOpcPage.getContent().stream().map(formeJuridiqueOpcMapper::deFormeJuridiqueOpc).collect(Collectors.toList());
            DataTablesResponse<FormeJuridiqueOpcDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)formeJuridiqueOpcPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)formeJuridiqueOpcPage.getTotalElements());
            dataTablesResponse.setData(content);

            return ResponseHandler.generateResponse(
                    "Liste des formeJuridiqueOpc par page datatable",
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
            return ResponseHandler.generateResponse(
                    "Liste de tous les formeJuridiqueOpc",
                    HttpStatus.OK,
                    formeJuridiqueOpcDao.findAll().stream().map(formeJuridiqueOpcMapper::deFormeJuridiqueOpc).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherParPage(int page, int size) {
        return null;
    }

    @Override
    public FormeJuridiqueOpc afficherSelonId(Long id) {
        return formeJuridiqueOpcDao.findById(id).orElseThrow(()-> new EntityNotFoundException("FormeJuridiqueOpc avec ID "+id+" introuvable"));
    }

    @Override
    public ResponseEntity<Object> creer(FormeJuridiqueOpcDto formeJuridiqueOpcDto) {
        try {
            FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcMapper.deFormeJuridiqueOpcDto(formeJuridiqueOpcDto);
            FormeJuridiqueOpc formeJuridiqueOpcSaved = formeJuridiqueOpcDao.save(formeJuridiqueOpc);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    formeJuridiqueOpcMapper.deFormeJuridiqueOpc(formeJuridiqueOpcSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(FormeJuridiqueOpcDto formeJuridiqueOpcDto) {
        try {
            if(!formeJuridiqueOpcDao.existsById(formeJuridiqueOpcDto.getIdFormeJuridiqueOpc()))
                throw  new com.ged.advice.EntityNotFoundException(FormeJuridiqueOpc.class, "id", formeJuridiqueOpcDto.getIdFormeJuridiqueOpc().toString());
            FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcMapper.deFormeJuridiqueOpcDto(formeJuridiqueOpcDto);
            formeJuridiqueOpc = formeJuridiqueOpcDao.save(formeJuridiqueOpc);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    formeJuridiqueOpcMapper.deFormeJuridiqueOpc(formeJuridiqueOpc));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            formeJuridiqueOpcDao.deleteById(id);
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
