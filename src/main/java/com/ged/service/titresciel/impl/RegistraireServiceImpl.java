package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.RegistraireDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.RegistraireDto;
import com.ged.entity.titresciel.Registraire;
import com.ged.mapper.titresciel.RegistraireMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.RegistraireService;
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
public class RegistraireServiceImpl implements RegistraireService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final RegistraireDao registraireDao;
    private final RegistraireMapper registraireMapper;

    public RegistraireServiceImpl(RegistraireDao registraireDao, RegistraireMapper registraireMapper){

        this.registraireDao = registraireDao;
        this.registraireMapper = registraireMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Registraire> registrairePage;
            registrairePage = registraireDao.findAll(pageable);
            List<RegistraireDto> content = registrairePage.getContent().stream().map(registraireMapper::deRegistraire).collect(Collectors.toList());
            DataTablesResponse<RegistraireDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)registrairePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)registrairePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des registraires par page datatable",
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
    public Page<RegistraireDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            List<RegistraireDto> registraireDtos = registraireDao.findAll(sort).stream().map(registraireMapper::deRegistraire).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des registraires ",
                    HttpStatus.OK,
                    registraireDtos);
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
    public Registraire afficherSelonId(Long idPersonne) {
        return registraireDao.findById(idPersonne).orElseThrow(() -> new EntityNotFoundException(Registraire.class, "ID",idPersonne.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long idPersonne) {
        try {
            return ResponseHandler.generateResponse(
                    "Registraire dont ID = " + idPersonne,
                    HttpStatus.OK,
                    registraireMapper.deRegistraire(afficherSelonId(idPersonne)));
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
    public ResponseEntity<Object> creer(RegistraireDto RegistraireDto) {
        try {
            Registraire  registraire =registraireMapper.deRegistraireDto(RegistraireDto);
            registraire = registraireDao.save(registraire);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    registraireMapper.deRegistraire(registraire));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(RegistraireDto registraireDto) {
        try {
            if(!registraireDao.existsById(registraireDto.getIdPersonne()))
                throw  new EntityNotFoundException(Registraire.class, "ID", registraireDto.getIdPersonne().toString());
            Registraire registraire =registraireMapper.deRegistraireDto(registraireDto);
            registraire=registraireDao.save(registraire);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    registraireMapper.deRegistraire(registraire));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idPersonne) {
        try {
            registraireDao.deleteById(idPersonne);
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
