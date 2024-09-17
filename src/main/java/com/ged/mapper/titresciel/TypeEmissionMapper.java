package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeEmissionDto;
import com.ged.entity.titresciel.TypeEmission;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeEmissionMapper {
    public TypeEmissionDto deTypeEmission(TypeEmission typeEmission)
    {
        if(typeEmission == null)
            return null;
        TypeEmissionDto typeEmissionDto = new TypeEmissionDto();
        BeanUtils.copyProperties(typeEmission, typeEmissionDto);
        return typeEmissionDto;
    }

    public TypeEmission deTypeEmissionDto(TypeEmissionDto typeEmissionDto)
    {
        TypeEmission typeEmission = new TypeEmission();
        BeanUtils.copyProperties(typeEmissionDto, typeEmission);
        return typeEmission;
    }
}
