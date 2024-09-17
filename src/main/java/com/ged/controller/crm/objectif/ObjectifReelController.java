package com.ged.controller.crm.objectif;

import com.ged.dto.crm.ObjectifReelDto;
import com.ged.service.crm.ObjectifReelService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/objectifreels")
public class ObjectifReelController {
    private final ObjectifReelService objectifReelService;

    public ObjectifReelController(ObjectifReelService objectifReelService) {
        this.objectifReelService = objectifReelService;
    }

    @GetMapping
    public Page<ObjectifReelDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return objectifReelService.afficherObjectifReels(page,size);
    }
    @PostMapping
    public ObjectifReelDto ajouter(@RequestBody ObjectifReelDto objectifReelDto)
    {
        return objectifReelService.creerObjectifReel(objectifReelDto);
    }
    @PutMapping("/{id}")
    public ObjectifReelDto modifier(@PathVariable long id, @RequestBody ObjectifReelDto objectifReelDto){
        objectifReelDto.setIdObjReel(id);
        return objectifReelService.modifierObjectifReel(objectifReelDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        objectifReelService.supprimerObjectifReel(id);
    }
}
