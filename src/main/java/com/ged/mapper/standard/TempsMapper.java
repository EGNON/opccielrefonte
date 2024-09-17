package com.ged.mapper.standard;

import com.ged.dto.standard.TempsDto;
import com.ged.entity.standard.Temps;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TempsMapper {
    public TempsDto deTemps(Temps temps)
    {
        TempsDto tempsDto = new TempsDto();
        BeanUtils.copyProperties(temps, tempsDto);
        return tempsDto;
    }

    public Temps deTempsDto(TempsDto tempsDto)
    {
        Temps temps = new Temps();
        BeanUtils.copyProperties(tempsDto, temps);
        return temps;
    }
}
