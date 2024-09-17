package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.CategorieClientDto;
import com.ged.entity.standard.revuecompte.CategorieClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategorieClientMapper {
    public CategorieClientDto deCatClient(CategorieClient categorieClient)
    {
        CategorieClientDto categorieClientDto = new CategorieClientDto();
        BeanUtils.copyProperties(categorieClient, categorieClientDto);
        return categorieClientDto;
    }

    public CategorieClient deCatClientDto(CategorieClientDto categorieClientDto)
    {
        CategorieClient categorieClient = new CategorieClient();
        BeanUtils.copyProperties(categorieClientDto, categorieClient);
        return categorieClient;
    }
}
