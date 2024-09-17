package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeActionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeActionDto;
import com.ged.entity.titresciel.TypeAction;
import com.ged.mapper.titresciel.TypeActionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeActionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeActionServiceImpl implements TypeActionService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final TypeActionDao typeActionDao;
    private final TypeActionMapper typeActionMapper;

    public TypeActionServiceImpl(TypeActionDao TypeActionDao, TypeActionMapper TypeActionMapper){
        this.typeActionDao = TypeActionDao;

        this.typeActionMapper = TypeActionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeAction");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<TypeAction> TypeActionPage;
            TypeActionPage = typeActionDao.findAll(pageable);
            List<TypeActionDto> content = TypeActionPage.getContent().stream().map(typeActionMapper::deTypeAction).collect(Collectors.toList());
            DataTablesResponse<TypeActionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TypeActionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TypeActionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des  type action par page datatable",
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
    public Page<TypeActionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeAction");
            List<TypeActionDto> TypeActions = typeActionDao.findAll().stream().map(typeActionMapper::deTypeAction).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des  type action",
                    HttpStatus.OK,
                    TypeActions);
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
    public TypeAction afficherSelonId(Long id) {
        return typeActionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeAction.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    " type action dont ID = " + id,
                    HttpStatus.OK,
                    typeActionMapper.deTypeAction(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeActionDto TypeActionDto) {
        try {
            TypeAction TypeAction = typeActionMapper.deTypeActionDto(TypeActionDto);
            TypeAction = typeActionDao.save(TypeAction);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeActionMapper.deTypeAction(TypeAction));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeActionDto TypeActionDto) {
        try {
            if(!typeActionDao.existsById(TypeActionDto.getIdTypeAction()))
                throw  new EntityNotFoundException(TypeAction.class, "ID", TypeActionDto.getIdTypeAction().toString());
            TypeAction TypeAction = typeActionMapper.deTypeActionDto(TypeActionDto);
            TypeAction = typeActionDao.save(TypeAction);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeActionMapper.deTypeAction(TypeAction));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOperation) {
        try {
            typeActionDao.deleteById(idOperation);
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
