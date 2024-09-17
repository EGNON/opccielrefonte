package com.ged.mapper.standard;

import com.ged.dto.lab.CritereAlerteDto;
import com.ged.entity.standard.CritereAlerte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CritereAlerteMapper {

    public CritereAlerteDto deCritereAlerte(CritereAlerte critereAlerte)
    {
        CritereAlerteDto critereAlerteDto = new CritereAlerteDto();
        BeanUtils.copyProperties(critereAlerte, critereAlerteDto);
        return critereAlerteDto;
    }

    public CritereAlerte deCritereAlerteDto(CritereAlerteDto critereAlerteDTO)
    {
        CritereAlerte critereAlerte = new CritereAlerte();
        BeanUtils.copyProperties(critereAlerteDTO, critereAlerte);
        return critereAlerte;
    }
}
