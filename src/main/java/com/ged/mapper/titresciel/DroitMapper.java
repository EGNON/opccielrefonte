package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.DroitDto;
import com.ged.entity.titresciel.Droit;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DroitMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final PlaceMapper placeMapper;
    private final CompartimentMapper compartimentMapper;
    private final TypeEmissionMapper typeEmissionMapper;
    private final ActionMapper actionMapper;

    public DroitMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, TypeTitreMapper typeTitreMapper, PersonneMoraleMapper personneMoraleMapper, PlaceMapper placeMapper, CompartimentMapper compartimentMapper, TypeEmissionMapper typeEmissionMapper, ActionMapper actionMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.placeMapper = placeMapper;
        this.compartimentMapper = compartimentMapper;
        this.typeEmissionMapper = typeEmissionMapper;
        this.actionMapper = actionMapper;
    }

    public DroitDto deDroit(Droit droit)
    {
        if(droit == null)
            return null;
        DroitDto droitDto = new DroitDto();
        BeanUtils.copyProperties(droit, droitDto);
        droitDto.setSecteur(secteurMapper.deSecteur(droit.getSecteur()));
        droitDto.setPays(paysMapper.dePays(droit.getPays()));
        droitDto.setTypeTitre(typeTitreMapper.deTypeTitre(droit.getTypeTitre()));
        droitDto.setEmetteur(personneMoraleMapper.dePersonneMorale(droit.getEmetteur()));
        droitDto.setRegistraire(personneMoraleMapper.dePersonneMorale(droit.getRegistraire()));
        droitDto.setDepositaire(personneMoraleMapper.dePersonneMorale(droit.getDepositaire()));
        droitDto.setPlace(placeMapper.dePlace(droit.getPlace()));
        droitDto.setCompartiment(compartimentMapper.deCompartiment(droit.getCompartiment()));
        droitDto.setTypeEmission(typeEmissionMapper.deTypeEmission(droit.getTypeEmission()));
        droitDto.setActionLiee(actionMapper.deAction(droit.getActionLiee()));
        droitDto.setNouvelleAction(actionMapper.deAction(droit.getNouvelleAction()));
        return droitDto;
    }

    public Droit deDroitDto(DroitDto DroitDTO)
    {
        Droit Droit = new Droit();
        BeanUtils.copyProperties(DroitDTO, Droit);
        return Droit;
    }
}
