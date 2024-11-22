package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.PlanDto;
import com.ged.service.opcciel.OperationSouscriptionRachatService;
import com.ged.service.opcciel.PlanService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationSouscriptionRachatDto operationSouscriptionRachatDto)
    {
        return operationSouscriptionRachatService.creer(operationSouscriptionRachatDto);
    }
    @PostMapping("/creer")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationSouscriptionRachatDto[] operationSouscriptionRachatDto)
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
