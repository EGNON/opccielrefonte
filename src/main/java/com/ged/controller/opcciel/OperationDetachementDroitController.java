package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationDetachementDroitDto;
import com.ged.service.opcciel.OperationDetachementDroitService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationdetachementdroits")
public class OperationDetachementDroitController {
    private final OperationDetachementDroitService operationDetachementDroitService;

    public OperationDetachementDroitController(OperationDetachementDroitService operationDetachementDroitService) {
        this.operationDetachementDroitService = operationDetachementDroitService;
    }

    @GetMapping("tous/{idOpcvm}/{idTitre}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm,
                                               @PathVariable Long idTitre)
    {
        return operationDetachementDroitService.afficherTous(idOpcvm,idTitre);
    }

    @GetMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherTitre()
    {
        return operationDetachementDroitService.afficherTitre();
    }

    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) throws JsonProcessingException {
        return operationDetachementDroitService.afficherTous(params,idOpcvm);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationDetachementDroitDto operationDetachementDroitDto)
    {
        return operationDetachementDroitService.creer(operationDetachementDroitDto);
    }
    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationDetachementDroitDto operationDetachementDroitDto)
    {
//        operationDetachementDroitDto.setIdOperation(id);
        return operationDetachementDroitService.modifier(operationDetachementDroitDto);
    }

    @DeleteMapping("/{userLogin}/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String userLogin,
                                            @PathVariable Long id)
    {
        return operationDetachementDroitService.supprimer(userLogin,id);
    }
}
