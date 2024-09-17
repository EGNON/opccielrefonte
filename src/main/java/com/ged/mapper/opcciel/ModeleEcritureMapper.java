package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.ModeleEcritureDto;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureFormuleDto;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureFormule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ModeleEcritureMapper {
    private final FormuleMapper formuleMapper;

    public ModeleEcritureMapper(FormuleMapper formuleMapper) {

        this.formuleMapper = formuleMapper;
    }

    public ModeleEcritureDto deModeleEcriture(ModeleEcriture ModeleEcriture)
    {
        if(ModeleEcriture == null)
            return null;
        ModeleEcritureDto ModeleEcritureDto = new ModeleEcritureDto();
        BeanUtils.copyProperties(ModeleEcriture, ModeleEcritureDto);
        if(ModeleEcriture.getModeleEcritureFormules() != null)
        {
            Set<ModeleEcritureFormuleDto> modeleEcritureFormules=new HashSet<>();
            ModeleEcriture.getModeleEcritureFormules().forEach(modeleEcritureFormule -> {
                ModeleEcritureFormuleDto modeleEcritureFormuleDto=new ModeleEcritureFormuleDto();
                if(modeleEcritureFormule.getModeleEcriture()!=null) {
                    ModeleEcritureDto modeleEcritureDto=new ModeleEcritureDto();
                    BeanUtils.copyProperties(modeleEcritureFormule.getModeleEcriture(), modeleEcritureDto);
                    modeleEcritureFormuleDto.setModeleEcriture(modeleEcritureDto);

                }
                if(modeleEcritureFormule.getFormule()!=null) {
                    modeleEcritureFormuleDto.setFormule(formuleMapper.deFormule(modeleEcritureFormule.getFormule()));
                }
                modeleEcritureFormules.add(modeleEcritureFormuleDto);
            });
            ModeleEcritureDto.setModeleEcritureFormules(modeleEcritureFormules);
        }
        return ModeleEcritureDto;
    }

    public ModeleEcriture deModeleEcritureDto(ModeleEcritureDto ModeleEcritureDto)
    {
        ModeleEcriture ModeleEcriture = new ModeleEcriture();
        BeanUtils.copyProperties(ModeleEcritureDto, ModeleEcriture);
         if(ModeleEcritureDto.getModeleEcritureFormules() != null)
        {
            Set<ModeleEcritureFormule> modeleEcritureFormules=new HashSet<>();
            ModeleEcritureDto.getModeleEcritureFormules().forEach(modeleEcritureFormule -> {
                ModeleEcritureFormule modeleEcritureFormule1=new ModeleEcritureFormule();
                if(modeleEcritureFormule.getModeleEcriture()!=null) {
                    ModeleEcriture modeleEcriture=new ModeleEcriture();
                    BeanUtils.copyProperties(modeleEcritureFormule.getModeleEcriture(), modeleEcriture);
                    modeleEcritureFormule1.setModeleEcriture(modeleEcriture);
                }
                if(modeleEcritureFormule.getFormule()!=null) {
                    modeleEcritureFormule1.setFormule(formuleMapper.deFormuleDto(modeleEcritureFormule.getFormule()));
                }
                modeleEcritureFormules.add(modeleEcritureFormule1);
            });
            ModeleEcriture.setModeleEcritureFormules(modeleEcritureFormules);
        }
         //System.out.println("size="+ModeleEcriture.getModeleEcritureFormules().size());
        return ModeleEcriture;
    }
}
