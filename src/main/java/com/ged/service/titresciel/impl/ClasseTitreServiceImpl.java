package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.ClasseTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ClasseTitreDto;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.mapper.titresciel.ClasseTitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.ClasseTitreService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClasseTitreServiceImpl implements ClasseTitreService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final ClasseTitreDao ClasseTitreDao;
    private final ClasseTitreMapper ClasseTitreMapper;

    public ClasseTitreServiceImpl(ClasseTitreDao ClasseTitreDao, ClasseTitreMapper ClasseTitreMapper){
        this.ClasseTitreDao = ClasseTitreDao;

        this.ClasseTitreMapper = ClasseTitreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomClasseTitre");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ClasseTitre> ClasseTitrePage;
            ClasseTitrePage = ClasseTitreDao.findAll(pageable);
            List<ClasseTitreDto> content = ClasseTitrePage.getContent().stream().map(ClasseTitreMapper::deClasseTitre).collect(Collectors.toList());
            DataTablesResponse<ClasseTitreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ClasseTitrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ClasseTitrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des ClasseTitres par page datatable",
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
    public Page<ClasseTitreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomClasseTitre");
            List<ClasseTitreDto> ClasseTitres = ClasseTitreDao.findAll().stream().map(ClasseTitreMapper::deClasseTitre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des ClasseTitres",
                    HttpStatus.OK,
                    ClasseTitres);
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
    public ClasseTitre afficherSelonId(String id) {
        return ClasseTitreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ClasseTitre.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String id) {
        try {
            return ResponseHandler.generateResponse(
                    "ClasseTitre dont ID = " + id,
                    HttpStatus.OK,
                    ClasseTitreMapper.deClasseTitre(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(ClasseTitreDto ClasseTitreDto) {
        try {
            ClasseTitre ClasseTitre = ClasseTitreMapper.deClasseTitreDto(ClasseTitreDto);
            ClasseTitre = ClasseTitreDao.save(ClasseTitre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ClasseTitreMapper.deClasseTitre(ClasseTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ClasseTitreDto ClasseTitreDto) {
        try {
            if(!ClasseTitreDao.existsById(ClasseTitreDto.getCodeClasseTitre()))
                throw  new EntityNotFoundException(ClasseTitre.class, "ID", ClasseTitreDto.getCodeClasseTitre().toString());
            ClasseTitre ClasseTitre = ClasseTitreMapper.deClasseTitreDto(ClasseTitreDto);
            ClasseTitre = ClasseTitreDao.save(ClasseTitre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ClasseTitreMapper.deClasseTitre(ClasseTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String id) {
        try {
            ClasseTitreDao.deleteById(id);
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

