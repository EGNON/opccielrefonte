package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.TypePositionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypePositionDto;
import com.ged.entity.opcciel.comptabilite.TypePosition;
import com.ged.mapper.opcciel.TypePositionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TypePositionService;
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
public class TypePositionServiceImpl implements TypePositionService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypePositionDao typePositionDao;
    private final TypePositionMapper typePositionMapper;

    public TypePositionServiceImpl(TypePositionDao TypePositionDao, TypePositionMapper TypePositionMapper){

        this.typePositionDao = TypePositionDao;
        this.typePositionMapper = TypePositionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypePosition");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypePosition> typePositionPage;
            typePositionPage = typePositionDao.findBySupprimer(false,pageable);
            List<TypePositionDto> content = typePositionPage.getContent().stream().map(typePositionMapper::deTypePosition).collect(Collectors.toList());
            DataTablesResponse<TypePositionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typePositionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typePositionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Position par page datatable",
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
    public Page<TypePositionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypePosition");
            List<TypePositionDto> TypePositionDtos = typePositionDao.findBySupprimerOrderByLibelleTypePositionAsc(false).stream().map(typePositionMapper::deTypePosition).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Position",
                    HttpStatus.OK,
                    TypePositionDtos);
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
    public TypePosition afficherSelonId(String code) {
        return typePositionDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypePosition.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypePosition dont ID = " + code,
                    HttpStatus.OK,
                    typePositionMapper.deTypePosition(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypePositionDto TypePositionDto) {
        try {
            TypePositionDto.setSupprimer(false);
            TypePosition  TypePosition = typePositionMapper.deTypePositionDto(TypePositionDto);
            TypePosition = typePositionDao.save(TypePosition);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typePositionMapper.deTypePosition(TypePosition));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypePositionDto typePositionDto) {
        try {
            if(!typePositionDao.existsById(typePositionDto.getCodeTypePosition()))
                throw  new EntityNotFoundException(TypePosition.class, "ID", typePositionDto.getCodeTypePosition().toString());
            typePositionDto.setSupprimer(false);
            TypePosition typePosition = typePositionMapper.deTypePositionDto(typePositionDto);
            typePosition= typePositionDao.save(typePosition);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typePositionMapper.deTypePosition(typePosition));
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
            typePositionDao.deleteById(code);
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
