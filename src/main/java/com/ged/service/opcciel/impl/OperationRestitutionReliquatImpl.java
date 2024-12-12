package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationRestitutionReliquatDao;
import com.ged.dao.opcciel.OperationSouscriptionRachatDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.OperationRestitutionReliquatDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.opcciel.OperationRestitutionReliquat;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.opcciel.OperationRestitutionReliquatMapper;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationRestitutionReliquatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationRestitutionReliquatImpl implements OperationRestitutionReliquatService {
    private final OperationRestitutionReliquatMapper operationRestitutionReliquatMapper;
    private final OperationRestitutionReliquatDao operationRestitutionReliquatDao;
    private final OperationSouscriptionRachatDao souscriptionRachatDao;
    private final OpcvmMapper opcvmMapper;
    private final OpcvmDao opcvmDao;
    private final PersonneMapper personneMapper;
    private final PersonneDao personneDao;
    private final AppService appService;

    public OperationRestitutionReliquatImpl(OperationRestitutionReliquatMapper operationRestitutionReliquatMapper, OperationRestitutionReliquatDao operationRestitutionReliquatDao, OperationSouscriptionRachatDao souscriptionRachatDao, OpcvmMapper opcvmMapper, OpcvmDao opcvmDao, PersonneMapper personneMapper, PersonneDao personneDao, AppService appService) {
        this.operationRestitutionReliquatMapper = operationRestitutionReliquatMapper;
        this.operationRestitutionReliquatDao = operationRestitutionReliquatDao;
        this.souscriptionRachatDao = souscriptionRachatDao;
        this.opcvmMapper = opcvmMapper;
        this.opcvmDao = opcvmDao;
        this.personneMapper = personneMapper;
        this.personneDao = personneDao;
        this.appService = appService;
    }

    @Override
    public ResponseEntity<Object> listeOpRestitutionReliquat(DatatableParameters parameters, Long idOpcvm, Long idSeance) {
        try {
            Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<OperationRestitutionReliquat> operationRestitutionReliquatPage;
            operationRestitutionReliquatPage = operationRestitutionReliquatDao.listeOpRestitutionReliquat(idOpcvm, idSeance, pageable);
            List<OperationRestitutionReliquatDto> content = operationRestitutionReliquatPage.getContent().stream().map(operationRestitutionReliquatMapper::aEntite).toList();
            DataTablesResponse<OperationRestitutionReliquatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationRestitutionReliquatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationRestitutionReliquatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations de restitution de reliquat par page datatable",
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
    public ResponseEntity<Object> listeOpRestitutionReliquat(Long idOpcvm, Long idSeance) {
        try {
            List<OperationRestitutionReliquatDto> restitutionReliquats = operationRestitutionReliquatDao.listeOpRestitutionReliquat(idOpcvm, idSeance).stream().map(operationRestitutionReliquatMapper::aEntite).toList();
            return ResponseHandler.generateResponse(
                    "Liste des opérations de restitution de reliquat",
                    HttpStatus.OK,
                    restitutionReliquats);
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
    public ResponseEntity<Object> precalculRestitutionReliquat(DatatableParameters parameters, Long idOpcvm, Long idSeance) {
        try {
            /*Pageable pageable = PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PrecalculRestitutionReliquatProjection> operationRestitutionReliquatPage = souscriptionRachatDao.precalculRestitutionReliquat(idOpcvm, idSeance, pageable);
            List<OperationRestitutionReliquatDto> content = operationRestitutionReliquatPage.getContent().stream().map(p -> {
                OperationRestitutionReliquatDto restitutionReliquatDto = new OperationRestitutionReliquatDto();
                restitutionReliquatDto.setEstGenere(false);
                restitutionReliquatDto.setIdSeance(p.getIdSeance());
                OpcvmDto opcvmDto = opcvmMapper.deOpcvm(opcvmDao.findById(p.getIdOpcvm()).orElse(null));
                restitutionReliquatDto.setIdOperation(0L);
                restitutionReliquatDto.setOpcvm(opcvmDto);
                PersonneDto personneDto = personneMapper.dePersonne(personneDao.findById(p.getIdActionnaire()).orElse(null));
                restitutionReliquatDto.setIdActionnaire(p.getIdActionnaire());
                restitutionReliquatDto.setActionnaire(personneDto);
                restitutionReliquatDto.setMontant(p.getMontant());
                return restitutionReliquatDto;
            }).toList();*/
            List<OperationRestitutionReliquatDto> content = souscriptionRachatDao.precalculRestitutionReliquat(idOpcvm, idSeance).stream().map(p -> {
                OperationRestitutionReliquatDto restitutionReliquatDto = new OperationRestitutionReliquatDto();
                restitutionReliquatDto.setEstGenere(false);
                restitutionReliquatDto.setIdSeance(p.getIdSeance());
                OpcvmDto opcvmDto = opcvmMapper.deOpcvm(opcvmDao.findById(p.getIdOpcvm()).orElse(null));
                restitutionReliquatDto.setIdOperation(0L);
                restitutionReliquatDto.setOpcvm(opcvmDto);
                PersonneDto personneDto = personneMapper.dePersonne(personneDao.findById(p.getIdActionnaire()).orElse(null));
                restitutionReliquatDto.setIdActionnaire(p.getIdActionnaire());
                restitutionReliquatDto.setActionnaire(personneDto);
                restitutionReliquatDto.setMontant(p.getMontant());
                return restitutionReliquatDto;
            }).toList();
            DataTablesResponse<OperationRestitutionReliquatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered(content.size());
            dataTablesResponse.setRecordsTotal(content.size());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                "Précalcul des opérations de restitution de reliquat",
                HttpStatus.OK,
                dataTablesResponse
            );
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
    public ResponseEntity<Object> enregisterTous(List<OperationRestitutionReliquatDto> restitutionReliquatDtos) {
        try {
            List<OperationRestitutionReliquat> restitutionReliquats = restitutionReliquatDtos.stream().map(op -> {
                op = appService.genererEcritureComptable(op);
                List<OperationSouscriptionRachat> souscriptionRachats = souscriptionRachatDao.recupererListeSouscriptionAMettreAJour(
                        op.getOpcvm().getIdOpcvm(),
                        op.getIdSeance(),
                        op.getActionnaire().getIdPersonne()
                ).stream().map(souscriptionRachat -> {
                    souscriptionRachat.setResteRembourse(true);
                    souscriptionRachat = souscriptionRachatDao.save(souscriptionRachat);
                    return  souscriptionRachat;
                }).toList();
                return operationRestitutionReliquatMapper.deDto(op);
            }).toList();
            return ResponseHandler.generateResponse(
                "Enregistrement effectué avec succès.",
                HttpStatus.OK,
                restitutionReliquats
            );
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                e.getMessage(),
                HttpStatus.MULTI_STATUS,
                e
            );
        }
    }
}
