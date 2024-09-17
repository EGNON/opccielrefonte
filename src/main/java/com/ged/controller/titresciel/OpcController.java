package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.OpcDto;
import com.ged.service.titresciel.OpcService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/opcs")
public class OpcController {
    private final OpcService OpcService;

    public OpcController(OpcService OpcService) {
        this.OpcService = OpcService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Opc')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return OpcService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Opc')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return OpcService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Opc')")
    public ResponseEntity<Object> creer(@Valid @RequestBody OpcDto OpcDto)
    {
        return OpcService.creer(OpcDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Opc')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody OpcDto OpcDto,
            @Positive @PathVariable Long id)
    {
        OpcDto.setIdTitre(id);
        return OpcService.modifier(OpcDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Opc')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return OpcService.supprimer(id);
    }
}
