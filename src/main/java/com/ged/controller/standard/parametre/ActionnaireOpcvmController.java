package com.ged.controller.standard.parametre;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ActionnaireOpcvmDto;
import com.ged.entity.standard.CleActionnaireOpcvm;
import com.ged.service.standard.ActionnaireOpcvmService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/actionnaireopcvms")
public class ActionnaireOpcvmController {
    private final ActionnaireOpcvmService ActionnaireOpcvmService;

    public ActionnaireOpcvmController(ActionnaireOpcvmService ActionnaireOpcvmService) {
        this.ActionnaireOpcvmService = ActionnaireOpcvmService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return ActionnaireOpcvmService.afficherTous();
    }
    @GetMapping("/{idPersonne}/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_ActionnaireOpcvm')")
    public ResponseEntity<Object> afficher(@PathVariable Long idPersonne,
                                           @PathVariable Long idOpcvm) {
        CleActionnaireOpcvm idActionnaireOpcvm = new CleActionnaireOpcvm();
        idActionnaireOpcvm.setIdPersonne(idPersonne);
        idActionnaireOpcvm.setIdOpcvm(idOpcvm);
        return ActionnaireOpcvmService.afficher(idActionnaireOpcvm);
    }
    @PostMapping("/datatable/list/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_ActionnaireOpcvm')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return ActionnaireOpcvmService.afficherTous(params,idOpcvm);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ActionnaireOpcvm')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ActionnaireOpcvmDto ActionnaireOpcvmDto)
    {
        return ActionnaireOpcvmService.creer(ActionnaireOpcvmDto);
    }

    @PutMapping("/{idPersonne}/{idOpcvm}")
    //  @PreAuthorize("hasAuthority('ROLE_ActionnaireOpcvm')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ActionnaireOpcvmDto ActionnaireOpcvmDto,
            @PathVariable Long idPersonne,
            @PathVariable Long idOpcvm)
    {
        ActionnaireOpcvmDto.getPersonne().setIdPersonne(idPersonne);
        ActionnaireOpcvmDto.getOpcvm().setIdOpcvm(idOpcvm);
        return ActionnaireOpcvmService.modifier(ActionnaireOpcvmDto);
    }

    @DeleteMapping("/{idPersonne}/{idOpcvm}")
    public ResponseEntity<Object> supprimer(@PathVariable Long idPersonne,
                                            @PathVariable Long idOpcvm)
    {
        CleActionnaireOpcvm idActionnaireOpcvm=new CleActionnaireOpcvm();
        idActionnaireOpcvm.setIdOpcvm(idOpcvm);
        idActionnaireOpcvm.setIdPersonne(idPersonne);
        return ActionnaireOpcvmService.supprimer(idActionnaireOpcvm);
    }
}
