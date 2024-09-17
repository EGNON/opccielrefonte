package com.ged.mapper.crm;

import com.ged.dto.standard.PeriodiciteDto;
import com.ged.entity.standard.Periodicite;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PeriodiciteMapper {
    public PeriodiciteDto dePeriodicite(Periodicite periodicite)
    {
        PeriodiciteDto periodiciteDto = new PeriodiciteDto();
        BeanUtils.copyProperties(periodicite, periodiciteDto);
        return periodiciteDto;
    }

    public Periodicite dePeriodiciteDto(PeriodiciteDto periodiciteDto)
    {
        Periodicite periodicite = new Periodicite();
        BeanUtils.copyProperties(periodiciteDto, periodicite);
        return periodicite;
    }
}
