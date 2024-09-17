package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.SecteurBoursierDto;
import com.ged.service.titresciel.SecteurBoursierService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/secteurboursiers")
public class SecteurBoursierController {
    private final SecteurBoursierService SecteurBoursierService;

    public SecteurBoursierController(SecteurBoursierService SecteurBoursierService) {
        this.SecteurBoursierService = SecteurBoursierService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SecteurBoursier')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return SecteurBoursierService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_SecteurBoursier')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return SecteurBoursierService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SecteurBoursier')")
    public ResponseEntity<Object> creer(@Valid @RequestBody SecteurBoursierDto SecteurBoursierDto)
    {
        return SecteurBoursierService.creer(SecteurBoursierDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SecteurBoursier')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody SecteurBoursierDto SecteurBoursierDto,
            @Positive @PathVariable Long id)
    {
        SecteurBoursierDto.setIdSecteurBoursier(id);
        return SecteurBoursierService.modifier(SecteurBoursierDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_SecteurBoursier')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return SecteurBoursierService.supprimer(id);
    }
}
