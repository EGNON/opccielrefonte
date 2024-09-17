package com.ged.mapper.crm;

import com.ged.mapper.standard.CategoriePersonneMapper;
import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.dto.crm.ObjectifAffecteEtatDto;
import com.ged.entity.crm.ObjectifAffecte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ObjectifAffecteMapper {
    private final AffectationMapper affectationMapper;
    private final CategoriePersonneMapper categoriePersonneMapper;
    private final PeriodiciteMapper periodiciteMapper;
    private final IndicateurMapper indicateurMapper;

    public ObjectifAffecteMapper(AffectationMapper affectationMapper, CategoriePersonneMapper categoriePersonneMapper, PeriodiciteMapper periodiciteMapper, IndicateurMapper indicateurMapper) {
        this.affectationMapper = affectationMapper;
        this.categoriePersonneMapper = categoriePersonneMapper;
        this.periodiciteMapper = periodiciteMapper;
        this.indicateurMapper = indicateurMapper;
    }

    public ObjectifAffecteDto deObjectifAffecte(ObjectifAffecte objectifAffecte)
    {
        ObjectifAffecteDto objectifAffecteDto = new ObjectifAffecteDto();
        BeanUtils.copyProperties(objectifAffecte, objectifAffecteDto);
        if(objectifAffecte.getAffectation() != null)
        {
            objectifAffecteDto.setAffectation(affectationMapper.deAffectation(objectifAffecte.getAffectation()));
        }
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
        return objectifAffecteDto;
    }
    public ObjectifAffecteEtatDto deObjectifAffecteEtat(ObjectifAffecte objectifAffecte)
    {
        ObjectifAffecteEtatDto objectifAffecteEtatDto = new ObjectifAffecteEtatDto();
        BeanUtils.copyProperties(objectifAffecte, objectifAffecteEtatDto);
        if(objectifAffecte.getAffectation() != null)
        {
            objectifAffecteEtatDto.setAffectation(affectationMapper.deAffectation(objectifAffecte.getAffectation()));
        }
        if(objectifAffecte.getCategoriePersonne() != null)
        {
            objectifAffecteEtatDto.setCategoriePersonne(categoriePersonneMapper.deCatPersonne(objectifAffecte.getCategoriePersonne()));
        }
        if(objectifAffecte.getPeriodicite() != null)
        {
            objectifAffecteEtatDto.setPeriodicite(periodiciteMapper.dePeriodicite(objectifAffecte.getPeriodicite()));
        }
        if(objectifAffecte.getIndicateur() != null)
        {
            objectifAffecteEtatDto.setIndicateur(indicateurMapper.deIndicateur(objectifAffecte.getIndicateur()));
        }
        return objectifAffecteEtatDto;
    }

    public ObjectifAffecte deObjectifAffecteDto(ObjectifAffecteDto objectifAffecteDto)
    {
        ObjectifAffecte objectifAffecte= new ObjectifAffecte();
        BeanUtils.copyProperties(objectifAffecteDto, objectifAffecte);
        if(objectifAffecteDto.getAffectation() != null)
        {
            objectifAffecte.setAffectation(affectationMapper.deAffectationDto(objectifAffecteDto.getAffectation()));
        }
        if(objectifAffecteDto.getCategoriePersonne() != null)
        {
            objectifAffecte.setCategoriePersonne(categoriePersonneMapper.deCatPersonneDto(objectifAffecteDto.getCategoriePersonne()));
        }
        if(objectifAffecteDto.getPeriodicite() != null)
        {
            objectifAffecte.setPeriodicite(periodiciteMapper.dePeriodiciteDto(objectifAffecteDto.getPeriodicite()));
        }
        if(objectifAffecteDto.getIndicateur() != null)
        {
            objectifAffecte.setIndicateur(indicateurMapper.deIndicateurDto(objectifAffecteDto.getIndicateur()));
        }
        return objectifAffecte;
    }

}
