package com.ged.controller.standard.parametre;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ActionnaireCommissionDto;
import com.ged.entity.standard.CleActionnaireCommission;
import com.ged.service.standard.ActionnaireCommissionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/actionnairecommissions")
public class ActionnaireCommissionController {
    private final ActionnaireCommissionService ActionnaireCommissionService;

    public ActionnaireCommissionController(ActionnaireCommissionService ActionnaireCommissionService) {
        this.ActionnaireCommissionService = ActionnaireCommissionService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return ActionnaireCommissionService.afficherTous();
    }
    @GetMapping("/{idPersonne}/{idOpcvm}/{typeCommission}")
    //@PreAuthorize("hasAuthority('ROLE_ActionnaireCommission')")
    public ResponseEntity<Object> afficher(@PathVariable Long idPersonne,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable String typeCommission) {
        CleActionnaireCommission idActionnaireCommission = new CleActionnaireCommission();
        idActionnaireCommission.setIdPersonne(idPersonne);
        idActionnaireCommission.setIdOpcvm(idOpcvm);
        idActionnaireCommission.setTypeCommission(typeCommission);
        return ActionnaireCommissionService.afficher(idActionnaireCommission);
    }
    @PostMapping("/datatable/list/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_ActionnaireCommission')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return ActionnaireCommissionService.afficherTous(params,idOpcvm);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ActionnaireCommission')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ActionnaireCommissionDto ActionnaireCommissionDto)
    {
        return ActionnaireCommissionService.creer(ActionnaireCommissionDto);
    }

    @PutMapping("/{idPersonne}/{idOpcvm}/{typeCommission}")
    //  @PreAuthorize("hasAuthority('ROLE_ActionnaireCommission')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ActionnaireCommissionDto ActionnaireCommissionDto,
            @PathVariable Long idPersonne,
            @PathVariable Long idOpcvm,
            @PathVariable String typeCommission)
    {
        ActionnaireCommissionDto.getPersonne().setIdPersonne(idPersonne);
        ActionnaireCommissionDto.getOpcvm().setIdOpcvm(idOpcvm);
        ActionnaireCommissionDto.setTypeCommission(typeCommission);
        return ActionnaireCommissionService.modifier(ActionnaireCommissionDto);
    }

    @DeleteMapping("/{idPersonne}/{idOpcvm}/{typeCommission}")
    public ResponseEntity<Object> supprimer(@PathVariable Long idPersonne,
                                            @PathVariable Long idOpcvm,
                                            @PathVariable String typeCommission)
    {
        CleActionnaireCommission idActionnaireCommission=new CleActionnaireCommission();
        idActionnaireCommission.setIdOpcvm(idOpcvm);
        idActionnaireCommission.setIdPersonne(idPersonne);
        idActionnaireCommission.setTypeCommission(typeCommission);
        return ActionnaireCommissionService.supprimer(idActionnaireCommission);
    }
}
