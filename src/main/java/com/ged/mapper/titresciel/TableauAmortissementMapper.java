package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.CleTableauAmortissementDto;
import com.ged.dto.titresciel.TableauAmortissementDto;
import com.ged.entity.titresciel.CleTableauAmortissement;
import com.ged.entity.titresciel.TableauAmortissement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TableauAmortissementMapper {
    private final TitreMapper titreMapper;

    public TableauAmortissementMapper(TitreMapper titreMapper) {
        this.titreMapper = titreMapper;
    }

    public TableauAmortissementDto deTableauAmortissement(TableauAmortissement TableauAmortissement)
    {
        if (TableauAmortissement == null)
            return null;
        TableauAmortissementDto TableauAmortissementDto = new TableauAmortissementDto();
        BeanUtils.copyProperties(TableauAmortissement, TableauAmortissementDto);
        CleTableauAmortissementDto cleTableauAmortissement = new CleTableauAmortissementDto();
        cleTableauAmortissement.setIdTitre(TableauAmortissement.getIdTabAmortissement().getIdTitre());
        cleTableauAmortissement.setDateEcheance(TableauAmortissement.getIdTabAmortissement().getDateEcheance());
        TableauAmortissementDto.setIdTabAmortissement(cleTableauAmortissement);
        TableauAmortissementDto.setTitre(titreMapper.deTitre(TableauAmortissement.getTitre()));
        return TableauAmortissementDto;
    }

    public TableauAmortissement deTableauAmortissementDto(TableauAmortissementDto TableauAmortissementDTO)
    {
        if(TableauAmortissementDTO == null)
            return null;
        TableauAmortissement TableauAmortissement = new TableauAmortissement();
        BeanUtils.copyProperties(TableauAmortissementDTO, TableauAmortissement);
        CleTableauAmortissement cleTableauAmortissement = new CleTableauAmortissement();
        cleTableauAmortissement.setIdTitre(TableauAmortissementDTO.getIdTabAmortissement().getIdTitre());
        cleTableauAmortissement.setDateEcheance(TableauAmortissementDTO.getIdTabAmortissement().getDateEcheance());
        TableauAmortissement.setIdTabAmortissement(cleTableauAmortissement);
        TableauAmortissement.setTitre(titreMapper.deTitreDto(TableauAmortissementDTO.getTitre()));
        return TableauAmortissement;
    }
}
