package com.ged.mapper.standard;

import com.ged.dto.standard.JoursAlerteDto;
import com.ged.entity.standard.JoursAlerte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class JoursAlerteMapper {
    public JoursAlerteDto deJoursAlerte(JoursAlerte joursAlerte)
    {
        JoursAlerteDto joursAlerteDto = new JoursAlerteDto();
        BeanUtils.copyProperties(joursAlerte, joursAlerteDto);
//        joursAlerteDto.setEtat(joursAlerte.getEtat());
        return joursAlerteDto;
    }

    public JoursAlerte deJoursAlerteDto(JoursAlerteDto joursAlerteDto)
    {
        JoursAlerte joursAlerte = new JoursAlerte();
        BeanUtils.copyProperties(joursAlerteDto, joursAlerte);
//        joursAlerte.setEtat(joursAlerteDto.getEtat());
        return joursAlerte;
    }
}
