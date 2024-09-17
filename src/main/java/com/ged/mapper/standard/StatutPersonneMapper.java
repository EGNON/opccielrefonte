package com.ged.mapper.standard;

import com.ged.dto.standard.CleStatutPersonneDto;
import com.ged.dto.standard.StatutPersonneDto;
import com.ged.entity.standard.StatutPersonne;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class StatutPersonneMapper {
    private final PersonneMapper personneMapper;
    private final QualiteMapper qualiteMapper;
    private final PersonnelMapper personnelMapper;

    public StatutPersonneMapper(PersonneMapper personneMapper, QualiteMapper qualiteMapper, PersonnelMapper personnelMapper) {
        this.personneMapper = personneMapper;
        this.qualiteMapper = qualiteMapper;
        this.personnelMapper = personnelMapper;
    }

    public StatutPersonneDto deStatutPersonne(StatutPersonne statutPersonne)
    {
        StatutPersonneDto statutPersonneDto = new StatutPersonneDto();
        BeanUtils.copyProperties(statutPersonne, statutPersonneDto);
        statutPersonneDto.setPersonneDto(personneMapper.dePersonne(statutPersonne.getPersonne()));
        statutPersonneDto.setQualite(qualiteMapper.deQualite(statutPersonne.getQualite()));
        if(statutPersonne.getPersonnel() != null) statutPersonneDto.setPersonnel(personnelMapper.dePersonnel(statutPersonne.getPersonnel()));
        if(statutPersonne.getPersonne().getIdPersonne() != null && statutPersonne.getQualite().getIdQualite() != null) {
            CleStatutPersonneDto cleStatutPersonne = new CleStatutPersonneDto(
                    statutPersonne.getPersonne().getIdPersonne(), statutPersonne.getQualite().getIdQualite());
            statutPersonneDto.setIdStatutPersonne(cleStatutPersonne);
        }
        return statutPersonneDto;
    }

    public StatutPersonne deStatutPersonneDto(StatutPersonneDto statutPersonneDto)
    {
        StatutPersonne statutPersonne = new StatutPersonne();
        BeanUtils.copyProperties(statutPersonneDto, statutPersonne);
        /*if(statutPersonneDto.getPersonne().getIdPersonne() != null && statutPersonneDto.getQualite().getIdQualite() != null) {
            CleStatutPersonne cleStatutPersonne = new CleStatutPersonne(statutPersonneDto.getPersonne().getIdPersonne(), statutPersonneDto.getQualite().getIdQualite());
            statutPersonne.setIdStatutPersonne(cleStatutPersonne);
        }*/
        return statutPersonne;
    }
}
