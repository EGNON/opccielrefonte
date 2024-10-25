package com.ged.mapper.standard;

import com.ged.dto.standard.ModeEtablissementDto;
import com.ged.entity.standard.ModeEtablissement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ModeEtablissementMapper {
    public ModeEtablissementDto deModeEtablissement(ModeEtablissement modeEtablissement)
    {
        if(modeEtablissement==null)
            return null;
        ModeEtablissementDto modeEtablissementDto = new ModeEtablissementDto();
        BeanUtils.copyProperties(modeEtablissement, modeEtablissementDto);
        return modeEtablissementDto;
    }

    public ModeEtablissement deModeEtablissementDto(ModeEtablissementDto modeEtablissementDto)
    {
        if(modeEtablissementDto == null) {
            return null;
        }
        ModeEtablissement modeEtablissement = new ModeEtablissement();
        BeanUtils.copyProperties(modeEtablissementDto, modeEtablissement);
        return modeEtablissement;
    }
}
