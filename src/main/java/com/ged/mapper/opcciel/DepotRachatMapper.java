package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.comptabilite.DepotRachatDto2;
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
    private final NatureOperationMapper natureOperationMapper;

    public DepotRachatMapper(PersonneMapper personneMapper, OpcvmMapper opcvmMapper, TitreMapper titreMapper, NatureOperationMapper natureOperationMapper) {
        this.personneMapper = personneMapper;
        this.opcvmMapper = opcvmMapper;
        this.titreMapper = titreMapper;
        this.natureOperationMapper = natureOperationMapper;
    }

    public DepotRachatDto deDepotRachat(DepotRachat depotRachat)
    {
        if(depotRachat == null)
            return null;
        DepotRachatDto depotRachatDto = new DepotRachatDto();
        BeanUtils.copyProperties(depotRachat, depotRachatDto);

        if(depotRachat.getNatureOperation() != null)
            depotRachatDto.setNatureOperation(natureOperationMapper.deNatureOperation(depotRachat.getNatureOperation()));
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

    public DepotRachatDto2 deDepotRachat2(DepotRachat depotRachat)
    {
        if(depotRachat == null)
            return null;
        DepotRachatDto2 depotRachatDto = new DepotRachatDto2();
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
        if(depotRachatDto == null)
        {
            return null;
        }
        DepotRachat depotRachat = new DepotRachat();
        BeanUtils.copyProperties(depotRachatDto, depotRachat);

        if(depotRachatDto.getNatureOperation() != null)
            depotRachat.setNatureOperation(natureOperationMapper.deNatureOperationDto(depotRachatDto.getNatureOperation()));
        if(depotRachatDto.getPersonne()!=null)
            depotRachat.setPersonne(personneMapper.dePersonneDto(depotRachatDto.getPersonne()));
        if(depotRachatDto.getActionnaire()!=null)
            depotRachat.setActionnaire(personneMapper.dePersonneDto(depotRachatDto.getActionnaire()));
        if(depotRachatDto.getOpcvm()!=null)
            depotRachat.setOpcvm(opcvmMapper.deOpcvmDto(depotRachatDto.getOpcvm()));
        if(depotRachatDto.getTitre()!=null)
            depotRachat.setTitre(titreMapper.deTitreDto(depotRachatDto.getTitre()));

        return depotRachat;
    }
}
