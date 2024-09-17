package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeFormuleDto;
import com.ged.service.opcciel.TypeFormuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeformules")
public class TypeFormuleController {
    private final TypeFormuleService TypeFormuleService;

    public TypeFormuleController(TypeFormuleService TypeFormuleService) {

        this.TypeFormuleService = TypeFormuleService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeFormuleService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return TypeFormuleService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeFormuleService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeFormuleDto TypeFormuleDto)
    {
        return TypeFormuleService.creer(TypeFormuleDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeFormuleDto TypeFormuleDto,
                                           @PathVariable String code)
    {
        TypeFormuleDto.setCodeTypeFormule(code);
        return TypeFormuleService.modifier(TypeFormuleDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return TypeFormuleService.supprimer(code);
    }
}
