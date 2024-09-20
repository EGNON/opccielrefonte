package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.EmetteurDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.EmetteurDto;
import com.ged.entity.titresciel.Emetteur;
import com.ged.mapper.titresciel.EmetteurMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.EmetteurService;
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
public class EmetteurServiceImpl implements EmetteurService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final EmetteurDao EmetteurDao;
    private final EmetteurMapper emetteurMapper;

    public EmetteurServiceImpl(EmetteurDao EmetteurDao, EmetteurMapper EmetteurMapper){
        this.EmetteurDao = EmetteurDao;

        this.emetteurMapper = EmetteurMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomEmetteur");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Emetteur> EmetteurPage;
            EmetteurPage = EmetteurDao.findAll(pageable);
            List<EmetteurDto> content = EmetteurPage.getContent().stream().map(emetteurMapper::deEmetteur).collect(Collectors.toList());
            DataTablesResponse<EmetteurDto> EmetteuraTablesResponse = new DataTablesResponse<>();
            EmetteuraTablesResponse.setDraw(parameters.getDraw());
            EmetteuraTablesResponse.setRecordsFiltered((int)EmetteurPage.getTotalElements());
            EmetteuraTablesResponse.setRecordsTotal((int)EmetteurPage.getTotalElements());
            EmetteuraTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Emetteurs par page Emetteuratable",
                    HttpStatus.OK,
                    EmetteuraTablesResponse);
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
    public Page<EmetteurDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomEmetteur");
            List<EmetteurDto> Emetteurs = EmetteurDao.findAll().stream().map(emetteurMapper::deEmetteur).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Emetteurs",
                    HttpStatus.OK,
                    Emetteurs);
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
    public Emetteur afficherSelonId(Long id) {
        return EmetteurDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Emetteur.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Emetteur dont ID = " + id,
                    HttpStatus.OK,
                    emetteurMapper.deEmetteur(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(EmetteurDto EmetteurDto) {
        try {
            Emetteur Emetteur = emetteurMapper.deEmetteurDto(EmetteurDto);
            Emetteur = EmetteurDao.save(Emetteur);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    emetteurMapper.deEmetteur(Emetteur));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(EmetteurDto EmetteurDto) {
        try {
            if(!EmetteurDao.existsById(EmetteurDto.getIdPersonne()))
                throw  new EntityNotFoundException(Emetteur.class, "ID", EmetteurDto.getIdPersonne().toString());
            Emetteur Emetteur = emetteurMapper.deEmetteurDto(EmetteurDto);
            Emetteur = EmetteurDao.save(Emetteur);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    emetteurMapper.deEmetteur(Emetteur));
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
            EmetteurDao.deleteById(idOperation);
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
