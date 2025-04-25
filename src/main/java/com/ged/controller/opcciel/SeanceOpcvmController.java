package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.service.opcciel.SeanceOpcvmService;
import jakarta.annotation.Priority;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seanceopcvms")
public class SeanceOpcvmController {
    private final SeanceOpcvmService SeanceOpcvmService;

    public SeanceOpcvmController(SeanceOpcvmService SeanceOpcvmService) {

        this.SeanceOpcvmService = SeanceOpcvmService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return SeanceOpcvmService.afficherTous();
    }

    @GetMapping("/{idOpcvm}/{idSeance}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "idOpcvm") Long idOpcvm,
                                           @PathVariable Long idSeance)
    {
        CleSeanceOpcvm cleSeanceOpcvm=new CleSeanceOpcvm();
        cleSeanceOpcvm.setIdSeance(idSeance);
        cleSeanceOpcvm.setIdOpcvm(idOpcvm);
        return SeanceOpcvmService.afficher(cleSeanceOpcvm);
    }
    @GetMapping("encours/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherSeanceEnCours(@PathVariable(name = "idOpcvm") Long idOpcvm)
    {
        return SeanceOpcvmService.afficher(idOpcvm);
    }
    @GetMapping("listedesc/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherSeanceDesc(@PathVariable Long idOpcvm)
    {
        return SeanceOpcvmService.afficherSeanceDesc(idOpcvm);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return SeanceOpcvmService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody SeanceOpcvmDto SeanceOpcvmDto)
    {
        return SeanceOpcvmService.creer(SeanceOpcvmDto);
    }

    @PutMapping("/{idOpcvm}/{idSeance}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody SeanceOpcvmDto SeanceOpcvmDto,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable Long idSeance)
    {
        CleSeanceOpcvm cleSeanceOpcvm=new CleSeanceOpcvm();
        cleSeanceOpcvm.setIdOpcvm(idOpcvm);
        cleSeanceOpcvm.setIdSeance(idSeance);
        SeanceOpcvmDto.setIdSeanceOpcvm(cleSeanceOpcvm);
        return SeanceOpcvmService.modifier(SeanceOpcvmDto);
    }

    @DeleteMapping("/{idOpcvm}/{idSeance}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long idOpcvm,
                                            @PathVariable Long idSeance)
    {
        CleSeanceOpcvm cleSeanceOpcvm=new CleSeanceOpcvm();
        cleSeanceOpcvm.setIdOpcvm(idOpcvm);
        cleSeanceOpcvm.setIdSeance(idSeance);
        return SeanceOpcvmService.supprimer(cleSeanceOpcvm);
    }

    @Priority(1)
    @PostMapping("liste/seance/opcvm-{idOpcvm}")
    public ResponseEntity<Object> listeSeanceOpcvm(@RequestBody DatatableParameters params, @PathVariable("idOpcvm") Long idOpcvm) {
        return SeanceOpcvmService.listeSeanceOpcvm(params, idOpcvm);
    }
}
