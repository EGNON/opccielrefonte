package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.TypeRubriqueDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeRubriqueDto;
import com.ged.entity.opcciel.comptabilite.TypeRubrique;
import com.ged.mapper.opcciel.TypeRubriqueMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TypeRubriqueService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeRubriqueServiceImpl implements TypeRubriqueService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final TypeRubriqueDao typeRubriqueDao;
    private final TypeRubriqueMapper typeRubriqueMapper;

    public TypeRubriqueServiceImpl(TypeRubriqueDao TypeRubriqueDao, TypeRubriqueMapper TypeRubriqueMapper){

        this.typeRubriqueDao = TypeRubriqueDao;
        this.typeRubriqueMapper = TypeRubriqueMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeRubrique");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeRubrique> typeRubriquePage;
            typeRubriquePage = typeRubriqueDao.findBySupprimer(false,pageable);
            List<TypeRubriqueDto> content = typeRubriquePage.getContent().stream().map(typeRubriqueMapper::deTypeRubrique).collect(Collectors.toList());
            DataTablesResponse<TypeRubriqueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeRubriquePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeRubriquePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Rubrique par page datatable",
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
    public Page<TypeRubriqueDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeRubrique");
            List<TypeRubriqueDto> TypeRubriqueDtos = typeRubriqueDao.findBySupprimerOrderByCodeTypeRubriqueAsc(false).stream().map(typeRubriqueMapper::deTypeRubrique).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Rubrique",
                    HttpStatus.OK,
                    TypeRubriqueDtos);
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
    public TypeRubrique afficherSelonId(String code) {
        return typeRubriqueDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypeRubrique.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeRubrique dont ID = " + code,
                    HttpStatus.OK,
                    typeRubriqueMapper.deTypeRubrique(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypeRubriqueDto TypeRubriqueDto) {
        try {
            TypeRubriqueDto.setSupprimer(false);
            TypeRubrique  TypeRubrique = typeRubriqueMapper.deTypeRubriqueDto(TypeRubriqueDto);
            TypeRubrique = typeRubriqueDao.save(TypeRubrique);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeRubriqueMapper.deTypeRubrique(TypeRubrique));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeRubriqueDto typeRubriqueDto) {
        try {
            if(!typeRubriqueDao.existsById(typeRubriqueDto.getCodeTypeRubrique()))
                throw  new EntityNotFoundException(TypeRubrique.class, "ID", typeRubriqueDto.getCodeTypeRubrique().toString());
            typeRubriqueDto.setSupprimer(false);
            TypeRubrique typeRubrique = typeRubriqueMapper.deTypeRubriqueDto(typeRubriqueDto);
            typeRubrique= typeRubriqueDao.save(typeRubrique);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeRubriqueMapper.deTypeRubrique(typeRubrique));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String code) {
        try {
            typeRubriqueDao.deleteById(code);
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
