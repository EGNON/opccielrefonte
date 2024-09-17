package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.StatutTitreDto;
import com.ged.entity.titresciel.StatutTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StatutTitreMapper {
    public StatutTitreDto deStatutTitre(StatutTitre StatutTitre)
    {
        StatutTitreDto StatutTitreDto = new StatutTitreDto();
        BeanUtils.copyProperties(StatutTitre, StatutTitreDto);
        return StatutTitreDto;
    }

    public StatutTitre deStatutTitreDto(StatutTitreDto StatutTitreDTO)
    {
        StatutTitre StatutTitre = new StatutTitre();
        BeanUtils.copyProperties(StatutTitreDTO, StatutTitre);
        return StatutTitre;
    }
}
