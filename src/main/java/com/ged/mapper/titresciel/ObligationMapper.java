package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.CleTableauAmortissementDto;
import com.ged.dto.titresciel.ObligationDto;
import com.ged.dto.titresciel.TableauAmortissementDto;
import com.ged.entity.titresciel.Obligation;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.SecteurMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ObligationMapper {
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final TypeTitreMapper typeTitreMapper;
    private final ModeAmortissementMapper modeAmortissementMapper;
    private final TypeAmortissementMapper typeAmortissementMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final PlaceMapper placeMapper;
    private final CompartimentMapper compartimentMapper;
    private final TypeEmissionMapper typeEmissionMapper;
    private final TypeObligationMapper typeObligationMapper;
    private final TableauAmortissementMapper tabAmortissementMapper;

    public ObligationMapper(SecteurMapper secteurMapper, PaysMapper paysMapper, TypeTitreMapper typeTitreMapper, ModeAmortissementMapper modeAmortissementMapper, TypeAmortissementMapper typeAmortissementMapper, PersonneMoraleMapper personneMoraleMapper, PlaceMapper placeMapper, CompartimentMapper compartimentMapper, TypeEmissionMapper typeEmissionMapper, TypeObligationMapper typeObligationMapper, TableauAmortissementMapper tabAmortissementMapper) {
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.typeTitreMapper = typeTitreMapper;
        this.modeAmortissementMapper = modeAmortissementMapper;
        this.typeAmortissementMapper = typeAmortissementMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.placeMapper = placeMapper;
        this.compartimentMapper = compartimentMapper;
        this.typeEmissionMapper = typeEmissionMapper;
        this.typeObligationMapper = typeObligationMapper;
        this.tabAmortissementMapper = tabAmortissementMapper;
    }

    public ObligationDto deObligation(Obligation obligation)
    {
        if(obligation == null)
            return null;
        ObligationDto obligationDto = new ObligationDto();
        BeanUtils.copyProperties(obligation, obligationDto);
        obligationDto.setSecteur(secteurMapper.deSecteur(obligation.getSecteur()));
        obligationDto.setPays(paysMapper.dePays(obligation.getPays()));
        obligationDto.setTypeTitre(typeTitreMapper.deTypeTitre(obligation.getTypeTitre()));
        obligationDto.setModeAmortissement(modeAmortissementMapper.deModeAmortissement(obligation.getModeAmortissement()));
        obligationDto.setTypeAmortissement(typeAmortissementMapper.deTypeAmortissement(obligation.getTypeAmortissement()));
        obligationDto.setEmetteur(personneMoraleMapper.dePersonneMorale(obligation.getEmetteur()));
        obligationDto.setRegistraire(personneMoraleMapper.dePersonneMorale(obligation.getRegistraire()));
        obligationDto.setDepositaire(personneMoraleMapper.dePersonneMorale(obligation.getDepositaire()));
        obligationDto.setPlace(placeMapper.dePlace(obligation.getPlace()));
        obligationDto.setCompartiment(compartimentMapper.deCompartiment(obligation.getCompartiment()));
        obligationDto.setTypeEmission(typeEmissionMapper.deTypeEmission(obligation.getTypeEmission()));
        obligationDto.setTypeObligation(typeObligationMapper.deTypeObligation(obligation.getTypeObligation()));
        //System.out.println("Taille tab amorti => " + obligation.getTabAmortissements().size());
        obligationDto.setTabAmortissements(obligation.getTabAmortissements().stream().map(tableauAmortissement -> {
            TableauAmortissementDto tableauAmortissementDto = new TableauAmortissementDto();
            BeanUtils.copyProperties(tableauAmortissement, tableauAmortissementDto);
            CleTableauAmortissementDto cleTableauAmortissementDto = new CleTableauAmortissementDto();
            cleTableauAmortissementDto.setDateEcheance(tableauAmortissement.getIdTabAmortissement().getDateEcheance());
            cleTableauAmortissementDto.setIdTitre(tableauAmortissement.getIdTabAmortissement().getIdTitre());
            tableauAmortissementDto.setIdTabAmortissement(cleTableauAmortissementDto);
//            System.out.println("Tab Capital => " + tableauAmortissement.getCapital().toString());
            return tableauAmortissementDto;
        }).collect(Collectors.toSet()));
        return obligationDto;
    }

    public Obligation deObligationDto(ObligationDto ObligationDTO)
    {
        Obligation Obligation = new Obligation();
        BeanUtils.copyProperties(ObligationDTO, Obligation);
        Obligation.setTabAmortissements(ObligationDTO.getTabAmortissements().stream().map(tabAmortissementMapper::deTableauAmortissementDto).collect(Collectors.toSet()));
        return Obligation;
    }
}
