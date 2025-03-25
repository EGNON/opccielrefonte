package com.ged.controller;

import com.ged.dao.LibraryDao;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.request.CumpRequest;
import com.ged.dto.request.RegistreActionPDFRequest;
import com.ged.dto.request.RegistreActionnaireRequest;
import com.ged.dto.request.SoldeToutCompteRequest;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.service.AppService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
