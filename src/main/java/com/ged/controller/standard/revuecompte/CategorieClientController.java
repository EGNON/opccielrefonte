package com.ged.controller.standard.revuecompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.CategorieClientDto;
import com.ged.service.standard.revuecompte.CategorieClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/categorieclients")
public class CategorieClientController {
    private final CategorieClientService categorieClientService;

    public CategorieClientController(CategorieClientService CategorieClientService) {

        this.categorieClientService = CategorieClientService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return categorieClientService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return categorieClientService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return categorieClientService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody CategorieClientDto CategorieClientDto)
    {
        return categorieClientService.creer(CategorieClientDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody CategorieClientDto CategorieClientDto,
                                           @PathVariable Long id)
    {
        CategorieClientDto.setIdCategorieClient(id);
        return categorieClientService.modifier(CategorieClientDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return categorieClientService.supprimer(id);
    }
}
