package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeAmortissementDto;
import com.ged.service.titresciel.TypeAmortissementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeamortissements")
public class TypeAmortissementController {
    private final TypeAmortissementService TypeAmortissementService;

    public TypeAmortissementController(TypeAmortissementService TypeAmortissementService) {
        this.TypeAmortissementService = TypeAmortissementService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_TypeAmortissement')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return TypeAmortissementService.afficher(id);
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeAmortissementService.afficherTous();
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_TypeAmortissement')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return TypeAmortissementService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_TypeAmortissement')")
    public ResponseEntity<Object> creer(@Valid @RequestBody TypeAmortissementDto TypeAmortissementDto)
    {
        return TypeAmortissementService.creer(TypeAmortissementDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_TypeAmortissement')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody TypeAmortissementDto TypeAmortissementDto,
            @Positive @PathVariable Long id)
    {
        TypeAmortissementDto.setIdTypeAmortissement(id);
        return TypeAmortissementService.modifier(TypeAmortissementDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_TypeAmortissement')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return TypeAmortissementService.supprimer(id);
    }
}
