package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.OrganismeDto;
import com.ged.service.titresciel.OrganismeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/organismes")
public class OrganismeController {
    private final OrganismeService OrganismeService;

    public OrganismeController(OrganismeService OrganismeService) {
        this.OrganismeService = OrganismeService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Organisme')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return OrganismeService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Organisme')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return OrganismeService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Organisme')")
    public ResponseEntity<Object> creer(@Valid @RequestBody OrganismeDto OrganismeDto)
    {
        return OrganismeService.creer(OrganismeDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Organisme')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody OrganismeDto OrganismeDto,
            @Positive @PathVariable Long id)
    {
        OrganismeDto.setIdPersonne(id);
        return OrganismeService.modifier(OrganismeDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Organisme')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return OrganismeService.supprimer(id);
    }
}
