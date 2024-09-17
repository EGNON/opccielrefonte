package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.IbRubriqueDto;
import com.ged.entity.opcciel.comptabilite.IbRubrique;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class IbRubriqueMapper {
    private final IbMapper ibMapper;
    private final TypeRubriqueMapper typeRubriqueMapper;

    public IbRubriqueMapper(IbMapper ibMapper, TypeRubriqueMapper typeRubriqueMapper) {
        this.ibMapper = ibMapper;
        this.typeRubriqueMapper = typeRubriqueMapper;
    }

    public IbRubriqueDto deIbRubrique(IbRubrique IbRubrique)
    {
        IbRubriqueDto IbRubriqueDto = new IbRubriqueDto();
        BeanUtils.copyProperties(IbRubrique, IbRubriqueDto);
       if(IbRubrique.getIb()!=null){
           IbRubriqueDto.setIb(ibMapper.deIb(IbRubrique.getIb()));
       }
       if(IbRubrique.getTypeRubrique()!=null){
           IbRubriqueDto.setTypeRubrique(typeRubriqueMapper.deTypeRubrique(IbRubrique.getTypeRubrique()));
       }
        return IbRubriqueDto;
    }

    public IbRubrique deIbRubriqueDto(IbRubriqueDto IbRubriqueDto)
    {
        IbRubrique IbRubrique = new IbRubrique();
        BeanUtils.copyProperties(IbRubriqueDto, IbRubrique);
        return IbRubrique;
    }
}
