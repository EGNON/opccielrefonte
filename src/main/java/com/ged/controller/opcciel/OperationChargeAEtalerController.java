package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dto.opcciel.OperationChargeAEtalerDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationChargeAEtalerService;
import com.ged.service.opcciel.OperationService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationchargeaetalers")
public class OperationChargeAEtalerController {
    private final OperationChargeAEtalerService operationChargeAEtalerService;
    private final OperationService operationService;

    public OperationChargeAEtalerController(OperationChargeAEtalerService operationChargeAEtalerService, OperationService operationService) {
        this.operationChargeAEtalerService = operationChargeAEtalerService;
        this.operationService = operationService;
    }

    @GetMapping("precalcul")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> precalculChargeAEtalerListe(@RequestBody DifferenceEstimationRequest params)
    {
        return operationChargeAEtalerService.precalculChargeAEtalerListe(params);
    }

    @PostMapping("/precalculchargeaetaler")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> precalculChargeAEtaler(@RequestBody DifferenceEstimationRequest params)  {
        return operationChargeAEtalerService.precalculChargeAEtaler(params);
    }
    @PostMapping("/creer")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> creer(@RequestBody List<OperationChargeAEtalerDto> params)  {
        return operationChargeAEtalerService.creer(params);
    }
    @PostMapping("/datatable-list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> chargerOperation(@RequestBody DifferenceEstimationRequest params)  {
        return operationChargeAEtalerService.chargerOperation(params);
    }
    @GetMapping("/jasperpdf/charge/{idSeance}/{idOpcvm}/{estVerifie1}/{estVerifie2}/{niveau}/{niv}")
    public ResponseEntity<Object> ordreDeBourseApercu(@PathVariable Long idSeance,
                                                      @PathVariable Long idOpcvm,
                                                      @PathVariable Boolean estVerifie1,
                                                      @PathVariable Boolean estVerifie2,
                                                      @PathVariable Long niveau,
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
            String headerValue = "attachment; filename=verificationAmortissementCharge_Niveau"+niv+"_" + currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);

            return operationChargeAEtalerService.verifier(idSeance,idOpcvm,estVerifie1,estVerifie2,niv,response);
        }

    }
    @PostMapping("/validerniveau")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> validerNiveau(@RequestBody DifferenceEstimationRequest params) throws JsonProcessingException {
        return operationChargeAEtalerService.validationNiveau(params);
    }
    @GetMapping("/jasperpdf/verifier/{idSeance}/{idOpcvm}")
    public ResponseEntity<Object> verifier(@PathVariable Long idSeance,
                                           @PathVariable Long idOpcvm,
                                           HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verification_Charge_A_Etaler" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return operationChargeAEtalerService.verifier(idSeance,idOpcvm,response);
    }

}
