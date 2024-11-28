package com.ged.controller.opcciel;


import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.service.opcciel.ExerciceService;
import org.apache.coyote.Response;
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
    @GetMapping("/courant/{idOpcvm}")
    public ResponseEntity<Object> exerciceEnCours(@PathVariable("idOpcvm") Long idOpcvm) {
        return exerciceService.exerciceEnCours(idOpcvm);
    }
    @PostMapping("/courant/{idOpcvm}")
    public ResponseEntity<Object> exerciceCourant(@PathVariable("idOpcvm") Long idOpcvm) {
        return exerciceService.exerciceCourant(idOpcvm);
    }
}
