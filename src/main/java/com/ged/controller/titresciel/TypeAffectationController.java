package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeAffectationTitreDto;
import com.ged.service.titresciel.TypeAffectationService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/titre/typeaffectations")
public class TypeAffectationController {
    private final TypeAffectationService typeAffectationService;

    public TypeAffectationController(TypeAffectationService typeAffectationService) {
        this.typeAffectationService = typeAffectationService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return typeAffectationService.afficherTous();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TYPEAFFECTATION_TITRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return typeAffectationService.afficher(id);
    }

    @PostMapping("/datatable/list")
    @PreAuthorize("hasAuthority('ROLE_TYPEAFFECTATION_TITRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return typeAffectationService.afficherTous(params);
    }

    /*@PostConstruct
    public List<Object> createTypeAffectationFromTitresciel1() {
        return typeAffectationService.createTypeAffectationFromTitresciel1();
    }*/

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_TYPEAFFECTATION_TITRE')")
    public ResponseEntity<Object> creer(@Valid @RequestBody TypeAffectationTitreDto typeAffectationTitreDto)
    {
        return typeAffectationService.creer(typeAffectationTitreDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TYPEAFFECTATION_TITRE')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody TypeAffectationTitreDto typeAffectationTitreDto,
            @Positive @PathVariable("id") Long id)
    {
        typeAffectationTitreDto.setIdTypeAffectation(id);
        return typeAffectationService.modifier(typeAffectationTitreDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_TYPEAFFECTATION_TITRE')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") Long id)
    {
        return typeAffectationService.supprimer(id);
    }
}
