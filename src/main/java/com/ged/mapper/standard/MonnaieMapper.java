package com.ged.mapper.standard;

import com.ged.dto.standard.MonnaieDto;
import com.ged.entity.standard.Monnaie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MonnaieMapper {
    public MonnaieDto deMonnaie(Monnaie monnaie)
    {
        if(monnaie == null)
            return null;
        MonnaieDto monnaieDto = new MonnaieDto();
        BeanUtils.copyProperties(monnaie, monnaieDto);
        return monnaieDto;
    }

    public Monnaie deMonnaieDto(MonnaieDto monnaieDto)
    {
        if(monnaieDto == null) {
            return null;
        }
        Monnaie monnaie = new Monnaie();
        BeanUtils.copyProperties(monnaieDto, monnaie);
        return monnaie;
    }
}
