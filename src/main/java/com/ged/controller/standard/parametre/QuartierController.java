package com.ged.controller.standard.parametre;

import com.ged.dto.standard.QuartierDto;
import com.ged.projection.QuartierProjection;
import com.ged.service.standard.QuartierService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/quartiers")
public class QuartierController {
    private final QuartierService quartierService;

    public QuartierController(QuartierService quartierService) {
        this.quartierService = quartierService;
    }

    @GetMapping
    public Page<QuartierDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return quartierService.afficherQuartiers(page,size);
    }
    @GetMapping("/liste/{libelle}")
    public List<QuartierProjection> afficherQuartierSelonQuartier(@PathVariable String libelle){
        return quartierService.afficherQuartierSelonCommune(libelle);
    }
    @GetMapping("/{id}")
    public QuartierDto afficher(@PathVariable long id) {
        return quartierService.afficherQuartier(id);
    }
    @PostMapping
    public QuartierDto ajouter(@RequestBody QuartierDto quartierDto)
    {
        return quartierService.creerQuartier(quartierDto);
    }
    @PutMapping("/{id}")
    public QuartierDto modifier(@PathVariable long id, @RequestBody QuartierDto quartierDto){
        quartierDto.setIdQuartier(id);
        return quartierService.modifierQuartier(quartierDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        quartierService.supprimerQuartier(id);
    }
}
