package com.ged.controller.standard.revuecompte;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.NumeroCompteDto;
import com.ged.dto.standard.revuecompte.NumeroCompteDto;
import com.ged.entity.standard.revuecompte.CleNumeroCompte;
import com.ged.service.standard.revuecompte.NumeroCompteService;
import com.ged.service.standard.revuecompte.NumeroCompteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/numerocomptes")
public class NumeroCompteController {
    private final NumeroCompteService NumeroCompteService;

    public NumeroCompteController(NumeroCompteService NumeroCompteService) {
        this.NumeroCompteService = NumeroCompteService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return NumeroCompteService.afficherTous();
    }
    @GetMapping("/{idPersonne}/{idSousTypeCompte}")
    //@PreAuthorize("hasAuthority('ROLE_NumeroCompte')")
    public ResponseEntity<Object> afficher(@PathVariable Long idPersonne,
                                           @PathVariable Long idSousTypeCompte) {
        CleNumeroCompte idNumeroCompte = new CleNumeroCompte();
        idNumeroCompte.setIdPersonne(idPersonne);
        idNumeroCompte.setIdSousTypeCompte(idSousTypeCompte);
        return NumeroCompteService.afficher(idNumeroCompte);
    }
    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_NumeroCompte')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return NumeroCompteService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_NumeroCompte')")
    public ResponseEntity<Object> creer(@Valid @RequestBody NumeroCompteDto NumeroCompteDto)
    {
        return NumeroCompteService.creer(NumeroCompteDto);
    }

    @PutMapping("/{idPersonne}/{idSousTypeCompte}")
    //  @PreAuthorize("hasAuthority('ROLE_NumeroCompte')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody NumeroCompteDto NumeroCompteDto,
            @PathVariable Long idPersonne,
            @PathVariable Long idSousTypeCompte)
    {
        NumeroCompteDto.getPersonne().setIdPersonne(idPersonne);
        NumeroCompteDto.getSousTypeCompte().setIdSousTypeCompte(idSousTypeCompte);
        return NumeroCompteService.modifier(NumeroCompteDto);
    }

    @DeleteMapping("/{idPersonne}/{idSousTypeCompte}")
    public ResponseEntity<Object> supprimer(@PathVariable Long idPersonne,
                                            @PathVariable Long idSousTypeCompte)
    {
        CleNumeroCompte idNumeroCompte=new CleNumeroCompte();
        idNumeroCompte.setIdSousTypeCompte(idSousTypeCompte);
        idNumeroCompte.setIdPersonne(idPersonne);
        return NumeroCompteService.supprimer(idNumeroCompte);
    }
}
