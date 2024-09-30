package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CorrespondanceDto;
import com.ged.entity.opcciel.comptabilite.CleCorrespondance;
import com.ged.service.opcciel.CorrespondanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/correspondances")
public class CorrespondanceController {
    private final CorrespondanceService correspondanceService;

    public CorrespondanceController(CorrespondanceService correspondanceService) {
        this.correspondanceService = correspondanceService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return correspondanceService.afficherTous();
    }
    @GetMapping("/{codeIB}/{codeRubrique}/{codePosition}/{codePlan}/{numCompteComptable}")
    //@PreAuthorize("hasAuthority('ROLE_Correspondance')")
    public ResponseEntity<Object> afficher(@PathVariable String codeIB,
                                           @PathVariable String codeRubrique,
                                           @PathVariable String codePosition,
                                           @PathVariable String codePlan,
                                           @PathVariable String numCompteComptable)
    {
        CleCorrespondance idCorrespondance=new CleCorrespondance();
        idCorrespondance.setNumeroCompteComptable(numCompteComptable);
        idCorrespondance.setCodePlan(codePlan);
        idCorrespondance.setCodeIB(codeIB);
        idCorrespondance.setCodeRubrique(codeRubrique);
        idCorrespondance.setCodePosition(codePosition);
        return correspondanceService.afficher(idCorrespondance);
    }

    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_Correspondance')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return correspondanceService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Correspondance')")
    public ResponseEntity<Object> creer(@Valid @RequestBody CorrespondanceDto correspondanceDto)
    {
        return correspondanceService.creer(correspondanceDto);
    }

    @PutMapping("/{codeIB}/{codeRubrique}/{codePosition}/{codePlan}/{numCompteComptable}")
  //  @PreAuthorize("hasAuthority('ROLE_Correspondance')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody CorrespondanceDto correspondanceDto,
            @PathVariable String codeIB,
            @PathVariable String codeRubrique,
            @PathVariable String codePosition,
            @PathVariable String codePlan,
            @PathVariable String numCompteComptable)
    {
        correspondanceDto.setNumeroCompteComptable(numCompteComptable);
        correspondanceDto.setCodePosition(codePosition);
        correspondanceDto.setCodeRubrique(codeRubrique);
        correspondanceDto.getIb().setCodeIB(codeIB);
        correspondanceDto.getPlan().setCodePlan(codePlan);
        return correspondanceService.modifier(correspondanceDto);
    }

    @DeleteMapping("/{codeIB}/{codeRubrique}/{codePosition}/{codePlan}/{numCompteComptable}")
    //  @PreAuthorize("hasAuthority('ROLE_Correspondance')")
    public ResponseEntity<Object> supprimer(
            @PathVariable String codeIB,
            @PathVariable String codeRubrique,
            @PathVariable String codePosition,
            @PathVariable String codePlan,
            @PathVariable String numCompteComptable)
    {
        CleCorrespondance idCorrespondance=new CleCorrespondance();
        idCorrespondance.setNumeroCompteComptable(numCompteComptable);
        idCorrespondance.setCodePlan(codePlan);
        idCorrespondance.setCodeIB(codeIB);
        idCorrespondance.setCodeRubrique(codeRubrique);
        idCorrespondance.setCodePosition(codePosition);
        return correspondanceService.supprimer(idCorrespondance);
    }
}
