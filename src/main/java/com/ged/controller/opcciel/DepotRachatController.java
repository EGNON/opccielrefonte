package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.ImportationDepotDto;
import com.ged.service.opcciel.DepotRachatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/depotrachats")
public class DepotRachatController {
    private final DepotRachatService depotRachatService;

    public DepotRachatController(DepotRachatService DepotRachatService) {
        this.depotRachatService = DepotRachatService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return depotRachatService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return depotRachatService.afficher(id);
    }

    @PostMapping("/datatable/list")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return depotRachatService.afficherTous(params);
    }

    @PostMapping
    public ResponseEntity<Object> ajouter(@Valid @RequestBody DepotRachatDto DepotRachatDto)
    {
        return depotRachatService.creer(DepotRachatDto);
    }

    @PostMapping("/importation/depot")
    public ResponseEntity<Object> importerDepot(@Valid @RequestBody ImportationDepotDto importationDepotDto) {
        System.out.println("Importation === " + importationDepotDto.toString());
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modifier(@Valid @RequestBody DepotRachatDto DepotRachatDto,
                                           @PathVariable Long id)
    {
        DepotRachatDto.setIdOperation(id);
        return depotRachatService.modifier(DepotRachatDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return depotRachatService.supprimer(id);
    }
}
