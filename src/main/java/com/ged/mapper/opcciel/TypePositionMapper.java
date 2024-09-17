package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.TypePositionDto;
import com.ged.entity.opcciel.comptabilite.TypePosition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypePositionMapper {
    public TypePositionDto deTypePosition(TypePosition typePosition)
    {
        TypePositionDto typePositionDto = new TypePositionDto();
        BeanUtils.copyProperties(typePosition, typePositionDto);
        return typePositionDto;
    }

    public TypePosition deTypePositionDto(TypePositionDto typePositionDto)
    {
        TypePosition typePosition = new TypePosition();
        BeanUtils.copyProperties(typePositionDto, typePosition);
        return typePosition;
    }
}
