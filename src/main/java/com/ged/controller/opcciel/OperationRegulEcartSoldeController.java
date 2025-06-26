package com.ged.controller.opcciel;

import com.ged.dto.opcciel.OperationRegulEcartSoldeDto;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.service.opcciel.OperationRegulEcartSoldeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/regulecartsoldes")
public class OperationRegulEcartSoldeController {

    private final OperationRegulEcartSoldeService RegulEcartSoldeService;

    @PostMapping("datatable/list")
    public ResponseEntity<?> afficherListe(@RequestBody ConstatationChargeListeRequest request) {
        return RegulEcartSoldeService.afficherTous(request);
    }

    @PostMapping()
    public ResponseEntity<?> creer(@RequestBody OperationRegulEcartSoldeDto listeRequest) {
        return RegulEcartSoldeService.creer( listeRequest);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> afficherSelonId(@PathVariable Long id) {
        return RegulEcartSoldeService.afficher(id);
    }

    @PutMapping()
    public ResponseEntity<?> modifier(@RequestBody @Valid OperationRegulEcartSoldeDto request) {
        return RegulEcartSoldeService.modifier(request);
    }
    @DeleteMapping("{id}/{userLogin}")
    public ResponseEntity<?> supprimer(@PathVariable Long id,
                                       @PathVariable String userLogin) {
        return RegulEcartSoldeService.supprimer(userLogin,id);
    }
}
