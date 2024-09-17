package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeObligationDto;
import com.ged.service.titresciel.TypeObligationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeobligations")
public class TypeObligationController {
    private final TypeObligationService TypeObligationService;

    public TypeObligationController(TypeObligationService TypeObligationService) {

        this.TypeObligationService = TypeObligationService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeObligationService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return TypeObligationService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeObligationService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeObligationDto TypeObligationDto)
    {
        return TypeObligationService.creer(TypeObligationDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeObligationDto TypeObligationDto,
                                           @PathVariable Long id)
    {
        TypeObligationDto.setIdTypeObligation(id);
        return TypeObligationService.modifier(TypeObligationDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeObligationService.supprimer(id);
    }
}
