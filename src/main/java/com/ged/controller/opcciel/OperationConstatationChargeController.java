package com.ged.controller.opcciel;

import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstitutionChargeAddRequest;
import com.ged.service.opcciel.OperationConstatationChargeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> creerConstatationCharge(@RequestBody @Valid ConstitutionChargeAddRequest request) {
        return constatationChargeService.creer(request);
    }
}
