package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.CleOperationDifferenceEstimation;
import com.ged.service.opcciel.OperationDifferenceEstimationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationdifferenceestimations")
public class OperationDifferenceEstimationController {
    private final OperationDifferenceEstimationService operationDifferenceEstimationService;

    public OperationDifferenceEstimationController(OperationDifferenceEstimationService operationDifferenceEstimationService) {
        this.operationDifferenceEstimationService = operationDifferenceEstimationService;
    }

    @GetMapping("tous/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm,
                                               @PathVariable Long idSeance)
    {
        return operationDifferenceEstimationService.afficherTous(idOpcvm,idSeance);
    }

    @GetMapping("/{idTitre}/{idOpcvm}/{idSeance}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable Long idSeance)
    {
        CleOperationDifferenceEstimation cleOperationDifferenceEstimation=new CleOperationDifferenceEstimation();
        cleOperationDifferenceEstimation.setIdOpcvm(idOpcvm);
        cleOperationDifferenceEstimation.setIdSeance(idSeance);
        cleOperationDifferenceEstimation.setIdTitre(idTitre);
        return operationDifferenceEstimationService.afficher(cleOperationDifferenceEstimation);
    }

    @GetMapping("/{idOpcvm}/{estEnCloture}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> differenceEstimation(@PathVariable Long idOpcvm,
                                                @PathVariable Boolean estEnCloture)
    {
        return operationDifferenceEstimationService.genrerDifferenceEstimation(idOpcvm, estEnCloture);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody ConstatationChargeListeRequest params) throws JsonProcessingException {
        return operationDifferenceEstimationService.afficherTous(params);
    }
    @PostMapping("/differenceestimation")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> precalculDifferenceEstimation(@RequestBody DifferenceEstimationRequest params) throws JsonProcessingException {
        return operationDifferenceEstimationService.precalculDifferenceEstimation(params);
    }
    @PostMapping("/validerniveau")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> validerNiveau(@RequestBody DifferenceEstimationRequest params) throws JsonProcessingException {
        return operationDifferenceEstimationService.validationNiveau(params);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationDifferenceEstimationDto operationDifferenceEstimationDto)
    {
        return operationDifferenceEstimationService.creer(operationDifferenceEstimationDto);
    }
    @PostMapping("/enregistrerdifferenceestimation")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> enregistrer(@Valid @RequestBody DifferenceEstimationRequest  request)
    {
        return operationDifferenceEstimationService.enregistrerDifferenceEstimation(request);
    }
    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationDifferenceEstimationDto operationDifferenceEstimationDto)
    {
//        operationDifferenceEstimationDto.setIdOperation(id);
        return operationDifferenceEstimationService.modifier(operationDifferenceEstimationDto);
    }

    @DeleteMapping("/{userLogin}/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String userLogin,
                                            @PathVariable Long id)
    {
        return operationDifferenceEstimationService.supprimer(userLogin,id);
    }
}
