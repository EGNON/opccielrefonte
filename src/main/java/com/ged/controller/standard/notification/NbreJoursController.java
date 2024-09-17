package com.ged.controller.standard.notification;

import com.ged.dto.standard.NbreJoursDto;
import com.ged.service.standard.NbreJoursService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nbrejourss")
public class NbreJoursController {
    private final NbreJoursService NbreJoursService;

    public NbreJoursController(NbreJoursService NbreJoursService) {
        this.NbreJoursService = NbreJoursService;
    }

    @GetMapping
    public List<NbreJoursDto> afficherTous()
    {
        return NbreJoursService.afficherTous();
    }
    @PostMapping
    public NbreJoursDto ajouter(@RequestBody NbreJoursDto NbreJoursDto)
    {
        return NbreJoursService.creerNbreJours(NbreJoursDto);
    }

    @PostConstruct
    public void generate()
    {
        for (int i = 0; i < 31; i++) {
            if(!NbreJoursService.existById(Long.parseLong(String.valueOf(i+1))))
            {
                NbreJoursDto nbreJoursDto = new NbreJoursDto();
                nbreJoursDto.setIdNbreJours(i+1);
                NbreJoursService.creerNbreJours(nbreJoursDto);
            }
        }
    }

    @PutMapping("/{id}")
    public NbreJoursDto modifier(@PathVariable long id,
                                       @RequestBody NbreJoursDto nbreJoursDto)
    {
        nbreJoursDto.setIdNbreJours(id);
        return NbreJoursService.modifierNbreJours(nbreJoursDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable long id)
    {
        NbreJoursService.supprimerNbreJours(id);
    }
}
