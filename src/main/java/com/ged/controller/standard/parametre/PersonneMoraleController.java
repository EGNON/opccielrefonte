package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.projection.PersonnePhysiqueProjection;
import com.ged.service.standard.PersonneMoraleService;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnemorales")
//@PreAuthorize("hasAuthority('ROLE_MORALE')")
public class PersonneMoraleController {
    private final PersonneMoraleService personneMoraleService;

    public PersonneMoraleController(PersonneMoraleService personneMoraleService) {
        this.personneMoraleService = personneMoraleService;
    }

    @GetMapping
    public Page<PersonneMoraleDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return personneMoraleService.afficherPersonneMorales(page,size);
    }

    @GetMapping("qualite/{qualite}")
    public List<PersonneMoraleDto> afficherSelonQualite(@PathVariable("qualite") String qualite)
    {
        return personneMoraleService.afficherSelonQualite(qualite);
    }
    @GetMapping("/qualite/etat/{qualite}")
    public List<PersonneMoraleDto> afficherSelonQualiteEtat(@PathVariable String qualite, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+qualite.toLowerCase()+"_morale" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return personneMoraleService.afficherSelonQualiteEtat(qualite,response);
    }
    @GetMapping("investi/{qualite}/{dateDebut}/{dateFin}")
    public List<PersonneMoraleDto> afficherPersonneMoraleNayantPasInvesti(@PathVariable("qualite") String qualite,
                                                                          @PathVariable("dateDebut") LocalDateTime dateDebut,
                                                                          @PathVariable("dateFin") LocalDateTime dateFin)
    {
        return personneMoraleService.afficherPersonneMoraleNayantPasInvesti(qualite,dateDebut,dateFin);
    }
    @GetMapping("investietat/{qualite}/{dateDebut}/{dateFin}")
    public List<PersonneMoraleDto> afficherPersonneMoraleNayantPasInvestiEtat(@PathVariable("qualite") String qualite,
                                                                          @PathVariable("dateDebut") LocalDateTime dateDebut,
                                                                          @PathVariable("dateFin") LocalDateTime dateFin,
                                                                          HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=client_morale_Nayant_pas_investi" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return personneMoraleService.afficherPersonneMoraleNayantPasInvestiEtat(qualite,dateDebut,dateFin,response);
    }

    @GetMapping("{id}")
    public PersonneMoraleDto afficher(@PathVariable("id") Long id)
    {
        return personneMoraleService.afficherPersonneMorale(id);
    }

    @PostMapping("/datatable-{qualite}/list")
    public DataTablesResponse<PersonneMoraleDto> datatableList(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personneMoraleService.afficherTous(qualite, params);
    }

    @PostMapping("/datatable/moralesanctionnee")
    public DataTablesResponse<PersonneMoraleDto> datatableList_Sanctionnee(
            @RequestBody DatatableParameters params)
    {
        return personneMoraleService.afficherPersonneSanctionnee(params);
    }

    @PostMapping("/datatable/juge/list-{qualite}")
    public DataTablesResponse<PersonneMoraleDto> datatableListJuge(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personneMoraleService.afficherPersonneMoraleJuge(qualite, params);
    }

    @PostMapping("/datatable/expose/list-{qualite}")
    public DataTablesResponse<PersonneMoraleDto> datatableListExpose(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personneMoraleService.afficherPersonneMoralePolitiquementExpose(qualite, params);
    }

    @PostMapping
    public PersonneMoraleDto ajouter(@RequestBody PersonneMoraleDto personneMoraleDto)
    {
        return personneMoraleService.creerPersonneMorale(personneMoraleDto);
    }

    @PutMapping("/{id}")
    public PersonneMoraleDto modifier(@PathVariable long id, @RequestBody PersonneMoraleDto personneMoraleDto){
        personneMoraleDto.setIdPersonne(id);
        return personneMoraleService.modifierPersonneMorale(personneMoraleDto);
    }

    /*@PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    public List<Object> createPMFromOppciel1() throws JsonProcessingException {
        return personneMoraleService.createPMFromOppciel1();
    }*/

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        personneMoraleService.supprimerPersonneMorale(id);
    }

    @DeleteMapping("/{idPersonne}/{idQualite}")
    public void Supprimer(@PathVariable long idPersonne,@PathVariable long idQualite){
        personneMoraleService.supprimerPersonneMorale(idPersonne,idQualite);
    }
}
