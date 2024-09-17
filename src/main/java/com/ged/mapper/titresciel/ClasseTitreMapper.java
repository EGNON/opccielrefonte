package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.ClasseTitreDto;
import com.ged.entity.titresciel.ClasseTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClasseTitreMapper {
    public ClasseTitreDto deClasseTitre(ClasseTitre classeTitre)
    {
        if(classeTitre == null)
        {
            return null;
        }
        ClasseTitreDto classeTitreDto = new ClasseTitreDto();
        BeanUtils.copyProperties(classeTitre, classeTitreDto);
        return classeTitreDto;
    }

    public ClasseTitre deClasseTitreDto(ClasseTitreDto classeTitreDTO)
    {
        ClasseTitre classeTitre = new ClasseTitre();
        BeanUtils.copyProperties(classeTitreDTO, classeTitre);
        return classeTitre;
    }
}
