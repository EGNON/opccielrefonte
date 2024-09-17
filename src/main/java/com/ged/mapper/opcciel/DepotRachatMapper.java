package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DepotRachatMapper {
    public DepotRachatDto deDepotRachat(DepotRachat depotRachat)
    {
        if(depotRachat == null)
            return null;
        DepotRachatDto depotRachatDto = new DepotRachatDto();
        BeanUtils.copyProperties(depotRachat, depotRachatDto);
        return depotRachatDto;
    }

    public DepotRachat deDepotRachatDto(DepotRachatDto depotRachatDto)
    {
        DepotRachat depotRachat = new DepotRachat();
        BeanUtils.copyProperties(depotRachatDto, depotRachat);
        return depotRachat;
    }
}
