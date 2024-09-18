package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.SeanceOpcvmMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.SeanceOpcvmService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SeanceOpcvmServiceImpl implements SeanceOpcvmService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final SeanceOpcvmDao SeanceOpcvmDao;
    private final SeanceOpcvmMapper SeanceOpcvmMapper;

    public SeanceOpcvmServiceImpl(SeanceOpcvmDao SeanceOpcvmDao, SeanceOpcvmMapper SeanceOpcvmMapper){

        this.SeanceOpcvmDao = SeanceOpcvmDao;
        this.SeanceOpcvmMapper = SeanceOpcvmMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<SeanceOpcvm> seanceOpcvmPage;
            seanceOpcvmPage = SeanceOpcvmDao.findAll(pageable);
            List<SeanceOpcvmDto> content = seanceOpcvmPage.getContent().stream().map(SeanceOpcvmMapper::deSeanceOpcvm).collect(Collectors.toList());
            DataTablesResponse<SeanceOpcvmDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)seanceOpcvmPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)seanceOpcvmPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des SeanceOpcvms par page datatable",
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
    public Page<SeanceOpcvmDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            List<SeanceOpcvmDto> SeanceOpcvmDtos = SeanceOpcvmDao.findAll().stream().map(SeanceOpcvmMapper::deSeanceOpcvm).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Seances",
                    HttpStatus.OK,
                    SeanceOpcvmDtos);
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
    public SeanceOpcvm afficherSelonId(CleSeanceOpcvm id) {
        return SeanceOpcvmDao.findById(id).orElseThrow(() -> new EntityNotFoundException(SeanceOpcvm.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleSeanceOpcvm id) {
        try {
            return ResponseHandler.generateResponse(
                    "Seance dont ID = " + id,
                    HttpStatus.OK,
                    SeanceOpcvmMapper.deSeanceOpcvm(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(SeanceOpcvmDto SeanceOpcvmDto) {
        try {
            SeanceOpcvm  SeanceOpcvm = SeanceOpcvmMapper.deSeanceOpcvmDto(SeanceOpcvmDto);
            SeanceOpcvm = SeanceOpcvmDao.save(SeanceOpcvm);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    SeanceOpcvmMapper.deSeanceOpcvm(SeanceOpcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(SeanceOpcvmDto SeanceOpcvmDto) {
        try {
            if(!SeanceOpcvmDao.existsById(SeanceOpcvmDto.getIdSeanceOpcvm()))
                throw  new EntityNotFoundException(SeanceOpcvm.class, "ID", SeanceOpcvmDto.getIdSeanceOpcvm().toString());
            SeanceOpcvm SeanceOpcvm = SeanceOpcvmMapper.deSeanceOpcvmDto(SeanceOpcvmDto);
            SeanceOpcvm= SeanceOpcvmDao.save(SeanceOpcvm);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    SeanceOpcvmMapper.deSeanceOpcvm(SeanceOpcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleSeanceOpcvm id) {
        try {
            SeanceOpcvmDao.deleteById(id);
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
