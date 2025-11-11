package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.dto.request.SousRachRequest;
import com.ged.projection.OrdreProjection;
import com.ged.service.opcciel.OrdreService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ordres")
public class OrdreController {
    private final OrdreService OrdreService;

    public OrdreController(OrdreService OrdreService) {
        this.OrdreService = OrdreService;
    }
    @GetMapping("/jasperpdf/ordreDeBourse/{numeroOrdre}")
    public void ordreDeBourseApercu(@PathVariable String[] numeroOrdre,
                                                HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=ordreDeBourse" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         OrdreService.jaspertReportOrdreBourse(numeroOrdre,response);
    }
    @GetMapping("tous/{idOpcvm}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm)
    {
        return OrdreService.afficherTous(idOpcvm);
    }
    @GetMapping("impression/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> impressionOrdreBourse(@PathVariable Long idOpcvm,
                                                        @PathVariable Long idSeance)
    {
        return OrdreService.impressionOrdreBourse(idOpcvm,idSeance);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return OrdreService.afficher(id);
    }
    @PostMapping("/calculer")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> calculer(@RequestBody OrdreDto ordreDto)
    {
        return OrdreService.calculer(ordreDto);
    }

    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,@PathVariable Long idOpcvm) throws JsonProcessingException {
        return OrdreService.afficherTous(idOpcvm,params);
    }
    @PostMapping("ordreencours/datatable/list/{idOpcvm}")
    //    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ordreEncCours(@RequestBody DatatableParameters params,@PathVariable Long idOpcvm) throws JsonProcessingException {
        return OrdreService.ordreEnCours(idOpcvm,params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OrdreDto OrdreDto)
    {
        return OrdreService.creer(OrdreDto);
    }

    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OrdreDto OrdreDto)
    {
        return OrdreService.modifier(OrdreDto);
    }
    @PutMapping("/validation/{id}/{userLogin}")
    public ResponseEntity<Object> validation(@PathVariable Long[] id,
                                             @PathVariable String userLogin)
    {
        return OrdreService.validation(id,userLogin);
    }

    @DeleteMapping("/{idOrdre}/{userLogin}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long idOrdre,
                                            @PathVariable String userLogin)
    {
        return OrdreService.supprimer(idOrdre,userLogin);
    }
}
