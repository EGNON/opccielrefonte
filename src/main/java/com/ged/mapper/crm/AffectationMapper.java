package com.ged.mapper.crm;

import com.ged.mapper.standard.CategoriePersonneMapper;
import com.ged.mapper.standard.PersonnelMapper;
import com.ged.dto.crm.AffectationDto;
import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.entity.crm.Affectation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class AffectationMapper {
    private final PersonnelMapper personnelMapper;
    private final CategoriePersonneMapper categoriePersonneMapper;
    private final PeriodiciteMapper periodiciteMapper;
    private final IndicateurMapper indicateurMapper;

    public AffectationMapper(PersonnelMapper personnelMapper, CategoriePersonneMapper categoriePersonneMapper, PeriodiciteMapper periodiciteMapper, IndicateurMapper indicateurMapper) {

        this.personnelMapper = personnelMapper;
        this.categoriePersonneMapper = categoriePersonneMapper;
        this.periodiciteMapper = periodiciteMapper;
        this.indicateurMapper = indicateurMapper;
    }

    public AffectationDto deAffectation(Affectation affectation)
    {
        AffectationDto affectationDTO = new AffectationDto();
        BeanUtils.copyProperties(affectation, affectationDTO);
        if(affectation.getPersonnel()!=null) {
            affectationDTO.setPersonnel(personnelMapper.dePersonnel(affectation.getPersonnel()));
        }
        if(affectation.getObjectifAffectes() != null)
        {
            affectationDTO.setObjectifAffectes(affectation.getObjectifAffectes().stream().map(objectifAffecte -> {
                ObjectifAffecteDto objectifAffecteDto = new ObjectifAffecteDto();
                if(objectifAffecte.getCategoriePersonne() != null)
                {
                    objectifAffecteDto.setCategoriePersonne(categoriePersonneMapper.deCatPersonne(objectifAffecte.getCategoriePersonne()));
                }
                if(objectifAffecte.getPeriodicite() != null)
                {
                    objectifAffecteDto.setPeriodicite(periodiciteMapper.dePeriodicite(objectifAffecte.getPeriodicite()));
                }
                if(objectifAffecte.getIndicateur() != null)
                {
                    objectifAffecteDto.setIndicateur(indicateurMapper.deIndicateur(objectifAffecte.getIndicateur()));
                }
                objectifAffecteDto.setValeurAffecte(objectifAffecte.getValeurAffecte());
                objectifAffecteDto.setValeurReelle(objectifAffecte.getValeurReelle());
                return objectifAffecteDto;
            }).collect(Collectors.toSet()));
        }
        return affectationDTO;
    }

    public Affectation deAffectationDto(AffectationDto affectationDto)
    {
        Affectation affectation = new Affectation();
        BeanUtils.copyProperties(affectationDto, affectation);
        if(affectationDto.getPersonnel()!=null) {
            affectation.setPersonnel(personnelMapper.dePersonnelDto(affectationDto.getPersonnel()));
        }
        return affectation;
    }
}
