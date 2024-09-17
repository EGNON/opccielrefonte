package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.SousCategorieDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SousCategorieDto;
import com.ged.entity.standard.SousCategorie;
import com.ged.mapper.standard.SousCategorieMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.SousCategorieService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SousCategorieServiceImpl implements SousCategorieService {
    private final SousCategorieDao sousCategorieDao;
    private final SousCategorieMapper sousCategorieMapper;

    public SousCategorieServiceImpl(SousCategorieDao SousCategorieDao, SousCategorieMapper SousCategorieMapper) {
        this.sousCategorieDao = SousCategorieDao;
        this.sousCategorieMapper = SousCategorieMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleSousCategorie");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<SousCategorie> SousCategoriePage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
////                ObjectMapper mapper = new ObjectMapper();
////                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
//                SousCategoriePage = SousCategorieDao.findByLibelleContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
//            }
//            else {
                SousCategoriePage = sousCategorieDao.findAll(pageable);
//            }
            List<SousCategorieDto> content = SousCategoriePage.getContent().stream().map(sousCategorieMapper::deSousCategorie).collect(Collectors.toList());
            DataTablesResponse<SousCategorieDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int) SousCategoriePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int) SousCategoriePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des sous categories par page datatable",
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
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleSousCategorie");
            return ResponseHandler.generateResponse(
                    "Liste de toutes les sous categories",
                    HttpStatus.OK,
                    sousCategorieDao.findAll(sort).stream().map(sousCategorieMapper::deSousCategorie).collect(Collectors.toList()));
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
    public List<SousCategorieDto> afficherListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleSousCategorie");
        return sousCategorieDao.findAll(sort).stream().map(sousCategorieMapper::deSousCategorie).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> afficherSousCategories(int page, int size) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleSousCategorie");
            PageRequest pageRequest = PageRequest.of(page, size,sort);
            Page<SousCategorie> SousCategoriePage = sousCategorieDao.findAll(pageRequest);
            return ResponseHandler.generateResponse(
                    "Liste des sous categories de " + page + " à " + size,
                    HttpStatus.OK,
                    new PageImpl<>(SousCategoriePage.getContent().stream().map(sousCategorieMapper::deSousCategorie).collect(Collectors.toList()),
                            pageRequest, SousCategoriePage.getTotalElements()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherSousCategorie(Long id) {
        try {
//            System.out.println(SousCategorieMapper.deSousCategorie(afficherSousCategorieSelonId(id)).getDescription());
            return ResponseHandler.generateResponse(
                    "Sous categorie dont ID = " + id.toString(),
                    HttpStatus.OK,
                    sousCategorieMapper.deSousCategorie(afficherSousCategorieSelonId(id)));
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
    public SousCategorie afficherSousCategorieSelonId(Long idSousCategorie) {
        return sousCategorieDao.findById(idSousCategorie).orElseThrow(() -> new EntityNotFoundException(SousCategorie.class, "id", idSousCategorie.toString()));
    }

    @Override
    public ResponseEntity<Object> creerSousCategorie(SousCategorieDto SousCategorieDto) {
        try {
            SousCategorie SousCategorie = sousCategorieMapper.deSousCategorieDto(SousCategorieDto);
            SousCategorie SousCategorieSaved = sousCategorieDao.save(SousCategorie);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    sousCategorieMapper.deSousCategorie(SousCategorieSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifierSousCategorie(SousCategorieDto SousCategorieDto) {
        try {
//            if(!SousCategorieDao.existsById(SousCategorieDto.getIdSousCategorie()))
//                throw  new EntityNotFoundException(SousCategorie.class, "id", SousCategorieDto.getIdSousCategorie().toString());
            SousCategorie SousCategorie = sousCategorieMapper.deSousCategorieDto(SousCategorieDto);
            SousCategorie = sousCategorieDao.save(SousCategorie);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    sousCategorieMapper.deSousCategorie(SousCategorie));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimerSousCategorie(Long id) {
        try {
            sousCategorieDao.deleteById(id);
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
