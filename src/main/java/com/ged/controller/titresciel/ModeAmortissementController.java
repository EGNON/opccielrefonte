package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ModeAmortissementDto;
import com.ged.service.titresciel.ModeAmortissementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/modeamortissements")
public class ModeAmortissementController {
    private final ModeAmortissementService modeAmortissementService;

    public ModeAmortissementController(ModeAmortissementService modeAmortissementService) {
        this.modeAmortissementService = modeAmortissementService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ModeAmortissement')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return modeAmortissementService.afficher(id);
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return modeAmortissementService.afficherTous();
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_ModeAmortissement')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return modeAmortissementService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ModeAmortissement')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ModeAmortissementDto ModeAmortissementDto)
    {
        return modeAmortissementService.creer(ModeAmortissementDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ModeAmortissement')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ModeAmortissementDto ModeAmortissementDto,
            @Positive @PathVariable Long id)
    {
        ModeAmortissementDto.setIdModeAmortissement(id);
        return modeAmortissementService.modifier(ModeAmortissementDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ModeAmortissement')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return modeAmortissementService.supprimer(id);
    }
}
