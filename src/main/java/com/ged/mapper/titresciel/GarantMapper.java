package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.GarantDto;
import com.ged.entity.titresciel.Garant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class GarantMapper {
    public GarantDto deGarant(Garant Garant)
    {
        if(Garant == null)
            return null;
        GarantDto GarantDto = new GarantDto();
        BeanUtils.copyProperties(Garant, GarantDto);
        return GarantDto;
    }

    public Garant deGarantDto(GarantDto GarantDto)
    {
        Garant Garant = new Garant();
        BeanUtils.copyProperties(GarantDto, Garant);
        return Garant;
    }
}
