package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.Exercice;
import com.ged.mapper.opcciel.ExerciceMapper;
import com.ged.projection.ExerciceProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ExerciceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciceImpl implements ExerciceService {
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;

    public ExerciceImpl(ExerciceDao exerciceDao, ExerciceMapper exerciceMapper) {
        this.exerciceDao = exerciceDao;
        this.exerciceMapper = exerciceMapper;
    }

    @Override
    public List<ExerciceDto> afficherTous() {
        return exerciceDao.afficherExercice().stream().map(exerciceMapper::deExerciceProjection).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> exerciceCourant(Long idOpcvm) {
        try {
            Exercice exercice = exerciceDao.exerciceCourant(idOpcvm).orElse(null);
            return ResponseHandler.generateResponse(
                    "Exercice courant",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> exerciceEnCours(Long idOpcvm) {
        try {
            ExerciceProjection exercice = exerciceDao.exerciceEnCours(idOpcvm);

            return ResponseHandler.generateResponse(
                    "Exercice courant",
                    HttpStatus.OK,
                    exercice);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
