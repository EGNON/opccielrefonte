package com.ged.controller.standard.parametre;

import com.ged.dto.standard.JoursDto;
import com.ged.service.standard.JoursService;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/jours")
public class JourController {
    private final JoursService joursService;

    public JourController(JoursService joursService) {
        this.joursService = joursService;
    }

    @GetMapping
    public List<JoursDto> afficherTous()
    {
        return joursService.afficherTous();
    }

    @PostConstruct
    public void ajouter()
    {
        String[] jours = {"Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"};
        for (String jour : jours) {
            if(!joursService.existByLibelle(jour))
            {
                JoursDto joursDto = new JoursDto();
                joursDto.setLibelleJours(jour);
                joursService.creerJours(joursDto);
            }
        }
    }
}
