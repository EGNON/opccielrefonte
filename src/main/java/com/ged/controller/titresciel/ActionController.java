package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ActionDto;
import com.ged.service.titresciel.ActionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/actions")
public class ActionController {
    private final ActionService actionService;

    public ActionController(ActionService actionService) {
        this.actionService = actionService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Action')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return actionService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Action')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return actionService.afficherTous(params);
    }

    @GetMapping()
    public ResponseEntity<Object> afficherTous(){
        return actionService.afficherTous();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Action')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ActionDto ActionDto)
    {
        return actionService.creer(ActionDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Action')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ActionDto ActionDto,
            @Positive @PathVariable Long id)
    {
        ActionDto.setIdTitre(id);
        return actionService.modifier(ActionDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Action')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return actionService.supprimer(id);
    }
}
