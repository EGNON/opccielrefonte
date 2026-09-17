package com.ged.controller.opcciel;

import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.service.opcciel.OperationConstatationChargeService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("constatation/charges")
public class OperationConstatationChargeController {

    private final OperationConstatationChargeService constatationChargeService;



    @PostMapping("datatable/list")
    public ResponseEntity<?> afficherListe(@RequestBody ConstatationChargeListeRequest request) {
        return constatationChargeService.afficherTous(request);
    }
    @GetMapping("/liste/{idOpcvm}/{idSeance}")
    public ResponseEntity<?> afficherListeOperation(@PathVariable Long idOpcvm,
                                                    @PathVariable Long idSeance) {
        return constatationChargeService.afficherConstationCharge(idOpcvm, idSeance);
    }
    @GetMapping("/print/{idOpcvm}/{idSeance}")
    public void imprimerListeOperation(@PathVariable Long idOpcvm,
                                                    @PathVariable Long idSeance, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=charges" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        constatationChargeService.imprimerConstationCharge(idOpcvm, idSeance,response);
    }

    @PostMapping
    public ResponseEntity<?> creerConstatationCharge(@RequestBody ConstatationChargeAddRequest request) {
        return constatationChargeService.creer(request);
    }

    @PutMapping("/{idOperation}")
    public ResponseEntity<?> modifierConstatationCharge(@RequestBody @Valid ConstatationChargeEditRequest request) {
        return constatationChargeService.modifier(request);
    }
}
