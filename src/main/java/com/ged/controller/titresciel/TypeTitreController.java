package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeTitreDto;
import com.ged.service.titresciel.TypeTitreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typetitres")
public class TypeTitreController {
    private final TypeTitreService TypeTitreService;

    public TypeTitreController(TypeTitreService TypeTitreService) {

        this.TypeTitreService = TypeTitreService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeTitreService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") String id)
    {
        return TypeTitreService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeTitreService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeTitreDto TypeTitreDto)
    {
        return TypeTitreService.creer(TypeTitreDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeTitreDto TypeTitreDto,
                                           @PathVariable Long id)
    {
        TypeTitreDto.setIdTypeTitre(id);
        return TypeTitreService.modifier(TypeTitreDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String id)
    {
        return TypeTitreService.supprimer(id);
    }
}
