package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.TypeOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeOperationDto;
import com.ged.entity.opcciel.comptabilite.TypeOperation;
import com.ged.mapper.opcciel.TypeOperationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TypeOperationService;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeOperationServiceImpl implements TypeOperationService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final TypeOperationDao typeOperationDao;
    private final TypeOperationMapper typeOperationMapper;

    public TypeOperationServiceImpl(TypeOperationDao TypeOperationDao, TypeOperationMapper TypeOperationMapper){

        this.typeOperationDao = TypeOperationDao;
        this.typeOperationMapper = TypeOperationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeOperation");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeOperation> typeOperationPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                typeOperationPage = typeOperationDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {
                typeOperationPage = typeOperationDao.findBySupprimer(false,pageable);
            }

            List<TypeOperationDto> content = typeOperationPage.getContent().stream().map(typeOperationMapper::deTypeOperation).collect(Collectors.toList());
            DataTablesResponse<TypeOperationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeOperationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeOperationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Operation par page datatable",
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
    public Page<TypeOperationDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeOperation");
            List<TypeOperationDto> TypeOperationDtos = typeOperationDao.findBySupprimerOrderByLibelleTypeOperationAsc(false).stream().map(typeOperationMapper::deTypeOperation).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Operation",
                    HttpStatus.OK,
                    TypeOperationDtos);
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
    public TypeOperation afficherSelonId(String code) {
        return typeOperationDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypeOperation.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeOperation dont ID = " + code,
                    HttpStatus.OK,
                    typeOperationMapper.deTypeOperation(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypeOperationDto TypeOperationDto) {
        try {
            TypeOperationDto.setSupprimer(false);
            TypeOperation  TypeOperation = typeOperationMapper.deTypeOperationDto(TypeOperationDto);
            TypeOperation = typeOperationDao.save(TypeOperation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeOperationMapper.deTypeOperation(TypeOperation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeOperationDto typeOperationDto) {
        try {
            if(!typeOperationDao.existsById(typeOperationDto.getCodeTypeOperation()))
                throw  new EntityNotFoundException(TypeOperation.class, "ID", typeOperationDto.getCodeTypeOperation().toString());
            typeOperationDto.setSupprimer(false);
            TypeOperation typeOperation = typeOperationMapper.deTypeOperationDto(typeOperationDto);
            typeOperation= typeOperationDao.save(typeOperation);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeOperationMapper.deTypeOperation(typeOperation));
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
            typeOperationDao.deleteById(code);
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
