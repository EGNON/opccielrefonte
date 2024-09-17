package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.CompartimentDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.CompartimentDto;
import com.ged.entity.titresciel.Compartiment;
import com.ged.mapper.titresciel.CompartimentMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.CompartimentService;
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
public class CompartimentServiceImpl implements CompartimentService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final CompartimentDao CompartimentDao;
    private final CompartimentMapper CompartimentMapper;

    public CompartimentServiceImpl(CompartimentDao CompartimentDao, CompartimentMapper CompartimentMapper){
        this.CompartimentDao = CompartimentDao;

        this.CompartimentMapper = CompartimentMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleCompartiment");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Compartiment> CompartimentPage;
            CompartimentPage = CompartimentDao.findAll(pageable);
            List<CompartimentDto> content = CompartimentPage.getContent().stream().map(CompartimentMapper::deCompartiment).collect(Collectors.toList());
            DataTablesResponse<CompartimentDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)CompartimentPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)CompartimentPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des compartiments par page datatable",
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
    public Page<CompartimentDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleCompartiment");
            List<CompartimentDto> Compartiments = CompartimentDao.findAll().stream().map(CompartimentMapper::deCompartiment).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des compartiments ",
                    HttpStatus.OK,
                    Compartiments);
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
    public Compartiment afficherSelonId(Long id) {
        return CompartimentDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Compartiment.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Compartiment dont ID = " + id,
                    HttpStatus.OK,
                    CompartimentMapper.deCompartiment(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(CompartimentDto CompartimentDto) {
        try {
            Compartiment Compartiment = CompartimentMapper.deCompartimentDto(CompartimentDto);
            Compartiment = CompartimentDao.save(Compartiment);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    CompartimentMapper.deCompartiment(Compartiment));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(CompartimentDto CompartimentDto) {
        try {
            if(!CompartimentDao.existsById(CompartimentDto.getIdCompartiment()))
                throw  new EntityNotFoundException(Compartiment.class, "ID", CompartimentDto.getIdCompartiment().toString());
            Compartiment Compartiment = CompartimentMapper.deCompartimentDto(CompartimentDto);
            Compartiment = CompartimentDao.save(Compartiment);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    CompartimentMapper.deCompartiment(Compartiment));
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
            CompartimentDao.deleteById(idOperation);
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
