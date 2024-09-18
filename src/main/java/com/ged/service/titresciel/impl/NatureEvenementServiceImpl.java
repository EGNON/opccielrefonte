package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.NatureEvenementDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.NatureEvenementDto;
import com.ged.entity.titresciel.NatureEvenement;
import com.ged.mapper.titresciel.NatureEvenementMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.NatureEvenementService;
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
public class NatureEvenementServiceImpl implements NatureEvenementService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final NatureEvenementDao natureEvenementDao;
    private final NatureEvenementMapper natureEvenementMapper;

    public NatureEvenementServiceImpl(NatureEvenementDao NatureEvenementDao, NatureEvenementMapper NatureEvenementMapper){
        this.natureEvenementDao = NatureEvenementDao;

        this.natureEvenementMapper = NatureEvenementMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleNatureEvenement");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<NatureEvenement> NatureEvenementPage;
            NatureEvenementPage = natureEvenementDao.findAll(pageable);
            List<NatureEvenementDto> content = NatureEvenementPage.getContent().stream().map(natureEvenementMapper::deNatureEvenement).collect(Collectors.toList());
            DataTablesResponse<NatureEvenementDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)NatureEvenementPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)NatureEvenementPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Nature évènements par page datatable",
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
    public Page<NatureEvenementDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleNatureEvenement");
            List<NatureEvenementDto> NatureEvenements = natureEvenementDao.findAll().stream().map(natureEvenementMapper::deNatureEvenement).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Natures  évenements ",
                    HttpStatus.OK,
                    NatureEvenements);
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
    public NatureEvenement afficherSelonId(Long id) {
        return natureEvenementDao.findById(id).orElseThrow(() -> new EntityNotFoundException(NatureEvenement.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Nature évènement dont ID = " + id,
                    HttpStatus.OK,
                    natureEvenementMapper.deNatureEvenement(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(NatureEvenementDto NatureEvenementDto) {
        try {
            NatureEvenement NatureEvenement = natureEvenementMapper.deNatureEvenementDto(NatureEvenementDto);
            NatureEvenement = natureEvenementDao.save(NatureEvenement);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    natureEvenementMapper.deNatureEvenement(NatureEvenement));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(NatureEvenementDto NatureEvenementDto) {
        try {
            if(!natureEvenementDao.existsById(NatureEvenementDto.getIdNatureEvenement()))
                throw  new EntityNotFoundException(NatureEvenement.class, "ID", NatureEvenementDto.getIdNatureEvenement().toString());
            NatureEvenement NatureEvenement = natureEvenementMapper.deNatureEvenementDto(NatureEvenementDto);
            NatureEvenement = natureEvenementDao.save(NatureEvenement);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    natureEvenementMapper.deNatureEvenement(NatureEvenement));
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
            natureEvenementDao.deleteById(idOperation);
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
