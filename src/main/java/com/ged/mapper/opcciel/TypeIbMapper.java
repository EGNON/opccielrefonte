package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.TypeIbDto;
import com.ged.entity.opcciel.comptabilite.TypeIb;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeIbMapper {
    public TypeIbDto deTypeIb(TypeIb typeIb)
    {
        TypeIbDto typeIbDto = new TypeIbDto();
        BeanUtils.copyProperties(typeIb, typeIbDto);
        return typeIbDto;
    }

    public TypeIb deTypeIbDto(TypeIbDto typeIbDto)
    {
        TypeIb typeIb = new TypeIb();
        BeanUtils.copyProperties(typeIbDto, typeIb);
        return typeIb;
    }
}
