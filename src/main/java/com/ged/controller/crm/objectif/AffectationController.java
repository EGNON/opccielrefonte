package com.ged.controller.crm.objectif;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.AffectationDto;
import com.ged.service.crm.AffectationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/affectations")
public class AffectationController {
    private final AffectationService affectationService;

    public AffectationController(AffectationService affectationService) {
        this.affectationService = affectationService;
    }

    @GetMapping
    public List<AffectationDto> afficherTous()
    {
        return affectationService.afficherTous();
    }

    @GetMapping("/etats/{id}")
    public List<AffectationDto> afficherSelonPersonnel(@PathVariable("id") long id)
    {
        return affectationService.afficherSelonPersonnel(id);
    }

    @GetMapping("/{id}")
    public AffectationDto afficher(@PathVariable Long id)
    {
        return affectationService.afficher(id);
    }

    @PostMapping("/datatable/list")
    public DataTablesResponse<AffectationDto> datatableList(@RequestBody DatatableParameters params)
    {
        return affectationService.afficherTous(params);
    }

    @PostMapping(value = "",
            consumes = {"*/*"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AffectationDto creer(@RequestBody AffectationDto affectationDto)
    {
        return affectationService.creer(affectationDto);
    }

    @PutMapping("/{id}")
    public AffectationDto modifier(@RequestBody AffectationDto affectationDto, @PathVariable Long id)
    {
        affectationDto.setIdAffectation(id);
        return affectationService.modifier(affectationDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id)
    {
        affectationService.supprimer(id);
    }
}
