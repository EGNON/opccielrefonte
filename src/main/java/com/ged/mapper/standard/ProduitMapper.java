package com.ged.mapper.standard;

import com.ged.dto.standard.ProduitDto;
import com.ged.entity.standard.Produit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProduitMapper {
    public ProduitDto deProduit(Produit produit)
    {
        ProduitDto produitDto = new ProduitDto();
        BeanUtils.copyProperties(produit, produitDto);
        return produitDto;
    }

    public Produit deProduitDto(ProduitDto produitDto)
    {
        Produit produit= new Produit();
        BeanUtils.copyProperties(produitDto, produit);
        return produit;
    }

}
