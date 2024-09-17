package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.RegistraireDto;
import com.ged.service.titresciel.RegistraireService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Registraires")
public class RegistraireController {
    private final RegistraireService RegistraireService;

    public RegistraireController(RegistraireService RegistraireService) {
        this.RegistraireService = RegistraireService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Registraire')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return RegistraireService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Registraire')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return RegistraireService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Registraire')")
    public ResponseEntity<Object> creer(@Valid @RequestBody RegistraireDto RegistraireDto)
    {
        return RegistraireService.creer(RegistraireDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Registraire')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody RegistraireDto RegistraireDto,
            @Positive @PathVariable Long id)
    {
        RegistraireDto.setIdPersonne(id);
        return RegistraireService.modifier(RegistraireDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Registraire')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return RegistraireService.supprimer(id);
    }
}
