package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeActionDto;
import com.ged.service.titresciel.TypeActionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeactions")
public class TypeActionController {
    private final TypeActionService typeActionService;

    public TypeActionController(TypeActionService TypeActionService) {
        this.typeActionService = TypeActionService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_TypeAction')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return typeActionService.afficher(id);
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return typeActionService.afficherTous();
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_TypeAction')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return typeActionService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_TypeAction')")
    public ResponseEntity<Object> creer(@Valid @RequestBody TypeActionDto TypeActionDto)
    {
        return typeActionService.creer(TypeActionDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_TypeAction')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody TypeActionDto TypeActionDto,
            @Positive @PathVariable Long id)
    {
        TypeActionDto.setIdTypeAction(id);
        return typeActionService.modifier(TypeActionDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_TypeAction')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return typeActionService.supprimer(id);
    }
}
