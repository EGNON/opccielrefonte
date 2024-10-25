package com.ged.mapper.standard;

import com.ged.dto.standard.PersonnePhysiqueDto;
import com.ged.dto.standard.PersonnePhysiqueDtoEJ;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.mapper.crm.DegreMapper;
import com.ged.mapper.standard.revuecompte.CategorieClientMapper;
import com.ged.mapper.standard.revuecompte.SousTypeClientMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class PersonnePhysiqueMapper {
    private final ProfessionMapper professionMapper;
    private final SecteurMapper secteurMapper;
    private final PaysMapper paysMapper;
    private final ModeEtablissementMapper modeEtablissementMapper;
    private final DegreMapper degreMapper;
    private final LangueMapper langueMapper;
    private final SousTypeClientMapper sousTypeClientMapper;
    private final CategorieClientMapper categorieClientMapper;
    private final PersonneMapper personneMapper;
    private final DocumentMapper documentMapper;
    private final StatutPersonneMapper statutPersonneMapper;
    private final PersonnePhysiquePaysMapper personnePhysiquePaysMapper;

    public PersonnePhysiqueMapper(ProfessionMapper professionMapper, SecteurMapper secteurMapper, PaysMapper paysMapper, ModeEtablissementMapper modeEtablissementMapper, DegreMapper degreMapper, LangueMapper langueMapper, SousTypeClientMapper sousTypeClientMapper, CategorieClientMapper categorieClientMapper, PersonneMapper personneMapper, DocumentMapper documentMapper, StatutPersonneMapper statutPersonneMapper, PersonnePhysiquePaysMapper personnePhysiquePaysMapper) {
        this.professionMapper = professionMapper;
        this.secteurMapper = secteurMapper;
        this.paysMapper = paysMapper;
        this.modeEtablissementMapper = modeEtablissementMapper;
        this.degreMapper = degreMapper;
        this.langueMapper = langueMapper;
        this.sousTypeClientMapper = sousTypeClientMapper;
        this.categorieClientMapper = categorieClientMapper;
        this.personneMapper = personneMapper;
        this.documentMapper = documentMapper;
        this.statutPersonneMapper = statutPersonneMapper;
        this.personnePhysiquePaysMapper = personnePhysiquePaysMapper;
    }

    public PersonnePhysiqueDto dePersonnePhysique(PersonnePhysique personnePhysique)
    {
        if(personnePhysique == null)
            return null;
        PersonnePhysiqueDto personnePhysiqueDto = new PersonnePhysiqueDto();
        BeanUtils.copyProperties(personnePhysique, personnePhysiqueDto);
        personnePhysiqueDto.setProfession(professionMapper.deProfession(personnePhysique.getProfession()));
        personnePhysiqueDto.setSecteurEmp(secteurMapper.deSecteur(personnePhysique.getSecteurEmp()));
        personnePhysiqueDto.setPaysPere(paysMapper.dePays(personnePhysique.getPaysPere()));
        personnePhysiqueDto.setPaysMere(paysMapper.dePays(personnePhysique.getPaysMere()));
        personnePhysiqueDto.setPaysConjoint(paysMapper.dePays(personnePhysique.getPaysConjoint()));
        personnePhysiqueDto.setPaysNationalite(paysMapper.dePays(personnePhysique.getPaysNationalite()));
        personnePhysiqueDto.setSecteur(secteurMapper.deSecteur(personnePhysique.getSecteur()));
        personnePhysiqueDto.setDegre(degreMapper.deDegre(personnePhysique.getDegre()));
        personnePhysiqueDto.setLangue(langueMapper.deLangue(personnePhysique.getLangue()));
        if(personnePhysique.getSousTypeClient()!=null)
            personnePhysiqueDto.setSousTypeClient(sousTypeClientMapper.deSousTypeClient(personnePhysique.getSousTypeClient()));

        if(personnePhysique.getCategorieClient()!=null)
            personnePhysiqueDto.setCategorieClient(categorieClientMapper.deCatClient(personnePhysique.getCategorieClient()));

        if(personnePhysique.getModeEtablissement2()!=null)
            personnePhysiqueDto.setModeEtablissementDto(modeEtablissementMapper.deModeEtablissement(personnePhysique.getModeEtablissement2()));
        personnePhysiqueDto.setDistributeur(personneMapper.dePersonne(personnePhysique.getDistributeur()));
        personnePhysiqueDto.setPaysResidence(paysMapper.dePays(personnePhysique.getPaysResidence()));
        if(personnePhysique.getDocuments() != null)
        {

                personnePhysiqueDto.setDocuments(personnePhysique.getDocuments().stream()
                        .map(documentMapper::deDocument).collect(Collectors.toSet()));

        }
        if(personnePhysique.getPersonnePhysiquePays()!=null)
        {
            personnePhysiqueDto.setPersonnePhysiquePaysDtos(personnePhysique.getPersonnePhysiquePays().stream().
                    map(personnePhysiquePaysMapper::dePersonnePhysiquePays).collect(Collectors.toSet()));
        }

        if(personnePhysique.getStatutPersonnes() != null)
        {
                personnePhysiqueDto.setStatutPersonnes(personnePhysique.getStatutPersonnes().stream()
                        .map(statutPersonneMapper::deStatutPersonne).collect(Collectors.toSet()));

        }
        return personnePhysiqueDto;
    }

    public PersonnePhysique dePersonnePhysiqueDto(PersonnePhysiqueDto personnePhysiqueDto)
    {
        if(personnePhysiqueDto == null) {
            return null;
        }
        PersonnePhysique personnePhysique = new PersonnePhysique();
        BeanUtils.copyProperties(personnePhysiqueDto, personnePhysique);
        /*if(personnePhysiqueDto.getStatutPersonnes() != null)
        {
            System.out.println("Taillexxxxxxxx === " + personnePhysiqueDto.getStatutPersonnes().size());
            personnePhysique.setStatutPersonnes(personnePhysiqueDto.getStatutPersonnes().stream()
                    .map(statutPersonneMapper::deStatutPersonneDto).collect(Collectors.toSet()));

        }*/
        return personnePhysique;
    }

    public PersonnePhysique dePersonnePhysiqueDtoEJ(PersonnePhysiqueDtoEJ personnePhysiqueDtoEJ)
    {
        PersonnePhysique personnePhysique = new PersonnePhysique();
        BeanUtils.copyProperties(personnePhysiqueDtoEJ, personnePhysique);
        return personnePhysique;
    }

    public PersonnePhysiqueDtoEJ dePersonnePhysiqueEJ(PersonnePhysique personnePhysique)
    {
        if(personnePhysique == null)
            return null;
        PersonnePhysiqueDtoEJ personnePhysiqueDto = new PersonnePhysiqueDtoEJ();
        BeanUtils.copyProperties(personnePhysique, personnePhysiqueDto);
        personnePhysiqueDto.setPaysNationalite(paysMapper.dePays(personnePhysique.getPaysNationalite()));
        personnePhysiqueDto.setPaysResidence(paysMapper.dePays(personnePhysique.getPaysResidence()));
        if(personnePhysique.getDocuments() != null)
        {

            personnePhysiqueDto.setDocuments(personnePhysique.getDocuments().stream()
                    .map(documentMapper::deDocument).collect(Collectors.toSet()));

        }
        if(personnePhysique.getPersonnePhysiquePays()!=null)
        {
            personnePhysiqueDto.setPersonnePhysiquePaysDtos(personnePhysique.getPersonnePhysiquePays().stream().
                    map(personnePhysiquePaysMapper::dePersonnePhysiquePays).collect(Collectors.toSet()));
        }

//        System.out.println(personnePhysique.getStatutPersonnes().toString());
        if(personnePhysique.getStatutPersonnes() != null)
        {

            personnePhysiqueDto.setStatutPersonnes(personnePhysique.getStatutPersonnes().stream()
                    .map(statutPersonneMapper::deStatutPersonne).collect(Collectors.toSet()));

        }
        return personnePhysiqueDto;
    }
}
