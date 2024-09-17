package com.ged.controller.standard.notification;

import com.ged.dto.standard.TempsAlerteDto;
import com.ged.entity.standard.CleTempsAlerte;
import com.ged.service.standard.TempsAlerteService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tempsalertes")
public class TempsAlerteController {
    private final TempsAlerteService tempsAlerteService;

    public TempsAlerteController(TempsAlerteService TempsAlerteService) {
        this.tempsAlerteService = TempsAlerteService;
    }

    @GetMapping
    public Page<TempsAlerteDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return tempsAlerteService.afficherTempsAlertes(page, size);
    }
    @GetMapping("/{idAlerte}")
    public List<TempsAlerteDto> afficherTempsAlerte(@PathVariable("idAlerte") long idAlerte)
    {
        return tempsAlerteService.afficherTempsAlerteSelonAlerte(idAlerte);
    }
    @PostMapping
    public TempsAlerteDto ajouter(@RequestBody TempsAlerteDto tempsAlerteDto)
    {
        return tempsAlerteService.creerTempsAlerte(tempsAlerteDto);
    }

    @PutMapping("/{idAlerte}/{idTemps}")
    public TempsAlerteDto modifier(@PathVariable long idAlerte,
                                       @PathVariable long idTemps,
                                       @RequestBody TempsAlerteDto tempsAlerteDto)
    {
        tempsAlerteDto.getAlerte().setIdAlerte(idAlerte);
        tempsAlerteDto.getTemps().setIdTemps(idTemps);
        return tempsAlerteService.modifierTempsAlerte(tempsAlerteDto);
    }

    @DeleteMapping("/{idAlerte}/{idTemps}")
    public void supprimer(@PathVariable long idAlerte,
                          @PathVariable long idTemps)
    {
        CleTempsAlerte cleTempsAlerte=new CleTempsAlerte();
        cleTempsAlerte.setIdTemps(idTemps);
        cleTempsAlerte.setIdAlerte(idAlerte);
        tempsAlerteService.supprimerTempsAlerte(cleTempsAlerte);
    }
    @DeleteMapping("/{idAlerte}")
    public void supprimerSelonRDV(@Positive @PathVariable("idAlerte") Long idAlerte)
    {
        tempsAlerteService.supprimerTempsAlerteSelonAlerte(idAlerte);
    }
}
