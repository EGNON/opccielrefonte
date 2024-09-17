package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.FormuleDto;
import com.ged.entity.opcciel.comptabilite.Formule;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class FormuleMapper {
    private final TypeFormuleMapper typeFormuleMapper;


    public FormuleMapper(TypeFormuleMapper typeFormuleMapper) {
        this.typeFormuleMapper = typeFormuleMapper;

    }


    public FormuleDto deFormule(Formule formule)
    {
        if(formule == null)
            return null;
        FormuleDto formuleDto = new FormuleDto();
        BeanUtils.copyProperties(formule, formuleDto);
        if(formule.getTypeFormule()!=null)
        {
            formuleDto.setTypeFormule(typeFormuleMapper.deTypeFormule(formule.getTypeFormule()));
        }
        /*if(formule.getModeleEcritureFormules() != null)
        {
            Set<ModeleEcritureFormuleDto> modeleEcritureFormules=new HashSet<>();
            formule.getModeleEcritureFormules().forEach(modeleEcritureFormule -> {
                ModeleEcritureFormuleDto modeleEcritureFormuleDto=new ModeleEcritureFormuleDto();
                if(modeleEcritureFormule.getModeleEcriture()!=null) {
                    modeleEcritureFormuleDto.setModeleEcriture(modeleEcritureMapper.deModeleEcriture(modeleEcritureFormule.getModeleEcriture()));
                }
                if(modeleEcritureFormule.getFormule()!=null) {
                    FormuleDto formuleDto1=new FormuleDto();
                    BeanUtils.copyProperties(modeleEcritureFormule.getFormule(), formuleDto1);
                    modeleEcritureFormuleDto.setFormule(formuleDto1);
                }
                modeleEcritureFormules.add(modeleEcritureFormuleDto);
            });
            formuleDto.setModeleEcritureFormules(modeleEcritureFormules);
        }*/
        return formuleDto;
    }

    public Formule deFormuleDto(FormuleDto formuleDto)
    {
        Formule formule = new Formule();
        BeanUtils.copyProperties(formuleDto, formule);
        if(formuleDto.getTypeFormule()!=null)
        {
            formule.setTypeFormule(typeFormuleMapper.deTypeFormuleDto(formuleDto.getTypeFormule()));
        }
        /*if(formuleDto.getModeleEcritureFormules() != null)
        {
            Set<ModeleEcritureFormule> modeleEcritureFormules=new HashSet<>();
            formuleDto.getModeleEcritureFormules().forEach(modeleEcritureFormule -> {
                ModeleEcritureFormule modeleEcritureFormule1=new ModeleEcritureFormule();
                if(modeleEcritureFormule.getModeleEcriture()!=null) {
                    modeleEcritureFormule1.setModeleEcriture(modeleEcritureMapper.deModeleEcritureDto(modeleEcritureFormule.getModeleEcriture()));
                }
                if(modeleEcritureFormule.getFormule()!=null) {
                    Formule formuleDto1=new Formule();
                    BeanUtils.copyProperties(modeleEcritureFormule.getFormule(), formuleDto1);
                    modeleEcritureFormule1.setFormule(formuleDto1);
                }
                modeleEcritureFormules.add(modeleEcritureFormule1);
            });
            formule.setModeleEcritureFormules(modeleEcritureFormules);
        }*/
        return formule;
    }
}
