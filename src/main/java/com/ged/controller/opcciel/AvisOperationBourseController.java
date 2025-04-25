package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.AvisOperationBourseDto;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.service.opcciel.AvisOperationBourseService;
import com.ged.service.opcciel.OrdreService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/avisoperationbourses")
public class AvisOperationBourseController {
    private final AvisOperationBourseService avisOperationBourseService;

    public AvisOperationBourseController(AvisOperationBourseService avisOperationBourseService) {
        this.avisOperationBourseService = avisOperationBourseService;
    }

//    @GetMapping("/jasperpdf/ordreDeBourse/{numeroOrdre}")
//    public ResponseEntity<Object> ordreDeBourseApercu(@PathVariable String[] numeroOrdre,
//                                                HttpServletResponse response) throws JRException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=ordreDeBourse" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        return OrdreService.jaspertReportOrdreBourse(numeroOrdre,response);
//    }
    @GetMapping("tous/{idOpcvm}/{idOrdre}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm,
                                               @PathVariable Long idOrdre)
    {
        return avisOperationBourseService.afficherTous(idOpcvm,idOrdre);
    }
//    @GetMapping("impression/{idOpcvm}/{idSeance}")
//    public ResponseEntity<Object> impressionOrdreBourse(@PathVariable Long idOpcvm,
//                                                        @PathVariable Long idSeance)
//    {
//        return OrdreService.impressionOrdreBourse(idOpcvm,idSeance);
//    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return avisOperationBourseService.afficher(id);
    }
//    @PostMapping("/calculer")
////    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
//    public ResponseEntity<Object> calculer(@RequestBody OrdreDto ordreDto)
//    {
//        return OrdreService.calculer(ordreDto);
//    }

    @PostMapping("/datatable/list/{idOpcvm}/{idOrdre}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm,
                                                @PathVariable Long idOrdre) throws JsonProcessingException {
        return avisOperationBourseService.afficherTous(idOpcvm,idOrdre,params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody AvisOperationBourseDto avisOperationBourseDto)
    {
        return avisOperationBourseService.creer(avisOperationBourseDto);
    }

    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody AvisOperationBourseDto avisOperationBourseDto)
    {
        return avisOperationBourseService.modifier(avisOperationBourseDto);
    }
//    @PutMapping("/validation/{id}/{userLogin}")
//    public ResponseEntity<Object> validation(@PathVariable Long[] id,
//                                             @PathVariable String userLogin)
//    {
//        return OrdreService.validation(id,userLogin);
//    }

    @DeleteMapping("/{idAvis}/{userLogin}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long idAvis,
                                            @PathVariable String userLogin)
    {
        return avisOperationBourseService.supprimer(idAvis,userLogin);
    }
}
