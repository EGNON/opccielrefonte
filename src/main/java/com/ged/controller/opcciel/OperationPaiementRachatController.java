package com.ged.controller.opcciel;

import com.ged.service.opcciel.OperationPaiementRachatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
