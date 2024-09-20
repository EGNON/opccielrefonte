package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.NormalAssimileDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.NormalAssimileDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.NormalAssimile;
import com.ged.mapper.opcciel.NormalAssimileMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.NormalAssimileService;
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
public class NormalAssimileServiceImpl implements NormalAssimileService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final NormalAssimileDao normalAssimileDao;
    private final NormalAssimileMapper normalAssimileMapper;

    public NormalAssimileServiceImpl(NormalAssimileDao normalAssimileDao, NormalAssimileMapper normalAssimileMapper){
        this.normalAssimileDao = normalAssimileDao;

        this.normalAssimileMapper = normalAssimileMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleNormalAssimile");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<NormalAssimile> normalAssimilePage;
            normalAssimilePage = normalAssimileDao.findAll(pageable);
            List<NormalAssimileDto> content = normalAssimilePage.getContent().stream().map(normalAssimileMapper::deNormalAssimile).collect(Collectors.toList());
            DataTablesResponse<NormalAssimileDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)normalAssimilePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)normalAssimilePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des normales assimilés par page datatable",
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
    public Page<NormalAssimileDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleNormalAssimile");
            List<NormalAssimileDto> normalAssimiles = normalAssimileDao.findAll(sort).stream().map(normalAssimileMapper::deNormalAssimile).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des normales assimilés ",
                    HttpStatus.OK,
                    normalAssimiles);
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
    public NormalAssimile afficherSelonId(String codeNormalAssimile) {
        return normalAssimileDao.findById(codeNormalAssimile).orElseThrow(() -> new EntityNotFoundException(NormalAssimile.class, "code",codeNormalAssimile));
    }

    @Override
    public ResponseEntity<Object> afficher(String codeNormalAssimile) {
        try {
            return ResponseHandler.generateResponse(
                    "NormalAssimile dont CODE = " + codeNormalAssimile,
                    HttpStatus.OK,
                    normalAssimileMapper.deNormalAssimile(afficherSelonId(codeNormalAssimile)));
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
    public ResponseEntity<Object> creer(NormalAssimileDto normalAssimileDto) {
        try {
            NormalAssimile normalAssimile = normalAssimileMapper.deNormalAssimileDto(normalAssimileDto);
            normalAssimile = normalAssimileDao.save(normalAssimile);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    normalAssimileMapper.deNormalAssimile(normalAssimile));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(NormalAssimileDto normalAssimileDto) {
        try {
            if(!normalAssimileDao.existsById(normalAssimileDto.getCodeNormalAssimile()))
                throw  new EntityNotFoundException(Opcvm.class, "code", normalAssimileDto.getCodeNormalAssimile().toString());
            NormalAssimile normalAssimile = normalAssimileMapper.deNormalAssimileDto(normalAssimileDto);
            normalAssimile = normalAssimileDao.save(normalAssimile);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    normalAssimileMapper.deNormalAssimile(normalAssimile));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeNormalAssimile) {
        try {
            normalAssimileDao.deleteById(codeNormalAssimile);
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
