package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OpcvmDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.mapper.standard.PaysMapper;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.mapper.standard.PersonnePhysiqueMapper;
import com.ged.mapper.standard.VilleMapper;
import com.ged.mapper.titresciel.ClassificationOPCMapper;
import com.ged.mapper.titresciel.FormeJuridiqueOpcMapper;
import com.ged.mapper.titresciel.TitreMapper;
import com.ged.mapper.titresciel.TypeAffectationTitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OpcvmMapper {
    private final ClassificationOPCMapper classificationOPCMapper;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final FormeJuridiqueOpcMapper formeJuridiqueOpcMapper;
    private final PersonnePhysiqueMapper personnePhysiqueMapper;
    private final PaysMapper paysMapper;
    private final VilleMapper villeMapper;
    private final TitreMapper titreMapper;
    private final TypeAffectationTitreMapper typeAffectationTitreMapper;

    public OpcvmMapper(ClassificationOPCMapper classificationOPCMapper, PersonneMoraleMapper personneMoraleMapper, FormeJuridiqueOpcMapper formeJuridiqueOpcMapper, PersonnePhysiqueMapper personnePhysiqueMapper, PaysMapper paysMapper, VilleMapper villeMapper, TitreMapper titreMapper, TypeAffectationTitreMapper typeAffectationTitreMapper) {
        this.classificationOPCMapper = classificationOPCMapper;
        this.personneMoraleMapper = personneMoraleMapper;
        this.formeJuridiqueOpcMapper = formeJuridiqueOpcMapper;
        this.personnePhysiqueMapper = personnePhysiqueMapper;
        this.paysMapper = paysMapper;
        this.villeMapper = villeMapper;
        this.titreMapper = titreMapper;
        this.typeAffectationTitreMapper = typeAffectationTitreMapper;
    }

    public OpcvmDto deOpcvm(Opcvm opcvm)
    {
        if(opcvm == null) {
            return null;
        }
        OpcvmDto opcvmDto = new OpcvmDto();
        BeanUtils.copyProperties(opcvm, opcvmDto);
        if(opcvm.getClassification()!=null)
            opcvmDto.setClassification(classificationOPCMapper.deClassification(opcvm.getClassification()));
       /* if(opcvm.getPersonneEmetteur()!=null)
            opcvmDto.setPersonneEmetteur(personneMoraleMapper.dePersonneMorale(opcvm.getPersonneEmetteur()));
        if(opcvm.getPersonneIntervenant()!=null)
            opcvmDto.setPersonneIntervenant(personneMoraleMapper.dePersonneMorale(opcvm.getPersonneIntervenant()));*/
        if(opcvm.getFormeJuridiqueOpc()!=null)
            opcvmDto.setFormeJuridiqueOpc(formeJuridiqueOpcMapper.deFormeJuridiqueOpc(opcvm.getFormeJuridiqueOpc()));
        if(opcvm.getPersonneGestionnaire()!=null)
            opcvmDto.setPersonneGestionnaire(personnePhysiqueMapper.dePersonnePhysique(opcvm.getPersonneGestionnaire()));
        if(opcvm.getPays()!=null)
            opcvmDto.setPays(paysMapper.dePays(opcvm.getPays()));
        if(opcvm.getVille()!=null)
            opcvmDto.setVille(villeMapper.deVille(opcvm.getVille()));
        /*if(opcvm.getTitre()!=null)
            opcvmDto.setTitre(titreMapper.deTitre(opcvm.getTitre()));*/
        if(opcvm.getTypeAffectationTitre()!=null)
            opcvmDto.setTypeAffectationTitre(typeAffectationTitreMapper.deTypeAffectation(opcvm.getTypeAffectationTitre()));

        return opcvmDto;
    }

    public Opcvm deOpcvmDto(OpcvmDto opcvmDto)
    {
        if(opcvmDto == null)
            return null;
        Opcvm opcvm= new Opcvm();
        BeanUtils.copyProperties(opcvmDto, opcvm);
        if(opcvmDto.getClassification()!=null)
            opcvm.setClassification(classificationOPCMapper.deClassificationDto(opcvmDto.getClassification()));
        /*if(opcvmDto.getPersonneEmetteur()!=null)
            opcvm.setPersonneEmetteur(personneMoraleMapper.dePersonneMoraleDto(opcvmDto.getPersonneEmetteur()));
        if(opcvmDto.getPersonneIntervenant()!=null)
            opcvm.setPersonneIntervenant(personneMoraleMapper.dePersonneMoraleDto(opcvmDto.getPersonneIntervenant()));*/
        if(opcvmDto.getFormeJuridiqueOpc()!=null)
            opcvm.setFormeJuridiqueOpc(formeJuridiqueOpcMapper.deFormeJuridiqueOpcDto(opcvmDto.getFormeJuridiqueOpc()));
        if(opcvmDto.getPersonneGestionnaire()!=null)
            opcvm.setPersonneGestionnaire(personnePhysiqueMapper.dePersonnePhysiqueDto(opcvmDto.getPersonneGestionnaire()));
        if(opcvmDto.getPays()!=null)
            opcvm.setPays(paysMapper.dePaysDto(opcvmDto.getPays()));
        if(opcvmDto.getVille()!=null)
            opcvm.setVille(villeMapper.deVilleDto(opcvmDto.getVille()));
        /*if(opcvmDto.getTitre()!=null)
            opcvm.setTitre(titreMapper.deTitreDto(opcvmDto.getTitre()));*/
        if(opcvmDto.getTypeAffectationTitre()!=null)
            opcvm.setTypeAffectationTitre(typeAffectationTitreMapper.deTypeAffectationDto(opcvmDto.getTypeAffectationTitre()));

        return opcvm;
    }
}
