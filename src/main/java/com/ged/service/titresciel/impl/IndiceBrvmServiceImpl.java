package com.ged.service.titresciel.impl;

import com.ged.dao.titresciel.IndiceBrvmDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.CleIndiceBrvmDto;
import com.ged.dto.titresciel.IndiceBrvmDto;
import com.ged.entity.titresciel.IndiceBrvm;
import com.ged.mapper.titresciel.IndiceBrvmMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.IndiceBrvmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndiceBrvmServiceImpl implements IndiceBrvmService {
    private final IndiceBrvmDao indiceBrvmDao;
    private final IndiceBrvmMapper indiceBrvmMapper;

    public IndiceBrvmServiceImpl(IndiceBrvmDao indiceBrvmDao, IndiceBrvmMapper indiceBrvmMapper) {
        this.indiceBrvmDao = indiceBrvmDao;
        this.indiceBrvmMapper = indiceBrvmMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        return null;
    }

    @Override
    public IndiceBrvm afficherSelonId(CleIndiceBrvmDto id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficher(CleIndiceBrvmDto id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> creer(IndiceBrvmDto indiceBrvmDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifier(IndiceBrvmDto indiceBrvmDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(CleIndiceBrvmDto id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> addAll(List<IndiceBrvmDto> indiceBrvmDtos) {
        try {
            List<IndiceBrvm> indiceBrvms = indiceBrvmDtos.stream().map(indiceBrvmMapper::deIndiceBrvmDto).toList();
            if(indiceBrvms.size() > 0) {
                indiceBrvmDtos = indiceBrvmDao.saveAll(indiceBrvms).stream().map(indiceBrvmMapper::deIndiceBrvm).collect(Collectors.toList());
            }
            return ResponseHandler.generateResponse(
                    "Collection d'indices enregistrée avec succès.",
                    HttpStatus.OK,
                    indiceBrvmDtos);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
