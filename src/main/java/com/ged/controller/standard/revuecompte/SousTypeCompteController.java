package com.ged.controller.standard.revuecompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.SousTypeCompteDto;
import com.ged.service.standard.revuecompte.SousTypeCompteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/soustypecomptes")
public class SousTypeCompteController {
    private final SousTypeCompteService SousTypeCompteService;

    public SousTypeCompteController(SousTypeCompteService SousTypeCompteService) {

        this.SousTypeCompteService = SousTypeCompteService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return SousTypeCompteService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return SousTypeCompteService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return SousTypeCompteService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody SousTypeCompteDto SousTypeCompteDto)
    {
        return SousTypeCompteService.creer(SousTypeCompteDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody SousTypeCompteDto SousTypeCompteDto,
                                           @PathVariable Long id)
    {
        SousTypeCompteDto.setIdSousTypeCompte(id);
        return SousTypeCompteService.modifier(SousTypeCompteDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return SousTypeCompteService.supprimer(id);
    }
}
