package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.mapper.opcciel.ExerciceMapper;
import com.ged.service.opcciel.ExerciceService;
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
        return exerciceDao.afficherExercice().stream().map(exerciceMapper::deExercice).collect(Collectors.toList());
    }
}
