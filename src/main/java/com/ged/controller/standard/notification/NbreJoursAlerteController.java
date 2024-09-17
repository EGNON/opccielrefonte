package com.ged.controller.standard.notification;

import com.ged.dto.standard.NbreJoursAlerteDto;
import com.ged.entity.standard.CleNbreJoursAlerte;
import com.ged.service.standard.NbreJoursAlerteService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nbrejoursalertes")
public class NbreJoursAlerteController {
    private final NbreJoursAlerteService NbreJoursAlerteService;

    public NbreJoursAlerteController(NbreJoursAlerteService NbreJoursAlerteService) {
        this.NbreJoursAlerteService = NbreJoursAlerteService;
    }

    @GetMapping
    public Page<NbreJoursAlerteDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return NbreJoursAlerteService.afficherNbreJoursAlertes(page, size);
    }
    @GetMapping("/{idAlerte}")
    public List<NbreJoursAlerteDto> afficherNbreJoursAlerte(@PathVariable("idAlerte") long idAlerte)
    {
        return NbreJoursAlerteService.afficherNbreJoursAlerteSelonAlerte(idAlerte);
    }
    @PostMapping
    public NbreJoursAlerteDto ajouter(@RequestBody NbreJoursAlerteDto NbreJoursAlerteDto)
    {
        return NbreJoursAlerteService.creerNbreJoursAlerte(NbreJoursAlerteDto);
    }

    @PutMapping("/{idAlerte}/{idNbreJours}")
    public NbreJoursAlerteDto modifier(@PathVariable long idAlerte,
                                       @PathVariable long idNbreJours,
                                       @RequestBody NbreJoursAlerteDto NbreJoursAlerteDto)
    {
        NbreJoursAlerteDto.getAlerte().setIdAlerte(idAlerte);
        NbreJoursAlerteDto.getNbreJours().setIdNbreJours(idNbreJours);
        return NbreJoursAlerteService.modifierNbreJoursAlerte(NbreJoursAlerteDto);
    }

    @DeleteMapping("/{idAlerte}/{idNbreJours}")
    public void supprimer(@PathVariable long idAlerte,
                          @PathVariable long idNbreJours)
    {
        CleNbreJoursAlerte cleNbreJoursAlerte=new CleNbreJoursAlerte();
        cleNbreJoursAlerte.setIdNbreJours(idNbreJours);
        cleNbreJoursAlerte.setIdAlerte(idAlerte);
        NbreJoursAlerteService.supprimerNbreJoursAlerte(cleNbreJoursAlerte);
    }
    @DeleteMapping("/{idAlerte}")
    public void supprimerSelonRDV(@Positive @PathVariable("idAlerte") Long idAlerte)
    {
        NbreJoursAlerteService.supprimerNbreJoursAlerteSelonAlerte(idAlerte);
    }
}
