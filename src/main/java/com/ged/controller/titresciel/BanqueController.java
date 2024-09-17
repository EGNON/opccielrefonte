package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.BanqueDto;
import com.ged.service.titresciel.BanqueService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/banques")
public class BanqueController {
    private final BanqueService BanqueService;

    public BanqueController(BanqueService BanqueService) {
        this.BanqueService = BanqueService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Banque')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return BanqueService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Banque')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return BanqueService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Banque')")
    public ResponseEntity<Object> creer(@Valid @RequestBody BanqueDto BanqueDto)
    {
        return BanqueService.creer(BanqueDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Banque')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody BanqueDto BanqueDto,
            @Positive @PathVariable Long id)
    {
        BanqueDto.setIdBanque(id);
        return BanqueService.modifier(BanqueDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Banque')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return BanqueService.supprimer(id);
    }
}
