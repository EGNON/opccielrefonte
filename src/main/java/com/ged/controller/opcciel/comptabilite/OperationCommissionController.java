package com.ged.controller.opcciel.comptabilite;

import com.ged.dto.request.CommissionAddRequest;
import com.ged.dto.request.CommissionListeRequest;
import com.ged.service.opcciel.OperationCommissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("commissionstaxes")
@RequiredArgsConstructor
public class OperationCommissionController {

    private final OperationCommissionService commissionService;

    @PostMapping("datatable/list")
    public ResponseEntity<?> afficherListeCommissions(@RequestBody CommissionListeRequest request) {
        return commissionService.afficherTous(request);
    }

    @PostMapping
    public ResponseEntity<?> creerPaiementCom(@RequestBody CommissionAddRequest request) {
        return commissionService.creer(request);
    }
}
