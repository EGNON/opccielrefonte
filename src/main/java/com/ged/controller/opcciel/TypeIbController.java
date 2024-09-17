package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeIbDto;
import com.ged.service.opcciel.TypeIbService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeibs")
public class TypeIbController {
    private final TypeIbService TypeIbService;

    public TypeIbController(TypeIbService TypeIbService) {

        this.TypeIbService = TypeIbService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeIbService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return TypeIbService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeIbService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeIbDto TypeIbDto)
    {
        return TypeIbService.creer(TypeIbDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeIbDto TypeIbDto,
                                           @PathVariable String code)
    {
        TypeIbDto.setCodeTypeIb(code);
        return TypeIbService.modifier(TypeIbDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return TypeIbService.supprimer(code);
    }
}
