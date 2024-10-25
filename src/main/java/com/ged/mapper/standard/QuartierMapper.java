package com.ged.mapper.standard;

import com.ged.dto.standard.QuartierDto;
import com.ged.entity.standard.Quartier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class QuartierMapper {
    public QuartierDto deQuartier(Quartier quartier)
    {
        if(quartier == null)
            return null;
        QuartierDto quartierDto = new QuartierDto();
        BeanUtils.copyProperties(quartier, quartierDto);
        return quartierDto;
    }

    public Quartier deQuartierDto(QuartierDto quartierDto)
    {
        if(quartierDto == null) {
            return null;
        }
        Quartier quartier= new Quartier();
        BeanUtils.copyProperties(quartierDto, quartier);
        return quartier;
    }
}
