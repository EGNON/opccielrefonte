package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.SecteurBoursierDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.SecteurBoursierDto;
import com.ged.entity.titresciel.SecteurBoursier;
import com.ged.mapper.titresciel.SecteurBoursierMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.SecteurBoursierService;
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
public class SecteurBoursierServiceImpl implements SecteurBoursierService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final SecteurBoursierDao secteurBoursierDao;
    private final SecteurBoursierMapper secteurBoursierMapper;

    public SecteurBoursierServiceImpl(SecteurBoursierDao SecteurBoursierDao, SecteurBoursierMapper SecteurBoursierMapper){
        this.secteurBoursierDao = SecteurBoursierDao;

        this.secteurBoursierMapper = SecteurBoursierMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleSecteurBoursier");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<SecteurBoursier> SecteurBoursierPage;
            SecteurBoursierPage = secteurBoursierDao.findAll(pageable);
            List<SecteurBoursierDto> content = SecteurBoursierPage.getContent().stream().map(secteurBoursierMapper::deSecteurBoursier).collect(Collectors.toList());
            DataTablesResponse<SecteurBoursierDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)SecteurBoursierPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)SecteurBoursierPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des sous type action par page datatable",
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
    public Page<SecteurBoursierDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleSecteurBoursier");
            List<SecteurBoursierDto> SecteurBoursiers = secteurBoursierDao.findAll().stream().map(secteurBoursierMapper::deSecteurBoursier).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des sous type action",
                    HttpStatus.OK,
                    SecteurBoursiers);
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
    public SecteurBoursier afficherSelonId(Long id) {
        return secteurBoursierDao.findById(id).orElseThrow(() -> new EntityNotFoundException(SecteurBoursier.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "sous type action dont ID = " + id,
                    HttpStatus.OK,
                    secteurBoursierMapper.deSecteurBoursier(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(SecteurBoursierDto SecteurBoursierDto) {
        try {
            SecteurBoursier SecteurBoursier = secteurBoursierMapper.deSecteurBoursierDto(SecteurBoursierDto);
            SecteurBoursier = secteurBoursierDao.save(SecteurBoursier);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    secteurBoursierMapper.deSecteurBoursier(SecteurBoursier));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(SecteurBoursierDto SecteurBoursierDto) {
        try {
            if(!secteurBoursierDao.existsById(SecteurBoursierDto.getIdSecteurBoursier()))
                throw  new EntityNotFoundException(SecteurBoursier.class, "ID", SecteurBoursierDto.getIdSecteurBoursier().toString());
            SecteurBoursier SecteurBoursier = secteurBoursierMapper.deSecteurBoursierDto(SecteurBoursierDto);
            SecteurBoursier = secteurBoursierDao.save(SecteurBoursier);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    secteurBoursierMapper.deSecteurBoursier(SecteurBoursier));
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
            secteurBoursierDao.deleteById(idOperation);
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
