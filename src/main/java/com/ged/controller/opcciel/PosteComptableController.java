package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.PosteComptableDto;
import com.ged.entity.opcciel.comptabilite.ClePosteComptable;
import com.ged.service.opcciel.PosteComptableService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/postecomptables")
public class PosteComptableController {
    private final PosteComptableService PosteComptableService;

    public PosteComptableController(PosteComptableService PosteComptableService) {

        this.PosteComptableService = PosteComptableService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return PosteComptableService.afficherTous();
    }

    @GetMapping("/{codePlan}/{codePosteComptable}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable String codePlan,
                                           @PathVariable String codePosteComptable)
    {
        ClePosteComptable clePosteComptable=new ClePosteComptable();
        clePosteComptable.setCodePosteComptable(codePosteComptable);
        clePosteComptable.setCodePlan(codePlan);
        return PosteComptableService.afficher(clePosteComptable);
    }
     @GetMapping("/{codePosteComptable}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherSelonCodePosteComptable(
                                           @PathVariable String codePosteComptable)
    {
        return PosteComptableService.afficherSelonCodePosteCOmptable(codePosteComptable);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return PosteComptableService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@RequestBody PosteComptableDto PosteComptableDto)
    {
        return PosteComptableService.creer(PosteComptableDto);
    }

    @PutMapping("/{codePlan}/{codePosteComptable}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier( @RequestBody PosteComptableDto PosteComptableDto,
                                           @PathVariable String codePlan,
                                           @PathVariable String codePosteComptable)
    {
        PosteComptableDto.setCodePosteComptable(codePosteComptable);
        PosteComptableDto.getPlan().setCodePlan(codePlan);
        return PosteComptableService.modifier(PosteComptableDto);
    }

    @DeleteMapping("/{codePlan}/{codePosteComptable}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String codePlan,
                                            @PathVariable String codePosteComptable)
    {
        ClePosteComptable clePosteComptable=new ClePosteComptable();
        clePosteComptable.setCodePosteComptable(codePosteComptable);
        clePosteComptable.setCodePlan(codePlan);
        return PosteComptableService.supprimer(clePosteComptable);
    }
}
