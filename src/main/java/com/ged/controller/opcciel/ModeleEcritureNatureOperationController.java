package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureNatureOperationDto;
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
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_ModeleEcritureNatureOperation')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return ModeleEcritureNatureOperationService.afficher(id);
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

    @PutMapping("/{id}")
  //  @PreAuthorize("hasAuthority('ROLE_ModeleEcritureNatureOperation')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto,
            @PathVariable Long id)
    {
        ModeleEcritureNatureOperationDto.setIdModeleEcritureNatureOperation(id);
        return ModeleEcritureNatureOperationService.modifier(ModeleEcritureNatureOperationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return ModeleEcritureNatureOperationService.supprimer(id);
    }
}
