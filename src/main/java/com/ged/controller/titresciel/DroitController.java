package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DroitDto;
import com.ged.service.titresciel.DroitService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/droits")
public class DroitController {
    private final DroitService DroitService;

    public DroitController(DroitService DroitService) {
        this.DroitService = DroitService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Droit')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return DroitService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Droit')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return DroitService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Droit')")
    public ResponseEntity<Object> creer(@Valid @RequestBody DroitDto DroitDto)
    {
        return DroitService.creer(DroitDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Droit')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody DroitDto DroitDto,
            @Positive @PathVariable Long id)
    {
        DroitDto.setIdTitre(id);
        return DroitService.modifier(DroitDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Droit')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return DroitService.supprimer(id);
    }
}
