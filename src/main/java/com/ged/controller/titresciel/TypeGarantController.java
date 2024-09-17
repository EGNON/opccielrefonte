package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeGarantDto;
import com.ged.service.titresciel.TypeGarantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typegarants")
public class TypeGarantController {
    private final TypeGarantService TypeGarantService;

    public TypeGarantController(TypeGarantService TypeGarantService) {

        this.TypeGarantService = TypeGarantService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeGarantService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return TypeGarantService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeGarantService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeGarantDto TypeGarantDto)
    {
        return TypeGarantService.creer(TypeGarantDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeGarantDto TypeGarantDto,
                                           @PathVariable Long id)
    {
        TypeGarantDto.setIdTypeGarant(id);
        return TypeGarantService.modifier(TypeGarantDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeGarantService.supprimer(id);
    }
}
