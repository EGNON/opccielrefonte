package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.EmetteurDto;
import com.ged.service.titresciel.EmetteurService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/emetteurs")
public class EmetteurController {
    private final EmetteurService EmetteurService;

    public EmetteurController(EmetteurService EmetteurService) {
        this.EmetteurService = EmetteurService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Emetteur')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return EmetteurService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Emetteur')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return EmetteurService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Emetteur')")
    public ResponseEntity<Object> creer(@Valid @RequestBody EmetteurDto EmetteurDto)
    {
        return EmetteurService.creer(EmetteurDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Emetteur')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody EmetteurDto EmetteurDto,
            @Positive @PathVariable Long id)
    {
        EmetteurDto.setIdPersonne(id);
        return EmetteurService.modifier(EmetteurDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Emetteur')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return EmetteurService.supprimer(id);
    }
}
