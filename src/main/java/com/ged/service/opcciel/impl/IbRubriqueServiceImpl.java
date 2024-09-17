package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.IbDao;
import com.ged.dao.opcciel.comptabilite.IbRubriqueDao;
import com.ged.dao.opcciel.comptabilite.TypeRubriqueDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbRubriqueDto;
import com.ged.entity.opcciel.comptabilite.*;
import com.ged.mapper.opcciel.IbMapper;
import com.ged.mapper.opcciel.IbRubriqueMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.IbRubriqueService;
import jakarta.transaction.Transactional;
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
public class IbRubriqueServiceImpl implements IbRubriqueService {
    private final IbRubriqueDao IbRubriqueDao;
    private final IbDao ibDao;
    private final TypeRubriqueDao typeRubriqueDao;
    private final IbMapper ibMapper;
    private final IbRubriqueMapper IbRubriqueMapper;

    public IbRubriqueServiceImpl(IbRubriqueDao IbRubriqueDao, IbDao ibDao, TypeRubriqueDao typeRubriqueDao, IbMapper ibMapper, IbRubriqueMapper IbRubriqueMapper) {
        this.IbRubriqueDao = IbRubriqueDao;
        this.ibDao = ibDao;
        this.typeRubriqueDao = typeRubriqueDao;
        this.ibMapper = ibMapper;
        this.IbRubriqueMapper = IbRubriqueMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeIB");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<IbRubrique> IbRubriquePage;
           // if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            //{
              //  IbRubriquePage = IbRubriqueDao.findByLibelleIbRubriqueContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            //}
            //else {
                IbRubriquePage = IbRubriqueDao.findAll(pageable);
            //}
            List<IbRubriqueDto> content = IbRubriquePage.getContent().stream().map(IbRubriqueMapper::deIbRubrique).collect(Collectors.toList());
            DataTablesResponse<IbRubriqueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)IbRubriquePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)IbRubriquePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Ib rubrique par page datatable",
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
                    "Liste de tous les ib rubrique",
                    HttpStatus.OK,
                    IbRubriqueDao.findAll(sort).stream().map(IbRubriqueMapper::deIbRubrique).collect(Collectors.toList()));
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
    public IbRubrique afficherSelonId(CleIbRubrique idIbRubrique) {
        return IbRubriqueDao.findById(idIbRubrique).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleIbRubrique codeIbRubrique) {
        try {
            return ResponseHandler.generateResponse(
                    "Id rubrique dont id = " + codeIbRubrique,
                    HttpStatus.OK,
                    IbRubriqueMapper.deIbRubrique(afficherSelonId(codeIbRubrique)));
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
    public ResponseEntity<Object> creer(IbRubriqueDto IbRubriqueDto) {
        try {
            IbRubriqueDto.setSupprimer(false);
            IbRubrique IbRubrique = IbRubriqueMapper.deIbRubriqueDto(IbRubriqueDto);
            CleIbRubrique cleIbRubrique=new CleIbRubrique();
            cleIbRubrique.setCodeIB(IbRubriqueDto.getIb().getCodeIB());
            cleIbRubrique.setCodeRubrique(IbRubriqueDto.getCodeRubrique());
            IbRubrique.setIdIbRubrique(cleIbRubrique);


            Ib ib=new Ib();
            TypeRubrique typeRubrique=new TypeRubrique();
            if(IbRubriqueDto.getIb()!=null){
                ib=ibDao.findById(IbRubriqueDto.getIb().getCodeIB()).orElseThrow();
                IbRubrique.setIb(ib);
            }
            if(IbRubriqueDto.getTypeRubrique()!=null){
                typeRubrique=typeRubriqueDao.findById(IbRubriqueDto.getTypeRubrique().getCodeTypeRubrique()).orElseThrow();
                IbRubrique.setTypeRubrique(typeRubrique);
            }
            IbRubrique = IbRubriqueDao.save(IbRubrique);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    IbRubriqueMapper.deIbRubrique(IbRubrique));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(IbRubriqueDto IbRubriqueDto) {
        try {
            IbRubriqueDto.setSupprimer(false);
            IbRubrique IbRubrique = IbRubriqueMapper.deIbRubriqueDto(IbRubriqueDto);

            CleIbRubrique cleIbRubrique=new CleIbRubrique();
            cleIbRubrique.setCodeIB(IbRubriqueDto.getIb().getCodeIB());
            cleIbRubrique.setCodeRubrique(IbRubriqueDto.getCodeRubrique());
            IbRubrique.setIdIbRubrique(cleIbRubrique);

            Ib ib=new Ib();
            TypeRubrique typeRubrique=new TypeRubrique();
            if(IbRubriqueDto.getIb()!=null){
                ib=ibDao.findById(IbRubriqueDto.getIb().getCodeIB()).orElseThrow();
                IbRubrique.setIb(ib);
            }
            if(IbRubriqueDto.getTypeRubrique()!=null){
                typeRubrique=typeRubriqueDao.findById(IbRubriqueDto.getTypeRubrique().getCodeTypeRubrique()).orElseThrow();
                IbRubrique.setTypeRubrique(typeRubrique);
            }

            IbRubrique = IbRubriqueDao.save(IbRubrique);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    IbRubriqueMapper.deIbRubrique(IbRubrique));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleIbRubrique codeIbRubrique) {
        try {
            IbRubriqueDao.deleteById(codeIbRubrique);
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
