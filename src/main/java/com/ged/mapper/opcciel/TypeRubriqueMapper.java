package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.TypeRubriqueDto;
import com.ged.entity.opcciel.comptabilite.TypeRubrique;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeRubriqueMapper {
    public TypeRubriqueDto deTypeRubrique(TypeRubrique typeRubrique)
    {
        TypeRubriqueDto typeRubriqueDto = new TypeRubriqueDto();
        BeanUtils.copyProperties(typeRubrique, typeRubriqueDto);
        return typeRubriqueDto;
    }

    public TypeRubrique deTypeRubriqueDto(TypeRubriqueDto typeRubriqueDto)
    {
        TypeRubrique typeRubrique = new TypeRubrique();
        BeanUtils.copyProperties(typeRubriqueDto, typeRubrique);
        return typeRubrique;
    }
}
