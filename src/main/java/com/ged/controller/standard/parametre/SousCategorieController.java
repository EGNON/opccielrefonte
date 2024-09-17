package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SousCategorieDto;
import com.ged.service.standard.SousCategorieService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/souscategories")
public class SousCategorieController {
    private final SousCategorieService sousCategorieService;

    public SousCategorieController(SousCategorieService SousCategorieService) {
        this.sousCategorieService = SousCategorieService;
    }

    @GetMapping("/liste")
    public List<SousCategorieDto> afficherListe(){
        return sousCategorieService.afficherListe();
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return sousCategorieService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SousCategorie')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return sousCategorieService.afficherSousCategorie(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_SousCategorie')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return sousCategorieService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SousCategorie')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody SousCategorieDto SousCategorieDto)
    {
        return sousCategorieService.creerSousCategorie(SousCategorieDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SousCategorie')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody SousCategorieDto SousCategorieDto, @Positive @PathVariable("id") Long id)
    {
        SousCategorieDto.setIdSousCategorie(id);
        return sousCategorieService.modifierSousCategorie(SousCategorieDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SousCategorie')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") Long id)
    {
        return sousCategorieService.supprimerSousCategorie(id);
    }
}
