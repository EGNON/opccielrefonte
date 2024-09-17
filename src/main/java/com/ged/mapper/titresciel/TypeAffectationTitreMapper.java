package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeAffectationTitreDto;
import com.ged.entity.titresciel.TypeAffectationVL;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeAffectationTitreMapper {
    public TypeAffectationTitreDto deTypeAffectation(TypeAffectationVL typeAffectationTitre) {
        if(typeAffectationTitre == null)
            return null;
        TypeAffectationTitreDto typeAffectationTitreDto = new TypeAffectationTitreDto();
        BeanUtils.copyProperties(typeAffectationTitre, typeAffectationTitreDto);

        return typeAffectationTitreDto;
    }

    public TypeAffectationVL deTypeAffectationDto(TypeAffectationTitreDto typeAffectationTitreDto) {
        if(typeAffectationTitreDto == null)
            return null;
        TypeAffectationVL typeAffectationTitre = new TypeAffectationVL();
        BeanUtils.copyProperties(typeAffectationTitreDto, typeAffectationTitre);

        return typeAffectationTitre;
    }
}
