package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.ImportationDepotDto;
import com.ged.dto.opcciel.comptabilite.VerifDepSouscriptionIntRachatDto;
import com.ged.projection.FT_DepotRachatProjection;
import com.ged.projection.NbrePartProjection;
import com.ged.projection.PrecalculRachatProjection;
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

    @PostMapping("/datatable/list/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> afficherTousLesDepots(@RequestBody DatatableParameters params,
                                                        @PathVariable("idOpcvm") Long idOpcvm,
                                                        @PathVariable("idSeance") Long idSeance)
    {
        return depotRachatService.afficherTousLesDepots(params, idOpcvm, idSeance);
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
    @GetMapping("/{idOpcvm}/{idSeance}/{codeNatureOperation}/{estVerifier}/{estVerifie1}/{estVerifie2}")
    public ResponseEntity<Object> afficherTous( @PathVariable long idOpcvm,
                                                @PathVariable long idSeance,
                                                @PathVariable String codeNatureOperation,
                                                @PathVariable boolean estVerifier,
                                                @PathVariable boolean estVerifie1,
                                                @PathVariable boolean estVerifie2) throws JsonProcessingException {
        return depotRachatService.afficherTous(idOpcvm,idSeance,codeNatureOperation,estVerifier,estVerifie1,estVerifie2);
    }
    @GetMapping("depotrachat/{idOpcvm}/{niveau1}/{niveau2}")
    public List<FT_DepotRachatProjection> afficherFT_DepotRachat(
                                                           @PathVariable Long idOpcvm,
                                                           @PathVariable boolean niveau1,
                                                           @PathVariable boolean niveau2) {
        return depotRachatService.afficherFT_DepotRachat(idOpcvm,niveau1,niveau2);
    }
    @GetMapping("precalculrachat/{idSeance}/{idOpcvm}/{idPersonne}")
    public List<PrecalculRachatProjection> afficherPrecalculRachat(
                                                           @PathVariable Long idSeance,
                                                           @PathVariable Long idOpcvm,
                                                           @PathVariable Long idPersonne) {
        return depotRachatService.afficherPrecalculRachat(idSeance,idOpcvm,idPersonne);
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
    @PostMapping("/creer")
    public ResponseEntity<Object> creer(@RequestBody VerifDepSouscriptionIntRachatDto verifDepSouscriptionIntRachatDto)
    {
        return depotRachatService.creer(verifDepSouscriptionIntRachatDto);
    }
    @PostMapping("/creer/{id}/{userLogin}")
    public ResponseEntity<Object> creer(@PathVariable Long[] id,
                                        @PathVariable String userLogin)
    {
        return depotRachatService.creer(id,userLogin);
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
    @PutMapping("/{id}/{userLogin}")
    public ResponseEntity<Object> modifier(@PathVariable Long[] id,
                                           @PathVariable String userLogin)
    {
        return depotRachatService.modifier(id,userLogin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return depotRachatService.supprimer(id);
    }
}
