package com.ged.mapper.crm;

import com.ged.mapper.standard.PersonnelMapper;
import com.ged.dto.crm.AgentConcerneDto;
import com.ged.entity.crm.AgentConcerne;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AgentConcerneMapper {
    private final RDVMapper rdvMapper;
    private final PersonnelMapper personnelMapper;

    public AgentConcerneMapper(RDVMapper rdvMapper, PersonnelMapper personnelMapper) {
        this.rdvMapper = rdvMapper;
        this.personnelMapper = personnelMapper;
    }

    public AgentConcerneDto deAgentConcerne(AgentConcerne agentConcerne)
    {
        AgentConcerneDto agentConcerneDto = new AgentConcerneDto();
        BeanUtils.copyProperties(agentConcerne, agentConcerneDto);
        if(agentConcerne.getRdv() != null)
        {
            agentConcerneDto.setRdvDto(rdvMapper.deRDV(agentConcerne.getRdv()));
        }
        if(agentConcerne.getPersonnel()!=null){
            agentConcerneDto.setPersonnelDto(personnelMapper.dePersonnel(agentConcerne.getPersonnel()));
        }
        return agentConcerneDto;
    }

    public AgentConcerne deAgentConcerneDto(AgentConcerneDto agentConcerneDto)
    {
        AgentConcerne agentConcerne = new AgentConcerne();
        BeanUtils.copyProperties(agentConcerneDto, agentConcerne);
        if(agentConcerneDto.getRdvDto() != null)
        {
            agentConcerne.setRdv(rdvMapper.deRDVDto(agentConcerneDto.getRdvDto()));
        }
        if(agentConcerneDto.getPersonnelDto()!=null) {
            agentConcerne.setPersonnel(personnelMapper.dePersonnelDto(agentConcerneDto.getPersonnelDto()));
        }
        return agentConcerne;
    }
}
