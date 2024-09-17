package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TcnDto;
import com.ged.entity.titresciel.Tcn;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TcnMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final ModeAmortissementMapper modeAmortissementMapper;
    private final TypeAmortissementMapper typeAmortissementMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final PlaceMapper placeMapper;
    private final CompartimentMapper compartimentMapper;
    private final TypeEmissionMapper typeEmissionMapper;
    private final NatureTcnMapper natureTcnMapper;
    private final ModeCalculInteretMapper modeCalculInteretMapper;

    public TcnMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, TypeTitreMapper typeTitreMapper, ModeAmortissementMapper modeAmortissementMapper, TypeAmortissementMapper typeAmortissementMapper, PersonneMoraleMapper personneMoraleMapper, PlaceMapper placeMapper, CompartimentMapper compartimentMapper, TypeEmissionMapper typeEmissionMapper, NatureTcnMapper natureTcnMapper, ModeCalculInteretMapper modeCalculInteretMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.modeAmortissementMapper = modeAmortissementMapper;
        this.typeAmortissementMapper = typeAmortissementMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.placeMapper = placeMapper;
        this.compartimentMapper = compartimentMapper;
        this.typeEmissionMapper = typeEmissionMapper;
        this.natureTcnMapper = natureTcnMapper;
        this.modeCalculInteretMapper = modeCalculInteretMapper;
    }

    public TcnDto deTcn(Tcn tcn)
    {
        if(tcn == null)
            return null;
        TcnDto tcnDto = new TcnDto();
        BeanUtils.copyProperties(tcn, tcnDto);
        tcnDto.setSecteur(secteurMapper.deSecteur(tcn.getSecteur()));
        tcnDto.setPays(paysMapper.dePays(tcn.getPays()));
        tcnDto.setTypeTitre(typeTitreMapper.deTypeTitre(tcn.getTypeTitre()));
        tcnDto.setModeAmortissement(modeAmortissementMapper.deModeAmortissement(tcn.getModeAmortissement()));
        tcnDto.setTypeAmortissement(typeAmortissementMapper.deTypeAmortissement(tcn.getTypeAmortissement()));
        tcnDto.setEmetteur(personneMoraleMapper.dePersonneMorale(tcn.getEmetteur()));
        tcnDto.setRegistraire(personneMoraleMapper.dePersonneMorale(tcn.getRegistraire()));
        tcnDto.setDepositaire(personneMoraleMapper.dePersonneMorale(tcn.getDepositaire()));
        tcnDto.setPlace(placeMapper.dePlace(tcn.getPlace()));
        tcnDto.setCompartiment(compartimentMapper.deCompartiment(tcn.getCompartiment()));
        tcnDto.setTypeEmission(typeEmissionMapper.deTypeEmission(tcn.getTypeEmission()));
        tcnDto.setNatureTcn(natureTcnMapper.deNatureTcn(tcn.getNatureTcn()));
        tcnDto.setFormulePrecomptee(modeCalculInteretMapper.deModeCalculInteret(tcn.getFormulePrecomptee()));
        return tcnDto;
    }

    public Tcn deTcnDto(TcnDto TcnDTO)
    {
        Tcn Tcn = new Tcn();
        BeanUtils.copyProperties(TcnDTO, Tcn);
        return Tcn;
    }
}
