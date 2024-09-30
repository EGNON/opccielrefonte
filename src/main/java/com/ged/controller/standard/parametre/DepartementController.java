package com.ged.controller.standard.parametre;

import com.ged.dto.standard.DepartementDto;
import com.ged.service.standard.DepartementService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/departements")
//@PreAuthorize("hasAuthority('ROLE_DEPARTEMENT')")
public class DepartementController {
    private final DepartementService departementService;

    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_DEPARTEMENT')")
    public Page<DepartementDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return departementService.afficherDepartements(page, size);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEPARTEMENT')")
    public DepartementDto ajouter(@RequestBody DepartementDto departementDto)
    {
        return departementService.creerDepartement(departementDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEPARTEMENT')")
    public DepartementDto modifier(@RequestBody DepartementDto departementDto, @PathVariable Long id)
    {
        departementDto.setIdDepartement(id);
        return departementService.modifierDepartement(departementDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEPARTEMENT')")
    public void supprimer(@PathVariable Long id)
    {
        departementService.supprimerDepartement(id);
    }
}
