package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.OpcDto;
import com.ged.entity.titresciel.Opc;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OpcMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final PlaceMapper placeMapper;
    private final CompartimentMapper compartimentMapper;
    private final TypeEmissionMapper typeEmissionMapper;
    private final FormeJuridiqueOpcMapper formeJuridiqueOpcMapper;
    private final TypeAffectationTitreMapper typeAffectationTitreMapper;
    private final ClassificationOPCMapper classificationOpcMapper;

    public OpcMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, TypeTitreMapper typeTitreMapper, PersonneMoraleMapper personneMoraleMapper, PlaceMapper placeMapper, CompartimentMapper compartimentMapper, TypeEmissionMapper typeEmissionMapper, FormeJuridiqueOpcMapper formeJuridiqueOpcMapper, TypeAffectationTitreMapper typeAffectationTitreMapper, ClassificationOPCMapper classificationOpcMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.placeMapper = placeMapper;
        this.compartimentMapper = compartimentMapper;
        this.typeEmissionMapper = typeEmissionMapper;
        this.formeJuridiqueOpcMapper = formeJuridiqueOpcMapper;
        this.typeAffectationTitreMapper = typeAffectationTitreMapper;
        this.classificationOpcMapper = classificationOpcMapper;
    }

    public OpcDto deOpc(Opc opc)
    {
        if(opc == null)
            return null;
        OpcDto opcDto = new OpcDto();
        BeanUtils.copyProperties(opc, opcDto);
        opcDto.setSecteur(secteurMapper.deSecteur(opc.getSecteur()));
        opcDto.setPays(paysMapper.dePays(opc.getPays()));
        opcDto.setTypeTitre(typeTitreMapper.deTypeTitre(opc.getTypeTitre()));
        opcDto.setEmetteur(personneMoraleMapper.dePersonneMorale(opc.getEmetteur()));
        opcDto.setRegistraire(personneMoraleMapper.dePersonneMorale(opc.getRegistraire()));
        opcDto.setDepositaire(personneMoraleMapper.dePersonneMorale(opc.getDepositaire()));
        opcDto.setPlace(placeMapper.dePlace(opc.getPlace()));
        opcDto.setCompartiment(compartimentMapper.deCompartiment(opc.getCompartiment()));
        opcDto.setTypeEmission(typeEmissionMapper.deTypeEmission(opc.getTypeEmission()));
        opcDto.setFormeJuridiqueOpc(formeJuridiqueOpcMapper.deFormeJuridiqueOpc(opc.getFormeJuridiqueOpc()));
        opcDto.setTypeAffectationTitre(typeAffectationTitreMapper.deTypeAffectation(opc.getTypeAffectationTitre()));
        opcDto.setClassificationOPC(classificationOpcMapper.deClassification(opc.getClassificationOPC()));
        return opcDto;
    }

    public Opc deOpcDto(OpcDto OpcDTO)
    {
        Opc Opc = new Opc();
        BeanUtils.copyProperties(OpcDTO, Opc);
        return Opc;
    }
}
