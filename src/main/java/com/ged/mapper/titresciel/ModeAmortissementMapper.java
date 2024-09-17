package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.ModeAmortissementDto;
import com.ged.entity.titresciel.ModeAmortissement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ModeAmortissementMapper {
    public ModeAmortissementDto deModeAmortissement(ModeAmortissement ModeAmortissement)
    {
        if(ModeAmortissement == null)
            return null;
        ModeAmortissementDto ModeAmortissementDto = new ModeAmortissementDto();
        BeanUtils.copyProperties(ModeAmortissement, ModeAmortissementDto);
        return ModeAmortissementDto;
    }

    public ModeAmortissement deModeAmortissementDto(ModeAmortissementDto ModeAmortissementDTO)
    {
        ModeAmortissement ModeAmortissement = new ModeAmortissement();
        BeanUtils.copyProperties(ModeAmortissementDTO, ModeAmortissement);
        return ModeAmortissement;
    }
}
