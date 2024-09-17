package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.SousTypeCompteDto;
import com.ged.entity.standard.revuecompte.SousTypeCompte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SousTypeCompteMapper {
    private final TypeCompteMapper typeCompteMapper;

    public SousTypeCompteMapper(TypeCompteMapper typeCompteMapper) {
        this.typeCompteMapper = typeCompteMapper;
    }

    public SousTypeCompteDto deSousTypeCompte(SousTypeCompte SousTypeCompte)
    {
        SousTypeCompteDto SousTypeCompteDTO = new SousTypeCompteDto();
        BeanUtils.copyProperties(SousTypeCompte, SousTypeCompteDTO);
        SousTypeCompteDTO.setTypeCompte(typeCompteMapper.deTypeCompte(SousTypeCompte.getTypeCompte()));
        return SousTypeCompteDTO;
    }

    public SousTypeCompte deSousTypeCompteDto(SousTypeCompteDto SousTypeCompteDto)
    {
        SousTypeCompte SousTypeCompte = new SousTypeCompte();
        BeanUtils.copyProperties(SousTypeCompteDto, SousTypeCompte);
        SousTypeCompte.setTypeCompte(typeCompteMapper.deTypeCompteDto(SousTypeCompteDto.getTypeCompte()));
        return SousTypeCompte;
    }
}
