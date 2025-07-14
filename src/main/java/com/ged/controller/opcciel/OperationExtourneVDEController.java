package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationExtourneVDEDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ExtourneVDERequest;
import com.ged.dto.request.SoldeToutCompteRequest;
import com.ged.entity.opcciel.CleOperationExtourneVDE;
import com.ged.service.opcciel.OperationExtourneVDEService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationextournevdes")
public class OperationExtourneVDEController {
    private final OperationExtourneVDEService operationExtourneVDEService;

    public OperationExtourneVDEController(OperationExtourneVDEService operationExtourneVDEService) {
        this.operationExtourneVDEService = operationExtourneVDEService;
    }
    @GetMapping("/jasperpdf/vde/{idSeance}/{idOpcvm}/{estVerifie}/{estVerifie1}/{estVerifie2}/{niveau}")
    public ResponseEntity<Object> ordreDeBourseApercu(@PathVariable Long idSeance,
                                                      @PathVariable Long idOpcvm,
                                                      @PathVariable Boolean estVerifie,
                                                      @PathVariable Boolean estVerifie1,
                                                      @PathVariable Boolean estVerifie2,
                                                      @PathVariable Long niveau,
                                                      HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verificationExtourneVDE_Niveau"+niveau+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return operationExtourneVDEService.jaspertReportVDE(idSeance,idOpcvm,estVerifie,estVerifie1,estVerifie2,niveau,response);
    }
    @PostMapping("/jasperpdf/vde/soldecompteextourne")
    public ResponseEntity<Object> soldeCompteExtourne(@RequestBody SoldeToutCompteRequest soldeToutCompteRequest,
                                                      HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=soldeCompteExtourne_Niveau_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return operationExtourneVDEService.soldeCompteExtourne(soldeToutCompteRequest.getIdOpcvm(),
                soldeToutCompteRequest.getNumCompteComptable(),soldeToutCompteRequest.getDateEstimation(),response);
    }
    @GetMapping("tous/{idOpcvm}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm)
    {
        return operationExtourneVDEService.afficherTous(idOpcvm);
    }

    @GetMapping("/{idTitre}/{idOpcvm}/{idSeance}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable Long idSeance)
    {
        CleOperationExtourneVDE cleOperationExtourneVDE=new CleOperationExtourneVDE();
        cleOperationExtourneVDE.setIdOpcvm(idOpcvm);
        cleOperationExtourneVDE.setIdSeance(idSeance);
        cleOperationExtourneVDE.setIdTitre(idTitre);
        return operationExtourneVDEService.afficher(cleOperationExtourneVDE);
    }

    @GetMapping("/{idOpcvm}/{dateEstimation}/{typeEvenement}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherTitre(@PathVariable Long idOpcvm,
                                                @PathVariable LocalDateTime dateEstimation,
                                                @PathVariable String typeEvenement)
    {
        return operationExtourneVDEService.afficherTitre(idOpcvm, dateEstimation, typeEvenement);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody ConstatationChargeListeRequest params) throws JsonProcessingException {
        return operationExtourneVDEService.afficherTous(params);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationExtourneVDEDto operationExtourneVDEDto)
    {
        return operationExtourneVDEService.creer(operationExtourneVDEDto);
    }
    @PutMapping("creerverif/{niveau}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ExtourneVDERequest extourneVDERequest,
                                        @PathVariable Long niveau)
    {
        if(niveau==1)
            return operationExtourneVDEService.creerNiveau1(extourneVDERequest);
        else
            return operationExtourneVDEService.creerNiveau2(extourneVDERequest);
    }
    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationExtourneVDEDto operationExtourneVDEDto)
    {
//        operationExtourneVDEDto.setIdOperation(id);
        return operationExtourneVDEService.modifier(operationExtourneVDEDto);
    }

    @DeleteMapping("/{userLogin}/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String userLogin,
                                            @PathVariable Long id)
    {
        return operationExtourneVDEService.supprimer(userLogin,id);
    }
}
