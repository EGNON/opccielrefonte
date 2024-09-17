package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.NumeroCompteDto;
import com.ged.entity.standard.revuecompte.NumeroCompte;
import com.ged.mapper.standard.PersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NumeroCompteMapper {
    private final PersonneMapper personneMapper;
    private final SousTypeCompteMapper sousTypeCompteMapper;

    public NumeroCompteMapper(PersonneMapper personneMapper, SousTypeCompteMapper sousTypeCompteMapper){
        this.personneMapper = personneMapper;
        this.sousTypeCompteMapper = sousTypeCompteMapper;
    }

    public NumeroCompteDto deNumeroCompte(NumeroCompte NumeroCompte)
    {
        NumeroCompteDto NumeroCompteDTO = new NumeroCompteDto();
        BeanUtils.copyProperties(NumeroCompte, NumeroCompteDTO);
        NumeroCompteDTO.setPersonne(personneMapper.dePersonne(NumeroCompte.getPersonne()));
        NumeroCompteDTO.setSousTypeCompte(sousTypeCompteMapper.deSousTypeCompte(NumeroCompte.getSousTypeCompte()));
        return NumeroCompteDTO;
    }

    public NumeroCompte deNumeroCompteDto(NumeroCompteDto NumeroCompteDto)
    {
        NumeroCompte NumeroCompte = new NumeroCompte();
        BeanUtils.copyProperties(NumeroCompteDto, NumeroCompte);
        NumeroCompte.setPersonne(personneMapper.dePersonneDto(NumeroCompteDto.getPersonne()));
        NumeroCompte.setSousTypeCompte(sousTypeCompteMapper.deSousTypeCompteDto(NumeroCompteDto.getSousTypeCompte()));
        return NumeroCompte;
    }
}
