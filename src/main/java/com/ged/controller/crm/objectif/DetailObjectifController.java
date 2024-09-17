package com.ged.controller.crm.objectif;

import com.ged.dto.crm.DetailObjectifDto;
import com.ged.entity.crm.CleDetailObjectif;
import com.ged.service.crm.DetailObjectifService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detailobjectifs")
public class DetailObjectifController {
    private final DetailObjectifService detailObjectifService;

    public DetailObjectifController(DetailObjectifService detailObjectifService) {
        this.detailObjectifService = detailObjectifService;
    }

    @GetMapping
    public Page<DetailObjectifDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return detailObjectifService.afficherDetailObjectifs(page, size);
    }

    @PostMapping
    public DetailObjectifDto ajouter(@Valid @RequestBody DetailObjectifDto detailObjectifDto)
    {
        return detailObjectifService.creerDetailObjectif(detailObjectifDto);
    }

    @PutMapping("/{idCat}/{idPeriod}/{idIndic}")
    public DetailObjectifDto modifier(@Valid @RequestBody DetailObjectifDto detailObjectifDto,
                                      @Positive @PathVariable("idCat") Long idCat,
                                      @Positive @PathVariable("idPeriod") Long idPeriod,
                                      @Positive @PathVariable("idIndic") Long idIndic)
    {
        detailObjectifDto.getCategoriePersonne().setIdCategorie(idCat);
        detailObjectifDto.getPeriodicite().setIdPeriodicite(idPeriod);
        detailObjectifDto.getIndicateur().setIdIndicateur(idIndic);
        return detailObjectifService.modifierDetailObjectif(detailObjectifDto);
    }

    @DeleteMapping("/{idCat}/{idPeriod}/{idIndic}")
    public void supprimer(@Positive @PathVariable("idCat") Long idCat,
                          @Positive @PathVariable("idPeriod") Long idPeriod,
                          @Positive @PathVariable("idIndic") Long idIndic)
    {
        CleDetailObjectif cleDetailObjectif = new CleDetailObjectif();
        cleDetailObjectif.setIdPeriodicite(idPeriod);
        cleDetailObjectif.setIdIndicateur(idIndic);
        cleDetailObjectif.setIdCategorie(idCat);
        detailObjectifService.supprimerDetailObjectif(cleDetailObjectif);
    }
}
