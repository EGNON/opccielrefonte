package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureFormuleDto;
import com.ged.entity.opcciel.comptabilite.CleModeleEcritureFormule;
import com.ged.service.opcciel.ModeleEcritureFormuleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/modeleecritureformules")
public class ModeleEcritureFormuleController {
    private final ModeleEcritureFormuleService ModeleEcritureFormuleService;

    public ModeleEcritureFormuleController(ModeleEcritureFormuleService ModeleEcritureFormuleService) {
        this.ModeleEcritureFormuleService = ModeleEcritureFormuleService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return ModeleEcritureFormuleService.afficherTous();
    }
    @GetMapping("/{codeModeleEcriture}/{idFormule}")
    //@PreAuthorize("hasAuthority('ROLE_ModeleEcritureFormule')")
    public ResponseEntity<Object> afficher(@PathVariable String codeModeleEcriture,
                                           @PathVariable Long idFormule)
    {
        CleModeleEcritureFormule idModeleEcritureFormule=new CleModeleEcritureFormule();
        idModeleEcritureFormule.setCodeModeleEcriture(codeModeleEcriture);
        idModeleEcritureFormule.setIdFormule(idFormule);
        return ModeleEcritureFormuleService.afficher(idModeleEcritureFormule);
    }
    @GetMapping("/{codeModeleEcriture}")
    //@PreAuthorize("hasAuthority('ROLE_ModeleEcritureFormule')")
    public ResponseEntity<Object> afficherSelonModeleEcriture(@PathVariable String codeModeleEcriture)
    {
        return ModeleEcritureFormuleService.afficherSelonModeleEcriture(codeModeleEcriture);
    }

    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_ModeleEcritureFormule')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return ModeleEcritureFormuleService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ModeleEcritureFormule')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ModeleEcritureFormuleDto ModeleEcritureFormuleDto)
    {
        return ModeleEcritureFormuleService.creer(ModeleEcritureFormuleDto);
    }

    @PutMapping("/{codeModeleEcriture}/{idFormule}")
  //  @PreAuthorize("hasAuthority('ROLE_ModeleEcritureFormule')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ModeleEcritureFormuleDto ModeleEcritureFormuleDto,
            @PathVariable String codeModeleEcriture,
            @PathVariable Long idFormule)
    {
        ModeleEcritureFormuleDto.getFormule().setIdFormule(idFormule);
        ModeleEcritureFormuleDto.getModeleEcriture().setCodeModeleEcriture(codeModeleEcriture);
        return ModeleEcritureFormuleService.modifier(ModeleEcritureFormuleDto);
    }

    @DeleteMapping("/{codeModeleEcriture}/{idFormule}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeModeleEcriture,
                                            @PathVariable Long idFormule)
    {
        CleModeleEcritureFormule idModeleEcritureFormule=new CleModeleEcritureFormule();
        idModeleEcritureFormule.setCodeModeleEcriture(codeModeleEcriture);
        idModeleEcritureFormule.setIdFormule(idFormule);
        return ModeleEcritureFormuleService.supprimer(idModeleEcritureFormule);
    }
    @DeleteMapping("/{codeModeleEcriture}")
    public ResponseEntity<Object> supprimerSelonModeleEcriture(@PathVariable String codeModeleEcriture)
    {
        return ModeleEcritureFormuleService.supprimerSelonModeleEcriture(codeModeleEcriture);
    }
}
