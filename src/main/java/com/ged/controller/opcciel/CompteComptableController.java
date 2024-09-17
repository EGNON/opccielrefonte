package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CompteComptableDto;
import com.ged.entity.opcciel.comptabilite.CleCompteComptable;
import com.ged.service.opcciel.CompteComptableService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/comptecomptables")
public class CompteComptableController {
    private final CompteComptableService compteComptableService;

    public CompteComptableController(CompteComptableService compteComptableService) {
        this.compteComptableService = compteComptableService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return compteComptableService.afficherTous();
    }

    @GetMapping("/{num}")
    public ResponseEntity<Object> afficherSelonNumCompteComptable(@PathVariable String num) {
        return compteComptableService.afficherSelonNumCompteComptable(num);
    }
   /* @GetMapping("/plan/{codePlan}")
    public ResponseEntity<Object> afficherSelonPlan(@PathVariable String codePlan) {
        return compteComptableService.afficherSelonPlan(codePlan);
    }*/
    @GetMapping("/plan/{codePlan}")
    //@PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> afficherSelonPlan(
                                                    @PathVariable String codePlan) {
        return compteComptableService.afficherSelonPlan(codePlan);
    }

    @GetMapping("/planestmvt/{codePlan}")
    //@PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> afficherSelonPlanEtEstMvt(
                                                    @PathVariable String codePlan) {
        return compteComptableService.afficherSelonPlanEtEstMvt(codePlan,true);
    }
    @PostMapping("/plan/{codePlan}")
    //@PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> afficherSelonPlan(@RequestBody DatatableParameters params,
                                                    @PathVariable String codePlan) {
        return compteComptableService.afficherSelonPlan(params,codePlan);
    }

    @GetMapping("/{codePlan}/{numCompteComptable}")
    //@PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> afficher(@PathVariable String codePlan,
                                           @PathVariable String numCompteComptable)
    {
        CleCompteComptable idCompteComptable=new CleCompteComptable();
        idCompteComptable.setNumCompteComptable(numCompteComptable);
        idCompteComptable.setCodePlan(codePlan);
        return compteComptableService.afficher(idCompteComptable);
    }

    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return compteComptableService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> creer(@Valid @RequestBody CompteComptableDto compteComptableDto)
    {
        return compteComptableService.creer(compteComptableDto);
    }

    @PutMapping("/{codePlan}/{numCompteComptable}")
  //  @PreAuthorize("hasAuthority('ROLE_CompteComptable')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody CompteComptableDto compteComptableDto,
            @PathVariable String codePlan,
            @PathVariable String numCompteComptable)
    {
        compteComptableDto.setNumCompteComptable(numCompteComptable);
        compteComptableDto.getPlan().setCodePlan(codePlan);
        return compteComptableService.modifier(compteComptableDto);
    }

    @DeleteMapping("/{codePlan}/{numCompteComptable}")
    public ResponseEntity<Object> supprimer(@PathVariable String codePlan,
                                            @PathVariable String numCompteComptable)
    {
        CleCompteComptable idCompteComptable=new CleCompteComptable();
        idCompteComptable.setNumCompteComptable(numCompteComptable);
        idCompteComptable.setCodePlan(codePlan);
        return compteComptableService.supprimer(idCompteComptable);
    }
}
