package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.entity.standard.FormeJuridique;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FormeJuridiqueMapper {
    public FormeJuridiqueDto deFormeJuridique(FormeJuridique formeJuridique)
    {
        if(formeJuridique == null)
            return null;
        FormeJuridiqueDto formeJuridiqueDto = new FormeJuridiqueDto();
        BeanUtils.copyProperties(formeJuridique, formeJuridiqueDto);
        return formeJuridiqueDto;
    }

    public FormeJuridique deFormeJuridiqueDto(FormeJuridiqueDto formeJuridiqueDto)
    {
        FormeJuridique formeJuridique = new FormeJuridique();
        BeanUtils.copyProperties(formeJuridiqueDto, formeJuridique);
        return formeJuridique;
    }
}
