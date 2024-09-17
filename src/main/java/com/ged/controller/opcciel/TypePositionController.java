package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypePositionDto;
import com.ged.service.opcciel.TypePositionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typepositions")
public class TypePositionController {
    private final TypePositionService TypePositionService;

    public TypePositionController(TypePositionService TypePositionService) {

        this.TypePositionService = TypePositionService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypePositionService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return TypePositionService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypePositionService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypePositionDto TypePositionDto)
    {
        return TypePositionService.creer(TypePositionDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypePositionDto TypePositionDto,
                                           @PathVariable String code)
    {
        TypePositionDto.setCodeTypePosition(code);
        return TypePositionService.modifier(TypePositionDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return TypePositionService.supprimer(code);
    }
}
