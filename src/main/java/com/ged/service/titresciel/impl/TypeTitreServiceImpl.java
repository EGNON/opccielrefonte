package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeTitreDto;
import com.ged.entity.titresciel.TypeTitre;
import com.ged.mapper.titresciel.TypeTitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeTitreService;
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
public class TypeTitreServiceImpl implements TypeTitreService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypeTitreDao typeTitreDao;
    private final TypeTitreMapper typeTitreMapper;

    public TypeTitreServiceImpl(TypeTitreDao TypeTitreDao, TypeTitreMapper TypeTitreMapper){

        this.typeTitreDao = TypeTitreDao;
        this.typeTitreMapper = TypeTitreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeTitre");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeTitre> typeTitrePage;
            typeTitrePage = typeTitreDao.findAll(pageable);
            List<TypeTitreDto> content = typeTitrePage.getContent().stream().map(typeTitreMapper::deTypeTitre).collect(Collectors.toList());
            DataTablesResponse<TypeTitreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeTitrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeTitrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Titre par page datatable",
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
    public Page<TypeTitreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeTitre");
            List<TypeTitreDto> TypeTitreDtos = typeTitreDao.findAll(sort).stream().map(typeTitreMapper::deTypeTitre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Titre",
                    HttpStatus.OK,
                    TypeTitreDtos);
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
    public TypeTitre afficherSelonId(String code) {
        return typeTitreDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypeTitre.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeTitre dont ID = " + code,
                    HttpStatus.OK,
                    typeTitreMapper.deTypeTitre(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypeTitreDto TypeTitreDto) {
        try {
            TypeTitreDto.setSupprimer(false);
            TypeTitre  TypeTitre = typeTitreMapper.deTypeTitreDto(TypeTitreDto);
            TypeTitre = typeTitreDao.save(TypeTitre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeTitreMapper.deTypeTitre(TypeTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeTitreDto typeTitreDto) {
        try {
            if(!typeTitreDao.existsById(typeTitreDto.getCodeTypeTitre()))
                throw  new EntityNotFoundException(TypeTitre.class, "ID", typeTitreDto.getCodeTypeTitre().toString());
            typeTitreDto.setSupprimer(false);
            TypeTitre typeTitre = typeTitreMapper.deTypeTitreDto(typeTitreDto);
            typeTitre= typeTitreDao.save(typeTitre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeTitreMapper.deTypeTitre(typeTitre));
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
            typeTitreDao.deleteById(code);
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
