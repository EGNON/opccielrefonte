package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureDto;
import com.ged.service.opcciel.ModeleEcritureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/modeleecritures")
public class ModeleEcritureController {
    private final ModeleEcritureService ModeleEcritureService;

    public ModeleEcritureController(ModeleEcritureService ModeleEcritureService) {

        this.ModeleEcritureService = ModeleEcritureService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return ModeleEcritureService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return ModeleEcritureService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return ModeleEcritureService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody ModeleEcritureDto ModeleEcritureDto)
    {
        return ModeleEcritureService.creer(ModeleEcritureDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody ModeleEcritureDto ModeleEcritureDto,
                                           @PathVariable String code)
    {
        ModeleEcritureDto.setCodeModeleEcriture(code);
        return ModeleEcritureService.modifier(ModeleEcritureDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return ModeleEcritureService.supprimer(code);
    }
}
