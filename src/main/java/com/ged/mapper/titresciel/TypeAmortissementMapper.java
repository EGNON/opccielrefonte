package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeAmortissementDto;
import com.ged.entity.titresciel.TypeAmortissement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeAmortissementMapper {
    public TypeAmortissementDto deTypeAmortissement(TypeAmortissement typeAmortissement) {
        if(typeAmortissement == null)
            return null;
        TypeAmortissementDto typeAmortissementDto = new TypeAmortissementDto();
        BeanUtils.copyProperties(typeAmortissement, typeAmortissementDto);

        return typeAmortissementDto;
    }

    public TypeAmortissement deTypeAmortissementDto(TypeAmortissementDto typeAmortissementDto) {
        if(typeAmortissementDto == null)
            return null;
        TypeAmortissement typeAmortissement = new TypeAmortissement();
        BeanUtils.copyProperties(typeAmortissementDto, typeAmortissement);

        return typeAmortissement;
    }
}
