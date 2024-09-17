package com.ged.controller.crm.rdv;

import com.ged.dto.crm.AgentConcerneDto;
import com.ged.entity.crm.CleAgentConcerne;
import com.ged.service.crm.AgentConcerneService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/agentconcernes")
public class AgentConcerneController {
    private final AgentConcerneService agentConcerneService;
    //construteur
    public AgentConcerneController(AgentConcerneService agentConcerneService) {
        this.agentConcerneService = agentConcerneService;
    }

    @GetMapping
    public Page<AgentConcerneDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return agentConcerneService.afficherAgentConcernes(page, size);
    }
    @GetMapping("/{idRdv}")
    public List<AgentConcerneDto> afiicherAgentConcerneSelonRDV(@PathVariable("idRdv") long idRdv)
    {
        return agentConcerneService.afficherAgentConcerneSelonRDV(idRdv);
    }
    @GetMapping("/personnel/{idPersonnel}")
    public List<AgentConcerneDto> afiicherAgentConcerneSelonPersonnel(@PathVariable("idPersonnel") long idPersonnel)
    {
        return agentConcerneService.afficherAgentConcerneSelonPersonnel(idPersonnel);
    }

    @PostMapping
    public AgentConcerneDto ajouter(@Valid @RequestBody AgentConcerneDto agentConcerneDto)
    {
        return agentConcerneService.creerAgentConcerne(agentConcerneDto);
    }
    @PostMapping("/group")
    public void ajouter(@Valid @RequestBody AgentConcerneDto[] agentConcerneDto)
    {
         agentConcerneService.creerAgentConcerne(agentConcerneDto);
    }

    @PutMapping("/{idPersonnel}/{idRdv}")
    public AgentConcerneDto modifier(@Valid @RequestBody AgentConcerneDto agentConcerneDto,
                              @Positive @PathVariable("idPersonnel") Long idPersonnel,
                              @Positive @PathVariable("idRdv") Long idRdv)
    {
        agentConcerneDto.getRdvDto().setIdRdv(idRdv);
        agentConcerneDto.getPersonnelDto().setIdPersonne(idPersonnel);
        return agentConcerneService.modifierAgentConcerne(agentConcerneDto);
    }
    @PutMapping("/update/{idPersonnel}/{idRdv}")
    public int modifierUnePartieDeAgentConcerne(@Valid @RequestBody AgentConcerneDto agentConcerneDto,
                                     @Positive @PathVariable("idPersonnel") Long idPersonnel,
                                     @Positive @PathVariable("idRdv") Long idRdv)
    {
        agentConcerneDto.getRdvDto().setIdRdv(idRdv);
        agentConcerneDto.getPersonnelDto().setIdPersonne(idPersonnel);
        return agentConcerneService.modifierUnePartieAgentConcerne(agentConcerneDto);
    }

    @DeleteMapping("/{idPersonnel}/{idRdv}")
    public void supprimer(@Positive @PathVariable("idPersonnel") Long idPersonnel,
                          @Positive @PathVariable("idRdv") Long idRdv)
    {
        CleAgentConcerne cleAgentConcerne=new CleAgentConcerne();
        cleAgentConcerne.setIdRdv(idRdv);
        cleAgentConcerne.setIdPersonne(idPersonnel);
        agentConcerneService.supprimerAgentConcerne(cleAgentConcerne);
    }
    @DeleteMapping("/{idRdv}")
    public void supprimerSelonRDV(@Positive @PathVariable("idRdv") Long idRdv)
    {
        agentConcerneService.supprimerAgentConcerneSelonRDV(idRdv);
    }
}
