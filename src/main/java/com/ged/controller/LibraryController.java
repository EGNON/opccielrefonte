package com.ged.controller;

import com.ged.dao.LibraryDao;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.request.*;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.service.AppService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("libraries")
public class LibraryController {
    private final LibraryDao libraryDao;
    private final AppService service;

    public LibraryController(LibraryDao libraryDao, AppService service) {
        this.libraryDao = libraryDao;
        this.service = service;
    }

    @PostMapping("/{idActionnaire}/{idOpcvm}")
    public BigDecimal solde(
            @PathVariable("idActionnaire") Long idActionnaire,
            @PathVariable("idOpcvm") Long idOpcvm) {
        return libraryDao.solde(idActionnaire, idOpcvm);
    }

    @PostMapping("/{idOpcvm}")
    public SeanceOpcvm currentSeance(@PathVariable("idOpcvm") Long id) {
        return libraryDao.currentSeance(id);
    }

    @PostMapping("/opcvm/solde/tout/compte")
    public ResponseEntity<?> soldeToutCompte(@RequestBody @Valid SoldeToutCompteRequest request) {
        return service.soldeToutCompte(request);
    }
//    @PostMapping("/opcvm/etats/portefeuille")
    @PostMapping("/opcvm/etats/portefeuille")
    public ResponseEntity<Object> porteFeuille(@RequestBody @Valid DifferenceEstimationRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=portefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherPortefeuille(request,response);
    }
    @PostMapping("/opcvm/portefeuille")
    public ResponseEntity<?> porteFeuille(@RequestBody @Valid ConstatationChargeListeRequest request) {
        return service.afficherPortefeuille(request);
    }
    @PostMapping("/opcvm/portefeuille/liste")
    public ResponseEntity<?> porteFeuilleListe(@RequestBody @Valid ConstatationChargeListeRequest request) {
        return service.afficherPortefeuilleListe(request);
    }
    @PostMapping("/opcvm/etats/relevetitrefcp")
    public ResponseEntity<Object> releveTitreFCP(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=releveTitreFCP" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherReleveTitreFCP(request,response);
    }
    @PostMapping("/opcvm/relevetitrefcp")
    public ResponseEntity<?> releveTitreFCP(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveTitreFcp(request);
    }
    @PostMapping("/opcvm/relevetitrefcp/liste")
    public ResponseEntity<?> releveTitreFCPListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveTitreFCPListe(request);
    }
    @Order(1)
    @PostMapping("/registre/actionnaire/opcvm")
    public ResponseEntity<?> registreActionnaire(@RequestBody RegistreActionnaireRequest request) {
        return service.registreActionnaire(request);
    }

    @PostMapping("/etats/registre/actionnaire/opcvm/xxxxxxx")
    public ResponseEntity<?> registreActionnaires(@RequestBody RegistreActionnaireRequest request) {
        return service.registreActionnaires(request);
    }

    @Order(2)
    @PostMapping("/cump/actionnaire/opcvm")
    public ResponseEntity<?> cumpActionnaire(@RequestBody CumpRequest request) {
        return service.cumpActionnaire(request);
    }

    @PostMapping("jasperpdf/export/registre/actionnaire/xxxxxxx/yyyy")
    public ResponseEntity<Object> registreActionnairePDF(
            HttpServletResponse response,
            @RequestBody RegistreActionPDFRequest request) {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=registre_actionnaire_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.registreActionnaireExportJasperReport(
            response,
            request
        );
    }
}
