
package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeObligationDto;
import com.ged.entity.titresciel.TypeObligation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeObligationMapper {
    public TypeObligationDto deTypeObligation(TypeObligation typeObligation)
    {
        if(typeObligation == null)
        {
            return null;
        }
        TypeObligationDto typeObligationDto = new TypeObligationDto();
        BeanUtils.copyProperties(typeObligation, typeObligationDto);
        return typeObligationDto;
    }

    public TypeObligation deTypeObligationDto(TypeObligationDto typeObligationDto)
    {
        TypeObligation typeObligation = new TypeObligation();
        BeanUtils.copyProperties(typeObligationDto, typeObligation);
        return typeObligation;
    }
}
