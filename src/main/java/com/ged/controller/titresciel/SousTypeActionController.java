package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.SousTypeActionDto;
import com.ged.service.titresciel.SousTypeActionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/soustypeactions")
public class SousTypeActionController {
    private final SousTypeActionService SousTypeActionService;

    public SousTypeActionController(SousTypeActionService SousTypeActionService) {
        this.SousTypeActionService = SousTypeActionService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SousTypeAction')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return SousTypeActionService.afficher(id);
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return SousTypeActionService.afficherTous();
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_SousTypeAction')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return SousTypeActionService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SousTypeAction')")
    public ResponseEntity<Object> creer(@Valid @RequestBody SousTypeActionDto SousTypeActionDto)
    {
        return SousTypeActionService.creer(SousTypeActionDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SousTypeAction')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody SousTypeActionDto SousTypeActionDto,
            @Positive @PathVariable Long id)
    {
        SousTypeActionDto.setIdSousTypeAction(id);
        return SousTypeActionService.modifier(SousTypeActionDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SousTypeAction')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return SousTypeActionService.supprimer(id);
    }
}
