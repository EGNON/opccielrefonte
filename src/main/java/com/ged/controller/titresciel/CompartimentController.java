package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SecteurDto;
import com.ged.dto.titresciel.CompartimentDto;
import com.ged.service.titresciel.CompartimentService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/compartiments")
public class CompartimentController {
    private final CompartimentService compartimentService;

    public CompartimentController(CompartimentService compartimentService) {
        this.compartimentService = compartimentService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Compartiment')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return compartimentService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Compartiment')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return compartimentService.afficherTous(params);
    }

    @GetMapping("/tous")
    public ResponseEntity<Object> afficherTous(){
        return compartimentService.afficherTous();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Compartiment')")
    public ResponseEntity<Object> creer(@Valid @RequestBody CompartimentDto compartimentDto)
    {
        return compartimentService.creer(compartimentDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Compartiment')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody CompartimentDto compartimentDto,
            @Positive @PathVariable Long id)
    {
        compartimentDto.setIdCompartiment(id);
        return compartimentService.modifier(compartimentDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Compartiment')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return compartimentService.supprimer(id);
    }
}
