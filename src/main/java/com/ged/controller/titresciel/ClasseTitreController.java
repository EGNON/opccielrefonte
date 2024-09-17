package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ClasseTitreDto;
import com.ged.service.titresciel.ClasseTitreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/classetitres")
public class ClasseTitreController {
    private final ClasseTitreService ClasseTitreService;

    public ClasseTitreController(ClasseTitreService ClasseTitreService) {
        this.ClasseTitreService = ClasseTitreService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ClasseTitre')")
    public ResponseEntity<Object> afficher(@PathVariable String id)
    {
        return ClasseTitreService.afficher(id);
    }
    @GetMapping()
//    @PreAuthorize("hasAuthority('ROLE_ClasseTitre')")
    public ResponseEntity<Object> afficherTous()
    {
        return ClasseTitreService.afficherTous();
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_ClasseTitre')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return ClasseTitreService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ClasseTitre')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ClasseTitreDto ClasseTitreDto)
    {
        return ClasseTitreService.creer(ClasseTitreDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ClasseTitre')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ClasseTitreDto ClasseTitreDto,
            @Positive @PathVariable String id)
    {
        ClasseTitreDto.setCodeClasseTitre(id);
        return ClasseTitreService.modifier(ClasseTitreDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ClasseTitre')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable String id)
    {
        return ClasseTitreService.supprimer(id);
    }
}
