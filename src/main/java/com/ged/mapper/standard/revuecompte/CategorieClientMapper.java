package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.CategorieClientDto;
import com.ged.entity.standard.revuecompte.CategorieClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategorieClientMapper {
    public CategorieClientDto deCatClient(CategorieClient categorieClient)
    {
        if(categorieClient == null) {
            return null;
        }
        CategorieClientDto categorieClientDto = new CategorieClientDto();
        BeanUtils.copyProperties(categorieClient, categorieClientDto);
        return categorieClientDto;
    }

    public CategorieClient deCatClientDto(CategorieClientDto categorieClientDto)
    {
        if(categorieClientDto == null) {
            return null;
        }
        CategorieClient categorieClient = new CategorieClient();
        BeanUtils.copyProperties(categorieClientDto, categorieClient);
        return categorieClient;
    }
}
