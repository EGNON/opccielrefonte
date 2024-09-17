package com.ged.mapper.crm;

import com.ged.dto.crm.DetailObjectifDto;
import com.ged.entity.crm.DetailObjectif;
import com.ged.mapper.crm.IndicateurMapper;
import com.ged.mapper.crm.PeriodiciteMapper;
import com.ged.mapper.standard.CategoriePersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DetailObjectifMapper {
    private final CategoriePersonneMapper categoriePersonneMapper;
    private final IndicateurMapper indicateurMapper;
    private final PeriodiciteMapper periodiciteMapper;

    public DetailObjectifMapper(CategoriePersonneMapper categoriePersonneMapper, IndicateurMapper indicateurMapper, PeriodiciteMapper periodiciteMapper) {
        this.categoriePersonneMapper = categoriePersonneMapper;
        this.indicateurMapper = indicateurMapper;
        this.periodiciteMapper = periodiciteMapper;
    }

    public DetailObjectifDto deDetailObjectif(DetailObjectif detailObjectif)
    {
        DetailObjectifDto detailObjectifDto = new DetailObjectifDto();
        BeanUtils.copyProperties(detailObjectif, detailObjectifDto);
        if(detailObjectif.getCategoriePersonne() != null) detailObjectifDto.setCategoriePersonne(categoriePersonneMapper.deCatPersonne(detailObjectif.getCategoriePersonne()));
        if(detailObjectif.getIndicateur() != null) detailObjectifDto.setIndicateur(indicateurMapper.deIndicateur(detailObjectif.getIndicateur()));
        if(detailObjectif.getPeriodicite() != null) detailObjectifDto.setPeriodicite(periodiciteMapper.dePeriodicite(detailObjectif.getPeriodicite()));
        return detailObjectifDto;
    }

    public DetailObjectif deDetailObjectifDto(DetailObjectifDto detailObjectifDto)
    {
        DetailObjectif detailObjectif = new DetailObjectif();
        BeanUtils.copyProperties(detailObjectifDto, detailObjectif);
        return detailObjectif;
    }
}
