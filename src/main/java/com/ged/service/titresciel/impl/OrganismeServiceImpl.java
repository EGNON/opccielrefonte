package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.OrganismeDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.OrganismeDto;
import com.ged.entity.titresciel.Organisme;
import com.ged.mapper.titresciel.OrganismeMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.OrganismeService;
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
public class OrganismeServiceImpl implements OrganismeService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOrganismeciel;*/
    private final OrganismeDao OrganismeDao;
    private final OrganismeMapper OrganismeMapper;

    public OrganismeServiceImpl(OrganismeDao OrganismeDao, OrganismeMapper OrganismeMapper){
        this.OrganismeDao = OrganismeDao;

        this.OrganismeMapper = OrganismeMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomOrganisme");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Organisme> OrganismePage;
            OrganismePage = OrganismeDao.findAll(pageable);
            List<OrganismeDto> content = OrganismePage.getContent().stream().map(OrganismeMapper::deOrganisme).collect(Collectors.toList());
            DataTablesResponse<OrganismeDto> OrganismeaTablesResponse = new DataTablesResponse<>();
            OrganismeaTablesResponse.setDraw(parameters.getDraw());
            OrganismeaTablesResponse.setRecordsFiltered((int)OrganismePage.getTotalElements());
            OrganismeaTablesResponse.setRecordsTotal((int)OrganismePage.getTotalElements());
            OrganismeaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Organismes par page Organismeatable",
                    HttpStatus.OK,
                    OrganismeaTablesResponse);
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
    public Page<OrganismeDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomOrganisme");
            List<OrganismeDto> Organismes = OrganismeDao.findAll().stream().map(OrganismeMapper::deOrganisme).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Organismes",
                    HttpStatus.OK,
                    Organismes);
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
    public Organisme afficherSelonId(Long id) {
        return OrganismeDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Organisme.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Organisme dont ID = " + id,
                    HttpStatus.OK,
                    OrganismeMapper.deOrganisme(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(OrganismeDto OrganismeDto) {
        try {
            Organisme Organisme = OrganismeMapper.deOrganismeDto(OrganismeDto);
            Organisme = OrganismeDao.save(Organisme);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    OrganismeMapper.deOrganisme(Organisme));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OrganismeDto OrganismeDto) {
        try {
            if(!OrganismeDao.existsById(OrganismeDto.getIdPersonne()))
                throw  new EntityNotFoundException(Organisme.class, "ID", OrganismeDto.getIdPersonne().toString());
            Organisme Organisme = OrganismeMapper.deOrganismeDto(OrganismeDto);
            Organisme = OrganismeDao.save(Organisme);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    OrganismeMapper.deOrganisme(Organisme));
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
            OrganismeDao.deleteById(idOperation);
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
