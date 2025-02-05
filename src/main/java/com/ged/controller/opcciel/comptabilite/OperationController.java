package com.ged.controller.opcciel.comptabilite;

import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.service.opcciel.OperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comptabilite/operations")
public class OperationController {
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<Object> afficherListeOperations(@RequestBody ConsultationEcritureRequest request) {
        return operationService.afficherTous(request);
    }
}
