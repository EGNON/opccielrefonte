package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TitreMapper {
    private final PlaceMapper placeMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final TypeEmissionMapper typeEmissionMapper;
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final PersonneMoraleMapper personneMoraleMapper;

    public TitreMapper(PlaceMapper placeMapper, TypeTitreMapper typeTitreMapper, TypeEmissionMapper typeEmissionMapper, SecteurMapper secteurMapper, PaysMapper paysMapper, PersonneMoraleMapper personneMoraleMapper) {
        this.placeMapper = placeMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.typeEmissionMapper = typeEmissionMapper;
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.personneMoraleMapper = personneMoraleMapper;
    }

    public TitreDto deTitre(Titre titre)
    {
        if(titre == null)
            return null;
        TitreDto titreDto = new TitreDto();
        BeanUtils.copyProperties(titre, titreDto);
        titreDto.setPlace(placeMapper.dePlace(titre.getPlace()));
        titreDto.setTypeTitre(typeTitreMapper.deTypeTitre(titre.getTypeTitre()));
        titreDto.setTypeEmission(typeEmissionMapper.deTypeEmission(titre.getTypeEmission()));
        titreDto.setSecteur(secteurMapper.deSecteur(titre.getSecteur()));
        titreDto.setPays(paysMapper.dePays(titre.getPays()));
        titreDto.setEmetteur(personneMoraleMapper.dePersonneMorale(titre.getEmetteur()));
        titreDto.setRegistraire(personneMoraleMapper.dePersonneMorale(titre.getRegistraire()));
        titreDto.setDepositaire(personneMoraleMapper.dePersonneMorale(titre.getDepositaire()));
        return titreDto;
    }

    public Titre deTitreDto(TitreDto titreDto)
    {
        if(titreDto == null)
            return null;
        Titre titre = new Titre();
        BeanUtils.copyProperties(titreDto, titre);
        return titre;
    }
}
