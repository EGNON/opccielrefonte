package com.ged.controller.crm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.service.crm.DegreService;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.DegreDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/degres")
public class DegreController {
    private final DegreService degreService;

    public DegreController(DegreService degreService) {
        this.degreService = degreService;
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
//    public ResponseEntity<Object> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
//                                      @RequestParam(value = "size", defaultValue = "10") int size)
//    {
//        return degreService.afficherDegres(page, size);
//    }
    @GetMapping("/liste")
    public List<DegreDto> afficherListe(){
        return degreService.afficherListe();
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return degreService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return degreService.afficherDegre(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return degreService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody DegreDto degreDto)
    {
        return degreService.creerDegre(degreDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody DegreDto degreDto, @Positive @PathVariable("id") Long id)
    {
        degreDto.setIdDegre(id);
        return degreService.modifierDegre(degreDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") Long id)
    {
        return degreService.supprimerDegre(id);
    }
}
