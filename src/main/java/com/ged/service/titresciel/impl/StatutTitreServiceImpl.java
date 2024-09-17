package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.StatutTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.StatutTitreDto;
import com.ged.entity.titresciel.CleStatutTitre;
import com.ged.entity.titresciel.StatutTitre;
import com.ged.mapper.titresciel.StatutTitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.StatutTitreService;
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
public class StatutTitreServiceImpl implements StatutTitreService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final StatutTitreDao StatutTitreDao;
    private final StatutTitreMapper StatutTitreMapper;

    public StatutTitreServiceImpl(StatutTitreDao StatutTitreDao, StatutTitreMapper StatutTitreMapper){
        this.StatutTitreDao = StatutTitreDao;

        this.StatutTitreMapper = StatutTitreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomStatutTitre");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<StatutTitre> StatutTitrePage;
            StatutTitrePage = StatutTitreDao.findAll(pageable);
            List<StatutTitreDto> content = StatutTitrePage.getContent().stream().map(StatutTitreMapper::deStatutTitre).collect(Collectors.toList());
            DataTablesResponse<StatutTitreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)StatutTitrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)StatutTitrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des StatutTitres par page datatable",
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
    public Page<StatutTitreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomStatutTitre");
            List<StatutTitreDto> StatutTitres = StatutTitreDao.findAll().stream().map(StatutTitreMapper::deStatutTitre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des StatutTitres",
                    HttpStatus.OK,
                    StatutTitres);
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
    public StatutTitre afficherSelonId(CleStatutTitre id) {
        return StatutTitreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(StatutTitre.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleStatutTitre id) {
        try {
            return ResponseHandler.generateResponse(
                    "StatutTitre dont ID = " + id,
                    HttpStatus.OK,
                    StatutTitreMapper.deStatutTitre(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(StatutTitreDto StatutTitreDto) {
        try {
            StatutTitre StatutTitre = StatutTitreMapper.deStatutTitreDto(StatutTitreDto);
            CleStatutTitre cleStatutTitre=new CleStatutTitre();
            cleStatutTitre.setIdTitre(StatutTitreDto.getTitre().getIdTitre());
            cleStatutTitre.setIdQualite(StatutTitreDto.getQualiteTitre().getIdQualite());
            StatutTitre.setIdStatutTitre(cleStatutTitre);

            StatutTitre = StatutTitreDao.save(StatutTitre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    StatutTitreMapper.deStatutTitre(StatutTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(StatutTitreDto StatutTitreDto) {
        try {
            StatutTitre StatutTitre = StatutTitreMapper.deStatutTitreDto(StatutTitreDto);
            CleStatutTitre cleStatutTitre=new CleStatutTitre();
            cleStatutTitre.setIdTitre(StatutTitreDto.getTitre().getIdTitre());
            cleStatutTitre.setIdQualite(StatutTitreDto.getQualiteTitre().getIdQualite());
            StatutTitre.setIdStatutTitre(cleStatutTitre);
            StatutTitre = StatutTitreDao.save(StatutTitre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès!",
                    HttpStatus.OK,
                    StatutTitreMapper.deStatutTitre(StatutTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleStatutTitre id) {
        try {
            StatutTitreDao.deleteById(id);
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
