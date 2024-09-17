package com.ged.mapper.lab;

import com.ged.mapper.standard.PersonneMapper;
import com.ged.dto.lab.GelDegelDto;
import com.ged.entity.lab.GelDegel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GelDegelMapper {
    private final PersonneMapper personneMapper;

    public GelDegelMapper(PersonneMapper personneMapper) {
        this.personneMapper = personneMapper;
    }

    public GelDegelDto deGelDegel(GelDegel gelDegel)
    {
        GelDegelDto gelDegelDto = new GelDegelDto();
        BeanUtils.copyProperties(gelDegel, gelDegelDto);
        if(gelDegel.getPersonne()!=null){
            gelDegelDto.setPersonneDto(personneMapper.dePersonne(gelDegel.getPersonne()));
        }
        return gelDegelDto;
    }

    public GelDegel deGelDegelDto(GelDegelDto gelDegelDto)
    {
        GelDegel gelDegel = new GelDegel();
        BeanUtils.copyProperties(gelDegelDto, gelDegel);
        if(gelDegelDto.getPersonneDto()!=null){
            gelDegel.setPersonne(personneMapper.dePersonneDto(gelDegelDto.getPersonneDto()));
        }
        return gelDegel;
    }
}
