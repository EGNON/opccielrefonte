package com.ged.mapper.standard;

import com.ged.dto.standard.NbreJoursAlerteDto;
import com.ged.entity.standard.NbreJoursAlerte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NbreJoursAlerteMapper {
    public NbreJoursAlerteDto deNbreJoursAlerte(NbreJoursAlerte nbreJoursAlerte)
    {
        NbreJoursAlerteDto nbreJoursAlerteDto = new NbreJoursAlerteDto();
        BeanUtils.copyProperties(nbreJoursAlerte, nbreJoursAlerteDto);
        return nbreJoursAlerteDto;
    }

    public NbreJoursAlerte deNbreJoursAlerteDto(NbreJoursAlerteDto nbreJoursAlerteDto)
    {
        NbreJoursAlerte nbreJoursAlerte = new NbreJoursAlerte();
        BeanUtils.copyProperties(nbreJoursAlerteDto, nbreJoursAlerte);
        return nbreJoursAlerte;
    }
}
