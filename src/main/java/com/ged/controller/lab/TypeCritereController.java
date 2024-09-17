package com.ged.controller.lab;

import com.ged.dto.lab.TypeCritereDto;
import com.ged.service.lab.TypeCritereService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/typecriteres")
public class TypeCritereController {
    private final TypeCritereService typeCritereService;

    public TypeCritereController(TypeCritereService typeCritereService) {
        this.typeCritereService = typeCritereService;
    }

    @GetMapping
    public Page<TypeCritereDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "0") int size)
    {
        return typeCritereService.afficherTypeCriteres(page, size);
    }

    @PostMapping
    public TypeCritereDto ajouter(@RequestBody TypeCritereDto typeCritereDto)
    {
        return typeCritereService.creerTypeCritere(typeCritereDto);
    }

    @PutMapping("/{id}")
    public TypeCritereDto modifier(@RequestBody TypeCritereDto typeCritereDto, @PathVariable("id") Long id)
    {
        typeCritereDto.setIdTypeCritere(id);
        return typeCritereService.modifierTypeCritere(typeCritereDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable("id") Long id)
    {
        typeCritereService.supprimerTypeCritere(id);
    }
}
