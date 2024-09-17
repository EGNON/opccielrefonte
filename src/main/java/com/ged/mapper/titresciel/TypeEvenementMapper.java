
package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeEvenementDto;
import com.ged.entity.titresciel.TypeEvenement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeEvenementMapper {
    public TypeEvenementDto deTypeEvenement(TypeEvenement typeEvenement)
    {
        TypeEvenementDto typeEvenementDto = new TypeEvenementDto();
        BeanUtils.copyProperties(typeEvenement, typeEvenementDto);
        return typeEvenementDto;
    }

    public TypeEvenement deTypeEvenementDto(TypeEvenementDto typeEvenementDto)
    {
        TypeEvenement typeEvenement = new TypeEvenement();
        BeanUtils.copyProperties(typeEvenementDto, typeEvenement);
        return typeEvenement;
    }
}
