package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.dto.opcciel.OrdreSignataireDto;
import com.ged.service.opcciel.OrdreService;
import com.ged.service.opcciel.OrdreSignataireService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ordresignataires")
public class OrdreSignataireController {
    private final OrdreSignataireService ordreSignataireService;

    public OrdreSignataireController(OrdreSignataireService ordreSignataireService) {
        this.ordreSignataireService = ordreSignataireService;
    }

    @GetMapping("/{idOrdre}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOrdre)
    {
        return ordreSignataireService.afficherTous(idOrdre);
    }
//
//    @GetMapping("/{id}")
////    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
//    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
//    {
//        return OrdreService.afficher(id);
//    }
//
//    @PostMapping("/datatable/list/{idOpcvm}")
////    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
//    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,@PathVariable Long idOpcvm) throws JsonProcessingException {
//        return OrdreService.afficherTous(idOpcvm,params);
//    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OrdreSignataireDto ordreSignataireDto)
    {
        return ordreSignataireService.creer(ordreSignataireDto);
    }
    @PostMapping("/{idOrdre}/{idPersonne}")
    public ResponseEntity<Object> ajouter(@PathVariable Long idOrdre,
                                          @PathVariable Long[]idPersonne)
    {
        return ordreSignataireService.creer(idOrdre, idPersonne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return ordreSignataireService.supprimer(id);
    }
}
