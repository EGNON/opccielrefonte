package com.ged.mapper.crm;

import com.ged.dto.crm.TypeAffectationDto;
import com.ged.entity.crm.TypeAffectation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeAffectationMapper {
    public TypeAffectationDto deTypeAffectation(TypeAffectation typeAffectation)
    {
        TypeAffectationDto typeAffectationDto = new TypeAffectationDto();
        BeanUtils.copyProperties(typeAffectation, typeAffectationDto);
        return typeAffectationDto;
    }

    public TypeAffectation deTypeAffectationDto(TypeAffectationDto typeAffectationDto)
    {
        TypeAffectation typeAffectation = new TypeAffectation();
        BeanUtils.copyProperties(typeAffectationDto, typeAffectation);
        return typeAffectation;
    }
}
