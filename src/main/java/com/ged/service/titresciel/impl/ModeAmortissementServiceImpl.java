package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.ModeAmortissementDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ModeAmortissementDto;
import com.ged.entity.titresciel.ModeAmortissement;
import com.ged.mapper.titresciel.ModeAmortissementMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.ModeAmortissementService;
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
public class ModeAmortissementServiceImpl implements ModeAmortissementService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final ModeAmortissementDao ModeAmortissementDao;
    private final ModeAmortissementMapper ModeAmortissementMapper;

    public ModeAmortissementServiceImpl(ModeAmortissementDao ModeAmortissementDao, ModeAmortissementMapper ModeAmortissementMapper){
        this.ModeAmortissementDao = ModeAmortissementDao;

        this.ModeAmortissementMapper = ModeAmortissementMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomModeAmortissement");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ModeAmortissement> ModeAmortissementPage;
            ModeAmortissementPage = ModeAmortissementDao.findAll(pageable);
            List<ModeAmortissementDto> content = ModeAmortissementPage.getContent().stream().map(ModeAmortissementMapper::deModeAmortissement).collect(Collectors.toList());
            DataTablesResponse<ModeAmortissementDto> ModeAmortissementaTablesResponse = new DataTablesResponse<>();
            ModeAmortissementaTablesResponse.setDraw(parameters.getDraw());
            ModeAmortissementaTablesResponse.setRecordsFiltered((int)ModeAmortissementPage.getTotalElements());
            ModeAmortissementaTablesResponse.setRecordsTotal((int)ModeAmortissementPage.getTotalElements());
            ModeAmortissementaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des ModeAmortissements par page ModeAmortissementatable",
                    HttpStatus.OK,
                    ModeAmortissementaTablesResponse);
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
    public Page<ModeAmortissementDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomModeAmortissement");
            List<ModeAmortissementDto> ModeAmortissements = ModeAmortissementDao.findAll().stream().map(ModeAmortissementMapper::deModeAmortissement).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des ModeAmortissements",
                    HttpStatus.OK,
                    ModeAmortissements);
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
    public ModeAmortissement afficherSelonId(Long id) {
        return ModeAmortissementDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ModeAmortissement.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "ModeAmortissement dont ID = " + id,
                    HttpStatus.OK,
                    ModeAmortissementMapper.deModeAmortissement(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(ModeAmortissementDto ModeAmortissementDto) {
        try {
            ModeAmortissement ModeAmortissement = ModeAmortissementMapper.deModeAmortissementDto(ModeAmortissementDto);
            ModeAmortissement = ModeAmortissementDao.save(ModeAmortissement);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ModeAmortissementMapper.deModeAmortissement(ModeAmortissement));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ModeAmortissementDto ModeAmortissementDto) {
        try {
            if(!ModeAmortissementDao.existsById(ModeAmortissementDto.getIdModeAmortissement()))
                throw  new EntityNotFoundException(ModeAmortissement.class, "ID", ModeAmortissementDto.getIdModeAmortissement().toString());
            ModeAmortissement ModeAmortissement = ModeAmortissementMapper.deModeAmortissementDto(ModeAmortissementDto);
            ModeAmortissement = ModeAmortissementDao.save(ModeAmortissement);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ModeAmortissementMapper.deModeAmortissement(ModeAmortissement));
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
            ModeAmortissementDao.deleteById(idOperation);
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
