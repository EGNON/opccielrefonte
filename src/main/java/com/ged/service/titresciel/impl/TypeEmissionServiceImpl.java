package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeEmissionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEmissionDto;
import com.ged.entity.titresciel.TypeEmission;
import com.ged.mapper.titresciel.TypeEmissionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeEmissionService;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeEmissionServiceImpl implements TypeEmissionService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypeEmissionDao typeEmissionDao;
    private final TypeEmissionMapper typeEmissionMapper;

    public TypeEmissionServiceImpl(TypeEmissionDao TypeEmissionDao, TypeEmissionMapper TypeEmissionMapper){

        this.typeEmissionDao = TypeEmissionDao;
        this.typeEmissionMapper = TypeEmissionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeEmission");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeEmission> typeEmissionPage;
            typeEmissionPage = typeEmissionDao.findAll(pageable);
            List<TypeEmissionDto> content = typeEmissionPage.getContent().stream().map(typeEmissionMapper::deTypeEmission).collect(Collectors.toList());
            DataTablesResponse<TypeEmissionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeEmissionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeEmissionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type emission par page datatable",
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
    public Page<TypeEmissionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeEmission");
            List<TypeEmissionDto> TypeEmissionDtos = typeEmissionDao.findAll(sort).stream().map(typeEmissionMapper::deTypeEmission).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Type Emission ",
                    HttpStatus.OK,
                    TypeEmissionDtos);
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
    public TypeEmission afficherSelonId(Long id) {
        return typeEmissionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeEmission.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeEmission dont ID = " + id,
                    HttpStatus.OK,
                    typeEmissionMapper.deTypeEmission(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeEmissionDto TypeEmissionDto) {
        try {
            TypeEmission  TypeEmission = typeEmissionMapper.deTypeEmissionDto(TypeEmissionDto);
            TypeEmission = typeEmissionDao.save(TypeEmission);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeEmissionMapper.deTypeEmission(TypeEmission));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeEmissionDto typeEmissionDto) {
        try {
            if(!typeEmissionDao.existsById(typeEmissionDto.getIdTypeEmission()))
                throw  new EntityNotFoundException(TypeEmission.class, "ID", typeEmissionDto.getIdTypeEmission().toString());
            TypeEmission typeEmission = typeEmissionMapper.deTypeEmissionDto(typeEmissionDto);
            typeEmission= typeEmissionDao.save(typeEmission);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeEmissionMapper.deTypeEmission(typeEmission));
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
            typeEmissionDao.deleteById(id);
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
