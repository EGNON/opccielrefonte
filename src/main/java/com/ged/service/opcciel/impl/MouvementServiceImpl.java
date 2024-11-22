package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.MouvementDao;
import com.ged.dto.opcciel.comptabilite.MouvementDto;
import com.ged.entity.opcciel.comptabilite.Mouvement;
import com.ged.mapper.opcciel.MouvementMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.MouvementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MouvementServiceImpl implements MouvementService {
    private final MouvementDao mouvementDao;
    private final MouvementMapper mouvementMapper;

    public MouvementServiceImpl(MouvementDao mouvementDao, MouvementMapper mouvementMapper) {
        this.mouvementDao = mouvementDao;
        this.mouvementMapper = mouvementMapper;
    }

    @Override
    public ResponseEntity<Object> afficher(Long idMvt) {
        try {
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats par page datatable",
                    HttpStatus.OK,
                    mouvementMapper.deMouvement(afficherSelondId(idMvt)));
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
    public Mouvement afficherSelondId(Long idMvt) {
        return mouvementDao.findById(idMvt).orElse(null);
    }

    @Override
    public ResponseEntity<Object> creer(MouvementDto mouvementDto) {
        try {
            Mouvement mouvement = mouvementMapper.deMouvementDto(mouvementDto);
            return ResponseHandler.generateResponse(
                "Enregistrement effectué avec succès.",
                HttpStatus.MULTI_STATUS,
                null
            );
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(
                e.getMessage(),
                HttpStatus.MULTI_STATUS,
                e
            );
        }
    }

    @Override
    public ResponseEntity<Object> modifier(MouvementDto mouvementDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idMvt) {
        return null;
    }
}
