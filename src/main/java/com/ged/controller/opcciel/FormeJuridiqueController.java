package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.service.opcciel.FormeJuridiqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/formejuridiques")
public class FormeJuridiqueController {
    private final FormeJuridiqueService formeJuridiqueService;

    public FormeJuridiqueController(FormeJuridiqueService formeJuridiqueService) {

        this.formeJuridiqueService = formeJuridiqueService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return formeJuridiqueService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return formeJuridiqueService.afficherFormeJuridique(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return formeJuridiqueService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody FormeJuridiqueDto formeJuridiqueDto)
    {
        return formeJuridiqueService.creerFormeJuridique(formeJuridiqueDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody FormeJuridiqueDto formeJuridiqueDto,
                                           @PathVariable String code)
    {
        formeJuridiqueDto.setCodeFormeJuridique(code);
        return formeJuridiqueService.modifierFormeJuridique(formeJuridiqueDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return formeJuridiqueService.supprimerFormeJuridique(code);
    }
}
