package com.ged.mapper.standard;

import com.ged.dto.standard.PersonnePhysiquePaysDto;
import com.ged.dto.standard.PersonnePhysiquePaysDtoEJ;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.entity.standard.PersonnePhysiquePays;
import com.ged.mapper.crm.DegreMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonnePhysiquePaysMapper {
    private final PaysMapper paysMapper;
    private final StatutPersonneMapper statutPersonneMapper;
    private final ProfessionMapper professionMapper;
    private final SecteurMapper secteurMapper;
    private final DegreMapper degreMapper;
    private final PersonneMapper personneMapper;
    private final DocumentMapper documentMapper;
    private final ModelMapper modelMapper;

    public PersonnePhysiquePaysMapper(PaysMapper paysMapper, StatutPersonneMapper statutPersonneMapper, ProfessionMapper professionMapper, SecteurMapper secteurMapper, DegreMapper degreMapper, PersonneMapper personneMapper, DocumentMapper documentMapper, ModelMapper modelMapper) {
        this.paysMapper = paysMapper;


        this.statutPersonneMapper = statutPersonneMapper;
        this.professionMapper = professionMapper;
        this.secteurMapper = secteurMapper;
        this.degreMapper = degreMapper;
        this.personneMapper = personneMapper;
        this.documentMapper = documentMapper;
        this.modelMapper = modelMapper;
    }

    public PersonnePhysiquePaysDto dePersonnePhysiquePays(PersonnePhysiquePays personnePhysiquePays)
    {
        PersonnePhysiquePaysDto personnePhysiquePaysDto = new PersonnePhysiquePaysDto();
        BeanUtils.copyProperties(personnePhysiquePays, personnePhysiquePaysDto);
        //System.out.println(personnePhysiquePays.toString());
        if(personnePhysiquePays.getPays()!=null) {
            personnePhysiquePaysDto.setPaysDto(paysMapper.dePays(personnePhysiquePays.getPays()));
        }
        if(personnePhysiquePays.getPersonnePhysique()!=null) {

        }
        return personnePhysiquePaysDto;
    }

    public PersonnePhysiquePays dePersonnePhysiquePaysDto(PersonnePhysiquePaysDto personnePhysiquePaysDto)
    {
        PersonnePhysiquePays personnePhysiquePays= new PersonnePhysiquePays();
        BeanUtils.copyProperties(personnePhysiquePaysDto, personnePhysiquePays);
        if(personnePhysiquePaysDto.getPaysDto()!=null) {
            personnePhysiquePays.setPays(paysMapper.dePaysDto(personnePhysiquePaysDto.getPaysDto()));
        }
        if(personnePhysiquePaysDto.getPersonnePhysiqueDto()!=null) {
            personnePhysiquePays.setPersonnePhysique(modelMapper.map(personnePhysiquePaysDto.getPersonnePhysiqueDto(), PersonnePhysique.class));
        }
        return personnePhysiquePays;
    }

    public PersonnePhysiquePays dePersonnePhysiquePaysDtoEJ(PersonnePhysiquePaysDtoEJ personnePhysiquePaysDto)
    {
        PersonnePhysiquePays personnePhysiquePays= new PersonnePhysiquePays();
        BeanUtils.copyProperties(personnePhysiquePaysDto, personnePhysiquePays);
        if(personnePhysiquePaysDto.getPaysDto()!=null) {
            personnePhysiquePays.setPays(paysMapper.dePaysDto(personnePhysiquePaysDto.getPaysDto()));
        }
        if(personnePhysiquePaysDto.getPersonnePhysiqueDto()!=null) {
            personnePhysiquePays.setPersonnePhysique(modelMapper.map(personnePhysiquePaysDto.getPersonnePhysiqueDto(), PersonnePhysique.class));
        }
        return personnePhysiquePays;
    }
    public PersonnePhysiquePaysDtoEJ dePersonnePhysiquePaysEJ(PersonnePhysiquePays personnePhysiquePays)
    {
        PersonnePhysiquePaysDtoEJ personnePhysiquePaysDto = new PersonnePhysiquePaysDtoEJ();
        BeanUtils.copyProperties(personnePhysiquePays, personnePhysiquePaysDto);
        //System.out.println(personnePhysiquePays.toString());
        if(personnePhysiquePays.getPays()!=null) {
            personnePhysiquePaysDto.setPaysDto(paysMapper.dePays(personnePhysiquePays.getPays()));
        }
        if(personnePhysiquePays.getPersonnePhysique()!=null) {

        }
        return personnePhysiquePaysDto;
    }

}
