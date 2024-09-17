package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeObligationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeObligationDto;
import com.ged.entity.titresciel.TypeObligation;
import com.ged.mapper.titresciel.TypeObligationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeObligationService;
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
public class TypeObligationServiceImpl implements TypeObligationService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final TypeObligationDao typeObligationDao;
    private final TypeObligationMapper typeObligationMapper;

    public TypeObligationServiceImpl(TypeObligationDao TypeObligationDao, TypeObligationMapper TypeObligationMapper){

        this.typeObligationDao = TypeObligationDao;
        this.typeObligationMapper = TypeObligationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeObligation");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeObligation> typeObligationPage;
            typeObligationPage = typeObligationDao.findAll(pageable);
            List<TypeObligationDto> content = typeObligationPage.getContent().stream().map(typeObligationMapper::deTypeObligation).collect(Collectors.toList());
            DataTablesResponse<TypeObligationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeObligationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeObligationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Obligation par page datatable",
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
    public Page<TypeObligationDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeObligation");
            List<TypeObligationDto> TypeObligationDtos = typeObligationDao.findAll(sort).stream().map(typeObligationMapper::deTypeObligation).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Type Obligation ",
                    HttpStatus.OK,
                    TypeObligationDtos);
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
    public TypeObligation afficherSelonId(Long id) {
        return typeObligationDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeObligation.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeObligation dont ID = " + id,
                    HttpStatus.OK,
                    typeObligationMapper.deTypeObligation(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeObligationDto TypeObligationDto) {
        try {
            TypeObligation  TypeObligation = typeObligationMapper.deTypeObligationDto(TypeObligationDto);
            TypeObligation = typeObligationDao.save(TypeObligation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeObligationMapper.deTypeObligation(TypeObligation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeObligationDto typeObligationDto) {
        try {
            if(!typeObligationDao.existsById(typeObligationDto.getIdTypeObligation()))
                throw  new EntityNotFoundException(TypeObligation.class, "ID", typeObligationDto.getIdTypeObligation().toString());
            TypeObligation typeObligation = typeObligationMapper.deTypeObligationDto(typeObligationDto);
            typeObligation= typeObligationDao.save(typeObligation);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeObligationMapper.deTypeObligation(typeObligation));
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
            typeObligationDao.deleteById(id);
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
