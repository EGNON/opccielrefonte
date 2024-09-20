package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.TypeIbDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeIbDto;
import com.ged.entity.opcciel.comptabilite.TypeIb;
import com.ged.mapper.opcciel.TypeIbMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TypeIbService;
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
public class TypeIbServiceImpl implements TypeIbService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypeIbDao typeIbDao;
    private final TypeIbMapper typeIbMapper;

    public TypeIbServiceImpl(TypeIbDao TypeIbDao, TypeIbMapper TypeIbMapper){

        this.typeIbDao = TypeIbDao;
        this.typeIbMapper = TypeIbMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeIb");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeIb> typeIbPage;
            typeIbPage = typeIbDao.findBySupprimer(false,pageable);
            List<TypeIbDto> content = typeIbPage.getContent().stream().map(typeIbMapper::deTypeIb).collect(Collectors.toList());
            DataTablesResponse<TypeIbDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeIbPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeIbPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Ib par page datatable",
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
    public Page<TypeIbDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeIb");
            List<TypeIbDto> TypeIbDtos = typeIbDao.findBySupprimerOrderByLibelleTypeIBAsc(false).stream().map(typeIbMapper::deTypeIb).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Ib",
                    HttpStatus.OK,
                    TypeIbDtos);
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
    public TypeIb afficherSelonId(String code) {
        return typeIbDao.findById(code).orElseThrow(() -> new EntityNotFoundException(TypeIb.class, "ID",code.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(String code) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeIb dont ID = " + code,
                    HttpStatus.OK,
                    typeIbMapper.deTypeIb(afficherSelonId(code)));
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
    public ResponseEntity<Object> creer(TypeIbDto TypeIbDto) {
        try {
            TypeIbDto.setSupprimer(false);
            TypeIb  TypeIb = typeIbMapper.deTypeIbDto(TypeIbDto);
            TypeIb = typeIbDao.save(TypeIb);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeIbMapper.deTypeIb(TypeIb));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeIbDto typeIbDto) {
        try {
            if(!typeIbDao.existsById(typeIbDto.getCodeTypeIb()))
                throw  new EntityNotFoundException(TypeIb.class, "ID", typeIbDto.getCodeTypeIb().toString());
            typeIbDto.setSupprimer(false);
            TypeIb typeIb = typeIbMapper.deTypeIbDto(typeIbDto);
            typeIb= typeIbDao.save(typeIb);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeIbMapper.deTypeIb(typeIb));
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
            typeIbDao.deleteById(code);
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
