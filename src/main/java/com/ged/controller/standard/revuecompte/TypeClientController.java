package com.ged.controller.standard.revuecompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.TypeClientDto;
import com.ged.service.standard.revuecompte.TypeClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeclients")
public class TypeClientController {
    private final TypeClientService TypeClientService;

    public TypeClientController(TypeClientService TypeClientService) {

        this.TypeClientService = TypeClientService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeClientService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return TypeClientService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeClientService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeClientDto TypeClientDto)
    {
        return TypeClientService.creer(TypeClientDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeClientDto TypeClientDto,
                                           @PathVariable Long id)
    {
        TypeClientDto.setIdTypeClient(id);
        return TypeClientService.modifier(TypeClientDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeClientService.supprimer(id);
    }
}
