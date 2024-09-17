package com.ged.mapper.standard;

import com.ged.dto.standard.NbreJoursDto;
import com.ged.entity.standard.NbreJours;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NbreJoursMapper {
    public NbreJoursDto deNbreJours(NbreJours nbreJours)
    {
        NbreJoursDto nbreJoursDto = new NbreJoursDto();
        BeanUtils.copyProperties(nbreJours, nbreJoursDto);
        return nbreJoursDto;
    }

    public NbreJours deNbreJoursDto(NbreJoursDto nbreJoursDto)
    {
        NbreJours nbreJours = new NbreJours();
        BeanUtils.copyProperties(nbreJoursDto, nbreJours);
        return nbreJours;
    }
}
