package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.DetailModeleDto;
import com.ged.entity.opcciel.comptabilite.DetailModele;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DetailModeleMapper {
    private final ModeleEcritureMapper modeleEcritureMapper;
    private final FormuleMapper formuleMapper;

    public DetailModeleMapper(ModeleEcritureMapper modeleEcritureMapper, FormuleMapper formuleMapper) {
        this.modeleEcritureMapper = modeleEcritureMapper;
        this.formuleMapper = formuleMapper;
    }

    public DetailModeleDto deDetailModele(DetailModele DetailModele)
    {
        if(DetailModele == null)
            return null;
        DetailModeleDto DetailModeleDto = new DetailModeleDto();
        BeanUtils.copyProperties(DetailModele, DetailModeleDto);
        if(DetailModele.getModeleEcriture()!=null){
            DetailModeleDto.setModeleEcriture(modeleEcritureMapper.deModeleEcriture(DetailModele.getModeleEcriture()));
        }
        if(DetailModele.getFormule()!=null){
            DetailModeleDto.setFormule(formuleMapper.deFormule(DetailModele.getFormule()));
        }
        return DetailModeleDto;
    }

    public DetailModele deDetailModeleDto(DetailModeleDto DetailModeleDto)
    {
        DetailModele DetailModele = new DetailModele();
        BeanUtils.copyProperties(DetailModeleDto, DetailModele);
        return DetailModele;
    }
}
