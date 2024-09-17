package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.service.opcciel.ChargeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/charges")
public class ChargeController {
    private final ChargeService ChargeService;

    public ChargeController(ChargeService ChargeService) {

        this.ChargeService = ChargeService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return ChargeService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return ChargeService.afficherChargeSelonId(id);
    }

    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) throws JsonProcessingException {
        return ChargeService.afficherTous(params,idOpcvm);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody ChargeDto ChargeDto)
    {
        return ChargeService.creer(ChargeDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody ChargeDto ChargeDto,
                                           @PathVariable Long id)
    {
        ChargeDto.setIdCharge(id);
        return ChargeService.modifier(ChargeDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return ChargeService.supprimer(id);
    }
}
