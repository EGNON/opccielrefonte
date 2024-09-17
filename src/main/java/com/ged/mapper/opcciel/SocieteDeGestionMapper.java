package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.SocieteDeGestionDto;
import com.ged.entity.opcciel.SocieteDeGestion;
import com.ged.mapper.standard.CommuneMapper;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SocieteDeGestionMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final CommuneMapper communeMapper;
    private final FormeJuridiqueMapper formeJuridiqueMapper;

    public SocieteDeGestionMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, CommuneMapper communeMapper, FormeJuridiqueMapper formeJuridiqueMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.communeMapper = communeMapper;
        this.formeJuridiqueMapper = formeJuridiqueMapper;
    }

    public SocieteDeGestionDto deSocieteDeGestion(SocieteDeGestion societeDeGestion)
    {
        if(societeDeGestion == null)
            return  null;
        SocieteDeGestionDto societeDeGestionDto = new SocieteDeGestionDto();
        BeanUtils.copyProperties(societeDeGestion, societeDeGestionDto);
        societeDeGestionDto.setSecteur(secteurMapper.deSecteur(societeDeGestion.getSecteur()));
        societeDeGestionDto.setPaysResidence(paysMapper.dePays(societeDeGestion.getPaysResidence()));
        if(societeDeGestion.getCommune()!=null) {
            societeDeGestionDto.setCommune(communeMapper.deCommune(societeDeGestion.getCommune()));
        }

        /*if(societeDeGestion.getFormeJuridique()!=null) {
            societeDeGestionDto.setFormeJuridique(formeJuridiqueMapper.deFormeJuridique(societeDeGestion.getFormeJuridique()));
        }*/
        return societeDeGestionDto;
    }

    public SocieteDeGestion deSocieteDeGestionDto(SocieteDeGestionDto societeDeGestionDto)
    {
        SocieteDeGestion societeDeGestion = new SocieteDeGestion();
        BeanUtils.copyProperties(societeDeGestionDto, societeDeGestion);
        return societeDeGestion;
    }
}
