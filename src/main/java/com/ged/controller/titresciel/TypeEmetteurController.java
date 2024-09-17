package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEmetteurDto;
import com.ged.service.titresciel.TypeEmetteurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeemetteurs")
public class TypeEmetteurController {
    private final TypeEmetteurService TypeEmetteurService;

    public TypeEmetteurController(TypeEmetteurService TypeEmetteurService) {

        this.TypeEmetteurService = TypeEmetteurService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeEmetteurService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return TypeEmetteurService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeEmetteurService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeEmetteurDto TypeEmetteurDto)
    {
        return TypeEmetteurService.creer(TypeEmetteurDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeEmetteurDto TypeEmetteurDto,
                                           @PathVariable String code)
    {
        TypeEmetteurDto.setCodeTypeEmetteur(code);
        return TypeEmetteurService.modifier(TypeEmetteurDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return TypeEmetteurService.supprimer(code);
    }
}
