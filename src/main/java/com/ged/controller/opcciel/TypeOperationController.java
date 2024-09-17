package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeOperationDto;
import com.ged.service.opcciel.TypeOperationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeoperations")
public class TypeOperationController {
    private final TypeOperationService TypeOperationService;

    public TypeOperationController(TypeOperationService TypeOperationService) {

        this.TypeOperationService = TypeOperationService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeOperationService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return TypeOperationService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeOperationService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeOperationDto TypeOperationDto)
    {
        return TypeOperationService.creer(TypeOperationDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeOperationDto TypeOperationDto,
                                           @PathVariable String code)
    {
        TypeOperationDto.setCodeTypeOperation(code);
        return TypeOperationService.modifier(TypeOperationDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return TypeOperationService.supprimer(code);
    }
}
