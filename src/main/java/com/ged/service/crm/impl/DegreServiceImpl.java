package com.ged.service.crm.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.crm.DegreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.DegreDto;
import com.ged.entity.crm.Degre;
import com.ged.mapper.crm.DegreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.crm.DegreService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DegreServiceImpl implements DegreService {
    private final DegreDao degreDao;
    private final DegreMapper degreMapper;

    public DegreServiceImpl(DegreDao degreDao, DegreMapper degreMapper) {
        this.degreDao = degreDao;
        this.degreMapper = degreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Degre> degrePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
//                ObjectMapper mapper = new ObjectMapper();
//                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
                degrePage = degreDao.findByLibelleContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            }
            else {
                degrePage = degreDao.findAll(pageable);
            }
            List<DegreDto> content = degrePage.getContent().stream().map(degreMapper::deDegre).collect(Collectors.toList());
            DataTablesResponse<DegreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)degrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)degrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des dégrés par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
            return ResponseHandler.generateResponse(
                    "Liste de tous les dégrés",
                    HttpStatus.OK,
                    degreDao.findAll(sort).stream().map(degreMapper::deDegre).collect(Collectors.toList()));
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
    public List<DegreDto> afficherListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        return degreDao.findAll(sort).stream().map(degreMapper::deDegre).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> afficherDegres(int page, int size) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
            PageRequest pageRequest = PageRequest.of(page, size,sort);
            Page<Degre> degrePage = degreDao.findAll(pageRequest);
            return ResponseHandler.generateResponse(
                    "Liste des dégrés de " + page + " à " + size,
                    HttpStatus.OK,
                    new PageImpl<>(degrePage.getContent().stream().map(degreMapper::deDegre).collect(Collectors.toList()),
                            pageRequest, degrePage.getTotalElements()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherDegre(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Dégré dont ID = " + id.toString(),
                    HttpStatus.OK,
                    degreMapper.deDegre(afficherDegreSelonId(id)));
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
    public Degre afficherDegreSelonId(Long idDegre) {
        return degreDao.findById(idDegre).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", idDegre.toString()));
    }

    @Override
    public ResponseEntity<Object> rechercherDegreParLibelle(String libelle) {
        try {
            Degre degre = degreDao.findByLibelle(libelle);
            return ResponseHandler.generateResponse(
                    "Dégré dont Libellé = " + libelle,
                    HttpStatus.OK,
                    degreMapper.deDegre(degre));
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
    public ResponseEntity<Object> creerDegre(DegreDto degreDto) {
        try {
            Degre degre = degreMapper.deDegreDto(degreDto);
            Degre degreSaved = degreDao.save(degre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    degreMapper.deDegre(degreSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifierDegre(DegreDto degreDto) {
        try {
            if(!degreDao.existsById(degreDto.getIdDegre()))
                throw  new EntityNotFoundException(Degre.class, "id", degreDto.getIdDegre().toString());
            Degre degre = degreMapper.deDegreDto(degreDto);
            degre = degreDao.save(degre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    degreMapper.deDegre(degre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimerDegre(Long id) {
        try {
            degreDao.deleteById(id);
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
