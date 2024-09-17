package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.DepositaireDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DepositaireDto;
import com.ged.entity.titresciel.Depositaire;
import com.ged.mapper.titresciel.DepositaireMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.DepositaireService;
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
public class DepositaireServiceImpl implements DepositaireService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final DepositaireDao DepositaireDao;
    private final DepositaireMapper depositaireMapper;

    public DepositaireServiceImpl(DepositaireDao DepositaireDao, DepositaireMapper DepositaireMapper){
        this.DepositaireDao = DepositaireDao;

        this.depositaireMapper = DepositaireMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomDepositaire");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Depositaire> DepositairePage;
            DepositairePage = DepositaireDao.findAll(pageable);
            List<DepositaireDto> content = DepositairePage.getContent().stream().map(depositaireMapper::deDepositaire).collect(Collectors.toList());
            DataTablesResponse<DepositaireDto> DepositaireaTablesResponse = new DataTablesResponse<>();
            DepositaireaTablesResponse.setDraw(parameters.getDraw());
            DepositaireaTablesResponse.setRecordsFiltered((int)DepositairePage.getTotalElements());
            DepositaireaTablesResponse.setRecordsTotal((int)DepositairePage.getTotalElements());
            DepositaireaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Depositaires par page Depositaireatable",
                    HttpStatus.OK,
                    DepositaireaTablesResponse);
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
    public Page<DepositaireDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomDepositaire");
            List<DepositaireDto> Depositaires = DepositaireDao.findAll().stream().map(depositaireMapper::deDepositaire).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Depositaires",
                    HttpStatus.OK,
                    Depositaires);
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
    public Depositaire afficherSelonId(Long id) {
        return DepositaireDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Depositaire.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Depositaire dont ID = " + id,
                    HttpStatus.OK,
                    depositaireMapper.deDepositaire(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(DepositaireDto DepositaireDto) {
        try {
            Depositaire Depositaire = depositaireMapper.deDepositaireDto(DepositaireDto);
            Depositaire = DepositaireDao.save(Depositaire);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    depositaireMapper.deDepositaire(Depositaire));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DepositaireDto DepositaireDto) {
        try {
            if(!DepositaireDao.existsById(DepositaireDto.getIdPersonne()))
                throw  new EntityNotFoundException(Depositaire.class, "ID", DepositaireDto.getIdPersonne().toString());
            Depositaire Depositaire = depositaireMapper.deDepositaireDto(DepositaireDto);
            Depositaire = DepositaireDao.save(Depositaire);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    depositaireMapper.deDepositaire(Depositaire));
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
            DepositaireDao.deleteById(idOperation);
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
