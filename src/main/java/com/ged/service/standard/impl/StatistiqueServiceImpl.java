package com.ged.service.standard.impl;

import com.ged.dao.crm.ObjectifAffecteDao;
import com.ged.dao.crm.RDVDao;
import com.ged.dao.standard.StatutPersonneDao;
import com.ged.dto.standard.response.StatistiqueDto;
import com.ged.projection.StatistiqueProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.StatistiqueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatistiqueServiceImpl implements StatistiqueService {
    private final StatutPersonneDao statutPersonneDao;
    private final RDVDao rdvDao;
    private final ObjectifAffecteDao objectifAffecteDao;

    public StatistiqueServiceImpl(StatutPersonneDao statutPersonneDao, RDVDao rdvDao, ObjectifAffecteDao objectifAffecteDao) {
        this.statutPersonneDao = statutPersonneDao;
        this.rdvDao = rdvDao;
        this.objectifAffecteDao = objectifAffecteDao;
    }

    @Override
    public ResponseEntity<Object> nbrePersonneParQualite(Optional<String> libelleQualite) {
        try {
            Map<String,Long> stringList = statutPersonneDao.afficherNbrePersonneParQualite(libelleQualite).stream().
                    collect(Collectors.toMap(StatistiqueProjection::getLibelleQualite,StatistiqueProjection::getNbre));
            return ResponseHandler.generateResponse(
                    "Nbre de personne par qualité",
                    HttpStatus.OK,
                    stringList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> nbreRDVEnCours() {
        try {
//            List<StatistiqueProjection> rdvList=rdvDao.afficherNbreRDVEnCours().stream().collect(Collectors.toList());
            Long countRdv = rdvDao.countByDateDebReelleIsNull();
            return ResponseHandler.generateResponse(
                    "Nbre de rdv en cours",
                    HttpStatus.OK,
                    countRdv);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> nbreRDVParMois(String annee) {
        try {
            List<StatistiqueDto> stringList = rdvDao.afficherNbreRDVParMois(annee).stream().map((res) -> {
                StatistiqueDto statistiqueDto = new StatistiqueDto();
                statistiqueDto.setMois(res.getMois());
                statistiqueDto.setNbrRdv(res.getNbre());
                statistiqueDto.setNomMois(new DateFormatSymbols().getMonths()[res.getMois()-1]);
                return statistiqueDto;
            }).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Nbre de rdv par mois",
                    HttpStatus.OK,
                    stringList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> niveauEvolutionObjectif(String annee) {
        try {
            Map<String,Double> stringList = objectifAffecteDao.niveauEvolutionObjectif(annee).stream().
                    collect(Collectors.toMap(StatistiqueProjection::getDenomination,StatistiqueProjection::getValeurReelle));
            return ResponseHandler.generateResponse(
                    "Niveau d'évolution objectif",
                    HttpStatus.OK,
                    stringList);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
