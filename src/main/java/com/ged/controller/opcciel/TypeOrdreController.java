package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.TypeOrdreDto;
import com.ged.service.opcciel.TypeOrdreService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeordres")
public class TypeOrdreController {
    private final TypeOrdreService TypeOrdreService;

    public TypeOrdreController(TypeOrdreService TypeOrdreService) {

        this.TypeOrdreService = TypeOrdreService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeOrdreService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return TypeOrdreService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeOrdreService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeOrdreDto TypeOrdreDto)
    {
        return TypeOrdreService.creer(TypeOrdreDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeOrdreDto TypeOrdreDto,
                                           @PathVariable Long id)
    {
        TypeOrdreDto.setIdTypeOrdre(id);
        return TypeOrdreService.modifier(TypeOrdreDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeOrdreService.supprimer(id);
    }
}
