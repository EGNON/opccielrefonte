package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.NatureEvenementDto;
import com.ged.service.titresciel.NatureEvenementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/natureevenements")
public class NatureEvenementController {
    private final NatureEvenementService natureEvenementService;

    public NatureEvenementController(NatureEvenementService NatureEvenementService) {
        this.natureEvenementService = NatureEvenementService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_NatureEvenement')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return natureEvenementService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_NatureEvenement')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return natureEvenementService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_NatureEvenement')")
    public ResponseEntity<Object> creer(@Valid @RequestBody NatureEvenementDto NatureEvenementDto)
    {
        return natureEvenementService.creer(NatureEvenementDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_NatureEvenement')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody NatureEvenementDto NatureEvenementDto,
            @Positive @PathVariable Long id)
    {
        NatureEvenementDto.setIdNatureEvenement(id);
        return natureEvenementService.modifier(NatureEvenementDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_NatureEvenement')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return natureEvenementService.supprimer(id);
    }
}
