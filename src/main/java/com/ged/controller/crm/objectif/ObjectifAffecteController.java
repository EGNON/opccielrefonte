package com.ged.controller.crm.objectif;

import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.dto.crm.ObjectifAffecteEtatDto;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.projection.ObjectifAffecteProjection;
import com.ged.service.crm.ObjectifAffecteService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    @PostMapping("/etats/{idPersonne}")
    public List<ObjectifAffecteEtatDto> afficherSelonPersonnelEtPeriodicite(@PathVariable("idPersonne") long idPersonne,
                                                                            @RequestBody BeginEndDateParameter beginEndDateParameter){
        return objectifAffecteService.afficherSelonPersonnelEtPeriodicite(idPersonne,beginEndDateParameter);
    }
    @PostMapping("/objectifprevu/{idPersonne}")
    public void afficherObjectifPrevu(@PathVariable("idPersonne") long idPersonne,
                                                                 @RequestBody BeginEndDateParameter beginEndDateParameter,
                                                                 HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String etat="Prevu";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=objectif"+etat + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         objectifAffecteService.afficherSelonPersonnelEtPeriodiciteEtat(etat,idPersonne,beginEndDateParameter,response);
    }
    @PostMapping("/objectifreel/{idPersonne}")
    public void afficherObjectifReel(@PathVariable("idPersonne") long idPersonne,
                                                                @RequestBody BeginEndDateParameter beginEndDateParameter,
                                                                HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String etat="Fourni";
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=objectif"+etat + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         objectifAffecteService.afficherSelonPersonnelEtPeriodiciteEtat(etat,idPersonne,beginEndDateParameter,response);
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
