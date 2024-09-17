package com.ged.mapper.standard;

import com.ged.dto.standard.SecteurDto;
import com.ged.entity.standard.Secteur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SecteurMapper {
    public SecteurDto deSecteur(Secteur secteur)
    {
        if(secteur == null)
            return  null;
        SecteurDto secteurDto = new SecteurDto();
        BeanUtils.copyProperties(secteur, secteurDto);

        return secteurDto;
    }

    public Secteur deSecteurDto(SecteurDto secteurDto)
    {
        Secteur secteur = new Secteur();
        BeanUtils.copyProperties(secteurDto, secteur);
        return secteur;
    }
}
