package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.SousTypeActionDto;
import com.ged.entity.titresciel.SousTypeAction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SousTypeActionMapper {
    public SousTypeActionDto deSousTypeAction(SousTypeAction SousTypeAction)
    {
        if(SousTypeAction == null)
            return null;
        SousTypeActionDto SousTypeActionDto = new SousTypeActionDto();
        BeanUtils.copyProperties(SousTypeAction, SousTypeActionDto);
        return SousTypeActionDto;
    }

    public SousTypeAction deSousTypeActionDto(SousTypeActionDto SousTypeActionDTO)
    {
        SousTypeAction SousTypeAction = new SousTypeAction();
        BeanUtils.copyProperties(SousTypeActionDTO, SousTypeAction);
        return SousTypeAction;
    }
}
