package com.ged.mapper.standard;

import com.ged.dto.standard.JoursDto;
import com.ged.entity.standard.Jours;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class JoursMapper {
    public JoursDto deJours(Jours jours)
    {
        JoursDto joursDto = new JoursDto();
        BeanUtils.copyProperties(jours, joursDto);
        return joursDto;
    }

    public Jours deJoursDto(JoursDto joursDto)
    {
        Jours jours = new Jours();
        BeanUtils.copyProperties(joursDto, jours);
        return jours;
    }
}
