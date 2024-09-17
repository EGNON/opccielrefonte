package com.ged.controller.standard.revuecompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.TypeCompteDto;
import com.ged.service.standard.revuecompte.TypeCompteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typecomptes")
public class TypeCompteController {
    private final TypeCompteService TypeCompteService;

    public TypeCompteController(TypeCompteService TypeCompteService) {

        this.TypeCompteService = TypeCompteService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeCompteService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return TypeCompteService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeCompteService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeCompteDto TypeCompteDto)
    {
        return TypeCompteService.creer(TypeCompteDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeCompteDto TypeCompteDto,
                                           @PathVariable Long id)
    {
        TypeCompteDto.setIdTypeCompte(id);
        return TypeCompteService.modifier(TypeCompteDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeCompteService.supprimer(id);
    }
}
