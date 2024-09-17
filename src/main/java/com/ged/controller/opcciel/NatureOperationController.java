package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.service.opcciel.NatureOperationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/natureoperations")
public class NatureOperationController {
    private final NatureOperationService NatureOperationService;

    public NatureOperationController(NatureOperationService NatureOperationService) {

        this.NatureOperationService = NatureOperationService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return NatureOperationService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return NatureOperationService.afficher(code);
    }
    @GetMapping("/typeoperation/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherSelonTypeOperation(@PathVariable(name = "code") String code)
    {
        return NatureOperationService.afficherSelonTypeOperation(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return NatureOperationService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody NatureOperationDto NatureOperationDto)
    {
        return NatureOperationService.creer(NatureOperationDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody NatureOperationDto NatureOperationDto,
                                           @PathVariable String code)
    {
        NatureOperationDto.setCodeNatureOperation(code);
        return NatureOperationService.modifier(NatureOperationDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return NatureOperationService.supprimer(code);
    }
}
