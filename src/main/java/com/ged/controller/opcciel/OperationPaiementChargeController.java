package com.ged.controller.opcciel;

import com.ged.dto.opcciel.OperationPaiementChargeDto;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.entity.opcciel.OperationPaiementCharge;
import com.ged.service.opcciel.OperationConstatationChargeService;
import com.ged.service.opcciel.OperationPaiementChargeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("paiement/charges")
public class OperationPaiementChargeController {

    private final OperationPaiementChargeService paiementChargeService;

    @PostMapping("datatable/list")
    public ResponseEntity<?> afficherListe(@RequestBody ConstatationChargeListeRequest request) {
        return paiementChargeService.afficherTous(request);
    }

    @PostMapping("/{idOperation}")
    public ResponseEntity<?> creerConstatationCharge(@PathVariable Long [] idOperation,
                                                     @RequestBody ConstatationChargeListeRequest listeRequest) {
        return paiementChargeService.creer(idOperation, listeRequest);
    }

    @PutMapping("/{idOperation}")
    public ResponseEntity<?> modifierConstatationCharge(@RequestBody @Valid ConstatationChargeEditRequest request) {
        return paiementChargeService.modifier(request);
    }
}
