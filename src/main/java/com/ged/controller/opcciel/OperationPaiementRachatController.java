package com.ged.controller.opcciel;

import com.ged.dto.opcciel.OperationPaiementRachatDto2;
import com.ged.service.opcciel.OperationPaiementRachatService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationpaiementrachats")
public class OperationPaiementRachatController {
    private final OperationPaiementRachatService operationPaiementRachatService;

    public OperationPaiementRachatController(OperationPaiementRachatService operationPaiementRachatService) {

        this.operationPaiementRachatService = operationPaiementRachatService;
    }

    @GetMapping("/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> PrecalculPaiementRachat(@PathVariable Long idOpcvm,
                                                          @PathVariable Long idSeance)
    {
        return operationPaiementRachatService.precalculPAiementRachat(idOpcvm,idSeance);
    }
    @GetMapping("liste/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> Liste(@PathVariable Long idOpcvm,
                                                          @PathVariable Long idSeance)
    {
        return operationPaiementRachatService.liste(idOpcvm,idSeance);
    }
    @GetMapping("verifierpaiementrachat/{idOpcvm}/{idSeance}/{denominationOpcvm}/{dateOuv}/{dateFerm}")
    public void Liste(@PathVariable Long idOpcvm,
                                        @PathVariable Long idSeance,
                                        @PathVariable String denominationOpcvm,
                                        @PathVariable String dateOuv,
                                        @PathVariable String dateFerm,
                                        HttpServletResponse response)
    {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=vérification_Paiement_Rachat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         operationPaiementRachatService.verifierPaiementRachat(idOpcvm,idSeance,denominationOpcvm,dateOuv,dateFerm,response);
    }

    @PostMapping()
    public ResponseEntity<Object> creer(@RequestBody OperationPaiementRachatDto2[] operationPaiementRachatTab)
    {
        return operationPaiementRachatService.creer(operationPaiementRachatTab);
    }


}
