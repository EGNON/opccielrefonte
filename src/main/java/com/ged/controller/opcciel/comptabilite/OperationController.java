package com.ged.controller.opcciel.comptabilite;

import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.dto.request.OperationRequest;
import com.ged.dto.request.VerificationEcritureRequest;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
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
    @PostMapping("/resultat")
    public ResponseEntity<Object> afficherListeOperationsResultat(@RequestBody ConsultationEcritureRequest request) {
        return operationService.afficherOperationResultat(request);
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
    @GetMapping("/verifieretape/{niveau}/{idOpcvm}/{idSeance}/{estVerifie1}/{estVerifie2}/{niv}")
    public ResponseEntity<Object> verifierEtape(@PathVariable Long niveau,
                                                   @PathVariable Long idOpcvm,
                                                   @PathVariable Long idSeance,
                                                @PathVariable Boolean estVerifie1,
                                                @PathVariable Boolean estVerifie2,
                                                @PathVariable Long niv,
                                                HttpServletResponse response) throws JRException, IOException {
        String etapes=operationService.verifierEtape(niveau, idOpcvm);
        if(!etapes.equals(""))
        {
            return ResponseHandler.generateResponse(
                    "Ordre de bourse",
                    HttpStatus.OK,
                    "Les étapes suivantes n'ont pas encore été faites:" + etapes);
        }
        else
        {
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=verificationDe_Niveau"+niv+ "_"+ currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            return operationService.apercuVerificationDE1(idOpcvm,idSeance,estVerifie1,estVerifie2,niv,response);
        }

    }
    @GetMapping("/verifieretape/{niveau}/{idOpcvm}")
    public ResponseEntity<Object> verifierEtape(@PathVariable Long niveau,
                                                   @PathVariable Long idOpcvm)  {
        String etapes=operationService.verifierEtape(niveau, idOpcvm);
        if(!etapes.equals(""))
        {
            return ResponseHandler.generateResponse(
                    "Ordre de bourse",
                    HttpStatus.OK,
                    "Les étapes suivantes n'ont pas encore été faites:" + etapes);
        }
        else
        {
            return ResponseHandler.generateResponse(
                    "Ordre de bourse",
                    HttpStatus.OK,
                    "Vous pouvez passer à l'affichage des écritures");
        }

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
    public void ordreDeBourseApercu(@PathVariable String niveau,
                                                      @PathVariable String codeTypeOperation,
                                                      @RequestBody VerificationEcritureRequest verificationEcritureRequest,
                                                      HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verification_Niveau"+niveau+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         operationService.verificationeEcritureNiveauPrint(verificationEcritureRequest,response,niveau,codeTypeOperation);
    }
}
