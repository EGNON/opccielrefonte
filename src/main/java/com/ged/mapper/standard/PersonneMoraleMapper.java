package com.ged.mapper.standard;

import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.entity.standard.PersonneMorale;
import com.ged.mapper.crm.DegreMapper;
import com.ged.mapper.opcciel.FormeJuridiqueMapper;
import com.ged.mapper.standard.revuecompte.CategorieClientMapper;
import com.ged.mapper.standard.revuecompte.SousTypeClientMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonneMoraleMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final DegreMapper degreMapper;
    private final SousTypeClientMapper sousTypeClientMapper;
    private final CategorieClientMapper categorieClientMapper;
    private final PersonneMapper personneMapper;
    private final StatutPersonneMapper statutPersonneMapper;
    private final FormeJuridiqueMapper formeJuridiqueMapper;

    public PersonneMoraleMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, DegreMapper degreMapper, SousTypeClientMapper sousTypeClientMapper, CategorieClientMapper categorieClientMapper, PersonneMapper personneMapper, StatutPersonneMapper statutPersonneMapper, FormeJuridiqueMapper formeJuridiqueMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.degreMapper = degreMapper;
        this.sousTypeClientMapper = sousTypeClientMapper;
        this.categorieClientMapper = categorieClientMapper;
        this.personneMapper = personneMapper;
        this.statutPersonneMapper = statutPersonneMapper;
        this.formeJuridiqueMapper = formeJuridiqueMapper;
    }

    public PersonneMoraleDto dePersonneMorale(PersonneMorale personneMorale)
    {
        if(personneMorale == null)
            return null;
        PersonneMoraleDto personneMoraleDto = new PersonneMoraleDto();
        BeanUtils.copyProperties(personneMorale, personneMoraleDto);
        personneMoraleDto.setSecteur(secteurMapper.deSecteur(personneMorale.getSecteur()));
        personneMoraleDto.setDegre(degreMapper.deDegre(personneMorale.getDegre()));
        personneMoraleDto.setDistributeur(personneMapper.dePersonne(personneMorale.getDistributeur()));
        personneMoraleDto.setPaysResidence(paysMapper.dePays(personneMorale.getPaysResidence()));
        /*if(personneMorale.getFormeJuridique()!=null) {
            personneMoraleDto.setFormeJuridique(formeJuridiqueMapper.deFormeJuridique(personneMorale.getFormeJuridique()));
        }*/
        if(personneMorale.getSousTypeClient()!=null) {
            personneMoraleDto.setSousTypeClient(sousTypeClientMapper.deSousTypeClient(personneMorale.getSousTypeClient()));
        }
        if(personneMorale.getCategorieClient()!=null) {
            personneMoraleDto.setCategorieClient(categorieClientMapper.deCatClient(personneMorale.getCategorieClient()));
        }
        if(personneMorale.getStatutPersonnes()!=null) {
            personneMorale.getStatutPersonnes().forEach(statutPersonne -> {
                personneMoraleDto.setStatutPersonnes(personneMorale.getStatutPersonnes().stream()
                        .map(statutPersonneMapper::deStatutPersonne).collect(Collectors.toSet()));
            });
        }
        return personneMoraleDto;
    }

    public PersonneMorale dePersonneMoraleDto(PersonneMoraleDto personneMoraleDto)
    {
        PersonneMorale personneMorale= new PersonneMorale();
        BeanUtils.copyProperties(personneMoraleDto, personneMorale);
        return personneMorale;
    }
}
