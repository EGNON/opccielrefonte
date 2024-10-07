package com.ged.mapper.opcciel;


import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.Exercice;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
}
