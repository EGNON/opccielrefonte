package com.ged.controller.opcciel.comptabilite;

import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.dto.request.OperationRequest;
import com.ged.dto.request.VerificationEcritureRequest;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comptabilite/operations")
public class OperationController {
    private final OperationService operationService;
    private final AppService appService;

    public OperationController(OperationService operationService, AppService appService) {
        this.operationService = operationService;
        this.appService = appService;
    }

    @PostMapping
    public ResponseEntity<Object> afficherListeOperations(@RequestBody ConsultationEcritureRequest request) {
        return operationService.afficherTous(request);
    }
    @PostMapping("/commissioninvestissement/liste")
    public ResponseEntity<Object> afficherListeComSurInvestissement(@RequestBody ConsultationEcritureRequest request) {
        return operationService.afficherPaiementCommissionInvestissement(request);
    }

    @PostMapping("/commissioninvestissement/creer")
    public ResponseEntity<Object> creer(@RequestBody OperationRequest request) {
        return operationService.creer(request);
    }

    @PostMapping("/operationtout/creer")
    public ResponseEntity<Object> creerTout(@RequestBody OperationRequest request) {
        return operationService.creerTout(request);
    }
    @GetMapping("/actionnairebanque/{idOpcvm}/{code}")
    public ResponseEntity<Object> actionnaieBanque(@PathVariable Long idOpcvm,
                                                   @PathVariable String code) {
        return operationService.actionnaireBanque(idOpcvm, code);
    }
    @PostMapping("/verificationecriture")
    public ResponseEntity<Object> afficherListeOperations(@RequestBody VerificationEcritureRequest obj) {
        return operationService.listeVerificationEcriturePage(obj);
    }
    @PostMapping("/verificationecriture/liste")
    public ResponseEntity<Object> afficherListeVerificationEcriture(@RequestBody VerificationEcritureRequest obj) {
        return operationService.listeVerificationEcritureListe(obj);
    }
    @PostMapping("/verificationecritureniveau1/{codeTypeOperation}")
    public ResponseEntity<Object> verificationEcritureNiveau1(@RequestBody VerificationEcritureRequest obj,
                                                              @PathVariable String codeTypeOperation) {
        return operationService.verificationEcritureNiveau1(obj,codeTypeOperation);
    }
    @PostMapping("/verificationecritureniveau2/{codeTypeOperation}")
    public ResponseEntity<Object> verificationEcritureNiveau2(@RequestBody VerificationEcritureRequest obj,
                                                              @PathVariable String codeTypeOperation) {
        return operationService.verificationEcritureNiveau2(obj,codeTypeOperation);
    }
    @PutMapping("/validationecritureniveau1/{userLogin}/{codeTypeOperation}/{form}/{idOpcvm}")
    public ResponseEntity<Object> validationEcritureNiveau1(@RequestBody Long[] list,
                                                              @PathVariable String  userLogin,
                                                              @PathVariable String codeTypeOperation,
                                                            @PathVariable String form,
                                                            @PathVariable Long idOpcvm) {
        return operationService.validerVerificationEcritureN1(list,userLogin,codeTypeOperation,form,idOpcvm);
    }
    @PutMapping("/validationecritureniveau2/{userLogin}/{codeTypeOperation}/{form}/{idOpcvm}")
    public ResponseEntity<Object> validationEcritureNiveau2(@RequestBody Long[] list,
                                                              @PathVariable String  userLogin,
                                                              @PathVariable String codeTypeOperation,
                                                            @PathVariable String form,
                                                            @PathVariable Long idOpcvm) {
        return operationService.validerVerificationEcritureN2(list,userLogin,codeTypeOperation,form,idOpcvm);
    }

    @PostMapping("details-ecriture/{idOperation}")
    public ResponseEntity<Object> afficherDetailsEcriture(@PathVariable("idOperation") Long idOperation) {
        return appService.afficherDetailsEcriture(idOperation);
    }
    @PostMapping("/jasperpdf/verificationecriture/{niveau}/{codeTypeOperation}")
    public ResponseEntity<Object> ordreDeBourseApercu(@PathVariable String niveau,
                                                      @PathVariable String codeTypeOperation,
                                                      @RequestBody VerificationEcritureRequest verificationEcritureRequest,
                                                      HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verification_Niveau"+niveau+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return operationService.verificationeEcritureNiveauPrint(verificationEcritureRequest,response,niveau,codeTypeOperation);
    }
}
