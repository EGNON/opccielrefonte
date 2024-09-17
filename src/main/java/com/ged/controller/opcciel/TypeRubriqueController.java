package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeRubriqueDto;
import com.ged.service.opcciel.TypeRubriqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typerubriques")
public class TypeRubriqueController {
    private final TypeRubriqueService TypeRubriqueService;

    public TypeRubriqueController(TypeRubriqueService TypeRubriqueService) {

        this.TypeRubriqueService = TypeRubriqueService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeRubriqueService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return TypeRubriqueService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeRubriqueService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeRubriqueDto TypeRubriqueDto)
    {
        return TypeRubriqueService.creer(TypeRubriqueDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeRubriqueDto TypeRubriqueDto,
                                           @PathVariable String code)
    {
        TypeRubriqueDto.setCodeTypeRubrique(code);
        return TypeRubriqueService.modifier(TypeRubriqueDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return TypeRubriqueService.supprimer(code);
    }
}
