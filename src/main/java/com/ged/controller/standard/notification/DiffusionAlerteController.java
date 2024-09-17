package com.ged.controller.standard.notification;

import com.ged.dto.standard.DiffusionAlerteDto;
import com.ged.entity.standard.CleDiffusionAlerte;
import com.ged.service.standard.DiffusionAlerteService;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/diffusionalertes")
public class DiffusionAlerteController {
    private final DiffusionAlerteService diffusionAlerteService;

    public DiffusionAlerteController(DiffusionAlerteService diffusionAlerteService) {
        this.diffusionAlerteService = diffusionAlerteService;
    }

    @GetMapping
    public List<DiffusionAlerteDto> afficherTous()
    {
        return diffusionAlerteService.afficherTous();
    }

    @GetMapping("/{idAlerte}")
    public List<DiffusionAlerteDto> afficherDiffusionAlerte(@PathVariable("idAlerte") long idAlerte)
    {
        return diffusionAlerteService.afficherDiffusionAlerteSelonAlerte(idAlerte);
    }

    @PostMapping
    public DiffusionAlerteDto ajouter(@RequestBody DiffusionAlerteDto diffusionAlerteDto)
    {
        return diffusionAlerteService.creerDiffusionAlerte(diffusionAlerteDto);
    }

//    @PostMapping("/etat/isShown")
//    public DiffusionAlerteDto majEtatIsShown(@RequestBody)
//    {
//
//    }

    @PutMapping("/{idAlerte}/{idPersonnel}/{idModele}")
    public DiffusionAlerteDto modifier(@PathVariable long idAlerte,
                                       @PathVariable long idPersonnel,
                                       @PathVariable long idModele,
                                       @RequestBody DiffusionAlerteDto diffusionAlerteDto)
    {
        diffusionAlerteDto.getAlerte().setIdAlerte(idAlerte);
        diffusionAlerteDto.getPersonne().setIdPersonne(idPersonnel);
        diffusionAlerteDto.getModeleMsgAlerte().setIdModele(idModele);
        return diffusionAlerteService.modifierDiffusionAlerte(diffusionAlerteDto);
    }

    @DeleteMapping("/{idAlerte}/{idPersonnel}/{idModele}/{idTypeCritere}")
    public void supprimer(@PathVariable long idAlerte,
                          @PathVariable long idPersonnel,
                          @PathVariable long idModele)
    {
        CleDiffusionAlerte cleDiffusionAlerte=new CleDiffusionAlerte();
        cleDiffusionAlerte.setIdModele(idModele);
        cleDiffusionAlerte.setIdPersonne(idPersonnel);
        cleDiffusionAlerte.setIdAlerte(idAlerte);
        diffusionAlerteService.supprimerDiffusionAlerte(cleDiffusionAlerte);
    }
    @DeleteMapping("/{idAlerte}")
    public void supprimerSelonRDV(@Positive @PathVariable("idAlerte") Long idAlerte)
    {
        diffusionAlerteService.supprimerDiffusionAlerteSelonAlerte(idAlerte);
    }
}
