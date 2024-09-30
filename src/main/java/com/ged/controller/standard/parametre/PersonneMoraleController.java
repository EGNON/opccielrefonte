package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.service.standard.PersonneMoraleService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnemorales")
//@PreAuthorize("hasAuthority('ROLE_MORALE')")
public class PersonneMoraleController {
    private final PersonneMoraleService personneMoraleService;

    public PersonneMoraleController(PersonneMoraleService personneMoraleService) {
        this.personneMoraleService = personneMoraleService;
    }

    @GetMapping
    public Page<PersonneMoraleDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return personneMoraleService.afficherPersonneMorales(page,size);
    }

    @GetMapping("qualite/{qualite}")
    public List<PersonneMoraleDto> afficherSelonQualite(@PathVariable("qualite") String qualite)
    {
        return personneMoraleService.afficherSelonQualite(qualite);
    }

    @GetMapping("investi/{qualite}/{dateDebut}/{dateFin}")
    public List<PersonneMoraleDto> afficherPersonneMoraleNayantPasInvesti(@PathVariable("qualite") String qualite,
                                                                          @PathVariable("dateDebut") LocalDateTime dateDebut,
                                                                          @PathVariable("dateFin") LocalDateTime dateFin)
    {
        return personneMoraleService.afficherPersonneMoraleNayantPasInvesti(qualite,dateDebut,dateFin);
    }

    @GetMapping("{id}")
    public PersonneMoraleDto afficher(@PathVariable("id") Long id)
    {
        return personneMoraleService.afficherPersonneMorale(id);
    }

    @PostMapping("/datatable-{qualite}/list")
    public DataTablesResponse<PersonneMoraleDto> datatableList(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personneMoraleService.afficherTous(qualite, params);
    }

    @PostMapping("/datatable/moralesanctionnee")
    public DataTablesResponse<PersonneMoraleDto> datatableList_Sanctionnee(
            @RequestBody DatatableParameters params)
    {
        return personneMoraleService.afficherPersonneSanctionnee(params);
    }

    @PostMapping("/datatable/juge/list-{qualite}")
    public DataTablesResponse<PersonneMoraleDto> datatableListJuge(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personneMoraleService.afficherPersonneMoraleJuge(qualite, params);
    }

    @PostMapping("/datatable/expose/list-{qualite}")
    public DataTablesResponse<PersonneMoraleDto> datatableListExpose(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personneMoraleService.afficherPersonneMoralePolitiquementExpose(qualite, params);
    }

    @PostMapping
    public PersonneMoraleDto ajouter(@RequestBody PersonneMoraleDto personneMoraleDto)
    {
        return personneMoraleService.creerPersonneMorale(personneMoraleDto);
    }

    @PutMapping("/{id}")
    public PersonneMoraleDto modifier(@PathVariable long id, @RequestBody PersonneMoraleDto personneMoraleDto){
        personneMoraleDto.setIdPersonne(id);
        return personneMoraleService.modifierPersonneMorale(personneMoraleDto);
    }

    /*@PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    public List<Object> createPMFromOppciel1() throws JsonProcessingException {
        return personneMoraleService.createPMFromOppciel1();
    }*/

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        personneMoraleService.supprimerPersonneMorale(id);
    }

    @DeleteMapping("/{idPersonne}/{idQualite}")
    public void Supprimer(@PathVariable long idPersonne,@PathVariable long idQualite){
        personneMoraleService.supprimerPersonneMorale(idPersonne,idQualite);
    }
}
