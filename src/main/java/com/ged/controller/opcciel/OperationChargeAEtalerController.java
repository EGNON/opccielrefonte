package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dto.opcciel.OperationChargeAEtalerDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationChargeAEtalerService;
import com.ged.service.opcciel.OperationService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
//    @GetMapping("/jasperpdf/charge/{idSeance}/{idOpcvm}/{estVerifie1}/{estVerifie2}/{niveau}/{niv}")
//    public ResponseEntity<?> ordreDeBourseApercu(@PathVariable Long idSeance,
//                                                      @PathVariable Long idOpcvm,
//                                                      @PathVariable Boolean estVerifie1,
//                                                      @PathVariable Boolean estVerifie2,
//                                                      @PathVariable Long niveau,
//                                                      @PathVariable Long niv) throws JRException, IOException {
//
//        String etapes=operationService.verifierEtape(niveau, idOpcvm);
//        if (!etapes.equals("")) {
//            return ResponseEntity
//                    .ok()
//                    .body("Les étapes suivantes n'ont pas encore été faites: " + etapes);
//        }
//
//        // Génération PDF en byte[]
//        byte[] pdfBytes = operationChargeAEtalerService.verifier(idSeance,idOpcvm,estVerifie1,estVerifie2,niv);
//
//        String fileName = "verificationDe_Niveau" + niv + "_" +
//                new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + ".pdf";
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_PDF)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
//                .body(pdfBytes);
//
////            response.setContentType("application/pdf");
////            DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
////            String currentDateTime = dateFormatter.format(new Date());
////
////            String headerKey = "Content-Disposition";
////            String headerValue = "attachment; filename=verificationAmortissementCharge_Niveau"+niv+"_" + currentDateTime + ".pdf";
////            response.setHeader(headerKey, headerValue);
//
//
//
//
//    }
@GetMapping("/jasperpdf/charge/{idSeance}/{idOpcvm}/{estVerifie1}/{estVerifie2}/{niveau}/{niv}")
public ResponseEntity<byte[]> ordreDeBourseApercu(
        @PathVariable Long idSeance,
        @PathVariable Long idOpcvm,
        @PathVariable Boolean estVerifie1,
        @PathVariable Boolean estVerifie2,
        @PathVariable Long niveau,
        @PathVariable Long niv) throws JRException, IOException {

    String etapes = operationService.verifierEtape(niveau, idOpcvm);

    if (!etapes.equals("")) {

        byte[] messageBytes = (
                "Les étapes suivantes n'ont pas encore été faites: " + etapes
        ).getBytes(StandardCharsets.UTF_8);

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(messageBytes);
    }

    byte[] pdfBytes =
            operationChargeAEtalerService.verifier(
                    idSeance, idOpcvm, estVerifie1, estVerifie2, niv);

    String fileName = "verificationDe_Niveau" + niv + "_" +
            new SimpleDateFormat("ddMMyyyy_HHmmss")
                    .format(new Date()) + ".pdf";

    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + fileName)
            .body(pdfBytes);
}
    @PostMapping("/validerniveau")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> validerNiveau(@RequestBody DifferenceEstimationRequest params) throws JsonProcessingException {
        return operationChargeAEtalerService.validationNiveau(params);
    }
    @GetMapping("/jasperpdf/verifier/{idSeance}/{idOpcvm}")
    public void verifier(@PathVariable Long idSeance,
                                           @PathVariable Long idOpcvm,
                                           HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verification_Charge_A_Etaler" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         operationChargeAEtalerService.verifier(idSeance,idOpcvm,response);
    }

}
