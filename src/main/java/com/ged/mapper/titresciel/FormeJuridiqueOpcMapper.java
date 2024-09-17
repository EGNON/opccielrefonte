package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.FormeJuridiqueOpcDto;
import com.ged.entity.titresciel.FormeJuridiqueOpc;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FormeJuridiqueOpcMapper {
    public FormeJuridiqueOpcDto deFormeJuridiqueOpc(FormeJuridiqueOpc formeJuridiqueOpc)
    {
        if(formeJuridiqueOpc == null)
            return null;
        FormeJuridiqueOpcDto formeJuridiqueOpcDto = new FormeJuridiqueOpcDto();
        BeanUtils.copyProperties(formeJuridiqueOpc, formeJuridiqueOpcDto);
        return formeJuridiqueOpcDto;
    }

    public FormeJuridiqueOpc deFormeJuridiqueOpcDto(FormeJuridiqueOpcDto formeJuridiqueOpcDto)
    {
        if(formeJuridiqueOpcDto == null)
            return null;
        FormeJuridiqueOpc formeJuridiqueOpc = new FormeJuridiqueOpc();
        BeanUtils.copyProperties(formeJuridiqueOpcDto, formeJuridiqueOpc);
        return formeJuridiqueOpc;
    }
}
