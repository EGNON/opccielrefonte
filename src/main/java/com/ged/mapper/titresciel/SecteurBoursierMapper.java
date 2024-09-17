package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.SecteurBoursierDto;
import com.ged.entity.titresciel.SecteurBoursier;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SecteurBoursierMapper {
    public SecteurBoursierDto deSecteurBoursier(SecteurBoursier SecteurBoursier)
    {
        SecteurBoursierDto SecteurBoursierDto = new SecteurBoursierDto();
        BeanUtils.copyProperties(SecteurBoursier, SecteurBoursierDto);
        return SecteurBoursierDto;
    }

    public SecteurBoursier deSecteurBoursierDto(SecteurBoursierDto SecteurBoursierDTO)
    {
        SecteurBoursier SecteurBoursier = new SecteurBoursier();
        BeanUtils.copyProperties(SecteurBoursierDTO, SecteurBoursier);
        return SecteurBoursier;
    }
}
