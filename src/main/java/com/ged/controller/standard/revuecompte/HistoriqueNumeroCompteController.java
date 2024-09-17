package com.ged.controller.standard.revuecompte;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.HistoriqueNumeroCompteDto;
import com.ged.service.standard.revuecompte.HistoriqueNumeroCompteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/historiquenumerocomptes")
public class HistoriqueNumeroCompteController {
    private final HistoriqueNumeroCompteService HistoriqueNumeroCompteService;

    public HistoriqueNumeroCompteController(HistoriqueNumeroCompteService HistoriqueNumeroCompteService) {

        this.HistoriqueNumeroCompteService = HistoriqueNumeroCompteService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return HistoriqueNumeroCompteService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return HistoriqueNumeroCompteService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return HistoriqueNumeroCompteService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto)
    {
        return HistoriqueNumeroCompteService.creer(HistoriqueNumeroCompteDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto,
                                           @PathVariable Long id)
    {
        HistoriqueNumeroCompteDto.setIdHistoriqueNumeroCompte(id);
        return HistoriqueNumeroCompteService.modifier(HistoriqueNumeroCompteDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return HistoriqueNumeroCompteService.supprimer(id);
    }
}
