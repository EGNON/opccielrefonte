package com.ged.mapper.crm;

import com.ged.dto.crm.AgentConcerneDto;
import com.ged.dto.crm.RDVDto;
import com.ged.dto.standard.LangueDto;
import com.ged.entity.crm.RDV;
import com.ged.projection.RDVProjection;
import com.ged.mapper.standard.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RDVMapper {
    private final PaysMapper paysMapper;
    private final QuartierMapper quartierMapper;
    private final PersonneMapper personneMapper;
    private final DocumentMapper documentMapper;
    private final PersonnelMapper personnelMapper;
    private final CommuneMapper communeMapper;
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;

    public RDVMapper(PaysMapper paysMapper, QuartierMapper quartierMapper, PersonneMapper personneMapper, DocumentMapper documentMapper, PersonnelMapper personnelMapper, CommuneMapper communeMapper, ModeleMsgAlerteMapper modeleMsgAlerteMapper) {
        this.paysMapper = paysMapper;
        this.quartierMapper = quartierMapper;
        this.personneMapper = personneMapper;
        this.documentMapper = documentMapper;
        this.personnelMapper = personnelMapper;
        this.communeMapper = communeMapper;
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
    }

    public RDVDto deRDV(RDV rdv)
    {
        RDVDto rdvDto = new RDVDto();
        BeanUtils.copyProperties(rdv, rdvDto);
        rdvDto.setPaysDto(paysMapper.dePays(rdv.getPays()));
        rdvDto.setPersonnePhysiqueMoraleDto(personneMapper.dePersonnePhysiqueMorale(rdv.getPersonne()));
        rdvDto.setQuartierDto(quartierMapper.deQuartier(rdv.getQuartier()));
        if(rdv.getModeleMsgAlerte()!=null){
            rdvDto.setModeleMsgAlerteDto((modeleMsgAlerteMapper.deModeleMsgAlerte(rdv.getModeleMsgAlerte())));
        }

        rdvDto.setDocuments(rdv.getDocuments().stream().map(documentMapper::deDocument).collect(Collectors.toSet()));
        if(rdv.getAgentConcernes() != null)
        {
            Set<AgentConcerneDto> agentConcerneDtos=new HashSet<>();
            rdv.getAgentConcernes().forEach(agentConcerne -> {
                AgentConcerneDto agentConcerneDto=new AgentConcerneDto();
                if(agentConcerne.getPersonnel()!=null) {
                    agentConcerneDto.setPersonnelDto(personnelMapper.dePersonnel(agentConcerne.getPersonnel()));
                }
                if(agentConcerne.getRdv()!=null) {
                    RDVDto rdvDto1=new RDVDto();
                    BeanUtils.copyProperties(agentConcerne.getRdv(), rdvDto1);
                    agentConcerneDto.setRdvDto(rdvDto1);
                }
                agentConcerneDtos.add(agentConcerneDto);
            });
            rdvDto.setAgentConcernes(agentConcerneDtos);
        }
        return rdvDto;
    }
    public RDVDto deRDVProjection(RDVProjection rdv)
    {
        if(rdv == null) {
            return null;
        }
        RDVDto rdvDto = new RDVDto();
        BeanUtils.copyProperties(rdv, rdvDto);
        if(rdv.getPays()!=null) {
            rdvDto.setPaysDto(paysMapper.dePays(rdv.getPays()));
        }
        if(rdv.getPersonne()!=null) {
            rdvDto.setPersonnePhysiqueMoraleDto(personneMapper.dePersonnePhysiqueMorale(rdv.getPersonne()));
        }
        if(rdv.getQuartier()!=null) {
            rdvDto.setQuartierDto(quartierMapper.deQuartier(rdv.getQuartier()));
        }
        if(rdv.getCommune()!=null) {
            rdvDto.setCommuneDto(communeMapper.deCommune(rdv.getCommune()));
        }
        if(rdv.getModeleMsgAlerte()!=null) {
            rdvDto.setModeleMsgAlerteDto(modeleMsgAlerteMapper.deModeleMsgAlerte(rdv.getModeleMsgAlerte()));
        }
        if(rdv.getAgentConcernes() != null)
        {
            Set<AgentConcerneDto> agentConcerneDtos=new HashSet<>();
            rdv.getAgentConcernes().forEach(agentConcerne -> {
                AgentConcerneDto agentConcerneDto=new AgentConcerneDto();
                if(agentConcerne.getPersonnel()!=null) {
                    agentConcerneDto.setPersonnelDto(personnelMapper.dePersonnel(agentConcerne.getPersonnel()));
                }
                if(agentConcerne.getRdv()!=null) {
                    RDVDto rdvDto1=new RDVDto();
                    BeanUtils.copyProperties(agentConcerne.getRdv(), rdvDto1);
                    agentConcerneDto.setRdvDto(rdvDto1);
                }
                agentConcerneDtos.add(agentConcerneDto);
            });
            rdvDto.setAgentConcernes(agentConcerneDtos);
        }
        return rdvDto;
    }


    public RDV deRDVDto(RDVDto rdvDto)
    {
        RDV rdv = new RDV();
        BeanUtils.copyProperties(rdvDto, rdv);
        if(rdvDto.getPaysDto()!=null) {
            rdv.setPays(paysMapper.dePaysDto(rdvDto.getPaysDto()));
        }
        if(rdvDto.getPersonnePhysiqueMoraleDto()!=null) {
            rdv.setPersonne(personneMapper.dePersonnePhysiqueMoraleDto(rdvDto.getPersonnePhysiqueMoraleDto()));
        }
        if(rdvDto.getQuartierDto()!=null) {
            rdv.setQuartier(quartierMapper.deQuartierDto(rdvDto.getQuartierDto()));
        }
        if(rdvDto.getModeleMsgAlerteDto()!=null){
            rdv.setModeleMsgAlerte((modeleMsgAlerteMapper.deModeleMsgAlerteDto(rdvDto.getModeleMsgAlerteDto())));
        }
        return rdv;
    }
}
