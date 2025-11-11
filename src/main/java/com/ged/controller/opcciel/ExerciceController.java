package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.service.opcciel.ExerciceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exercices")
public class ExerciceController {
    private final ExerciceService exerciceService;

    public ExerciceController(ExerciceService exerciceService) {
        this.exerciceService = exerciceService;
    }

    @GetMapping
    public List<ExerciceDto> afficherTous() throws SQLException {
        return exerciceService.afficherTous();
    }
    @GetMapping("/{idOpcvm}")
    public List<ExerciceDto> afficherParOpcvm(@PathVariable Long idOpcvm) throws SQLException {
        return exerciceService.afficherParOPcvm(idOpcvm);
    }
    @GetMapping("/courant/{idOpcvm}")
    public ResponseEntity<Object> exerciceEnCours(@PathVariable("idOpcvm") Long idOpcvm) {
        return exerciceService.exerciceEnCours(idOpcvm);
    }
    @GetMapping("/{idOpcvm}/{code}")
    public ResponseEntity<Object> exerciceOpcvmCode(@PathVariable Long idOpcvm,
                                                    @PathVariable String code) {
        return exerciceService.exerciceParOpcvmEtCode(idOpcvm,code);
    }
    @PostMapping("/courant/{idOpcvm}")
    public ResponseEntity<Object> exerciceCourant(@PathVariable("idOpcvm") Long idOpcvm) {
        return exerciceService.exerciceCourant(idOpcvm);
    }
    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@PathVariable Long idOpcvm,
                                                @RequestBody DatatableParameters params) throws JsonProcessingException {
        return exerciceService.afficherTous(idOpcvm,params);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody ExerciceDto exerciceDto)
    {
        return exerciceService.creerExercice(exerciceDto);
    }

    @PutMapping("{idOpcvm}/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody ExerciceDto exerciceDto,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable String code)
    {
        CleExercice cleExercice=new CleExercice();
        cleExercice.setCodeExercice(code);
        cleExercice.setIdOpcvm(idOpcvm);
        exerciceDto.setIdExercie(cleExercice);
        return exerciceService.modifierExercice(exerciceDto);
    }

    @DeleteMapping("{idOpcvm}/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long idOpcvm,
                                            @PathVariable String code)
    {
        return exerciceService.supprimerExercice(idOpcvm,code);
    }
}
