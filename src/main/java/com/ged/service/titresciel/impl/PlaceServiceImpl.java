package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.PlaceDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.PlaceDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.titresciel.Place;
import com.ged.mapper.titresciel.PlaceMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.PlaceService;
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
public class PlaceServiceImpl implements PlaceService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final PlaceDao placeDao;
    private final PlaceMapper placeMapper;

    public PlaceServiceImpl(PlaceDao placeDao, PlaceMapper placeMapper){
        this.placeDao = placeDao;
        this.placeMapper = placeMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlace");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Place> placePage;
            placePage = placeDao.findAll(pageable);
            List<PlaceDto> content = placePage.getContent().stream().map(placeMapper::dePlace).collect(Collectors.toList());
            DataTablesResponse<PlaceDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)placePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)placePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des places par page datatable",
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
    public Page<PlaceDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlace");
            List<PlaceDto> placeDtos = placeDao.findAll(sort).stream().map(placeMapper::dePlace).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des places ",
                    HttpStatus.OK,
                    placeDtos);
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
    public Place afficherSelonId(String codePlace) {
        return placeDao.findById(codePlace).orElseThrow(() -> new EntityNotFoundException(Place.class, "code",codePlace));
    }

    @Override
    public ResponseEntity<Object> afficher(String codePlace) {
        try {
            return ResponseHandler.generateResponse(
                    "Place dont CODE = " + codePlace,
                    HttpStatus.OK,
                    placeMapper.dePlace(afficherSelonId(codePlace)));
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
    public ResponseEntity<Object> creer(PlaceDto placeDto) {
        try {
            Place  place =placeMapper.dePlaceDto(placeDto);
            place = placeDao.save(place);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    placeMapper.dePlace(place));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(PlaceDto placeDto) {
        try {
            if(!placeDao.existsById(placeDto.getCodePlace()))
                throw  new EntityNotFoundException(Opcvm.class, "code", placeDto.getCodePlace().toString());
            Place place =placeMapper.dePlaceDto(placeDto);
            place=placeDao.save(place);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    placeMapper.dePlace(place));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codePlace) {
        try {
            placeDao.deleteById(codePlace);
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
