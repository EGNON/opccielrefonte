package com.ged.controller.standard.revuecompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.SousTypeClientDto;
import com.ged.service.standard.revuecompte.SousTypeClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/soustypeclients")
public class SousTypeClientController {
    private final SousTypeClientService SousTypeClientService;

    public SousTypeClientController(SousTypeClientService SousTypeClientService) {

        this.SousTypeClientService = SousTypeClientService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return SousTypeClientService.afficherTous();
    }
    @GetMapping("/filtre/personnephysique")
    public ResponseEntity<Object> afficherPersonnePhysique()
    {
        return SousTypeClientService.afficherPersonnePhysique();
    }
    @GetMapping("/filtre/autre")
    public ResponseEntity<Object> afficherAutresTypePersonne()
    {
        return SousTypeClientService.afficherAutresTypePersonne();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return SousTypeClientService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return SousTypeClientService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody SousTypeClientDto SousTypeClientDto)
    {
        return SousTypeClientService.creer(SousTypeClientDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody SousTypeClientDto SousTypeClientDto,
                                           @PathVariable Long id)
    {
        SousTypeClientDto.setIdSousTypeClient(id);
        return SousTypeClientService.modifier(SousTypeClientDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return SousTypeClientService.supprimer(id);
    }
}
