package com.ged.service.opcciel.impl;

import com.ged.dao.titresciel.ModeCalculInteretDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ModeCalculInteretDto;
import com.ged.entity.titresciel.ModeCalculInteret;
import com.ged.mapper.titresciel.ModeCalculInteretMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.ModeCalculInteretService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeCalculInteretServiceImpl implements ModeCalculInteretService {
    private final ModeCalculInteretDao modeCalculInteretDao;
    private final ModeCalculInteretMapper modeCalculInteretMapper;

    public ModeCalculInteretServiceImpl(ModeCalculInteretDao modeCalculInteretDao, ModeCalculInteretMapper modeCalculInteretMapper) {
        this.modeCalculInteretDao = modeCalculInteretDao;
        this.modeCalculInteretMapper = modeCalculInteretMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            List<ModeCalculInteretDto> modeCalculInterets = modeCalculInteretDao.findAll().stream().map(modeCalculInteretMapper::deModeCalculInteret).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des modes de calcul d'intérêts",
                    HttpStatus.OK,
                    modeCalculInterets);
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
    public ModeCalculInteret afficherSelonId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> creer(ModeCalculInteretDto modeCalculInteretDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifier(ModeCalculInteretDto modeCalculInteretDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        return null;
    }
}
