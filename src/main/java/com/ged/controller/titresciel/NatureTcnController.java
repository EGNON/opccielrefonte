package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.NatureTcnDto;
import com.ged.service.titresciel.NatureTcnService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/naturetcns")
public class NatureTcnController {
    private final NatureTcnService natureTcnService;

    public NatureTcnController(NatureTcnService natureTcnService) {
        this.natureTcnService = natureTcnService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_NatureTcn')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return natureTcnService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_NatureTcn')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return natureTcnService.afficherTous(params);
    }

    @GetMapping()
    public ResponseEntity<Object> afficherTous(){
        return natureTcnService.afficherTous();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_NatureTcn')")
    public ResponseEntity<Object> creer(@Valid @RequestBody NatureTcnDto NatureTcnDto)
    {
        return natureTcnService.creer(NatureTcnDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_NatureTcn')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody NatureTcnDto NatureTcnDto,
            @Positive @PathVariable Long id)
    {
        NatureTcnDto.setIdNatureTcn(id);
        return natureTcnService.modifier(NatureTcnDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_NatureTcn')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return natureTcnService.supprimer(id);
    }
}
