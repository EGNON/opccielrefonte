package com.ged.controller.titresciel;

import com.ged.dto.titresciel.IndiceBrvmDto;
import com.ged.service.titresciel.IndiceBrvmService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/indicesbrvm")
public class IndiceBrvmController {
    private final IndiceBrvmService indiceBrvmService;

    public IndiceBrvmController(IndiceBrvmService indiceBrvmService) {
        this.indiceBrvmService = indiceBrvmService;
    }

    @PostMapping("/add/collection/all")
    public ResponseEntity<Object> addAll(
            @Valid @RequestBody List<IndiceBrvmDto> indiceBrvmDtos
            ) {
        return indiceBrvmService.addAll(indiceBrvmDtos);
    }
}
