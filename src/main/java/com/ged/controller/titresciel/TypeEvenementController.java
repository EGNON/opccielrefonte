package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEvenementDto;
import com.ged.service.titresciel.TypeEvenementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeevenements")
public class TypeEvenementController {
    private final TypeEvenementService TypeEvenementService;

    public TypeEvenementController(TypeEvenementService TypeEvenementService) {

        this.TypeEvenementService = TypeEvenementService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeEvenementService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return TypeEvenementService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeEvenementService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeEvenementDto TypeEvenementDto)
    {
        return TypeEvenementService.creer(TypeEvenementDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeEvenementDto TypeEvenementDto,
                                           @PathVariable Long id)
    {
        TypeEvenementDto.setIdTypeEvenement(id);
        return TypeEvenementService.modifier(TypeEvenementDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeEvenementService.supprimer(id);
    }
}
