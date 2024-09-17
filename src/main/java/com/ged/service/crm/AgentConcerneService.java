package com.ged.service.crm;

import com.ged.dto.crm.AgentConcerneDto;
import com.ged.entity.crm.AgentConcerne;
import com.ged.entity.crm.CleAgentConcerne;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AgentConcerneService{
    Page<AgentConcerneDto> afficherAgentConcernes(int page, int size);
    AgentConcerne afficherAgentConcerneSelonId(CleAgentConcerne idAgentConcerne);
    AgentConcerneDto creerAgentConcerne(AgentConcerneDto agentConcerneDto);
    void creerAgentConcerne(AgentConcerneDto[] agentConcerneDto);
    List<AgentConcerneDto>  afficherAgentConcerneSelonRDV(long idRdv);
    List<AgentConcerneDto>  afficherAgentConcerneSelonPersonnel(long idPersonnel);
    AgentConcerneDto modifierAgentConcerne(AgentConcerneDto agentConcerneDto);
    int modifierUnePartieAgentConcerne(AgentConcerneDto agentConcerneDto);
    void supprimerAgentConcerne(CleAgentConcerne agentConcerneDto);
    void supprimerAgentConcerneSelonRDV(long idRdv);
}
