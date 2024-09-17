package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeAmortissementDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeAmortissementDto;
import com.ged.entity.titresciel.TypeAmortissement;
import com.ged.mapper.titresciel.TypeAmortissementMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeAmortissementService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeAmortissementServiceImpl implements TypeAmortissementService {
    /*@Autowired
    @Qualifier("titrescielEntityManagerFactory")
    private EntityManager emTitresciel;*/
    private final TypeAmortissementDao typeAmortissementDao;
    private final TypeAmortissementMapper typeAmortissementMapper;

    public TypeAmortissementServiceImpl(TypeAmortissementDao typeAmortissementDao, TypeAmortissementMapper typeAmortissementMapper) {
        this.typeAmortissementDao = typeAmortissementDao;
        this.typeAmortissementMapper = typeAmortissementMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de tous les types d'Amortissement",
                    HttpStatus.OK,
                    typeAmortissementDao.findAll().stream().map(typeAmortissementMapper::deTypeAmortissement).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeAmortissement");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeAmortissement> typeAmortissementPage;
            typeAmortissementPage = typeAmortissementDao.findAll(pageable);
            List<TypeAmortissementDto> content = typeAmortissementPage.getContent().stream().map(typeAmortissementMapper::deTypeAmortissement).collect(Collectors.toList());
            DataTablesResponse<TypeAmortissementDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeAmortissementPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeAmortissementPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des typeAmortissements par page datatable",
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
    public Page<TypeAmortissementDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public TypeAmortissement afficherSelonId(Long id) {
        return typeAmortissementDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeAmortissement.class, "id", id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeAmortissement dont ID = " + id.toString(),
                    HttpStatus.OK,
                    typeAmortissementMapper.deTypeAmortissement(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeAmortissementDto typeAmortissementDto) {
        try {
            TypeAmortissement typeAmortissement = typeAmortissementMapper.deTypeAmortissementDto(typeAmortissementDto);
            typeAmortissement = typeAmortissementDao.save(typeAmortissement);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeAmortissementMapper.deTypeAmortissement(typeAmortissement));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeAmortissementDto typeAmortissementDto) {
        try {
            if(!typeAmortissementDao.existsById(typeAmortissementDto.getIdTypeAmortissement()))
                throw  new EntityNotFoundException(TypeAmortissement.class, "id", typeAmortissementDto.getIdTypeAmortissement().toString());
            TypeAmortissement typeAmortissement = typeAmortissementMapper.deTypeAmortissementDto(typeAmortissementDto);
            typeAmortissement = typeAmortissementDao.save(typeAmortissement);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeAmortissementMapper.deTypeAmortissement(typeAmortissement));
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
            typeAmortissementDao.deleteById(id);
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
