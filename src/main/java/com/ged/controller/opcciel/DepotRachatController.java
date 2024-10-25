package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.ImportationDepotDto;
import com.ged.projection.NbrePartProjection;
import com.ged.service.opcciel.DepotRachatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    @GetMapping("/{idOpcvm}/{idActionnaire}")
    public List<NbrePartProjection> afficherNbrePart(@PathVariable Long idOpcvm,
                                                     @PathVariable Long idActionnaire) {
        return depotRachatService.afficherNbrePart(idOpcvm,idActionnaire);
    }
    @PostMapping("/datatable/list/{idOpcvm}/{idSeance}/{codeNatureOperation}")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable long idOpcvm,
                                                @PathVariable long idSeance,
                                                @PathVariable String codeNatureOperation) throws JsonProcessingException {
        return depotRachatService.afficherTous(params,idOpcvm,idSeance,codeNatureOperation);
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
        DepotRachatDto.setIdDepotRachat(id);
        return depotRachatService.modifier(DepotRachatDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return depotRachatService.supprimer(id);
    }
}
