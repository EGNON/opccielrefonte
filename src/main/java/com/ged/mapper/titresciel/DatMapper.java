package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.DatDto;
import com.ged.entity.titresciel.Dat;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DatMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final PlaceMapper placeMapper;
    private final CompartimentMapper compartimentMapper;
    private final TypeEmissionMapper typeEmissionMapper;

    public DatMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, TypeTitreMapper typeTitreMapper, PersonneMoraleMapper personneMoraleMapper, PlaceMapper placeMapper, CompartimentMapper compartimentMapper, TypeEmissionMapper typeEmissionMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.placeMapper = placeMapper;
        this.compartimentMapper = compartimentMapper;
        this.typeEmissionMapper = typeEmissionMapper;
    }

    public DatDto deDat(Dat dat)
    {
        if(dat == null)
            return null;
        DatDto datDto = new DatDto();
        BeanUtils.copyProperties(dat, datDto);
        datDto.setSecteur(secteurMapper.deSecteur(dat.getSecteur()));
        datDto.setPays(paysMapper.dePays(dat.getPays()));
        datDto.setTypeTitre(typeTitreMapper.deTypeTitre(dat.getTypeTitre()));
        datDto.setEmetteur(personneMoraleMapper.dePersonneMorale(dat.getEmetteur()));
        datDto.setRegistraire(personneMoraleMapper.dePersonneMorale(dat.getRegistraire()));
        datDto.setDepositaire(personneMoraleMapper.dePersonneMorale(dat.getDepositaire()));
        datDto.setPlace(placeMapper.dePlace(dat.getPlace()));
        datDto.setCompartiment(compartimentMapper.deCompartiment(dat.getCompartiment()));
        datDto.setTypeEmission(typeEmissionMapper.deTypeEmission(dat.getTypeEmission()));
        datDto.setBanque(personneMoraleMapper.dePersonneMorale(dat.getBanque()));
        return datDto;
    }

    public Dat deDatDto(DatDto DatDTO)
    {
        Dat Dat = new Dat();
        BeanUtils.copyProperties(DatDTO, Dat);
        return Dat;
    }
}
