package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.TypeFormuleDto;
import com.ged.entity.opcciel.comptabilite.TypeFormule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeFormuleMapper {
    public TypeFormuleDto deTypeFormule(TypeFormule typeFormule)
    {
        TypeFormuleDto typeFormuleDto = new TypeFormuleDto();
        BeanUtils.copyProperties(typeFormule, typeFormuleDto);
        return typeFormuleDto;
    }

    public TypeFormule deTypeFormuleDto(TypeFormuleDto typeFormuleDto)
    {
        TypeFormule typeFormule = new TypeFormule();
        BeanUtils.copyProperties(typeFormuleDto, typeFormule);
        return typeFormule;
    }
}
