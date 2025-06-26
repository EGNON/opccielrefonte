package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.lab.reportings.FormuleParametre;
import com.ged.dto.opcciel.comptabilite.FormuleDto;
import com.ged.dto.opcciel.comptabilite.SoldeCompteDto;
import com.ged.dto.opcciel.comptabilite.SoldeCompteFormuleDto;
import com.ged.service.opcciel.FormuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @PostMapping("/soldecompte")
    public ResponseEntity<Object> soldeCompteFormule(@RequestBody SoldeCompteDto soldeCompteDto)
    {
        return formuleService.soldeCompte(soldeCompteDto);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return formuleService.afficher(id);
    }
    @GetMapping("/cump/{idOpcvm}/{idTitre}/{dateEstimation}")
    //    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherCump(@PathVariable Long idOpcvm,
                                               @PathVariable  Long idTitre,
                                               @PathVariable LocalDateTime dateEstimation)
    {
        return formuleService.afficherCump(idOpcvm, idTitre, dateEstimation);
    }
    @PostMapping("/quantitereel")
        //    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherQuantiteReel(@RequestBody FormuleParametre formuleParametre)
    {
        return formuleService.afficherQuantiteReelTitre(formuleParametre);
    }
    @PostMapping("/derniereecheance")
        //    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherDerniereEcheance(@RequestBody FormuleParametre formuleParametre)
    {
        return formuleService.derniereEcheance(formuleParametre);
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
