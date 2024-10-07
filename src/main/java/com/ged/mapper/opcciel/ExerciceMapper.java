package com.ged.mapper.opcciel;


import com.ged.dto.crm.AgentConcerneDto;
import com.ged.dto.crm.RDVDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.Exercice;
import com.ged.projection.ExerciceProjection;
import com.ged.projection.RDVProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExerciceMapper {
    public ExerciceDto deExercice(Exercice Exercice)
    {
        if(Exercice == null)
            return null;
        ExerciceDto ExerciceDto = new ExerciceDto();
        BeanUtils.copyProperties(Exercice, ExerciceDto);
        return ExerciceDto;
    }

    public Exercice deExerciceDto(ExerciceDto ExerciceDto)
    {
        Exercice Exercice = new Exercice();
        BeanUtils.copyProperties(ExerciceDto, Exercice);
        return Exercice;
    }
    public ExerciceDto deExerciceProjection(ExerciceProjection exercice)
    {
        if(exercice == null) {
            return null;
        }
        ExerciceDto ExerciceDto = new ExerciceDto();
        BeanUtils.copyProperties(exercice, ExerciceDto);
        return ExerciceDto;
    }
}
