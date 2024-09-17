package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.QualiteTitreDto;
import com.ged.service.titresciel.QualiteTitreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/qualitetitres")
public class QualiteTitreController {
    private final QualiteTitreService QualiteTitreService;

    public QualiteTitreController(QualiteTitreService QualiteTitreService) {
        this.QualiteTitreService = QualiteTitreService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_QualiteTitre')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return QualiteTitreService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_QualiteTitre')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return QualiteTitreService.afficherTous(params);
    }

    @GetMapping("/liste")
    public ResponseEntity<Object> listeQualiteTitre()
    {
        return QualiteTitreService.afficherTous();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_QualiteTitre')")
    public ResponseEntity<Object> creer(@Valid @RequestBody QualiteTitreDto QualiteTitreDto)
    {
        return QualiteTitreService.creer(QualiteTitreDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_QualiteTitre')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody QualiteTitreDto QualiteTitreDto,
            @Positive @PathVariable Long id)
    {
        QualiteTitreDto.setIdQualite(id);
        return QualiteTitreService.modifier(QualiteTitreDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_QualiteTitre')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return QualiteTitreService.supprimer(id);
    }
}
