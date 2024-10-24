package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DepotRachatMapper {
    private final PersonneMapper personneMapper;
    private final OpcvmMapper opcvmMapper;
    private final TitreMapper titreMapper;

    public DepotRachatMapper(PersonneMapper personneMapper, OpcvmMapper opcvmMapper, TitreMapper titreMapper) {
        this.personneMapper = personneMapper;
        this.opcvmMapper = opcvmMapper;
        this.titreMapper = titreMapper;
    }

    public DepotRachatDto deDepotRachat(DepotRachat depotRachat)
    {
        if(depotRachat == null)
            return null;
        DepotRachatDto depotRachatDto = new DepotRachatDto();
        BeanUtils.copyProperties(depotRachat, depotRachatDto);
        if(depotRachat.getPersonne()!=null)
            depotRachatDto.setPersonne(personneMapper.dePersonne(depotRachat.getPersonne()));

        if(depotRachat.getActionnaire()!=null)
            depotRachatDto.setActionnaire(personneMapper.dePersonne(depotRachat.getActionnaire()));

        if(depotRachat.getOpcvm()!=null)
            depotRachatDto.setOpcvm(opcvmMapper.deOpcvm(depotRachat.getOpcvm()));

        if(depotRachat.getTitre()!=null)
            depotRachatDto.setTitre(titreMapper.deTitre(depotRachat.getTitre()));

        return depotRachatDto;
    }

    public DepotRachat deDepotRachatDto(DepotRachatDto depotRachatDto)
    {
        DepotRachat depotRachat = new DepotRachat();
        BeanUtils.copyProperties(depotRachatDto, depotRachat);
        return depotRachat;
    }
}
