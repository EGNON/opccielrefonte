package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeAffectationTitreDto;
import com.ged.entity.titresciel.TypeAffectationTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeAffectationTitreMapper {
    public TypeAffectationTitreDto deTypeAffectation(TypeAffectationTitre typeAffectationTitre) {
        if(typeAffectationTitre == null)
            return null;
        TypeAffectationTitreDto typeAffectationTitreDto = new TypeAffectationTitreDto();
        BeanUtils.copyProperties(typeAffectationTitre, typeAffectationTitreDto);

        return typeAffectationTitreDto;
    }

    public TypeAffectationTitre deTypeAffectationDto(TypeAffectationTitreDto typeAffectationTitreDto) {
        if(typeAffectationTitreDto == null)
            return null;
        TypeAffectationTitre typeAffectationTitre = new TypeAffectationTitre();
        BeanUtils.copyProperties(typeAffectationTitreDto, typeAffectationTitre);

        return typeAffectationTitre;
    }
}
