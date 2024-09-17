package com.ged.controller.standard.parametre;

import com.ged.dto.standard.VilleDto;
import com.ged.service.standard.VilleService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/villes")
public class VilleController {
    private final VilleService villeService;

    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public Page<VilleDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return villeService.afficherVilles(page,size);
    }
    @GetMapping("/liste")
    public List<VilleDto> afficherVille(){
        return villeService.afficherVilleListe();
    }
    @GetMapping("/{id}")
    public VilleDto afficher(@PathVariable long id) {
        return villeService.afficherVille(id);
    }

    @PostMapping
    public VilleDto ajouter(@RequestBody VilleDto villeDto)
    {
        return villeService.creerVille(villeDto);
    }
    @PutMapping("/{id}")
    public VilleDto modifier(@PathVariable long id, @RequestBody VilleDto villeDto){
        villeDto.setIdVille(id);
        return villeService.modifierVille(villeDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        villeService.supprimerVille(id);
    }
}
