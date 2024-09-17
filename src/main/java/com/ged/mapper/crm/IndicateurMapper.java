package com.ged.mapper.crm;

import com.ged.dto.crm.IndicateurDto;
import com.ged.entity.crm.Indicateur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class IndicateurMapper {
    public IndicateurDto deIndicateur(Indicateur indicateur)
    {
        IndicateurDto indicateurDto = new IndicateurDto();
        BeanUtils.copyProperties(indicateur, indicateurDto);
        return indicateurDto;
    }

    public Indicateur deIndicateurDto(IndicateurDto indicateurDto)
    {
        Indicateur indicateur = new Indicateur();
        BeanUtils.copyProperties(indicateurDto, indicateur);
        return indicateur;
    }
}
