package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeEvenementDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEvenementDto;
import com.ged.entity.titresciel.TypeEvenement;
import com.ged.mapper.titresciel.TypeEvenementMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeEvenementService;
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
public class TypeEvenementServiceImpl implements TypeEvenementService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final TypeEvenementDao typeEvenementDao;
    private final TypeEvenementMapper typeEvenementMapper;

    public TypeEvenementServiceImpl(TypeEvenementDao TypeEvenementDao, TypeEvenementMapper TypeEvenementMapper){

        this.typeEvenementDao = TypeEvenementDao;
        this.typeEvenementMapper = TypeEvenementMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeEvenement");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeEvenement> typeEvenementPage;
            typeEvenementPage = typeEvenementDao.findAll(pageable);
            List<TypeEvenementDto> content = typeEvenementPage.getContent().stream().map(typeEvenementMapper::deTypeEvenement).collect(Collectors.toList());
            DataTablesResponse<TypeEvenementDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeEvenementPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeEvenementPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Evenement par page datatable",
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
    public Page<TypeEvenementDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeEvenement");
            List<TypeEvenementDto> TypeEvenementDtos = typeEvenementDao.findAll(sort).stream().map(typeEvenementMapper::deTypeEvenement).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Type Evenement ",
                    HttpStatus.OK,
                    TypeEvenementDtos);
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
    public TypeEvenement afficherSelonId(Long id) {
        return typeEvenementDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeEvenement.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeEvenement dont ID = " + id,
                    HttpStatus.OK,
                    typeEvenementMapper.deTypeEvenement(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeEvenementDto TypeEvenementDto) {
        try {
            TypeEvenement  TypeEvenement = typeEvenementMapper.deTypeEvenementDto(TypeEvenementDto);
            TypeEvenement = typeEvenementDao.save(TypeEvenement);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeEvenementMapper.deTypeEvenement(TypeEvenement));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeEvenementDto typeEvenementDto) {
        try {
            if(!typeEvenementDao.existsById(typeEvenementDto.getIdTypeEvenement()))
                throw  new EntityNotFoundException(TypeEvenement.class, "ID", typeEvenementDto.getIdTypeEvenement().toString());
            TypeEvenement typeEvenement = typeEvenementMapper.deTypeEvenementDto(typeEvenementDto);
            typeEvenement= typeEvenementDao.save(typeEvenement);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeEvenementMapper.deTypeEvenement(typeEvenement));
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
            typeEvenementDao.deleteById(id);
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
