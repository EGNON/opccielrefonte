package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DepositaireDto;
import com.ged.service.titresciel.DepositaireService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/depositaires")
public class DepositaireController {
    private final DepositaireService DepositaireService;

    public DepositaireController(DepositaireService DepositaireService) {
        this.DepositaireService = DepositaireService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Depositaire')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return DepositaireService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Depositaire')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return DepositaireService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Depositaire')")
    public ResponseEntity<Object> creer(@Valid @RequestBody DepositaireDto DepositaireDto)
    {
        return DepositaireService.creer(DepositaireDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Depositaire')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody DepositaireDto DepositaireDto,
            @Positive @PathVariable Long id)
    {
        DepositaireDto.setIdPersonne(id);
        return DepositaireService.modifier(DepositaireDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Depositaire')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return DepositaireService.supprimer(id);
    }
}
