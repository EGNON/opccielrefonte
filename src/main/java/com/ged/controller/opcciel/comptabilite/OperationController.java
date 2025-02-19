package com.ged.controller.opcciel.comptabilite;

import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("details-ecriture/{idOperation}")
    public ResponseEntity<Object> afficherDetailsEcriture(@PathVariable("idOperation") Long idOperation) {
        return appService.afficherDetailsEcriture(idOperation);
    }
}
