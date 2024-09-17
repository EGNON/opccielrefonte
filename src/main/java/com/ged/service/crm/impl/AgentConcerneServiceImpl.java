package com.ged.service.crm.impl;

import com.ged.dao.crm.AgentConcerneDao;
import com.ged.dao.standard.PersonnelDao;
import com.ged.dao.crm.RDVDao;
import com.ged.dto.crm.AgentConcerneDto;
import com.ged.entity.crm.AgentConcerne;
import com.ged.entity.crm.CleAgentConcerne;
import com.ged.entity.standard.Personnel;
import com.ged.entity.crm.RDV;
import com.ged.mapper.crm.AgentConcerneMapper;
import com.ged.mapper.standard.PersonnelMapper;
import com.ged.mapper.crm.RDVMapper;
import com.ged.service.crm.AgentConcerneService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AgentConcerneServiceImpl implements AgentConcerneService {
    private final AgentConcerneDao agentConcerneDao;
    private final RDVDao rdvDao;
    private final PersonnelDao personnelDao;
    private final AgentConcerneMapper agentConcerneMapper;
    private final PersonnelMapper personnelMapper;
    private final RDVMapper rdvMapper;

    public AgentConcerneServiceImpl(AgentConcerneDao agentConcerneDao, RDVDao rdvDao, PersonnelDao personnelDao, AgentConcerneMapper agentConcerneMapper, PersonnelMapper personnelMapper, RDVMapper rdvMapper) {
        this.agentConcerneDao = agentConcerneDao;
        this.rdvDao = rdvDao;
        this.personnelDao = personnelDao;
        this.agentConcerneMapper = agentConcerneMapper;
        this.personnelMapper = personnelMapper;
        this.rdvMapper = rdvMapper;
    }

    public Page<AgentConcerneDto> afficherAgentConcernes(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<AgentConcerne> agentConcernePage=agentConcerneDao.findAll(pageRequest);
        return new PageImpl<>(agentConcernePage.getContent().stream().map(agentConcerneMapper::deAgentConcerne).collect(Collectors.toList()),pageRequest,agentConcernePage.getTotalElements());
    }

    @Override
    public AgentConcerne afficherAgentConcerneSelonId(CleAgentConcerne idAgentConcerne) {
        return agentConcerneDao.findById(idAgentConcerne).orElseThrow(()-> new EntityNotFoundException("Elément introuvable"));
    }

    @Override
    public AgentConcerneDto creerAgentConcerne(AgentConcerneDto agentConcerneDto) {
        AgentConcerne agentConcerne = agentConcerneMapper.deAgentConcerneDto(agentConcerneDto);

        CleAgentConcerne cleAgentConcerne = new CleAgentConcerne();
        cleAgentConcerne.setIdPersonne(agentConcerneDto.getPersonnelDto().getIdPersonne());
        cleAgentConcerne.setIdRdv(agentConcerneDto.getRdvDto().getIdRdv());
        agentConcerne.setId(cleAgentConcerne);
        if(agentConcerneDto.getPersonnelDto() != null)
        {
            Personnel personnel=personnelDao.findById(agentConcerneDto.getPersonnelDto().getIdPersonne()).orElseThrow();
            agentConcerne.setPersonnel(personnel);
        }
        if(agentConcerneDto.getRdvDto() != null)
        {
            RDV rdv=rdvDao.findById(agentConcerneDto.getRdvDto().getIdRdv()).orElseThrow();
            agentConcerne.setRdv(rdv);
        }
        AgentConcerne agentConcerneSave = agentConcerneDao.save(agentConcerne);
        return agentConcerneMapper.deAgentConcerne(agentConcerneSave);
    }

    @Override
    public void creerAgentConcerne(AgentConcerneDto[] agentConcerneDto) {
        for(AgentConcerneDto o:agentConcerneDto){
            creerAgentConcerne(o);
        }
    }

    @Override
    public List<AgentConcerneDto> afficherAgentConcerneSelonRDV(long idRdv) {
        RDV rdv=rdvDao.findById(idRdv);
        return agentConcerneDao.findByRdv(rdv).stream().map(agentConcerneMapper::deAgentConcerne).collect(Collectors.toList());
    }

    @Override
    public List<AgentConcerneDto> afficherAgentConcerneSelonPersonnel(long idPersonnel) {
        Personnel personnel=personnelDao.findById(idPersonnel).orElseThrow();
        return agentConcerneDao.findByPersonnel(personnel).stream().map(agentConcerneMapper::deAgentConcerne).collect(Collectors.toList());
    }

    @Override
    public AgentConcerneDto modifierAgentConcerne(AgentConcerneDto agentConcerneDto) {
        CleAgentConcerne cleAgentConcerne = new CleAgentConcerne();
        cleAgentConcerne.setIdPersonne(agentConcerneDto.getPersonnelDto().getIdPersonne());
        cleAgentConcerne.setIdRdv(agentConcerneDto.getRdvDto().getIdRdv());
        AgentConcerne agentConcerne = afficherAgentConcerneSelonId(cleAgentConcerne);
        if(agentConcerne.getId() == null)
            return agentConcerneDto;

        agentConcerne=agentConcerneMapper.deAgentConcerneDto(agentConcerneDto);

        agentConcerne.setId(cleAgentConcerne);
        if(agentConcerneDto.getPersonnelDto() != null)
        {
            Personnel personnel=personnelDao.findById(agentConcerneDto.getPersonnelDto().getIdPersonne()).orElseThrow();
            agentConcerne.setPersonnel(personnel);
        }
        if(agentConcerneDto.getRdvDto() != null)
        {
            RDV rdv=rdvDao.findById(agentConcerneDto.getRdvDto().getIdRdv()).orElseThrow();
            agentConcerne.setRdv(rdv);
        }
        AgentConcerne agentConcerneMaj=agentConcerneDao.save(agentConcerne);
        return agentConcerneMapper.deAgentConcerne(agentConcerneMaj);
    }

    @Override
    public int modifierUnePartieAgentConcerne(AgentConcerneDto agentConcerneDto) {
        return agentConcerneDao.updateAgentConcerne(agentConcerneDto.getRdvDto().getIdRdv(),
                                                    agentConcerneDto.getPersonnelDto().getIdPersonne(),
                                                    agentConcerneDto.isEtat())   ;
    }

    @Override
    public void supprimerAgentConcerne(CleAgentConcerne idAgentConcerne) {
        agentConcerneDao.deleteById(idAgentConcerne);
    }

    @Override
    public void supprimerAgentConcerneSelonRDV(long idRdv) {
//        RDV rdv=rdvService.afficherRDVSelonId(idRdv);
        RDV rdv=rdvDao.findById(idRdv);
        agentConcerneDao.deleteByRdv(rdv);
    }
}
