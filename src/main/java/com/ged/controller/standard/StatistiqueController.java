package com.ged.controller.standard;

import com.ged.service.standard.StatistiqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statistiques")
public class StatistiqueController {
    private final StatistiqueService statistiqueService;

    public StatistiqueController(StatistiqueService statistiqueService) {
        this.statistiqueService = statistiqueService;
    }

    @GetMapping(value = {"/{libelleQualite}", ""})
    public ResponseEntity<Object> afficherTous(
            @PathVariable(name = "libelleQualite", required = false) Optional<String> libelleQualite)
    {
        return statistiqueService.nbrePersonneParQualite(libelleQualite);
    }

    @GetMapping("nbrerdvparmois/{annee}")
    public ResponseEntity<Object> afficherNbreRdvParMois(@PathVariable String annee)
    {
        return statistiqueService.nbreRDVParMois(annee);
    }

    @GetMapping("/rdv/nbrerdvencours")
    public ResponseEntity<Object> afficherNbreRdvEnCOurs()
    {
        return statistiqueService.nbreRDVEnCours();
    }

    @GetMapping("/objectif/niveau-evolution/{annee}")
    public ResponseEntity<Object> afficherNiveauAtteinteObjectif(@PathVariable String annee)
    {
        return statistiqueService.niveauEvolutionObjectif(annee);
    }
}
