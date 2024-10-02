package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CleModeleEcritureNatureOperationDto;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureNatureOperationDto;
import com.ged.entity.opcciel.comptabilite.CleModeleEcritureNatureOperation;
import com.ged.service.opcciel.ModeleEcritureNatureOperationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/modeleecriturenatureoperations")
public class ModeleEcritureNatureOperationController {
    private final ModeleEcritureNatureOperationService ModeleEcritureNatureOperationService;

    public ModeleEcritureNatureOperationController(ModeleEcritureNatureOperationService ModeleEcritureNatureOperationService) {
        this.ModeleEcritureNatureOperationService = ModeleEcritureNatureOperationService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return ModeleEcritureNatureOperationService.afficherTous();
    }
    @GetMapping("/{codeModeleEcriture}/{codeNatureOperation}/{codeTypeTitre}")
    //@PreAuthorize("hasAuthority('ROLE_ModeleEcritureNatureOperation')")
    public ResponseEntity<Object> afficher(@PathVariable String codeModeleEcriture,
                                           @PathVariable String codeNatureOperation,
                                           @PathVariable String codeTypeTitre)
    {
        CleModeleEcritureNatureOperation cleModeleEcritureNatureOperation=new CleModeleEcritureNatureOperation();
        cleModeleEcritureNatureOperation.setCodeModeleEcriture(codeModeleEcriture);
        cleModeleEcritureNatureOperation.setCodeNatureOperation(codeNatureOperation);
        cleModeleEcritureNatureOperation.setCodeTypeTitre(codeTypeTitre);
        return ModeleEcritureNatureOperationService.afficher(cleModeleEcritureNatureOperation);
    }

    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_ModeleEcritureNatureOperation')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return ModeleEcritureNatureOperationService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ModeleEcritureNatureOperation')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto)
    {
        return ModeleEcritureNatureOperationService.creer(ModeleEcritureNatureOperationDto);
    }

    @PutMapping("/{codeModeleEcriture}/{codeNatureOperation}/{codeTypeTitre}")
  //  @PreAuthorize("hasAuthority('ROLE_ModeleEcritureNatureOperation')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto,
            @PathVariable String codeModeleEcriture,
            @PathVariable String codeNatureOperation,
            @PathVariable String codeTypeTitre)
    {
        CleModeleEcritureNatureOperationDto cleModeleEcritureNatureOperationDto=new CleModeleEcritureNatureOperationDto();
        cleModeleEcritureNatureOperationDto.setCodeModeleEcriture(codeModeleEcriture);
        cleModeleEcritureNatureOperationDto.setCodeNatureOperation(codeNatureOperation);
        cleModeleEcritureNatureOperationDto.setCodeTypeTitre(codeTypeTitre);
        ModeleEcritureNatureOperationDto.setIdModeleEcritureNatureOperation(cleModeleEcritureNatureOperationDto);

        return ModeleEcritureNatureOperationService.modifier(ModeleEcritureNatureOperationDto);
    }

    @DeleteMapping("/{codeModeleEcriture}/{codeNatureOperation}/{codeTypeTitre}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeModeleEcriture,
                                            @PathVariable String codeNatureOperation,
                                            @PathVariable String codeTypeTitre)
    {
        CleModeleEcritureNatureOperation cleModeleEcritureNatureOperation=new CleModeleEcritureNatureOperation();
        cleModeleEcritureNatureOperation.setCodeTypeTitre(codeTypeTitre);
        cleModeleEcritureNatureOperation.setCodeNatureOperation(codeNatureOperation);
        cleModeleEcritureNatureOperation.setCodeModeleEcriture(codeModeleEcriture);
        return ModeleEcritureNatureOperationService.supprimer(cleModeleEcritureNatureOperation);
    }
}
