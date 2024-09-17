package com.ged.controller.titresciel;

import com.ged.service.titresciel.ModeCalculInteretService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/modecalculinterets")
public class ModeCalculInteretController {
    private final ModeCalculInteretService modeCalculInteretService;

    public ModeCalculInteretController(ModeCalculInteretService modeCalculInteretService) {
        this.modeCalculInteretService = modeCalculInteretService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return modeCalculInteretService.afficherTous();
    }
}
