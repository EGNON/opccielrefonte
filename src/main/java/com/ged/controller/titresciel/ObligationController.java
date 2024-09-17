package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ObligationDto;
import com.ged.service.titresciel.ObligationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/obligations")
public class ObligationController {
    private final ObligationService ObligationService;

    public ObligationController(ObligationService ObligationService) {
        this.ObligationService = ObligationService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Obligation')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return ObligationService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Obligation')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return ObligationService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Obligation')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ObligationDto ObligationDto)
    {
        return ObligationService.creer(ObligationDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Obligation')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ObligationDto ObligationDto,
            @Positive @PathVariable Long id)
    {
        ObligationDto.setIdTitre(id);
        return ObligationService.modifier(ObligationDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Obligation')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return ObligationService.supprimer(id);
    }
}
