
package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeGarantDto;
import com.ged.entity.titresciel.TypeGarant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeGarantMapper {
    public TypeGarantDto deTypeGarant(TypeGarant typeGarant)
    {
        TypeGarantDto typeGarantDto = new TypeGarantDto();
        BeanUtils.copyProperties(typeGarant, typeGarantDto);
        return typeGarantDto;
    }

    public TypeGarant deTypeGarantDto(TypeGarantDto typeGarantDto)
    {
        TypeGarant typeGarant = new TypeGarant();
        BeanUtils.copyProperties(typeGarantDto, typeGarant);
        return typeGarant;
    }
}
