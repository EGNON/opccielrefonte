package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.DepositaireDto;
import com.ged.entity.titresciel.Depositaire;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DepositaireMapper {
    public DepositaireDto deDepositaire(Depositaire depositaire)
    {
        if(depositaire == null)
            return null;
        DepositaireDto depositaireDto = new DepositaireDto();
        BeanUtils.copyProperties(depositaire, depositaireDto);
        return depositaireDto;
    }

    public Depositaire deDepositaireDto(DepositaireDto depositaireDto)
    {
        Depositaire depositaire = new Depositaire();
        BeanUtils.copyProperties(depositaireDto, depositaire);
        return depositaire;
    }
}
