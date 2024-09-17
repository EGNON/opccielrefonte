package com.ged.mapper.standard;

import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.entity.standard.CategoriePersonne;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoriePersonneMapper {
    public CategoriePersonneDto deCatPersonne(CategoriePersonne categoriePersonne)
    {
        CategoriePersonneDto categoriePersonneDto = new CategoriePersonneDto();
        BeanUtils.copyProperties(categoriePersonne, categoriePersonneDto);
        return categoriePersonneDto;
    }

    public CategoriePersonne deCatPersonneDto(CategoriePersonneDto categoriePersonneDto)
    {
        CategoriePersonne categoriePersonne = new CategoriePersonne();
        BeanUtils.copyProperties(categoriePersonneDto, categoriePersonne);
        return categoriePersonne;
    }
}
