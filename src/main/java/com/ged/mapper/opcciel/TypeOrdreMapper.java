package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.TypeOrdreDto;
import com.ged.entity.opcciel.TypeOrdre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeOrdreMapper {
    public TypeOrdreDto deTypeOrdre(TypeOrdre typeOrdre)
    {
        TypeOrdreDto typeOrdreDto = new TypeOrdreDto();
        BeanUtils.copyProperties(typeOrdre, typeOrdreDto);
        return typeOrdreDto;
    }

    public TypeOrdre deTypeOrdreDto(TypeOrdreDto typeOrdreDto)
    {
        TypeOrdre typeOrdre = new TypeOrdre();
        BeanUtils.copyProperties(typeOrdreDto, typeOrdre);
        return typeOrdre;
    }
}
