package com.ged.mapper.standard;

import com.ged.dto.standard.TempsAlerteDto;
import com.ged.entity.standard.TempsAlerte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TempsAlerteMapper {
    public TempsAlerteDto deTempsAlerte(TempsAlerte tempsAlerte)
    {
        TempsAlerteDto tempsAlerteDto = new TempsAlerteDto();
        BeanUtils.copyProperties(tempsAlerte, tempsAlerteDto);
        return tempsAlerteDto;
    }

    public TempsAlerte deTempsAlerteDto(TempsAlerteDto tempsAlerteDto)
    {
        TempsAlerte tempsAlerte = new TempsAlerte();
        BeanUtils.copyProperties(tempsAlerteDto, tempsAlerte);
        return tempsAlerte;
    }
}
