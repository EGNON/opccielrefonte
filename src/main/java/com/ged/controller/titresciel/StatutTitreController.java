package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.CleStatutTitreDto;
import com.ged.dto.titresciel.StatutTitreDto;
import com.ged.entity.titresciel.CleStatutTitre;
import com.ged.service.titresciel.StatutTitreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statuttitres")
public class StatutTitreController {
    private final StatutTitreService StatutTitreService;

    public StatutTitreController(StatutTitreService StatutTitreService) {
        this.StatutTitreService = StatutTitreService;
    }

    @GetMapping("/{idTitre}/{idQualite}")
//    @PreAuthorize("hasAuthority('ROLE_StatutTitre')")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable Long idQualite)
    {
        CleStatutTitre cleStatutTitre=new CleStatutTitre();
        cleStatutTitre.setIdQualite(idQualite);
        cleStatutTitre.setIdTitre(idTitre);
        return StatutTitreService.afficher(cleStatutTitre);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_StatutTitre')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return StatutTitreService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_StatutTitre')")
    public ResponseEntity<Object> creer(@Valid @RequestBody StatutTitreDto StatutTitreDto)
    {
        return StatutTitreService.creer(StatutTitreDto);
    }

    @PutMapping("/{idTitre}/{idQualite}")
//    @PreAuthorize("hasAuthority('ROLE_StatutTitre')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody StatutTitreDto StatutTitreDto,
            @Positive @PathVariable Long idTitre,
            @PathVariable Long idQualite)
    {
        CleStatutTitreDto cleStatutTitre=new CleStatutTitreDto();
        cleStatutTitre.setIdTitre(idTitre);
        cleStatutTitre.setIdQualite(idQualite);
        StatutTitreDto.setIdStatutTitre(cleStatutTitre);
        return StatutTitreService.modifier(StatutTitreDto);
    }

    @DeleteMapping("/{idTitre}/{idQualite}")
//    @PreAuthorize("hasAuthority('ROLE_StatutTitre')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long idTitre,
                                            @PathVariable Long idQualite)
    {
        CleStatutTitre cleStatutTitre=new CleStatutTitre();
        cleStatutTitre.setIdQualite(idQualite);
        cleStatutTitre.setIdTitre(idTitre);
        return StatutTitreService.supprimer(cleStatutTitre);
    }
}
