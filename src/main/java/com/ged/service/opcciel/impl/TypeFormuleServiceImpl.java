package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.TypeFormuleDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeFormuleDto;
import com.ged.entity.opcciel.comptabilite.TypeFormule;
import com.ged.mapper.opcciel.TypeFormuleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TypeFormuleService;
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
public class TypeFormuleServiceImpl implements TypeFormuleService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypeFormuleDao typeFormuleDao;
    private final TypeFormuleMapper typeFormuleMapper;

    public TypeFormuleServiceImpl(TypeFormuleDao TypeFormuleDao, TypeFormuleMapper TypeFormuleMapper){

        this.typeFormuleDao = TypeFormuleDao;
        this.typeFormuleMapper = TypeFormuleMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeFormule");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeFormule> typeFormulePage;
            typeFormulePage = typeFormuleDao.findBySupprimer(false,pageable);
            List<TypeFormuleDto> content = typeFormulePage.getContent().stream().map(typeFormuleMapper::deTypeFormule).collect(Collectors.toList());
            DataTablesResponse<TypeFormuleDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeFormulePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeFormulePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Formule par page datatable",
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
    public Page<TypeFormuleDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeFormule");
            List<TypeFormuleDto> TypeFormuleDtos = typeFormuleDao.findBySupprimerOrderByCodeTypeFormuleAsc(false).stream().map(typeFormuleMapper::deTypeFormule).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Formule",
                    HttpStatus.OK,
                    TypeFormuleDtos);
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
    public TypeFormule afficherSelonId(String code) {
        return typeFormuleDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypeFormule.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeFormule dont ID = " + code,
                    HttpStatus.OK,
                    typeFormuleMapper.deTypeFormule(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypeFormuleDto TypeFormuleDto) {
        try {
            TypeFormuleDto.setSupprimer(false);
            TypeFormule  TypeFormule = typeFormuleMapper.deTypeFormuleDto(TypeFormuleDto);
            TypeFormule = typeFormuleDao.save(TypeFormule);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeFormuleMapper.deTypeFormule(TypeFormule));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeFormuleDto typeFormuleDto) {
        try {
            if(!typeFormuleDao.existsById(typeFormuleDto.getCodeTypeFormule()))
                throw  new EntityNotFoundException(TypeFormule.class, "ID", typeFormuleDto.getCodeTypeFormule().toString());
            typeFormuleDto.setSupprimer(false);
            TypeFormule typeFormule = typeFormuleMapper.deTypeFormuleDto(typeFormuleDto);
            typeFormule= typeFormuleDao.save(typeFormule);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeFormuleMapper.deTypeFormule(typeFormule));
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
            typeFormuleDao.deleteById(code);
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
