package com.ged.mapper.standard;

import com.ged.dto.standard.SousCategorieDto;
import com.ged.entity.standard.SousCategorie;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SousCategorieMapper {
    private final CategoriePersonneMapper categoriePersonneMapper;

    public SousCategorieMapper(CategoriePersonneMapper categoriePersonneMapper) {
        this.categoriePersonneMapper = categoriePersonneMapper;
    }

    public SousCategorieDto deSousCategorie(SousCategorie sousCategorie) {
        SousCategorieDto sousCategorieDto = new SousCategorieDto();
        BeanUtils.copyProperties(sousCategorie, sousCategorieDto);
        if(sousCategorie.getCategoriePersonne()!=null)
            sousCategorieDto.setCategoriePersonne(categoriePersonneMapper.deCatPersonne(sousCategorie.getCategoriePersonne()));
        return sousCategorieDto;
    }

    public SousCategorie deSousCategorieDto(SousCategorieDto sousCategorieDto) {
        SousCategorie sousCategorie = new SousCategorie();
        BeanUtils.copyProperties(sousCategorieDto, sousCategorie);
        if(sousCategorieDto.getCategoriePersonne()!=null)
            sousCategorie.setCategoriePersonne(categoriePersonneMapper.deCatPersonneDto(sousCategorieDto.getCategoriePersonne()));

        return sousCategorie;
    }
}
