package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.FormuleDto;
import com.ged.dto.opcciel.comptabilite.SoldeCompteFormuleDto;
import com.ged.service.opcciel.FormuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/formules")
public class FormuleController {
    private final FormuleService formuleService;

    public FormuleController(FormuleService formuleService) {

        this.formuleService = formuleService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return formuleService.afficherTous();
    }

    @PostMapping("/soldecompteformule")
    public ResponseEntity<Object> soldeCompteFormule(@RequestBody SoldeCompteFormuleDto soldeCompteFormuleDto)
    {
        return formuleService.soldeCompteFormule(soldeCompteFormuleDto);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return formuleService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return formuleService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter( @RequestBody FormuleDto formuleDto)
    {
        return formuleService.creer(formuleDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier( @RequestBody FormuleDto formuleDto,
                                           @PathVariable Long id)
    {
        formuleDto.setIdFormule(id);
        return formuleService.modifier(formuleDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return formuleService.supprimer(id);
    }
}
