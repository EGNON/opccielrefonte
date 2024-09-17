package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TcnDto;
import com.ged.service.titresciel.TcnService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tcns")
public class TcnController {
    private final TcnService TcnService;

    public TcnController(TcnService TcnService) {
        this.TcnService = TcnService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Tcn')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return TcnService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Tcn')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return TcnService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Tcn')")
    public ResponseEntity<Object> creer(@Valid @RequestBody TcnDto TcnDto)
    {
        return TcnService.creer(TcnDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Tcn')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody TcnDto TcnDto,
            @Positive @PathVariable Long id)
    {
        TcnDto.setIdTitre(id);
        return TcnService.modifier(TcnDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Tcn')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return TcnService.supprimer(id);
    }
}
