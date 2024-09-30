package com.ged.controller.standard.parametre;

import com.ged.dto.standard.PersonnePhysiquePaysDto;
import com.ged.dto.standard.PersonnePhysiquePaysDtoEJ;
import com.ged.entity.standard.ClePersonnePhysiquePays;
import com.ged.service.standard.PersonnePhysiquePaysService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnePhysiquePayss")
public class PersonnePhysiquePaysController {
    private final PersonnePhysiquePaysService personnePhysiquePaysService;

    public PersonnePhysiquePaysController(PersonnePhysiquePaysService personnePhysiquePaysService) {
        this.personnePhysiquePaysService = personnePhysiquePaysService;
    }

    @GetMapping
    public Page<PersonnePhysiquePaysDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return personnePhysiquePaysService.afficherPersonnePhysiquePays(page,size);
    }
    @GetMapping("/personne/{id}")
    public List<PersonnePhysiquePaysDto> afficherSelonPersonnePhysique(@PathVariable long id){
        return personnePhysiquePaysService.afficherSelonPersonnePhysique(id);
    }
    @PostMapping
    public PersonnePhysiquePaysDto ajouter(@RequestBody PersonnePhysiquePaysDto personnePhysiquePaysDto)
    {
        return personnePhysiquePaysService.creerPersonnePhysiquePays(personnePhysiquePaysDto);
    }

    @PostMapping("/EJ")
    public PersonnePhysiquePaysDtoEJ ajouter(@RequestBody PersonnePhysiquePaysDtoEJ personnePhysiquePaysDto)
    {
        return personnePhysiquePaysService.creerPersonnePhysiquePaysEJ(personnePhysiquePaysDto);
    }

    @PutMapping("/{idPersonne}/{idPays}")
    public PersonnePhysiquePaysDto modifier(@PathVariable long idPersonne,
                                            @PathVariable long idPays,
                                            @RequestBody PersonnePhysiquePaysDto personnePhysiquePaysDto){
        personnePhysiquePaysDto.getPersonnePhysiqueDto().setIdPersonne(idPersonne);
        personnePhysiquePaysDto.getPaysDto().setIdPays(idPays);
        return personnePhysiquePaysService.modifierPersonnePhysiquePays(personnePhysiquePaysDto);
    }

    @DeleteMapping("/{idPersonne}/{idPays}")
    public void Supprimer(@PathVariable long idPersonne,
                          @PathVariable long idPays){
        ClePersonnePhysiquePays clePersonnePhysiquePays=new ClePersonnePhysiquePays();
        clePersonnePhysiquePays.setIdPersonne(idPersonne);
        clePersonnePhysiquePays.setIdPays(idPays);
        personnePhysiquePaysService.supprimerPersonnePhysiquePays(clePersonnePhysiquePays);
    }

    @DeleteMapping("/{idPersonne}")
    public void SupprimerSelonPersonne(@PathVariable long idPersonne){
        personnePhysiquePaysService.supprimerSelonPersonne(idPersonne);
    }
}
