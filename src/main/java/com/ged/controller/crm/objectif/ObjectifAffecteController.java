package com.ged.controller.crm.objectif;

import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.dto.crm.ObjectifAffecteEtatDto;
import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.service.crm.ObjectifAffecteService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/objectifaffectes")
public class ObjectifAffecteController {
    private final ObjectifAffecteService objectifAffecteService;

    public ObjectifAffecteController(ObjectifAffecteService objectifAffecteService) {
        this.objectifAffecteService = objectifAffecteService;
    }

    @GetMapping
    public Page<ObjectifAffecteDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return objectifAffecteService.afficherObjectifAffectes(page,size);
    }
    @GetMapping("/etats/{idPersonne}/{idPeriodicite}")
    public List<ObjectifAffecteEtatDto> afficherSelonPersonnelEtPeriodicite(@PathVariable("idPersonne") long idPersonne,
                                                                            @PathVariable("idPeriodicite") long idPeriodicite){
        return objectifAffecteService.afficherSelonPersonnelEtPeriodicite(idPersonne,idPeriodicite);
    }
    @PostMapping
    public ObjectifAffecteDto ajouter(@RequestBody ObjectifAffecteDto objectifAffecteDto)
    {
        return objectifAffecteService.creerObjectifAffecte(objectifAffecteDto);
    }
    @PutMapping("/{idAffectation}/{idIndicateur}/{idPeriodicite}/{idCategorie}")
    public ObjectifAffecteDto modifier(@PathVariable Long idAffectation,
                                       @PathVariable Long idIndicateur,
                                       @PathVariable Long idPeriodicite,
                                       @PathVariable Long idCategorie,
                                       @RequestBody ObjectifAffecteDto objectifAffecteDto){
        objectifAffecteDto.getIndicateur().setIdIndicateur(idIndicateur);
        objectifAffecteDto.getPeriodicite().setIdPeriodicite(idPeriodicite);
        objectifAffecteDto.getCategoriePersonne().setIdCategorie(idCategorie);
        objectifAffecteDto.getAffectation().setIdAffectation(idAffectation);
        return objectifAffecteService.modifierObjectifAffecte(objectifAffecteDto);
    }
    @DeleteMapping("/{idAffectation}/{idIndicateur}/{idPeriodicite}/{idCategorie}")
    public void Supprimer(@PathVariable Long idAffectation,
                          @PathVariable Long idIndicateur,
                          @PathVariable Long idPeriodicite,
                          @PathVariable Long idCategorie){
        CleObjectifAffecte cleObjectifAffecte=new CleObjectifAffecte();
        cleObjectifAffecte.setIdAffectation(idAffectation);
        cleObjectifAffecte.setIdCategorie(idCategorie);
        cleObjectifAffecte.setIdIndicateur(idIndicateur);
        cleObjectifAffecte.setIdPeriodicite(idPeriodicite);
        objectifAffecteService.supprimerObjectifAffecte(cleObjectifAffecte);
    }
}
