package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.IbDao;
import com.ged.dao.opcciel.comptabilite.IbRubriquePositionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbRubriquePositionDto;
import com.ged.entity.opcciel.comptabilite.CleIbRubriquePosition;
import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.IbRubriquePosition;
import com.ged.mapper.opcciel.IbMapper;
import com.ged.mapper.opcciel.IbRubriquePositionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.IbRubriquePositionService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IbRubriquePositionServiceImpl implements IbRubriquePositionService {
    private final IbRubriquePositionDao IbRubriquePositionDao;
    private final IbDao ibDao;
    private final IbMapper ibMapper;
    private final IbRubriquePositionMapper IbRubriquePositionMapper;

    public IbRubriquePositionServiceImpl(IbRubriquePositionDao IbRubriquePositionDao, IbDao ibDao, IbMapper ibMapper, IbRubriquePositionMapper IbRubriquePositionMapper) {
        this.IbRubriquePositionDao = IbRubriquePositionDao;
        this.ibDao = ibDao;
        this.ibMapper = ibMapper;
        this.IbRubriquePositionMapper = IbRubriquePositionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeIB");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<IbRubriquePosition> IbRubriquePositionPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                IbRubriquePositionPage = IbRubriquePositionDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                IbRubriquePositionPage = IbRubriquePositionDao.findBySupprimer(false,pageable);
            }
            List<IbRubriquePositionDto> content = IbRubriquePositionPage.getContent().stream().map(IbRubriquePositionMapper::deIbRubriquePosition).collect(Collectors.toList());
            DataTablesResponse<IbRubriquePositionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)IbRubriquePositionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)IbRubriquePositionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Ib rubrique position par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"codeIB");
            return ResponseHandler.generateResponse(
                    "Liste de tous les ib rubrique position",
                    HttpStatus.OK,
                    IbRubriquePositionDao.findBySupprimerOrderByIbAsc(false).stream().map(IbRubriquePositionMapper::deIbRubriquePosition).collect(Collectors.toList()));
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
    public IbRubriquePosition afficherSelonId(CleIbRubriquePosition idIbRubriquePosition) {
        return IbRubriquePositionDao.findById(idIbRubriquePosition).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleIbRubriquePosition codeIbRubriquePosition) {
        try {
            return ResponseHandler.generateResponse(
                    "Id rubrique position dont id = " + codeIbRubriquePosition,
                    HttpStatus.OK,
                    IbRubriquePositionMapper.deIbRubriquePosition(afficherSelonId(codeIbRubriquePosition)));
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
    public ResponseEntity<Object> creer(IbRubriquePositionDto IbRubriquePositionDto) {
        try {
            IbRubriquePositionDto.setSupprimer(false);
            IbRubriquePosition IbRubriquePosition = IbRubriquePositionMapper.deIbRubriquePositionDto(IbRubriquePositionDto);
            CleIbRubriquePosition cleIbRubriquePosition=new CleIbRubriquePosition();
            cleIbRubriquePosition.setCodeIB(IbRubriquePositionDto.getIb().getCodeIB());
            cleIbRubriquePosition.setCodeRubrique(IbRubriquePositionDto.getCodeRubrique());
            cleIbRubriquePosition.setCodePosition(IbRubriquePositionDto.getCodePosition());
            IbRubriquePosition.setIdIbRubriquePosition(cleIbRubriquePosition);
            
            Ib ib=new Ib();
            if(IbRubriquePositionDto.getIb()!=null){
                ib=ibDao.findById(IbRubriquePositionDto.getIb().getCodeIB()).orElseThrow();
                IbRubriquePosition.setIb(ib);
            }
            IbRubriquePosition = IbRubriquePositionDao.save(IbRubriquePosition);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    IbRubriquePositionMapper.deIbRubriquePosition(IbRubriquePosition));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(IbRubriquePositionDto IbRubriquePositionDto) {
        try {
            IbRubriquePositionDto.setSupprimer(false);
            IbRubriquePosition IbRubriquePosition = IbRubriquePositionMapper.deIbRubriquePositionDto(IbRubriquePositionDto);

            CleIbRubriquePosition cleIbRubriquePosition=new CleIbRubriquePosition();
            cleIbRubriquePosition.setCodeIB(IbRubriquePositionDto.getIb().getCodeIB());
            cleIbRubriquePosition.setCodeRubrique(IbRubriquePositionDto.getCodeRubrique());
            cleIbRubriquePosition.setCodePosition(IbRubriquePositionDto.getCodePosition());
            IbRubriquePosition.setIdIbRubriquePosition(cleIbRubriquePosition);

            Ib ib=new Ib();
            if(IbRubriquePositionDto.getIb()!=null){
                ib=ibDao.findById(IbRubriquePositionDto.getIb().getCodeIB()).orElseThrow();
                IbRubriquePosition.setIb(ib);
            }
            
            IbRubriquePosition = IbRubriquePositionDao.save(IbRubriquePosition);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    IbRubriquePositionMapper.deIbRubriquePosition(IbRubriquePosition));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleIbRubriquePosition codeIbRubriquePosition) {
        try {
            IbRubriquePositionDao.deleteById(codeIbRubriquePosition);
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
