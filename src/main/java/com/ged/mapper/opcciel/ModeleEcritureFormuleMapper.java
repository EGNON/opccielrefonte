package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.ModeleEcritureFormuleDto;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureFormule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ModeleEcritureFormuleMapper {
    private final ModeleEcritureMapper modeleEcritureMapper;
    private final FormuleMapper formuleMapper;

    public ModeleEcritureFormuleMapper(ModeleEcritureMapper modeleEcritureMapper, FormuleMapper formuleMapper) {
        this.modeleEcritureMapper = modeleEcritureMapper;
        this.formuleMapper = formuleMapper;
    }

    public ModeleEcritureFormuleDto deModeleEcritureFormule(ModeleEcritureFormule ModeleEcritureFormule)
    {
        if(ModeleEcritureFormule == null)
            return null;
        ModeleEcritureFormuleDto ModeleEcritureFormuleDto = new ModeleEcritureFormuleDto();
        BeanUtils.copyProperties(ModeleEcritureFormule, ModeleEcritureFormuleDto);
        if(ModeleEcritureFormule.getModeleEcriture()!=null){
            ModeleEcritureFormuleDto.setModeleEcriture(modeleEcritureMapper.deModeleEcriture(ModeleEcritureFormule.getModeleEcriture()));
        }
        if(ModeleEcritureFormule.getFormule()!=null){
            ModeleEcritureFormuleDto.setFormule(formuleMapper.deFormule(ModeleEcritureFormule.getFormule()));
        }
        return ModeleEcritureFormuleDto;
    }

    public ModeleEcritureFormule deModeleEcritureFormuleDto(ModeleEcritureFormuleDto ModeleEcritureFormuleDto)
    {
        ModeleEcritureFormule ModeleEcritureFormule = new ModeleEcritureFormule();
        BeanUtils.copyProperties(ModeleEcritureFormuleDto, ModeleEcritureFormule);
        return ModeleEcritureFormule;
    }
}
