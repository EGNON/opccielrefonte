package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.ActionDto;
import com.ged.entity.titresciel.Action;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActionMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final PlaceMapper placeMapper;
    private final CompartimentMapper compartimentMapper;
    private final TypeEmissionMapper typeEmissionMapper;
    private final TypeActionMapper typeActionMapper;
    private final SousTypeActionMapper sousTypeActionMapper;

    public ActionMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, TypeTitreMapper typeTitreMapper, PersonneMoraleMapper personneMoraleMapper, PlaceMapper placeMapper, CompartimentMapper compartimentMapper, TypeEmissionMapper typeEmissionMapper, TypeActionMapper typeActionMapper, SousTypeActionMapper sousTypeActionMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.placeMapper = placeMapper;
        this.compartimentMapper = compartimentMapper;
        this.typeEmissionMapper = typeEmissionMapper;
        this.typeActionMapper = typeActionMapper;
        this.sousTypeActionMapper = sousTypeActionMapper;
    }

    public ActionDto deAction(Action action)
    {
        if(action == null)
            return null;
        ActionDto actionDto = new ActionDto();
        BeanUtils.copyProperties(action, actionDto);
        actionDto.setSecteur(secteurMapper.deSecteur(action.getSecteur()));
        actionDto.setPays(paysMapper.dePays(action.getPays()));
        actionDto.setTypeTitre(typeTitreMapper.deTypeTitre(action.getTypeTitre()));
        actionDto.setEmetteur(personneMoraleMapper.dePersonneMorale(action.getEmetteur()));
        actionDto.setRegistraire(personneMoraleMapper.dePersonneMorale(action.getRegistraire()));
        actionDto.setDepositaire(personneMoraleMapper.dePersonneMorale(action.getDepositaire()));
        actionDto.setPlace(placeMapper.dePlace(action.getPlace()));
        actionDto.setCompartiment(compartimentMapper.deCompartiment(action.getCompartiment()));
        actionDto.setTypeEmission(typeEmissionMapper.deTypeEmission(action.getTypeEmission()));
        actionDto.setTypeAction(typeActionMapper.deTypeAction(action.getTypeAction()));
        actionDto.setSousTypeAction(sousTypeActionMapper.deSousTypeAction(action.getSousTypeAction()));
        return actionDto;
    }

    public Action deActionDto(ActionDto ActionDTO)
    {
        Action Action = new Action();
        BeanUtils.copyProperties(ActionDTO, Action);
        return Action;
    }
}
