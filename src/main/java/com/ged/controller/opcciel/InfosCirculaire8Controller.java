package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.InfosCirculaire8Dto;
import com.ged.service.opcciel.InfosCirculaire8Service;
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
@RequestMapping("/infoscirculaire8s")
public class InfosCirculaire8Controller {
    private final InfosCirculaire8Service InfosCirculaire8Service;

    public InfosCirculaire8Controller(InfosCirculaire8Service InfosCirculaire8Service) {
        this.InfosCirculaire8Service = InfosCirculaire8Service;
    }
//    @GetMapping("/jasperpdf/InfosCirculaire8DeBourse/{numeroInfosCirculaire8}")
//    public ResponseEntity<Object> InfosCirculaire8DeBourseApercu(@PathVariable String[] numeroInfosCirculaire8,
//                                                HttpServletResponse response) throws JRException, IOException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=InfosCirculaire8DeBourse" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);
//
//        return InfosCirculaire8Service.jaspertReportInfosCirculaire8Bourse(numeroInfosCirculaire8,response);
//    }
    @GetMapping("tous/{idOpcvm}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm)
    {
        return InfosCirculaire8Service.afficherTous(idOpcvm);
    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return InfosCirculaire8Service.afficher(id);
    }
    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,@PathVariable Long idOpcvm) throws JsonProcessingException {
        return InfosCirculaire8Service.afficherTous(idOpcvm,params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody InfosCirculaire8Dto InfosCirculaire8Dto)
    {
        return InfosCirculaire8Service.creer(InfosCirculaire8Dto);
    }

    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody InfosCirculaire8Dto InfosCirculaire8Dto)
    {
        return InfosCirculaire8Service.modifier(InfosCirculaire8Dto);
    }

    @DeleteMapping("/{id}/{userLogin}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id,
                                            @PathVariable String userLogin)
    {
        return InfosCirculaire8Service.supprimer(id,userLogin);
    }
}
