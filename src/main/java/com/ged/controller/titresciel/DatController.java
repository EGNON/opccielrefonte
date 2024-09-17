package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DatDto;
import com.ged.service.titresciel.DatService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dats")
public class DatController {
    private final DatService DatService;

    public DatController(DatService DatService) {
        this.DatService = DatService;
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Dat')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return DatService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_Dat')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return DatService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_Dat')")
    public ResponseEntity<Object> creer(@Valid @RequestBody DatDto DatDto)
    {
        return DatService.creer(DatDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Dat')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody DatDto DatDto,
            @Positive @PathVariable Long id)
    {
        DatDto.setIdTitre(id);
        return DatService.modifier(DatDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_Dat')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long id)
    {
        return DatService.supprimer(id);
    }
}
