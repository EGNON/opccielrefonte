package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SystemeDinformationDto;
import com.ged.service.standard.SystemeDinformationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/systemedinformations")
public class SystemeDinformationController {
    private final SystemeDinformationService systemeDinformationService;

    public SystemeDinformationController(SystemeDinformationService SystemeDinformationService) {
        this.systemeDinformationService = SystemeDinformationService;
    }

    @GetMapping("/liste")
    public List<SystemeDinformationDto> afficherListe(){
        return systemeDinformationService.afficherListe();
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return systemeDinformationService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SystemeDinformation')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return systemeDinformationService.afficherSystemeDinformation(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_SystemeDinformation')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return systemeDinformationService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SystemeDinformation')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody SystemeDinformationDto SystemeDinformationDto)
    {
        return systemeDinformationService.creerSystemeDinformation(SystemeDinformationDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SystemeDinformation')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody SystemeDinformationDto SystemeDinformationDto, @Positive @PathVariable("id") Long id)
    {
        SystemeDinformationDto.setIdSystemeDinformation(id);
        return systemeDinformationService.modifierSystemeDinformation(SystemeDinformationDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SystemeDinformation')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") Long id)
    {
        return systemeDinformationService.supprimerSystemeDinformation(id);
    }
}
