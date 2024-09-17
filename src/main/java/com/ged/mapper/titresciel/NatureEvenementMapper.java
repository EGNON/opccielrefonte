package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.NatureEvenementDto;
import com.ged.entity.titresciel.NatureEvenement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NatureEvenementMapper {
    public NatureEvenementDto deNatureEvenement(NatureEvenement NatureEvenement)
    {
        NatureEvenementDto NatureEvenementDto = new NatureEvenementDto();
        BeanUtils.copyProperties(NatureEvenement, NatureEvenementDto);
        return NatureEvenementDto;
    }

    public NatureEvenement deNatureEvenementDto(NatureEvenementDto NatureEvenementDTO)
    {
        NatureEvenement NatureEvenement = new NatureEvenement();
        BeanUtils.copyProperties(NatureEvenementDTO, NatureEvenement);
        return NatureEvenement;
    }
}
