package com.ged.mapper.lab;

import com.ged.dto.lab.TypeCritereDto;
import com.ged.entity.lab.TypeCritere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeCritereMapper {
    public TypeCritereDto deTypeCritere(TypeCritere typeCritere)
    {
        TypeCritereDto typeCritereDto = new TypeCritereDto();
        BeanUtils.copyProperties(typeCritere, typeCritereDto);
        return typeCritereDto;
    }

    public TypeCritere deTypeCritereDto(TypeCritereDto typeCritereDto)
    {
        TypeCritere typeCritere = new TypeCritere();
        BeanUtils.copyProperties(typeCritereDto, typeCritere);
        return typeCritere;
    }
}
