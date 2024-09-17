package com.ged.controller.standard.parametre;

import com.ged.dto.standard.ArrondissementDto;
import com.ged.service.standard.ArrondissementService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/arrondissements")
@PreAuthorize("hasAuthority('ROLE_ARRONDISSEMENT')")
public class ArrondissementController {
    private final ArrondissementService arrondissementService;

    public ArrondissementController(ArrondissementService arrondissementService) {
        this.arrondissementService = arrondissementService;
    }

    @GetMapping
    public Page<ArrondissementDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return arrondissementService.afficherArrondissements(page, size);
    }

    @PostMapping
    public ArrondissementDto ajouter(@RequestBody ArrondissementDto arrondissementDto)
    {
        return arrondissementService.creerArrondissement(arrondissementDto);
    }

    @PutMapping("/{id}")
    public ArrondissementDto modifier(@RequestBody ArrondissementDto arrondissementDto, @PathVariable Long id)
    {
        arrondissementDto.setIdArrondissement(id);
        return arrondissementService.modifierArrondissement(arrondissementDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id)
    {
        arrondissementService.supprimerArrondissement(id);
    }
}
