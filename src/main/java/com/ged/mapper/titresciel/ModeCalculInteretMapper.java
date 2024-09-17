package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.ModeCalculInteretDto;
import com.ged.entity.titresciel.ModeCalculInteret;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ModeCalculInteretMapper {
    public ModeCalculInteretDto deModeCalculInteret(ModeCalculInteret modeCalculInteret)
    {
        if(modeCalculInteret == null)
            return null;
        ModeCalculInteretDto modeCalculInteretDto = new ModeCalculInteretDto();
        BeanUtils.copyProperties(modeCalculInteret, modeCalculInteretDto);
        return modeCalculInteretDto;
    }

    public ModeCalculInteret deModeCalculInteretDto(ModeCalculInteretDto modeCalculInteretDto)
    {
        if(modeCalculInteretDto == null)
            return null;
        ModeCalculInteret modeCalculInteret = new ModeCalculInteret();
        BeanUtils.copyProperties(modeCalculInteretDto, modeCalculInteret);
        return modeCalculInteret;
    }
}
