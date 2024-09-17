package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeGarantDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeGarantDto;
import com.ged.entity.titresciel.TypeGarant;
import com.ged.mapper.titresciel.TypeGarantMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeGarantService;
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
public class TypeGarantServiceImpl implements TypeGarantService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final TypeGarantDao typeGarantDao;
    private final TypeGarantMapper typeGarantMapper;

    public TypeGarantServiceImpl(TypeGarantDao TypeGarantDao, TypeGarantMapper TypeGarantMapper){

        this.typeGarantDao = TypeGarantDao;
        this.typeGarantMapper = TypeGarantMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeGarant");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeGarant> typeGarantPage;
            typeGarantPage = typeGarantDao.findAll(pageable);
            List<TypeGarantDto> content = typeGarantPage.getContent().stream().map(typeGarantMapper::deTypeGarant).collect(Collectors.toList());
            DataTablesResponse<TypeGarantDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeGarantPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeGarantPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Garant par page datatable",
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
    public Page<TypeGarantDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeGarant");
            List<TypeGarantDto> TypeGarantDtos = typeGarantDao.findAll(sort).stream().map(typeGarantMapper::deTypeGarant).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Type Garant ",
                    HttpStatus.OK,
                    TypeGarantDtos);
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
    public TypeGarant afficherSelonId(Long id) {
        return typeGarantDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeGarant.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeGarant dont ID = " + id,
                    HttpStatus.OK,
                    typeGarantMapper.deTypeGarant(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeGarantDto TypeGarantDto) {
        try {
            TypeGarant  TypeGarant = typeGarantMapper.deTypeGarantDto(TypeGarantDto);
            TypeGarant = typeGarantDao.save(TypeGarant);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeGarantMapper.deTypeGarant(TypeGarant));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeGarantDto typeGarantDto) {
        try {
            if(!typeGarantDao.existsById(typeGarantDto.getIdTypeGarant()))
                throw  new EntityNotFoundException(TypeGarant.class, "ID", typeGarantDto.getIdTypeGarant().toString());
            TypeGarant typeGarant = typeGarantMapper.deTypeGarantDto(typeGarantDto);
            typeGarant= typeGarantDao.save(typeGarant);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeGarantMapper.deTypeGarant(typeGarant));
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
            typeGarantDao.deleteById(id);
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
