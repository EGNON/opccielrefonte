package com.ged.mapper.standard;

import com.ged.dto.standard.TypeModeleDto;
import com.ged.entity.standard.TypeModele;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeModeleMapper {
    public TypeModeleDto deTypeModele(TypeModele TypeModele)
    {
        TypeModeleDto TypeModeleDto = new TypeModeleDto();
        BeanUtils.copyProperties(TypeModele, TypeModeleDto);
        return TypeModeleDto;
    }

    public TypeModele deTypeModeleDto(TypeModeleDto TypeModeleDto)
    {
        TypeModele TypeModele = new TypeModele();
        BeanUtils.copyProperties(TypeModeleDto, TypeModele);
        return TypeModele;
    }
}
