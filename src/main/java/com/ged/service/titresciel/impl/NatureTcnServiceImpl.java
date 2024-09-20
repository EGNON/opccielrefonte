package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.NatureTcnDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.NatureTcnDto;
import com.ged.entity.titresciel.NatureTcn;
import com.ged.mapper.titresciel.NatureTcnMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.NatureTcnService;
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
public class NatureTcnServiceImpl implements NatureTcnService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final NatureTcnDao NatureTcnDao;
    private final NatureTcnMapper NatureTcnMapper;

    public NatureTcnServiceImpl(NatureTcnDao NatureTcnDao, NatureTcnMapper NatureTcnMapper){
        this.NatureTcnDao = NatureTcnDao;

        this.NatureTcnMapper = NatureTcnMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomNatureTcn");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<NatureTcn> NatureTcnPage;
            NatureTcnPage = NatureTcnDao.findAll(pageable);
            List<NatureTcnDto> content = NatureTcnPage.getContent().stream().map(NatureTcnMapper::deNatureTcn).collect(Collectors.toList());
            DataTablesResponse<NatureTcnDto> NatureTcnaTablesResponse = new DataTablesResponse<>();
            NatureTcnaTablesResponse.setDraw(parameters.getDraw());
            NatureTcnaTablesResponse.setRecordsFiltered((int)NatureTcnPage.getTotalElements());
            NatureTcnaTablesResponse.setRecordsTotal((int)NatureTcnPage.getTotalElements());
            NatureTcnaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des NatureTcns par page NatureTcnatable",
                    HttpStatus.OK,
                    NatureTcnaTablesResponse);
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
    public Page<NatureTcnDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomNatureTcn");
            List<NatureTcnDto> NatureTcns = NatureTcnDao.findAll().stream().map(NatureTcnMapper::deNatureTcn).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des NatureTcns",
                    HttpStatus.OK,
                    NatureTcns);
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
    public NatureTcn afficherSelonId(Long id) {
        return NatureTcnDao.findById(id).orElseThrow(() -> new EntityNotFoundException(NatureTcn.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "NatureTcn dont ID = " + id,
                    HttpStatus.OK,
                    NatureTcnMapper.deNatureTcn(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(NatureTcnDto NatureTcnDto) {
        try {
            NatureTcn NatureTcn = NatureTcnMapper.deNatureTcnDto(NatureTcnDto);
            NatureTcn = NatureTcnDao.save(NatureTcn);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    NatureTcnMapper.deNatureTcn(NatureTcn));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(NatureTcnDto NatureTcnDto) {
        try {
            if(!NatureTcnDao.existsById(NatureTcnDto.getIdNatureTcn()))
                throw  new EntityNotFoundException(NatureTcn.class, "ID", NatureTcnDto.getIdNatureTcn().toString());
            NatureTcn NatureTcn = NatureTcnMapper.deNatureTcnDto(NatureTcnDto);
            NatureTcn = NatureTcnDao.save(NatureTcn);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    NatureTcnMapper.deNatureTcn(NatureTcn));
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
            NatureTcnDao.deleteById(idOperation);
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
