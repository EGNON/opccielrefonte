package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.dto.opcciel.PlanDto;
import com.ged.dto.request.SousRachRequest;
import com.ged.service.opcciel.OperationSouscriptionRachatService;
import com.ged.service.opcciel.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationsouscriptionrachats")
public class OperationSouscriptionRachatController {
    private final OperationSouscriptionRachatService operationSouscriptionRachatService;

    public OperationSouscriptionRachatController(OperationSouscriptionRachatService operationSouscriptionRachatService) {
        this.operationSouscriptionRachatService = operationSouscriptionRachatService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return operationSouscriptionRachatService.afficherTous();
    }
    @GetMapping("/avisoperation/{idOperation}")
    public ResponseEntity<Object> avisOperation(@PathVariable String idOperation)
    {
        return operationSouscriptionRachatService.avisOperation(idOperation);
    }
    @PostMapping("/{idOpcvm}/{codeNatureOperation}")
    public ResponseEntity<Object> listeOperationSouscriptionRachat(@PathVariable Long idOpcvm,
                                                                   @PathVariable String codeNatureOperation,
                                                                   @RequestBody BeginEndDateParameter beginEndDateParameter)
    {
        return operationSouscriptionRachatService.listeOperationSouscriptionRachat(idOpcvm,
                codeNatureOperation, beginEndDateParameter.getStartDate(),beginEndDateParameter.getEndDate());
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return operationSouscriptionRachatService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return operationSouscriptionRachatService.afficherTous(params);
    }

    @PostMapping("liste/opsousrach/datatable")
    public ResponseEntity<Object> listeOpSousRach(@RequestBody SousRachRequest sousRachRequest) {
        return operationSouscriptionRachatService.listeOpSouscriptionRachat(sousRachRequest);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationSouscriptionRachatDto operationSouscriptionRachatDto)
    {
        return operationSouscriptionRachatService.creer(operationSouscriptionRachatDto);
    }
    @PostMapping("/creer")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationSouscriptionRachatDto2[] operationSouscriptionRachatDto)
    {
        return operationSouscriptionRachatService.creer(operationSouscriptionRachatDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationSouscriptionRachatDto operationSouscriptionRachatDto,
                                           @PathVariable Long id)
    {
        operationSouscriptionRachatDto.setIdOperation(id);
        return operationSouscriptionRachatService.modifier(operationSouscriptionRachatDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return operationSouscriptionRachatService.supprimer(id);
    }
}
