package com.ged.controller.opcciel;

import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstatationChargeAddRequest;
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
    @GetMapping("/liste/{idOpcvm}/{idSeance}")
    public ResponseEntity<?> afficherListeOperation(@PathVariable Long idOpcvm,
                                                    @PathVariable Long idSeance) {
        return constatationChargeService.afficherConstationCharge(idOpcvm, idSeance);
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
