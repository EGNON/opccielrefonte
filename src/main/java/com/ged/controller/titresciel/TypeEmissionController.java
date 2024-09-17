package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEmissionDto;
import com.ged.service.titresciel.TypeEmissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeemissions")
public class TypeEmissionController {
    private final TypeEmissionService TypeEmissionService;

    public TypeEmissionController(TypeEmissionService TypeEmissionService) {

        this.TypeEmissionService = TypeEmissionService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeEmissionService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return TypeEmissionService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeEmissionService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeEmissionDto TypeEmissionDto)
    {
        return TypeEmissionService.creer(TypeEmissionDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeEmissionDto TypeEmissionDto,
                                           @PathVariable Long id)
    {
        TypeEmissionDto.setIdTypeEmission(id);
        return TypeEmissionService.modifier(TypeEmissionDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeEmissionService.supprimer(id);
    }
}
