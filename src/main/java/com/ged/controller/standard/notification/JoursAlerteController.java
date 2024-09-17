package com.ged.controller.standard.notification;

import com.ged.dto.standard.JoursAlerteDto;
import com.ged.entity.standard.CleJoursAlerte;
import com.ged.service.standard.JoursAlerteService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/joursalertes")
public class JoursAlerteController {
    private final JoursAlerteService JoursAlerteService;

    public JoursAlerteController(JoursAlerteService JoursAlerteService) {
        this.JoursAlerteService = JoursAlerteService;
    }

    @GetMapping
    public Page<JoursAlerteDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return JoursAlerteService.afficherJoursAlertes(page, size);
    }
    @GetMapping("/{idAlerte}")
    public List<JoursAlerteDto> afficherJoursAlerte(@PathVariable("idAlerte") long idAlerte)
    {
        return JoursAlerteService.afficherJoursAlerteSelonAlerte(idAlerte);
    }
    @PostMapping
    public JoursAlerteDto ajouter(@RequestBody JoursAlerteDto JoursAlerteDto)
    {
        return JoursAlerteService.creerJoursAlerte(JoursAlerteDto);
    }

    @PutMapping("/{idAlerte}/{idJours}")
    public JoursAlerteDto modifier(@PathVariable long idAlerte,
                                       @PathVariable long idJours,
                                       @RequestBody JoursAlerteDto JoursAlerteDto)
    {
        JoursAlerteDto.getAlerte().setIdAlerte(idAlerte);
        JoursAlerteDto.getJours().setIdJours(idJours);
        return JoursAlerteService.modifierJoursAlerte(JoursAlerteDto);
    }

    @DeleteMapping("/{idAlerte}/{idJours}")
    public void supprimer(@PathVariable long idAlerte,
                          @PathVariable long idJours)
    {
        CleJoursAlerte cleJoursAlerte=new CleJoursAlerte();
        cleJoursAlerte.setIdJours(idJours);
        cleJoursAlerte.setIdAlerte(idAlerte);
        JoursAlerteService.supprimerJoursAlerte(cleJoursAlerte);
    }
    @DeleteMapping("/{idAlerte}")
    public void supprimerSelonRDV(@Positive @PathVariable("idAlerte") Long idAlerte)
    {
        JoursAlerteService.supprimerJoursAlerteSelonAlerte(idAlerte);
    }
}
