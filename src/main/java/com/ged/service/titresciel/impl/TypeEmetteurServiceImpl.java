package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeEmetteurDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEmetteurDto;
import com.ged.entity.titresciel.TypeEmetteur;
import com.ged.mapper.titresciel.TypeEmetteurMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeEmetteurService;
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
public class TypeEmetteurServiceImpl implements TypeEmetteurService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypeEmetteurDao typeEmetteurDao;
    private final TypeEmetteurMapper typeEmetteurMapper;

    public TypeEmetteurServiceImpl(TypeEmetteurDao TypeEmetteurDao, TypeEmetteurMapper TypeEmetteurMapper){

        this.typeEmetteurDao = TypeEmetteurDao;
        this.typeEmetteurMapper = TypeEmetteurMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeEmetteur");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeEmetteur> typeEmetteurPage;
            typeEmetteurPage = typeEmetteurDao.findAll(pageable);
            List<TypeEmetteurDto> content = typeEmetteurPage.getContent().stream().map(typeEmetteurMapper::deTypeEmetteur).collect(Collectors.toList());
            DataTablesResponse<TypeEmetteurDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeEmetteurPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeEmetteurPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Emetteur par page datatable",
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
    public Page<TypeEmetteurDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeEmetteur");
            List<TypeEmetteurDto> TypeEmetteurDtos = typeEmetteurDao.findAll(sort).stream().map(typeEmetteurMapper::deTypeEmetteur).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des TypeEmetteurs ",
                    HttpStatus.OK,
                    TypeEmetteurDtos);
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
    public TypeEmetteur afficherSelonId(String code) {
        return typeEmetteurDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypeEmetteur.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeEmetteur dont ID = " + code,
                    HttpStatus.OK,
                    typeEmetteurMapper.deTypeEmetteur(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypeEmetteurDto TypeEmetteurDto) {
        try {
            TypeEmetteur  TypeEmetteur = typeEmetteurMapper.deTypeEmetteurDto(TypeEmetteurDto);
            TypeEmetteur = typeEmetteurDao.save(TypeEmetteur);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeEmetteurMapper.deTypeEmetteur(TypeEmetteur));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeEmetteurDto typeEmetteurDto) {
        try {
            if(!typeEmetteurDao.existsById(typeEmetteurDto.getCodeTypeEmetteur()))
                throw  new EntityNotFoundException(TypeEmetteur.class, "ID", typeEmetteurDto.getCodeTypeEmetteur().toString());
            TypeEmetteur typeEmetteur = typeEmetteurMapper.deTypeEmetteurDto(typeEmetteurDto);
            typeEmetteur= typeEmetteurDao.save(typeEmetteur);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeEmetteurMapper.deTypeEmetteur(typeEmetteur));
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
            typeEmetteurDao.deleteById(code);
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
