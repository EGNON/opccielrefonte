package com.ged.controller.opcciel;

import com.ged.dto.opcciel.OperationPaiementRachatDto2;
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
    @GetMapping("liste/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> Liste(@PathVariable Long idOpcvm,
                                                          @PathVariable Long idSeance)
    {
        return operationPaiementRachatService.liste(idOpcvm,idSeance);
    }

    @PostMapping()
    public ResponseEntity<Object> creer(@RequestBody OperationPaiementRachatDto2[] operationPaiementRachatTab)
    {
        return operationPaiementRachatService.creer(operationPaiementRachatTab);
    }


}
